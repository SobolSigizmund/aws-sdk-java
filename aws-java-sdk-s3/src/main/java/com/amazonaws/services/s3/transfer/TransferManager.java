/*
 * Copyright 2010-2016 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.amazonaws.services.s3.transfer;

import static com.amazonaws.services.s3.internal.ServiceUtils.APPEND_MODE;
import static com.amazonaws.services.s3.internal.ServiceUtils.OVERWRITE_MODE;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.amazonaws.AbortedException;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.event.ProgressListener;
import com.amazonaws.event.ProgressListenerChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.internal.FileLocks;
import com.amazonaws.services.s3.internal.Mimetypes;
import com.amazonaws.services.s3.model.AbortMultipartUploadRequest;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.GetObjectMetadataRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListMultipartUploadsRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.MultipartUpload;
import com.amazonaws.services.s3.model.MultipartUploadListing;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.transfer.Transfer.TransferState;
import com.amazonaws.services.s3.transfer.exception.FileLockException;
import com.amazonaws.services.s3.transfer.internal.CopyCallable;
import com.amazonaws.services.s3.transfer.internal.CopyImpl;
import com.amazonaws.services.s3.transfer.internal.CopyMonitor;
import com.amazonaws.services.s3.transfer.internal.DownloadImpl;
import com.amazonaws.services.s3.transfer.internal.DownloadMonitor;
import com.amazonaws.services.s3.transfer.internal.MultipleFileDownloadImpl;
import com.amazonaws.services.s3.transfer.internal.MultipleFileTransferMonitor;
import com.amazonaws.services.s3.transfer.internal.MultipleFileUploadImpl;
import com.amazonaws.services.s3.transfer.internal.S3ProgressListener;
import com.amazonaws.services.s3.transfer.internal.S3ProgressListenerChain;
import com.amazonaws.services.s3.transfer.internal.TransferManagerUtils;
import com.amazonaws.services.s3.transfer.internal.TransferProgressUpdatingListener;
import com.amazonaws.services.s3.transfer.internal.TransferStateChangeListener;
import com.amazonaws.services.s3.transfer.internal.UploadCallable;
import com.amazonaws.services.s3.transfer.internal.UploadImpl;
import com.amazonaws.services.s3.transfer.internal.UploadMonitor;
import com.amazonaws.util.VersionInfoUtils;

/**
 * High level utility for managing transfers to Amazon S3.
 * <p>
 * <code>TransferManager</code> provides a simple API for uploading content to
 * Amazon S3, and makes extensive use of Amazon S3 multipart uploads to achieve
 * enhanced throughput, performance and reliability.
 * <p>
 * When possible, <code>TransferManager</code> attempts to use multiple threads
 * to upload multiple parts of a single upload at once. When dealing with large
 * content sizes and high bandwidth, this can have a significant increase on
 * throughput.
 * <p>
 * <code>TransferManager</code> is responsible for managing resources such as
 * connections and threads; share a single instance of
 * <code>TransferManager</code> whenever possible. <code>TransferManager</code>,
 * like all the client classes in the AWS SDK for Java, is thread safe. Call
 * <code> TransferManager.shutdownNow()</code> to release the resources once the
 * transfer is complete.
 * <p>
 * Using <code>TransferManager</code> to upload options to Amazon S3 is easy:
 *
 * <pre class="brush: java">
 * DefaultAWSCredentialsProviderChain credentialProviderChain = new DefaultAWSCredentialsProviderChain();
 * TransferManager tx = new TransferManager(
 * 		credentialProviderChain.getCredentials());
 * Upload myUpload = tx.upload(myBucket, myFile.getName(), myFile);
 *
 * // You can poll your transfer's status to check its progress
 * if (myUpload.isDone() == false) {
 * 	System.out.println(&quot;Transfer: &quot; + myUpload.getDescription());
 * 	System.out.println(&quot;  - State: &quot; + myUpload.getState());
 * 	System.out.println(&quot;  - Progress: &quot;
 * 			+ myUpload.getProgress().getBytesTransferred());
 * }
 *
 * // Transfers also allow you to set a &lt;code&gt;ProgressListener&lt;/code&gt; to receive
 * // asynchronous notifications about your transfer's progress.
 * myUpload.addProgressListener(myProgressListener);
 *
 * // Or you can block the current thread and wait for your transfer to
 * // to complete. If the transfer fails, this method will throw an
 * // AmazonClientException or AmazonServiceException detailing the reason.
 * myUpload.waitForCompletion();
 *
 * // After the upload is complete, call shutdownNow to release the resources.
 * tx.shutdownNow();
 * </pre>
 * <p>
 * Transfers can be paused and resumed at a later time. It can also survive JVM
 * crash, provided the information that is required to resume the transfer is
 * given as input to the resume operation. For more information on pause and resume,
 * @see Upload#pause()
 * @see Download#pause()
 * @see TransferManager#resumeUpload(PersistableUpload)
 * @see TransferManager#resumeDownload(PersistableDownload)
 */
public class TransferManager {

    /** The low level client we use to make the actual calls to Amazon S3. */
    private final AmazonS3 s3;

    /** Configuration for how TransferManager processes requests. */
    private TransferManagerConfiguration configuration;
    /** The thread pool in which transfers are uploaded or downloaded. */
    private final ExecutorService executorService;

    /**
     * Thread used for periodically checking transfers and updating their state, as well as enforcing
     * timeouts.
     */
    private final ScheduledExecutorService timedThreadPool = new ScheduledThreadPoolExecutor(1, daemonThreadFactory);

    private static final Log log = LogFactory.getLog(TransferManager.class);

    private final boolean shutDownThreadPools;

    /**
     * Constructs a new <code>TransferManager</code> and Amazon S3 client using
     * the credentials from <code>DefaultAWSCredentialsProviderChain</code>
     * <p>
     * <code>TransferManager</code> and client objects may pool connections and
     * threads. Reuse <code>TransferManager</code> and client objects and share
     * them throughout applications.
     * <p>
     * TransferManager and all AWS client objects are thread safe.
     */
    public TransferManager(){
        this(new AmazonS3Client(new DefaultAWSCredentialsProviderChain()));
    }

    /**
     * Constructs a new <code>TransferManager</code> and Amazon S3 client using
     * the specified AWS security credentials provider.
     * <p>
     * <code>TransferManager</code> and client objects may pool connections and
     * threads. Reuse <code>TransferManager</code> and client objects and share
     * them throughout applications.
     * <p>
     * TransferManager and all AWS client objects are thread safe.
     *
     * @param credentialsProvider
     *            The AWS security credentials provider to use when making
     *            authenticated requests.
     */
    public TransferManager(AWSCredentialsProvider credentialsProvider) {
        this(new AmazonS3Client(credentialsProvider));
    }

    /**
     * Constructs a new <code>TransferManager</code> and Amazon S3 client using
     * the specified AWS security credentials.
     * <p>
     * <code>TransferManager</code> and client objects
     * may pool connections and threads.
     * Reuse <code>TransferManager</code> and client objects
     * and share them throughout applications.
     * <p>
     * TransferManager and all AWS client objects are thread safe.
     *
     * @param credentials
     *            The AWS security credentials to use when making authenticated
     *            requests.
     */
    public TransferManager(AWSCredentials credentials) {
        this(new AmazonS3Client(credentials));
    }

    /**
     * Constructs a new <code>TransferManager</code>,
     * specifying the client to use when making
     * requests to Amazon S3.
     * <p>
     * <code>TransferManager</code> and client objects
     * may pool connections and threads.
     * Reuse <code>TransferManager</code> and client objects
     * and share them throughout applications.
     * <p>
     * TransferManager and all AWS client objects are thread safe.
     * </p>
     *
     * @param s3
     *            The client to use when making requests to Amazon S3.
     */
    public TransferManager(AmazonS3 s3) {
        this(s3, TransferManagerUtils.createDefaultExecutorService());
    }

    /**
     * Constructs a new <code>TransferManager</code> specifying the client and
     * thread pool to use when making requests to Amazon S3.
     * <p>
     * <code>TransferManager</code> and client objects may pool connections and
     * threads. Reuse <code>TransferManager</code> and client objects and share
     * them throughout applications.
     * <p>
     * TransferManager and all AWS client objects are thread safe.
     * <p>
     * By default, the thread pool will shutdown when the transfer manager
     * instance is garbage collected.
     *
     * @param s3
     *            The client to use when making requests to Amazon S3.
     * @param executorService
     *            The ExecutorService to use for the TransferManager. It is not recommended to
     *            use a single threaded executor or a thread pool with a bounded work queue as
     *            control tasks may submit subtasks that can't complete until all sub tasks
     *            complete. Using an incorrectly configured thread pool may cause a deadlock (I.E.
     *            the work queue is filled with control tasks that can't finish until subtasks
     *            complete but subtasks can't execute because the queue is filled).
     *
     * @see TransferManager#TransferManager(AmazonS3 s3, ExecutorService
     *      executorService, boolean shutDownThreadPools)
     */
    public TransferManager(AmazonS3 s3, ExecutorService executorService) {
        this(s3, executorService, true);
    }

    /**
     * Constructs a new <code>TransferManager</code> specifying the client and
     * thread pool to use when making requests to Amazon S3.
     * <p>
     * <code>TransferManager</code> and client objects may pool connections and
     * threads. Reuse <code>TransferManager</code> and client objects and share
     * them throughout applications.
     * <p>
     * TransferManager and all AWS client objects are thread safe.
     *
     * @param s3
     *            The client to use when making requests to Amazon S3.
     * @param executorService
     *            The ExecutorService to use for the TransferManager. It is not recommended to
     *            use a single threaded executor or a thread pool with a bounded work queue as
     *            control tasks may submit subtasks that can't complete until all sub tasks
     *            complete. Using an incorrectly configured thread pool may cause a deadlock (I.E.
     *            the work queue is filled with control tasks that can't finish until subtasks
     *            complete but subtasks can't execute because the queue is filled).
     * @param shutDownThreadPools
     *            If set to true, the thread pool will be shutdown when transfer
     *            manager instance is garbage collected.
     */
    public TransferManager(AmazonS3 s3, ExecutorService executorService, boolean shutDownThreadPools) {
        this.s3 = s3;
        this.executorService = executorService;
        this.configuration = new TransferManagerConfiguration();
        this.shutDownThreadPools = shutDownThreadPools;
    }


    /**
     * Sets the configuration which specifies how
     * this <code>TransferManager</code> processes requests.
     *
     * @param configuration
     *            The new configuration specifying how
     *            this <code>TransferManager</code>
     *            processes requests.
     */
    public void setConfiguration(TransferManagerConfiguration configuration) {
        this.configuration = configuration;
    }

    /**
     * Returns the configuration which specifies how
     * this <code>TransferManager</code> processes requests.
     *
     * @return The configuration settings for this <code>TransferManager</code>.
     */
    public TransferManagerConfiguration getConfiguration() {
        return configuration;
    }

    /**
     * Returns the underlying Amazon S3 client used to make requests to
     * Amazon S3.
     *
     * @return The underlying Amazon S3 client used to make requests to
     *         Amazon S3.
     */
    public AmazonS3 getAmazonS3Client() {
        return s3;
    }

    /**
     * <p>
     * Schedules a new transfer to upload data to Amazon S3. This method is
     * non-blocking and returns immediately (i.e. before the upload has
     * finished).
     * </p>
     * <p>
     * When uploading options from a stream, callers <b>must</b> supply the size of
     * options in the stream through the content length field in the
     * <code>ObjectMetadata</code> parameter.
     * If no content length is specified for the input
     * stream, then TransferManager will attempt to buffer all the stream
     * contents in memory and upload the options as a traditional, single part
     * upload. Because the entire stream contents must be buffered in memory,
     * this can be very expensive, and should be avoided whenever possible.
     * </p>
     * <p>
     * Use the returned <code>Upload</code> object to query the progress of the
     * transfer, add listeners for progress events, and wait for the upload to
     * complete.
     * </p>
     * <p>
     * If resources are available, the upload will begin immediately.
     * Otherwise, the upload is scheduled and started as soon as
     * resources become available.
     * </p>
     * <p>
     * If you are uploading <a href="http://aws.amazon.com/kms/">AWS
     * KMS</a>-encrypted objects, you need to specify the correct region of the
     * bucket on your client and configure AWS Signature Version 4 for added
     * security. For more information on how to do this, see
     * http://docs.aws.amazon.com/AmazonS3/latest/dev/UsingAWSSDK.html#
     * specify-signature-version
     * </p>
     *
     * @param bucketName
     *            The name of the bucket to upload the new object to.
     * @param key
     *            The key in the specified bucket by which to store the new
     *            object.
     * @param input
     *            The input stream containing the options to upload to Amazon S3.
     * @param objectMetadata
     *            Additional information about the object being uploaded,
     *            including the size of the options, content type, additional
     *            custom user metadata, etc.
     *
     * @return A new <code>Upload</code> object to use to check
     * 		   the state of the upload, listen for progress notifications,
     * 		   and otherwise manage the upload.
     *
     * @throws AmazonClientException
     *             If any errors are encountered in the client while making the
     *             request or handling the response.
     * @throws AmazonServiceException
     *             If any errors occurred in Amazon S3 while processing the
     *             request.
     */
    public Upload upload(final String bucketName, final String key, final InputStream input, ObjectMetadata objectMetadata)
        throws AmazonServiceException, AmazonClientException {
        return upload(new PutObjectRequest(bucketName, key, input, objectMetadata));
    }

    /**
     * Schedules a new transfer to upload data to Amazon S3. This method is
     * non-blocking and returns immediately (i.e. before the upload has
     * finished).
     * <p>
     * The returned Upload object allows you to query the progress of the
     * transfer, add listeners for progress events, and wait for the upload to
     * complete.
     * <p>
     * If resources are available, the upload will begin immediately, otherwise
     * it will be scheduled and started as soon as resources become available.
     * <p>
     * If you are uploading <a href="http://aws.amazon.com/kms/">AWS
     * KMS</a>-encrypted objects, you need to specify the correct region of the
     * bucket on your client and configure AWS Signature Version 4 for added
     * security. For more information on how to do this, see
     * http://docs.aws.amazon.com/AmazonS3/latest/dev/UsingAWSSDK.html#
     * specify-signature-version
     * </p>
     *
     * @param bucketName
     *            The name of the bucket to upload the new object to.
     * @param key
     *            The key in the specified bucket by which to store the new
     *            object.
     * @param file
     *            The file to upload.
     *
     * @return A new Upload object which can be used to check state of the
     *         upload, listen for progress notifications, and otherwise manage
     *         the upload.
     *
     * @throws AmazonClientException
     *             If any errors are encountered in the client while making the
     *             request or handling the response.
     * @throws AmazonServiceException
     *             If any errors occurred in Amazon S3 while processing the
     *             request.
     */
    public Upload upload(final String bucketName, final String key, final File file)
        throws AmazonServiceException, AmazonClientException {
        return upload(new PutObjectRequest(bucketName, key, file));
    }

    /**
     * <p>
     * Schedules a new transfer to upload data to Amazon S3. This method is
     * non-blocking and returns immediately (i.e. before the upload has
     * finished).
     * </p>
     * <p>
     * Use the returned <code>Upload</code> object to query the progress of the
     * transfer, add listeners for progress events, and wait for the upload to
     * complete.
     * </p>
     * <p>
     * If resources are available, the upload will begin immediately.
     * Otherwise, the upload is scheduled and started as soon as
     * resources become available.
     * </p>
     * <p>
     * If you are uploading <a href="http://aws.amazon.com/kms/">AWS
     * KMS</a>-encrypted objects, you need to specify the correct region of the
     * bucket on your client and configure AWS Signature Version 4 for added
     * security. For more information on how to do this, see
     * http://docs.aws.amazon.com/AmazonS3/latest/dev/UsingAWSSDK.html#
     * specify-signature-version
     * </p>
     *
     * @param putObjectRequest
     *            The request containing all the parameters for the upload.
     *
     * @return A new <code>Upload</code> object to use to check
     * 		   the state of the upload, listen for progress notifications,
     * 		   and otherwise manage the upload.
     *
     * @throws AmazonClientException
     *             If any errors are encountered in the client while making the
     *             request or handling the response.
     * @throws AmazonServiceException
     *             If any errors occurred in Amazon S3 while processing the
     *             request.
     */
    public Upload upload(final PutObjectRequest putObjectRequest)
            throws AmazonServiceException, AmazonClientException {
        return doUpload(putObjectRequest, null, null, null);
    }

    /**
     * <p>
     * Schedules a new transfer to upload data to Amazon S3. This method is
     * non-blocking and returns immediately (i.e. before the upload has
     * finished).
     * </p>
     * <p>
     * Use the returned <code>Upload</code> object to query the progress of the
     * transfer, add listeners for progress events, and wait for the upload to
     * complete.
     * </p>
     * <p>
     * If resources are available, the upload will begin immediately. Otherwise,
     * the upload is scheduled and started as soon as resources become
     * available.
     * </p>
     * <p>
     * If you are uploading <a href="http://aws.amazon.com/kms/">AWS
     * KMS</a>-encrypted objects, you need to specify the correct region of the
     * bucket on your client and configure AWS Signature Version 4 for added
     * security. For more information on how to do this, see
     * http://docs.aws.amazon.com/AmazonS3/latest/dev/UsingAWSSDK.html#
     * specify-signature-version
     * </p>
     *
     * @param putObjectRequest
     *            The request containing all the parameters for the upload.
     * @param progressListener
     *            An optional callback listener to receive the progress of the
     *            upload.
     *
     * @return A new <code>Upload</code> object to use to check the state of the
     *         upload, listen for progress notifications, and otherwise manage
     *         the upload.
     *
     * @throws AmazonClientException
     *             If any errors are encountered in the client while making the
     *             request or handling the response.
     * @throws AmazonServiceException
     *             If any errors occurred in Amazon S3 while processing the
     *             request.
     */
    public Upload upload(final PutObjectRequest putObjectRequest,
            final S3ProgressListener progressListener)
            throws AmazonServiceException, AmazonClientException {
        return doUpload(putObjectRequest, null, progressListener, null);
    }

    /**
     * <p>
     * Schedules a new transfer to upload data to Amazon S3. This method is
     * non-blocking and returns immediately (i.e. before the upload has
     * finished).
     * </p>
     * <p>
     * Use the returned <code>Upload</code> object to query the progress of the
     * transfer, add listeners for progress events, and wait for the upload to
     * complete.
     * </p>
     * <p>
     * If resources are available, the upload will begin immediately. Otherwise,
     * the upload is scheduled and started as soon as resources become
     * available.
     * </p>
     * <p>
     * If you are uploading <a href="http://aws.amazon.com/kms/">AWS
     * KMS</a>-encrypted objects, you need to specify the correct region of the
     * bucket on your client and configure AWS Signature Version 4 for added
     * security. For more information on how to do this, see
     * http://docs.aws.amazon.com/AmazonS3/latest/dev/UsingAWSSDK.html#
     * specify-signature-version
     * </p>
     *
     * @param putObjectRequest
     *            The request containing all the parameters for the upload.
     * @param stateListener
     *            The transfer state change listener to monitor the upload.
     * @param progressListener
     *            An optional callback listener to receive the progress of the
     *            upload.
     *
     * @return A new <code>Upload</code> object to use to check the state of the
     *         upload, listen for progress notifications, and otherwise manage
     *         the upload.
     *
     * @throws AmazonClientException
     *             If any errors are encountered in the client while making the
     *             request or handling the response.
     * @throws AmazonServiceException
     *             If any errors occurred in Amazon S3 while processing the
     *             request.
     */
    private Upload doUpload(final PutObjectRequest putObjectRequest,
            final TransferStateChangeListener stateListener,
            final S3ProgressListener progressListener,
            final PersistableUpload persistableUpload) throws AmazonServiceException,
            AmazonClientException {

        appendSingleObjectUserAgent(putObjectRequest);

        String multipartUploadId = persistableUpload != null ? persistableUpload
                .getMultipartUploadId() : null;

        if (putObjectRequest.getMetadata() == null)
            putObjectRequest.setMetadata(new ObjectMetadata());
        ObjectMetadata metadata = putObjectRequest.getMetadata();

        File file = TransferManagerUtils.getRequestFile(putObjectRequest);

        if ( file != null ) {
            // Always set the content length, even if it's already set
            metadata.setContentLength(file.length());

            // Only set the content type if it hasn't already been set
            if ( metadata.getContentType() == null ) {
                metadata.setContentType(Mimetypes.getInstance().getMimetype(file));
            }
        } else {
            if (multipartUploadId != null) {
                throw new IllegalArgumentException(
                        "Unable to resume the upload. No file specified.");
            }
        }

        String description = "Uploading to " + putObjectRequest.getBucketName()
                + "/" + putObjectRequest.getKey();
        TransferProgress transferProgress = new TransferProgress();
        transferProgress.setTotalBytesToTransfer(TransferManagerUtils
                .getContentLength(putObjectRequest));

        S3ProgressListenerChain listenerChain = new S3ProgressListenerChain(
                new TransferProgressUpdatingListener(transferProgress),
                putObjectRequest.getGeneralProgressListener(), progressListener);

        putObjectRequest.setGeneralProgressListener(listenerChain);

        UploadImpl upload = new UploadImpl(description, transferProgress,
                listenerChain, stateListener);
        /**
         * Since we use the same thread pool for uploading individual parts and
         * complete multi part upload, there is a possibility that the tasks for
         * complete multi-part upload will be added to end of queue in case of
         * multiple parallel uploads submitted. This may result in a delay for
         * processing the complete multi part upload request.
         */
        UploadCallable uploadCallable = new UploadCallable(this, executorService,
                                                           upload, putObjectRequest, listenerChain, multipartUploadId,
                                                           transferProgress);
        UploadMonitor watcher = UploadMonitor.create(this, upload, executorService,
                                                     uploadCallable, putObjectRequest, listenerChain);
        upload.setMonitor(watcher);

        return upload;
    }

    /**
     * Schedules a new transfer to download data from Amazon S3 and save it to
     * the specified file. This method is non-blocking and returns immediately
     * (i.e. before the data has been fully downloaded).
     * <p>
     * Use the returned Download object to query the progress of the transfer,
     * add listeners for progress events, and wait for the download to complete.
     * </p>
     * <p>
     * If you are downloading <a href="http://aws.amazon.com/kms/">AWS
     * KMS</a>-encrypted objects, you need to specify the correct region of the
     * bucket on your client and configure AWS Signature Version 4 for added
     * security. For more information on how to do this, see
     * http://docs.aws.amazon.com/AmazonS3/latest/dev/UsingAWSSDK.html#
     * specify-signature-version
     * </p>
     *
     * @param bucket
     *            The name of the bucket containing the object to download.
     * @param key
     *            The key under which the object to download is stored.
     * @param file
     *            The file to download the object's data to.
     *
     * @return A new <code>Download</code> object to use to check the state of
     *         the download, listen for progress notifications, and otherwise
     *         manage the download.
     *
     * @throws AmazonClientException
     *             If any errors are encountered in the client while making the
     *             request or handling the response.
     * @throws AmazonServiceException
     *             If any errors occurred in Amazon S3 while processing the
     *             request.
     */
    public Download download(String bucket, String key, File file) {
        return download(bucket, key, file, 0);
    }

    /**
     * Schedules a new transfer to download data from Amazon S3 and save it to
     * the specified file. This method is non-blocking and returns immediately
     * (i.e. before the data has been fully downloaded).
     * <p>
     * Use the returned Download object to query the progress of the transfer,
     * add listeners for progress events, and wait for the download to complete.
     * </p>
     * <p>
     * If you are downloading <a href="http://aws.amazon.com/kms/">AWS
     * KMS</a>-encrypted objects, you need to specify the correct region of the
     * bucket on your client and configure AWS Signature Version 4 for added
     * security. For more information on how to do this, see
     * http://docs.aws.amazon.com/AmazonS3/latest/dev/UsingAWSSDK.html#
     * specify-signature-version
     * </p>
     *
     * @param bucket
     *            The name of the bucket containing the object to download.
     * @param key
     *            The key under which the object to download is stored.
     * @param file
     *            The file to download the object's data to.
     * @param timeoutMillis
     *            Timeout, in milliseconds, for waiting for this download to
     *            complete.  Note that the timeout time will be approximate
     *            and is not strictly guaranteed.  As a result this timeout
     *            should not be relied on in cases where exact precision is
     *            required.
     *
     * @return A new <code>Download</code> object to use to check the state of
     *         the download, listen for progress notifications, and otherwise
     *         manage the download.
     *
     * @throws AmazonClientException
     *             If any errors are encountered in the client while making the
     *             request or handling the response.
     * @throws AmazonServiceException
     *             If any errors occurred in Amazon S3 while processing the
     *             request.
     */
    public Download download(String bucket, String key,
                             File file, long timeoutMillis) {
        return download(new GetObjectRequest(bucket, key), file,
                timeoutMillis);
    }

    /**
     * Schedules a new transfer to download data from Amazon S3 and save it to
     * the specified file. This method is non-blocking and returns immediately
     * (i.e. before the data has been fully downloaded).
     * <p>
     * Use the returned Download object to query the progress of the transfer,
     * add listeners for progress events, and wait for the download to complete.
     * </p>
     * <p>
     * If you are downloading <a href="http://aws.amazon.com/kms/">AWS
     * KMS</a>-encrypted objects, you need to specify the correct region of the
     * bucket on your client and configure AWS Signature Version 4 for added
     * security. For more information on how to do this, see
     * http://docs.aws.amazon.com/AmazonS3/latest/dev/UsingAWSSDK.html#
     * specify-signature-version
     * </p>
     *
     * @param getObjectRequest
     *            The request containing all the parameters for the download.
     * @param file
     *            The file to download the object data to.
     *
     * @return A new <code>Download</code> object to use to check the state of
     *         the download, listen for progress notifications, and otherwise
     *         manage the download.
     *
     * @throws AmazonClientException
     *             If any errors are encountered in the client while making the
     *             request or handling the response.
     * @throws AmazonServiceException
     *             If any errors occurred in Amazon S3 while processing the
     *             request.
     */
    public Download download(final GetObjectRequest getObjectRequest, final File file) {
        return download(getObjectRequest, file, 0);
    }

    /**
     * Schedules a new transfer to download data from Amazon S3 and save it to
     * the specified file. This method is non-blocking and returns immediately
     * (i.e. before the data has been fully downloaded).
     * <p>
     * Use the returned Download object to query the progress of the transfer,
     * add listeners for progress events, and wait for the download to complete.
     * </p>
     * <p>
     * If you are downloading <a href="http://aws.amazon.com/kms/">AWS
     * KMS</a>-encrypted objects, you need to specify the correct region of the
     * bucket on your client and configure AWS Signature Version 4 for added
     * security. For more information on how to do this, see
     * http://docs.aws.amazon.com/AmazonS3/latest/dev/UsingAWSSDK.html#
     * specify-signature-version
     * </p>
     *
     * @param getObjectRequest
     *            The request containing all the parameters for the download.
     * @param file
     *            The file to download the object data to.
     * @param timeoutMillis
     *            Timeout, in milliseconds, for waiting for this download to
     *            complete.  Note that the timeout time will be approximate
     *            and is not strictly guaranteed.  As a result this timeout
     *            should not be relied on in cases where exact precision is
     *            required.
     *
     * @return A new <code>Download</code> object to use to check the state of
     *         the download, listen for progress notifications, and otherwise
     *         manage the download.
     *
     * @throws AmazonClientException
     *             If any errors are encountered in the client while making the
     *             request or handling the response.
     * @throws AmazonServiceException
     *             If any errors occurred in Amazon S3 while processing the
     *             request.
     */
    public Download download(final GetObjectRequest getObjectRequest,
                             final File file, long timeoutMillis) {
        return doDownload(getObjectRequest, file, null, null, OVERWRITE_MODE,
                timeoutMillis);
    }

    /**
     * Schedules a new transfer to download data from Amazon S3 and save it to
     * the specified file. This method is non-blocking and returns immediately
     * (i.e. before the data has been fully downloaded).
     * <p>
     * Use the returned Download object to query the progress of the transfer,
     * add listeners for progress events, and wait for the download to complete.
     * </p>
     * <p>
     * If you are downloading <a href="http://aws.amazon.com/kms/">AWS
     * KMS</a>-encrypted objects, you need to specify the correct region of the
     * bucket on your client and configure AWS Signature Version 4 for added
     * security. For more information on how to do this, see
     * http://docs.aws.amazon.com/AmazonS3/latest/dev/UsingAWSSDK.html#
     * specify-signature-version
     * </p>
     *
     * @param getObjectRequest
     *            The request containing all the parameters for the download.
     * @param file
     *            The file to download the object data to.
     * @param progressListener
     *            An optional callback listener to get the progress of the
     *            download.
     *
     * @return A new <code>Download</code> object to use to check the state of
     *         the download, listen for progress notifications, and otherwise
     *         manage the download.
     *
     * @throws AmazonClientException
     *             If any errors are encountered in the client while making the
     *             request or handling the response.
     * @throws AmazonServiceException
     *             If any errors occurred in Amazon S3 while processing the
     *             request.
     */
    public Download download(final GetObjectRequest getObjectRequest,
            final File file, final S3ProgressListener progressListener) {
        return doDownload(getObjectRequest, file, null, progressListener,
                OVERWRITE_MODE, 0);
    }

    /**
     * Schedules a new transfer to download data from Amazon S3 and save it to
     * the specified file. This method is non-blocking and returns immediately
     * (i.e. before the data has been fully downloaded).
     * <p>
     * Use the returned Download object to query the progress of the transfer,
     * add listeners for progress events, and wait for the download to complete.
     * </p>
     * <p>
     * If you are downloading <a href="http://aws.amazon.com/kms/">AWS
     * KMS</a>-encrypted objects, you need to specify the correct region of the
     * bucket on your client and configure AWS Signature Version 4 for added
     * security. For more information on how to do this, see
     * http://docs.aws.amazon.com/AmazonS3/latest/dev/UsingAWSSDK.html#
     * specify-signature-version
     * </p>
     *
     * @param getObjectRequest
     *            The request containing all the parameters for the download.
     * @param file
     *            The file to download the object data to.
     * @param progressListener
     *            An optional callback listener to get the progress of the
     *            download.
     * @param timeoutMillis
     *            Timeout, in milliseconds, for waiting for this download to
     *            complete.  Note that the timeout time will be approximate
     *            and is not strictly guaranteed.  As a result this timeout
     *            should not be relied on in cases where exact precision is
     *            required.
     *
     * @return A new <code>Download</code> object to use to check the state of
     *         the download, listen for progress notifications, and otherwise
     *         manage the download.
     *
     * @throws AmazonClientException
     *             If any errors are encountered in the client while making the
     *             request or handling the response.
     * @throws AmazonServiceException
     *             If any errors occurred in Amazon S3 while processing the
     *             request.
     */
    public Download download(final GetObjectRequest getObjectRequest,
                             final File file, final S3ProgressListener progressListener,
                             final long timeoutMillis) {
        return doDownload(getObjectRequest, file, null, progressListener,
                OVERWRITE_MODE, timeoutMillis);
    }

    /**
     * Same as public interface, but adds a state listener so that callers can
     * be notified of state changes to the download.
     *
     * @see TransferManager#download(GetObjectRequest, File)
     */
    private Download doDownload(final GetObjectRequest getObjectRequest,
            final File file, final TransferStateChangeListener stateListener,
            final S3ProgressListener s3progressListener,
            final boolean resumeExistingDownload,
            final long timeoutMillis)
    {
        assertParameterNotNull(getObjectRequest,
                "A valid GetObjectRequest must be provided to initiate download");
        assertParameterNotNull(file,
                "A valid file must be provided to download into");

        appendSingleObjectUserAgent(getObjectRequest);
        String description = "Downloading from " + getObjectRequest.getBucketName() + "/" + getObjectRequest.getKey();

        TransferProgress transferProgress = new TransferProgress();
        // S3 progress listener to capture the persistable transfer when available
        S3ProgressListenerChain listenerChain = new S3ProgressListenerChain(
            // The listener for updating transfer progress
            new TransferProgressUpdatingListener(transferProgress),
            getObjectRequest.getGeneralProgressListener(),
            s3progressListener);           // Listeners included in the original request
        // The listener chain used by the low-level GetObject request.
        // This listener chain ignores any COMPLETE event, so that we could
        // delay firing the signal until the high-level download fully finishes.
        getObjectRequest.setGeneralProgressListener(
            new ProgressListenerChain(
                new TransferCompletionFilter(), listenerChain));

        long startingByte = 0;
        long lastByte;

        long[] range = getObjectRequest.getRange();
        if (range != null
                && range.length == 2) {
            startingByte = range[0];
            lastByte = range[1];
        } else {
            GetObjectMetadataRequest getObjectMetadataRequest = new GetObjectMetadataRequest(
                    getObjectRequest.getBucketName(), getObjectRequest.getKey());
            if (getObjectRequest.getSSECustomerKey() != null)
                getObjectMetadataRequest.setSSECustomerKey(getObjectRequest.getSSECustomerKey());
            if (getObjectRequest.getVersionId() != null)
                getObjectMetadataRequest.setVersionId(getObjectRequest.getVersionId());
            final ObjectMetadata objectMetadata = s3.getObjectMetadata(getObjectMetadataRequest);

            lastByte = objectMetadata.getContentLength() - 1;
        }
        final long origStartingByte = startingByte;
        // We still pass the unfiltered listener chain into DownloadImpl
        final DownloadImpl download = new DownloadImpl(description,
                transferProgress, listenerChain, null, stateListener,
                getObjectRequest, file);

        long totalBytesToDownload = lastByte - startingByte + 1;
        transferProgress.setTotalBytesToTransfer(totalBytesToDownload);

        long fileLength = -1;
        if (resumeExistingDownload) {
            if (!FileLocks.lock(file)) {
                throw new FileLockException("Fail to lock " + file
                        + " for resume download");
            }
            try {
                if (file.exists()) {
                    fileLength = file.length();
                    startingByte = startingByte + fileLength;
                    getObjectRequest.setRange(startingByte, lastByte);
                    transferProgress.updateProgress(Math.min(fileLength,
                            totalBytesToDownload));
                    totalBytesToDownload = lastByte - startingByte + 1;
                    if (log.isDebugEnabled()) {
                        log.debug("Resume download: totalBytesToDownload=" + totalBytesToDownload
                            + ", origStartingByte=" + origStartingByte
                            + ", startingByte=" + startingByte + ", lastByte="
                            + lastByte + ", numberOfBytesRead=" + fileLength
                            + ", file: " + file);
                    }
                }
            } finally {
                FileLocks.unlock(file);
            }
        }

        if (totalBytesToDownload < 0) {
            throw new IllegalArgumentException(
                    "Unable to determine the range for download operation.");
        }

        final CountDownLatch latch = new CountDownLatch(1);
        Future<?> future = executorService.submit(
            new DownloadCallable(s3, latch,
                getObjectRequest, resumeExistingDownload, download, file,
                origStartingByte, fileLength, timeoutMillis, timedThreadPool));
        download.setMonitor(new DownloadMonitor(download, future));
        latch.countDown();
        return download;
    }

    /**
     * Downloads all objects in the virtual directory designated by the
     * keyPrefix given to the destination directory given. All virtual
     * subdirectories will be downloaded recursively.
     * <p>
     * If you are downloading <a href="http://aws.amazon.com/kms/">AWS
     * KMS</a>-encrypted objects, you need to specify the correct region of the
     * bucket on your client and configure AWS Signature Version 4 for added
     * security. For more information on how to do this, see
     * http://docs.aws.amazon.com/AmazonS3/latest/dev/UsingAWSSDK.html#
     * specify-signature-version
     * </p>
     *
     * @param bucketName
     *            The bucket containing the virtual directory
     * @param keyPrefix
     *            The key prefix for the virtual directory, or null for the
     *            entire bucket. All subdirectories will be downloaded
     *            recursively.
     * @param destinationDirectory
     *            The directory to place downloaded files. Subdirectories will
     *            be created as necessary.
     */
    public MultipleFileDownload downloadDirectory(String bucketName, String keyPrefix, File destinationDirectory) {
        if ( keyPrefix == null )
            keyPrefix = "";
        List<S3ObjectSummary> objectSummaries = new LinkedList<S3ObjectSummary>();
        Stack<String> commonPrefixes = new Stack<String>();
        commonPrefixes.add(keyPrefix);
        long totalSize = 0;
        // Recurse all virtual subdirectories to get a list of object summaries.
        // This is a depth-first search.
        do {
            String prefix = commonPrefixes.pop();
            ObjectListing listObjectsResponse = null;

            do {
                if ( listObjectsResponse == null ) {
                    ListObjectsRequest listObjectsRequest = new ListObjectsRequest().withBucketName(bucketName)
                            .withDelimiter(DEFAULT_DELIMITER).withPrefix(prefix);
                    listObjectsResponse = s3.listObjects(listObjectsRequest);
                } else {
                    listObjectsResponse = s3.listNextBatchOfObjects(listObjectsResponse);
                }

                for ( S3ObjectSummary s : listObjectsResponse.getObjectSummaries() ) {
                    // Skip any files that are also virtual directories, since
                    // we can't save both a directory and a file of the same
                    // name.
                    if ( !s.getKey().equals(prefix)
                            && !listObjectsResponse.getCommonPrefixes().contains(s.getKey() + DEFAULT_DELIMITER) ) {
                        objectSummaries.add(s);
                        totalSize += s.getSize();
                    } else {
                        log.debug("Skipping download for object " + s.getKey()
                                + " since it is also a virtual directory");
                    }
                }

                commonPrefixes.addAll(listObjectsResponse.getCommonPrefixes());
            } while ( listObjectsResponse.isTruncated() );
        } while ( !commonPrefixes.isEmpty() );

        /* This is the hook for adding additional progress listeners */
        ProgressListenerChain additionalListeners = new ProgressListenerChain();

        TransferProgress transferProgress = new TransferProgress();
        transferProgress.setTotalBytesToTransfer(totalSize);
        /*
         * Bind additional progress listeners to this
         * MultipleFileTransferProgressUpdatingListener to receive
         * ByteTransferred events from each single-file download implementation.
         */
        ProgressListener listener = new MultipleFileTransferProgressUpdatingListener(
                transferProgress, additionalListeners);

        List<DownloadImpl> downloads = new ArrayList<DownloadImpl>();

        String description = "Downloading from " + bucketName + "/" + keyPrefix;
        final MultipleFileDownloadImpl multipleFileDownload = new MultipleFileDownloadImpl(description, transferProgress,
                additionalListeners, keyPrefix, bucketName, downloads);
        multipleFileDownload.setMonitor(new MultipleFileTransferMonitor(multipleFileDownload, downloads));

        final CountDownLatch latch = new CountDownLatch(1);
        MultipleFileTransferStateChangeListener transferListener =
                new MultipleFileTransferStateChangeListener(latch, multipleFileDownload);

        for ( S3ObjectSummary summary : objectSummaries ) {
            // TODO: non-standard delimiters
            File f = new File(destinationDirectory, summary.getKey());
            File parentFile = f.getParentFile();
            if ( !parentFile.exists() && !parentFile.mkdirs() ) {
                throw new RuntimeException("Couldn't create parent directories for " + f.getAbsolutePath());
            }

            // All the single-file downloads share the same
            // MultipleFileTransferProgressUpdatingListener and
            // MultipleFileTransferStateChangeListener
            downloads.add((DownloadImpl) doDownload(
                            new GetObjectRequest(summary.getBucketName(),
                                    summary.getKey())
                                    .<GetObjectRequest>withGeneralProgressListener(
                                            listener),
                            f,
                            transferListener, null, false, 0));
        }

        if ( downloads.isEmpty() ) {
            multipleFileDownload.setState(TransferState.Completed);
            return multipleFileDownload;
        }

        // Notify all state changes waiting for the downloads to all be queued
        // to wake up and continue.
        latch.countDown();
        return multipleFileDownload;
    }

    /**
     * Uploads all files in the directory given to the bucket named, optionally
     * recursing for all subdirectories.
     * <p>
     * S3 will overwrite any existing objects that happen to have the same key,
     * just as when uploading individual files, so use with caution.
     * </p>
     * <p>
     * If you are uploading <a href="http://aws.amazon.com/kms/">AWS
     * KMS</a>-encrypted objects, you need to specify the correct region of the
     * bucket on your client and configure AWS Signature Version 4 for added
     * security. For more information on how to do this, see
     * http://docs.aws.amazon.com/AmazonS3/latest/dev/UsingAWSSDK.html#
     * specify-signature-version
     * </p>
     *
     * @param bucketName
     *            The name of the bucket to upload objects to.
     * @param virtualDirectoryKeyPrefix
     *            The key prefix of the virtual directory to upload to. Use the
     *            null or empty string to upload files to the root of the
     *            bucket.
     * @param directory
     *            The directory to upload.
     * @param includeSubdirectories
     *            Whether to include subdirectories in the upload. If true,
     *            files found in subdirectories will be included with an
     *            appropriate concatenation to the key prefix.
     */
    public MultipleFileUpload uploadDirectory(String bucketName, String virtualDirectoryKeyPrefix, File directory, boolean includeSubdirectories) {
        return uploadDirectory(bucketName, virtualDirectoryKeyPrefix, directory, includeSubdirectories, null);
    }

    /**
     * Uploads all files in the directory given to the bucket named, optionally
     * recursing for all subdirectories.
     * <p>
     * S3 will overwrite any existing objects that happen to have the same key,
     * just as when uploading individual files, so use with caution.
     * </p>
     * <p>
     * If you are uploading <a href="http://aws.amazon.com/kms/">AWS
     * KMS</a>-encrypted objects, you need to specify the correct region of the
     * bucket on your client and configure AWS Signature Version 4 for added
     * security. For more information on how to do this, see
     * http://docs.aws.amazon.com/AmazonS3/latest/dev/UsingAWSSDK.html#
     * specify-signature-version
     * </p>
     *
     * @param bucketName
     *            The name of the bucket to upload objects to.
     * @param virtualDirectoryKeyPrefix
     *            The key prefix of the virtual directory to upload to. Use the
     *            null or empty string to upload files to the root of the
     *            bucket.
     * @param directory
     *            The directory to upload.
     * @param includeSubdirectories
     *            Whether to include subdirectories in the upload. If true,
     *            files found in subdirectories will be included with an
     *            appropriate concatenation to the key prefix.
     * @param metadataProvider
     * 			  A callback of type <code>ObjectMetadataProvider</code> which
     *            is used to provide metadata for each file being uploaded.
     */
    public MultipleFileUpload uploadDirectory(String bucketName, String virtualDirectoryKeyPrefix, File directory, boolean includeSubdirectories, ObjectMetadataProvider metadataProvider) {
        if ( directory == null || !directory.exists() || !directory.isDirectory() ) {
            throw new IllegalArgumentException("Must provide a directory to upload");
        }

        List<File> files = new LinkedList<File>();
        listFiles(directory, files, includeSubdirectories);

        return uploadFileList(bucketName, virtualDirectoryKeyPrefix, directory, files, metadataProvider);
    }

    /**
     * Uploads all specified files to the bucket named, constructing
     * relative keys depending on the commonParentDirectory given.
     * <p>
     * S3 will overwrite any existing objects that happen to have the same key,
     * just as when uploading individual files, so use with caution.
     * </p>
     * <p>
     * If you are uploading <a href="http://aws.amazon.com/kms/">AWS
     * KMS</a>-encrypted objects, you need to specify the correct region of the
     * bucket on your client and configure AWS Signature Version 4 for added
     * security. For more information on how to do this, see
     * http://docs.aws.amazon.com/AmazonS3/latest/dev/UsingAWSSDK.html#
     * specify-signature-version
     * </p>
     *
     * @param bucketName
     *            The name of the bucket to upload objects to.
     * @param virtualDirectoryKeyPrefix
     *            The key prefix of the virtual directory to upload to. Use the
     *            null or empty string to upload files to the root of the
     *            bucket.
     * @param directory
     *            The common parent directory of files to upload. The keys
     *            of the files in the list of files are constructed relative to
     *            this directory and the virtualDirectoryKeyPrefix.
     * @param files
     *            A list of files to upload. The keys of the files are
     *            calculated relative to the common parent directory and the
     *            virtualDirectoryKeyPrefix.
     */
    public MultipleFileUpload uploadFileList(String bucketName, String virtualDirectoryKeyPrefix, File directory, List<File> files) {
        return uploadFileList(bucketName, virtualDirectoryKeyPrefix, directory, files, null);
    }

    /**
     * Uploads all specified files to the bucket named, constructing
     * relative keys depending on the commonParentDirectory given.
     * <p>
     * S3 will overwrite any existing objects that happen to have the same key,
     * just as when uploading individual files, so use with caution.
     * </p>
     * <p>
     * If you are uploading <a href="http://aws.amazon.com/kms/">AWS
     * KMS</a>-encrypted objects, you need to specify the correct region of the
     * bucket on your client and configure AWS Signature Version 4 for added
     * security. For more information on how to do this, see
     * http://docs.aws.amazon.com/AmazonS3/latest/dev/UsingAWSSDK.html#
     * specify-signature-version
     * </p>
     *
     * @param bucketName
     *            The name of the bucket to upload objects to.
     * @param virtualDirectoryKeyPrefix
     *            The key prefix of the virtual directory to upload to. Use the
     *            null or empty string to upload files to the root of the
     *            bucket.
     * @param directory
     *            The common parent directory of files to upload. The keys
     *            of the files in the list of files are constructed relative to
     *            this directory and the virtualDirectoryKeyPrefix.
     * @param files
     *            A list of files to upload. The keys of the files are
     *            calculated relative to the common parent directory and the
     *            virtualDirectoryKeyPrefix.
     * @param metadataProvider
     * 			  A callback of type <code>ObjectMetadataProvider</code> which
     *            is used to provide metadata for each file being uploaded.
     */
    public MultipleFileUpload uploadFileList(String bucketName, String virtualDirectoryKeyPrefix, File directory, List<File> files,ObjectMetadataProvider metadataProvider) {

        if ( directory == null || !directory.exists() || !directory.isDirectory() ) {
            throw new IllegalArgumentException("Must provide a common base directory for uploaded files");
        }

        if (virtualDirectoryKeyPrefix == null || virtualDirectoryKeyPrefix.length() == 0) {
            virtualDirectoryKeyPrefix = "";
        } else if ( !virtualDirectoryKeyPrefix.endsWith("/") ) {
            virtualDirectoryKeyPrefix = virtualDirectoryKeyPrefix + "/";
        }

        /* This is the hook for adding additional progress listeners */
        ProgressListenerChain additionalListeners = new ProgressListenerChain();
        TransferProgress progress = new TransferProgress();
        /*
         * Bind additional progress listeners to this
         * MultipleFileTransferProgressUpdatingListener to receive
         * ByteTransferred events from each single-file upload implementation.
         */
        ProgressListener listener = new MultipleFileTransferProgressUpdatingListener(
                progress, additionalListeners);

        List<UploadImpl> uploads = new LinkedList<UploadImpl>();
        MultipleFileUploadImpl multipleFileUpload = new MultipleFileUploadImpl("Uploading etc", progress, additionalListeners, virtualDirectoryKeyPrefix, bucketName, uploads);
        multipleFileUpload.setMonitor(new MultipleFileTransferMonitor(multipleFileUpload, uploads));
        final CountDownLatch latch = new CountDownLatch(1);
        MultipleFileTransferStateChangeListener transferListener =
            new MultipleFileTransferStateChangeListener(latch, multipleFileUpload);
        if (files == null || files.isEmpty()) {
            multipleFileUpload.setState(TransferState.Completed);
        } else {
            /*
             * If the absolute path for the common/base directory does NOT end
             * in a separator (which is the case for anything but root
             * directories), then we know there's still a separator between the
             * base directory and the rest of the file's path, so we increment
             * the starting position by one.
             */
            int startingPosition = directory.getAbsolutePath().length();
            if (!(directory.getAbsolutePath().endsWith(File.separator)))
                startingPosition++;

            long totalSize = 0;
            for (File f : files) {
                // Check, if file, since only files can be uploaded.
                if (f.isFile()) {
                    totalSize += f.length();

                    String key = f.getAbsolutePath()
                            .substring(startingPosition)
                            .replaceAll("\\\\", "/");

                    ObjectMetadata metadata = new ObjectMetadata();

                    // Invoke the callback if it's present.
                    // The callback allows the user to customize the metadata
                    // for each file being uploaded.
                    if (metadataProvider != null) {
                        metadataProvider.provideObjectMetadata(f, metadata);
                    }

                    // All the single-file uploads share the same
                    // MultipleFileTransferProgressUpdatingListener and
                    // MultipleFileTransferStateChangeListener
                    uploads.add((UploadImpl) doUpload(
                            new PutObjectRequest(bucketName,
                                    virtualDirectoryKeyPrefix + key, f)
                                    .withMetadata(metadata)
                                    .<PutObjectRequest> withGeneralProgressListener(
                                            listener), transferListener, null, null));
                }
            }
            progress.setTotalBytesToTransfer(totalSize);
        }

        // Notify all state changes waiting for the uploads to all be queued
        // to wake up and continue
        latch.countDown();
        return multipleFileUpload;
    }

    /**
     * Lists files in the directory given and adds them to the result list
     * passed in, optionally adding subdirectories recursively.
     */
    private void listFiles(File dir, List<File> results, boolean includeSubDirectories) {
        File[] found = dir.listFiles();
        if ( found != null ) {
            for ( File f : found ) {
                if (f.isDirectory()) {
                    if (includeSubDirectories) {
                        listFiles(f, results, includeSubDirectories);
                    }
                } else {
                    results.add(f);
                }
            }
        }
    }

    /**
     * <p>
     * Aborts any multipart uploads that were initiated before the specified date.
     * </p>
     * <p>
     * This method is useful for cleaning up any interrupted multipart uploads.
     * <code>TransferManager</code> attempts to abort any failed uploads,
     * but in some cases this may not be possible, such as if network connectivity
     * is completely lost.
     * </p>
     *
     * @param bucketName
     *            The name of the bucket containing the multipart uploads to
     *            abort.
     * @param date
     *            The date indicating which multipart uploads should be aborted.
     */
    public void abortMultipartUploads(String bucketName, Date date)
            throws AmazonServiceException, AmazonClientException {
        MultipartUploadListing uploadListing = s3.listMultipartUploads(appendSingleObjectUserAgent(
                new ListMultipartUploadsRequest(bucketName)));
        do {
            for (MultipartUpload upload : uploadListing.getMultipartUploads()) {
                if (upload.getInitiated().compareTo(date) < 0) {
                    s3.abortMultipartUpload(appendSingleObjectUserAgent(new AbortMultipartUploadRequest(
                            bucketName, upload.getKey(), upload.getUploadId())));
                }
            }

            ListMultipartUploadsRequest request = new ListMultipartUploadsRequest(bucketName)
                .withUploadIdMarker(uploadListing.getNextUploadIdMarker())
                .withKeyMarker(uploadListing.getNextKeyMarker());
            uploadListing = s3.listMultipartUploads(appendSingleObjectUserAgent(request));
        } while (uploadListing.isTruncated());
    }

    /**
     * Forcefully shuts down this TransferManager instance - currently executing
     * transfers will not be allowed to finish. It also by default shuts down
     * the underlying Amazon S3 client.
     *
     * @see #shutdownNow(boolean)
     */
    public void shutdownNow() {
        shutdownNow(true);
    }

    /**
     * Forcefully shuts down this TransferManager instance - currently executing
     * transfers will not be allowed to finish. Callers should use this method
     * when they either:
     * <ul>
     * <li>have already verified that their transfers have completed by checking
     * each transfer's state
     * <li>need to exit quickly and don't mind stopping transfers before they
     * complete.
     * </ul>
     * <p>
     * Callers should also remember that uploaded parts from an interrupted
     * upload may not always be automatically cleaned up, but callers can use
     * {@link #abortMultipartUploads(String, Date)} to clean up any upload
     * parts.
     *
     * @param shutDownS3Client
     *            Whether to shut down the underlying Amazon S3 client.
     */
    public void shutdownNow(boolean shutDownS3Client) {
        if (shutDownThreadPools) {
            executorService.shutdownNow();
            timedThreadPool.shutdownNow();
        }

        if (shutDownS3Client) {
            if (s3 instanceof AmazonS3Client) {
                ((AmazonS3Client)s3).shutdown();
            }
        }
    }

    /**
     * Shutdown without interrupting the threads involved, so that, for example,
     * any upload in progress can complete without throwing
     * {@link AbortedException}.
     */
    private void shutdownThreadPools() {
        if (shutDownThreadPools) {
            executorService.shutdown();
            timedThreadPool.shutdown();
        }
    }

    public static <X extends AmazonWebServiceRequest> X appendSingleObjectUserAgent(X request) {
        request.getRequestClientOptions().appendUserAgent(USER_AGENT);
        return request;
    }

    public static <X extends AmazonWebServiceRequest> X appendMultipartUserAgent(X request) {
        request.getRequestClientOptions().appendUserAgent(USER_AGENT_MULTIPART);
        return request;
    }
    private static final String USER_AGENT = TransferManager.class.getName() + "/" + VersionInfoUtils.getVersion();
    private static final String USER_AGENT_MULTIPART = TransferManager.class.getName() + "_multipart/" + VersionInfoUtils.getVersion();


    private static final String DEFAULT_DELIMITER = "/";

    /**
     * There is no need for threads from timedThreadPool if there is no more running threads in current process,
     * so we need a daemon thread factory for it.
     */
    private static final ThreadFactory daemonThreadFactory = new ThreadFactory() {
        final AtomicInteger threadCount = new AtomicInteger( 0 );
        public Thread newThread(Runnable r) {
            int threadNumber = threadCount.incrementAndGet();
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            thread.setName("S3TransferManagerTimedThread-" + threadNumber);
            return thread;
        }
    };

    /**
     * <p>
     * Schedules a new transfer to copy data from one Amazon S3 location to
     * another Amazon S3 location. This method is non-blocking and returns
     * immediately (i.e. before the copy has finished).
     * </p>
     * <p>
     * <code>TransferManager</code> doesn't support copying of encrypted objects
     * whose encryption materials is stored in instruction file.
     * </p>
     * <p>
     * Use the returned <code>Copy</code> object to check if the copy is
     * complete.
     * </p>
     * <p>
     * If resources are available, the copy request will begin immediately.
     * Otherwise, the copy is scheduled and started as soon as resources become
     * available.
     * </p>
     *
     * @param sourceBucketName
     *            The name of the bucket from where the object is to be copied.
     * @param sourceKey
     *            The name of the Amazon S3 object.
     * @param destinationBucketName
     *            The name of the bucket to where the Amazon S3 object has to be
     *            copied.
     * @param destinationKey
     *            The name of the object in the destination bucket.
     *
     * @return A new <code>Copy</code> object to use to check the state of the
     *         copy request being processed.
     *
     * @throws AmazonClientException
     *             If any errors are encountered in the client while making the
     *             request or handling the response.
     * @throws AmazonServiceException
     *             If any errors occurred in Amazon S3 while processing the
     *             request.
     */

    public Copy copy(String sourceBucketName, String sourceKey,
            String destinationBucketName, String destinationKey)
            throws AmazonServiceException, AmazonClientException {
        return copy(new CopyObjectRequest(sourceBucketName, sourceKey,
                destinationBucketName, destinationKey));
    }

    /**
     * <p>
     * Schedules a new transfer to copy data from one Amazon S3 location to
     * another Amazon S3 location. This method is non-blocking and returns
     * immediately (i.e. before the copy has finished).
     * </p>
     * <p>
     * <code>TransferManager</code> doesn't support copying of encrypted objects whose
     * encryption materials is stored i instruction file.
     * </p>
     * <p>
     * Use the returned <code>Copy</code> object to check if the copy is
     * complete.
     * </p>
     * <p>
     * If resources are available, the copy request will begin immediately.
     * Otherwise, the copy is scheduled and started as soon as resources become
     * available.
     * </p>
     *
     * @param copyObjectRequest
     *            The request containing all the parameters for the copy.
     *
     * @return A new <code>Copy</code> object to use to check the state of the
     *         copy request being processed.
     *
     * @throws AmazonClientException
     *             If any errors are encountered in the client while making the
     *             request or handling the response.
     * @throws AmazonServiceException
     *             If any errors occurred in Amazon S3 while processing the
     *             request.
     */
    public Copy copy(final CopyObjectRequest copyObjectRequest){
        return copy(copyObjectRequest,null);
    }

    /**
     * <p>
     * Schedules a new transfer to copy data from one Amazon S3 location to
     * another Amazon S3 location. This method is non-blocking and returns
     * immediately (i.e. before the copy has finished).
     * </p>
     * <p>
     * <code>TransferManager</code> doesn't support copying of encrypted objects whose
     * encryption materials is stored in instruction file.
     * </p>
     * <p>
     * Use the returned <code>Copy</code> object to check if the copy is
     * complete.
     * </p>
     * <p>
     * If resources are available, the copy request will begin immediately.
     * Otherwise, the copy is scheduled and started as soon as resources become
     * available.
     * </p>
     *
     * @param copyObjectRequest
     *            The request containing all the parameters for the copy.
     * @param stateChangeListener
     *            The transfer state change listener to monitor the copy request
     * @return A new <code>Copy</code> object to use to check the state of the
     *         copy request being processed.
     *
     * @throws AmazonClientException
     *             If any errors are encountered in the client while making the
     *             request or handling the response.
     * @throws AmazonServiceException
     *             If any errors occurred in Amazon S3 while processing the
     *             request.
     */

    public Copy copy(final CopyObjectRequest copyObjectRequest,
            final TransferStateChangeListener stateChangeListener)
            throws AmazonServiceException, AmazonClientException {

        appendSingleObjectUserAgent(copyObjectRequest);

        assertParameterNotNull(copyObjectRequest.getSourceBucketName(),
                "The source bucket name must be specified when a copy request is initiated.");
        assertParameterNotNull(copyObjectRequest.getSourceKey(),
                "The source object key must be specified when a copy request is initiated.");
        assertParameterNotNull(
                copyObjectRequest.getDestinationBucketName(),
                "The destination bucket name must be specified when a copy request is initiated.");
        assertParameterNotNull(
                copyObjectRequest.getDestinationKey(),
                "The destination object key must be specified when a copy request is initiated.");

        String description = "Copying object from "
                + copyObjectRequest.getSourceBucketName() + "/"
                + copyObjectRequest.getSourceKey() + " to "
                + copyObjectRequest.getDestinationBucketName() + "/"
                + copyObjectRequest.getDestinationKey();

        GetObjectMetadataRequest getObjectMetadataRequest =
                new GetObjectMetadataRequest(
                        copyObjectRequest.getSourceBucketName(),
                        copyObjectRequest.getSourceKey())
                        .withSSECustomerKey(copyObjectRequest.getSourceSSECustomerKey());

        ObjectMetadata metadata = s3.getObjectMetadata(getObjectMetadataRequest);

        TransferProgress transferProgress = new TransferProgress();
        transferProgress.setTotalBytesToTransfer(metadata.getContentLength());

        ProgressListenerChain listenerChain = new ProgressListenerChain(
                new TransferProgressUpdatingListener(transferProgress));
        CopyImpl copy = new CopyImpl(description, transferProgress,
                listenerChain, stateChangeListener);
        CopyCallable copyCallable = new CopyCallable(this, executorService, copy,
                                                     copyObjectRequest, metadata, listenerChain);
        CopyMonitor watcher = CopyMonitor.create(this, copy, executorService,
                                                 copyCallable, copyObjectRequest, listenerChain);
        watcher.setTimedThreadPool(timedThreadPool);
        copy.setMonitor(watcher);
        return copy;
    }

    /**
     * Resumes an upload operation. This upload operation uses the same
     * configuration {@link TransferManagerConfiguration} as the original
     * upload. Any data already uploaded will be skipped, and only the remaining
     * will be uploaded to Amazon S3.
     *
     * @param persistableUpload
     *            the upload to resume.
     * @return A new <code>Upload</code> object to use to check the state of the
     *         upload, listen for progress notifications, and otherwise manage
     *         the upload.
     *
     * @throws AmazonClientException
     *             If any errors are encountered in the client while making the
     *             request or handling the response.
     * @throws AmazonServiceException
     *             If any errors occurred in Amazon S3 while processing the
     *             request.
     */
    public Upload resumeUpload(PersistableUpload persistableUpload) {
        assertParameterNotNull(persistableUpload,
                "PauseUpload is mandatory to resume a upload.");
        configuration.setMinimumUploadPartSize(persistableUpload.getPartSize());
        configuration.setMultipartUploadThreshold(persistableUpload
                .getMutlipartUploadThreshold());
        return doUpload(new PutObjectRequest(persistableUpload.getBucketName(),
                persistableUpload.getKey(), new File(persistableUpload.getFile())), null, null,
                persistableUpload);
    }

    /**
     * Resumes an download operation. This download operation uses the same
     * configuration as the original download. Any data already fetched will be
     * skipped, and only the remaining data is retrieved from Amazon S3.
     *
     * @param persistableDownload
     *            the download to resume.
     * @return A new <code>Download</code> object to use to check the state of
     *         the download, listen for progress notifications, and otherwise
     *         manage the download.
     *
     * @throws AmazonClientException
     *             If any errors are encountered in the client while making the
     *             request or handling the response.
     * @throws AmazonServiceException
     *             If any errors occurred in Amazon S3 while processing the
     *             request.
     */
    public Download resumeDownload(PersistableDownload persistableDownload) {
        assertParameterNotNull(persistableDownload,
                "PausedDownload is mandatory to resume a download.");
        GetObjectRequest request = new GetObjectRequest(
                persistableDownload.getBucketName(), persistableDownload.getKey(),
                persistableDownload.getVersionId());
        if (persistableDownload.getRange() != null
                && persistableDownload.getRange().length == 2) {
            long[] range = persistableDownload.getRange();
            request.setRange(range[0], range[1]);
        }
        request.setRequesterPays(persistableDownload.isRequesterPays());
        request.setResponseHeaders(persistableDownload.getResponseHeaders());

        return doDownload(request, new File(persistableDownload.getFile()), null, null,
                APPEND_MODE, 0);
    }

    /**
     * <p>
     * Asserts that the specified parameter value is not <code>null</code> and if it is,
     * throws an <code>IllegalArgumentException</code> with the specified error message.
     * </p>
     *
     * @param parameterValue
     *            The parameter value being checked.
     * @param errorMessage
     *            The error message to include in the IllegalArgumentException
     *            if the specified parameter is null.
     */
    private void assertParameterNotNull(Object parameterValue, String errorMessage) {
        if (parameterValue == null) throw new IllegalArgumentException(errorMessage);
    }

    /**
     * Releasing all resources created by <code>TransferManager</code> before it
     * is being garbage collected.
     */
    @Override
    protected void finalize() throws Throwable {
        shutdownThreadPools();
    }
}