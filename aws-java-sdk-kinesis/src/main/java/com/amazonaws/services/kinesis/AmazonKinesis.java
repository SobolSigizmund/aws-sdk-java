/*
 * Copyright 2010-2016 Amazon.com, Inc. or its affiliates. All Rights
 * Reserved.
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
package com.amazonaws.services.kinesis;

import com.amazonaws.*;
import com.amazonaws.regions.*;

import com.amazonaws.services.kinesis.model.*;

/**
 * Interface for accessing Kinesis.
 * <p>
 * <fullname>Amazon Kinesis Streams Service API Reference</fullname>
 * <p>
 * Amazon Kinesis Streams is a managed service that scales elastically for real
 * time processing of streaming big data.
 * </p>
 */
public interface AmazonKinesis {

    /**
     * Overrides the default endpoint for this client
     * ("https://kinesis.us-east-1.amazonaws.com"). Callers can use this method
     * to control which AWS region they want to work with.
     * <p>
     * Callers can pass in just the endpoint (ex:
     * "kinesis.us-east-1.amazonaws.com") or a full URL, including the protocol
     * (ex: "https://kinesis.us-east-1.amazonaws.com"). If the protocol is not
     * specified here, the default protocol from this client's
     * {@link ClientConfiguration} will be used, which by default is HTTPS.
     * <p>
     * For more information on using AWS regions with the AWS SDK for Java, and
     * a complete list of all available endpoints for all AWS services, see: <a
     * href=
     * "http://developer.amazonwebservices.com/connect/entry.jspa?externalID=3912"
     * > http://developer.amazonwebservices.com/connect/entry.jspa?externalID=
     * 3912</a>
     * <p>
     * <b>This method is not threadsafe. An endpoint should be configured when
     * the client is created and before any service requests are made. Changing
     * it afterwards creates inevitable race conditions for any service requests
     * in transit or retrying.</b>
     *
     * @param endpoint
     *        The endpoint (ex: "kinesis.us-east-1.amazonaws.com") or a full
     *        URL, including the protocol (ex:
     *        "https://kinesis.us-east-1.amazonaws.com") of the region specific
     *        AWS endpoint this client will communicate with.
     */
    void setEndpoint(String endpoint);

    /**
     * An alternative to {@link AmazonKinesis#setEndpoint(String)}, sets the
     * regional endpoint for this client's service calls. Callers can use this
     * method to control which AWS region they want to work with.
     * <p>
     * By default, all service endpoints in all regions use the https protocol.
     * To use http instead, specify it in the {@link ClientConfiguration}
     * supplied at construction.
     * <p>
     * <b>This method is not threadsafe. A region should be configured when the
     * client is created and before any service requests are made. Changing it
     * afterwards creates inevitable race conditions for any service requests in
     * transit or retrying.</b>
     *
     * @param region
     *        The region this client will communicate with. See
     *        {@link Region#getRegion(com.amazonaws.regions.Regions)} for
     *        accessing a given region. Must not be null and must be a region
     *        where the service is available.
     *
     * @see Region#getRegion(com.amazonaws.regions.Regions)
     * @see Region#createClient(Class,
     *      com.amazonaws.auth.AWSCredentialsProvider, ClientConfiguration)
     * @see Region#isServiceSupported(String)
     */
    void setRegion(Region region);

    /**
     * <p>
     * Adds or updates tags for the specified Amazon Kinesis stream. Each stream
     * can have up to 10 tags.
     * </p>
     * <p>
     * If tags have already been assigned to the stream,
     * <code>AddTagsToStream</code> overwrites any existing tags that correspond
     * to the specified tag keys.
     * </p>
     * 
     * @param addTagsToStreamRequest
     *        Represents the input for <code>AddTagsToStream</code>.
     * @throws ResourceNotFoundException
     *         The requested resource could not be found. The stream might not
     *         be specified correctly, or it might not be in the
     *         <code>ACTIVE</code> state if the operation requires it.
     * @throws ResourceInUseException
     *         The resource is not available for this operation. For successful
     *         operation, the resource needs to be in the <code>ACTIVE</code>
     *         state.
     * @throws InvalidArgumentException
     *         A specified parameter exceeds its restrictions, is not supported,
     *         or can't be used. For more information, see the returned message.
     * @throws LimitExceededException
     *         The requested resource exceeds the maximum number allowed, or the
     *         number of concurrent stream requests exceeds the maximum number
     *         allowed (5).
     * @sample AmazonKinesis.AddTagsToStream
     */
    void addTagsToStream(AddTagsToStreamRequest addTagsToStreamRequest);

    /**
     * <p>
     * Creates an Amazon Kinesis stream. A stream captures and transports data
     * records that are continuously emitted from different data sources or
     * <i>producers</i>. Scale-out within a stream is explicitly supported by
     * means of shards, which are uniquely identified groups of data records in
     * a stream.
     * </p>
     * <p>
     * You specify and control the number of shards that a stream is composed
     * of. Each shard can support reads up to 5 transactions per second, up to a
     * maximum data read total of 2 MB per second. Each shard can support writes
     * up to 1,000 records per second, up to a maximum data write total of 1 MB
     * per second. You can add shards to a stream if the amount of data input
     * increases and you can remove shards if the amount of data input
     * decreases.
     * </p>
     * <p>
     * The stream name identifies the stream. The name is scoped to the AWS
     * account used by the application. It is also scoped by region. That is,
     * two streams in two different accounts can have the same name, and two
     * streams in the same account, but in two different regions, can have the
     * same name.
     * </p>
     * <p>
     * <code>CreateStream</code> is an asynchronous operation. Upon receiving a
     * <code>CreateStream</code> request, Amazon Kinesis immediately returns and
     * sets the stream status to <code>CREATING</code>. After the stream is
     * created, Amazon Kinesis sets the stream status to <code>ACTIVE</code>.
     * You should perform read and write operations only on an
     * <code>ACTIVE</code> stream.
     * </p>
     * <p>
     * You receive a <code>LimitExceededException</code> when making a
     * <code>CreateStream</code> request if you try to do one of the following:
     * </p>
     * <ul>
     * <li>Have more than five streams in the <code>CREATING</code> state at any
     * point in time.</li>
     * <li>Create more shards than are authorized for your account.</li>
     * </ul>
     * <p>
     * For the default shard limit for an AWS account, see <a href=
     * "http://docs.aws.amazon.com/kinesis/latest/dev/service-sizes-and-limits.html"
     * >Streams Limits</a> in the <i>Amazon Kinesis Streams Developer Guide</i>.
     * If you need to increase this limit, <a href=
     * "http://docs.aws.amazon.com/general/latest/gr/aws_service_limits.html"
     * >contact AWS Support</a>.
     * </p>
     * <p>
     * You can use <code>DescribeStream</code> to check the stream status, which
     * is returned in <code>StreamStatus</code>.
     * </p>
     * <p>
     * <a>CreateStream</a> has a limit of 5 transactions per second per account.
     * </p>
     * 
     * @param createStreamRequest
     *        Represents the input for <code>CreateStream</code>.
     * @throws ResourceInUseException
     *         The resource is not available for this operation. For successful
     *         operation, the resource needs to be in the <code>ACTIVE</code>
     *         state.
     * @throws LimitExceededException
     *         The requested resource exceeds the maximum number allowed, or the
     *         number of concurrent stream requests exceeds the maximum number
     *         allowed (5).
     * @throws InvalidArgumentException
     *         A specified parameter exceeds its restrictions, is not supported,
     *         or can't be used. For more information, see the returned message.
     * @sample AmazonKinesis.CreateStream
     */
    void createStream(CreateStreamRequest createStreamRequest);

    /**
     * Simplified method form for invoking the CreateStream operation.
     *
     * @see #createStream(CreateStreamRequest)
     */
    void createStream(String streamName, Integer shardCount);

    /**
     * <p>
     * Decreases the Amazon Kinesis stream's retention period, which is the
     * length of time data records are accessible after they are added to the
     * stream. The minimum value of a stream's retention period is 24 hours.
     * </p>
     * <p>
     * This operation may result in lost data. For example, if the stream's
     * retention period is 48 hours and is decreased to 24 hours, any data
     * already in the stream that is older than 24 hours is inaccessible.
     * </p>
     * 
     * @param decreaseStreamRetentionPeriodRequest
     *        Represents the input for <a>DecreaseStreamRetentionPeriod</a>.
     * @throws ResourceInUseException
     *         The resource is not available for this operation. For successful
     *         operation, the resource needs to be in the <code>ACTIVE</code>
     *         state.
     * @throws ResourceNotFoundException
     *         The requested resource could not be found. The stream might not
     *         be specified correctly, or it might not be in the
     *         <code>ACTIVE</code> state if the operation requires it.
     * @throws LimitExceededException
     *         The requested resource exceeds the maximum number allowed, or the
     *         number of concurrent stream requests exceeds the maximum number
     *         allowed (5).
     * @throws InvalidArgumentException
     *         A specified parameter exceeds its restrictions, is not supported,
     *         or can't be used. For more information, see the returned message.
     * @sample AmazonKinesis.DecreaseStreamRetentionPeriod
     */
    void decreaseStreamRetentionPeriod(
            DecreaseStreamRetentionPeriodRequest decreaseStreamRetentionPeriodRequest);

    /**
     * <p>
     * Deletes an Amazon Kinesis stream and all its shards and data. You must
     * shut down any applications that are operating on the stream before you
     * delete the stream. If an application attempts to operate on a deleted
     * stream, it will receive the exception
     * <code>ResourceNotFoundException</code>.
     * </p>
     * <p>
     * If the stream is in the <code>ACTIVE</code> state, you can delete it.
     * After a <code>DeleteStream</code> request, the specified stream is in the
     * <code>DELETING</code> state until Amazon Kinesis completes the deletion.
     * </p>
     * <p>
     * <b>Note:</b> Amazon Kinesis might continue to accept data read and write
     * operations, such as <a>PutRecord</a>, <a>PutRecords</a>, and
     * <a>GetRecords</a>, on a stream in the <code>DELETING</code> state until
     * the stream deletion is complete.
     * </p>
     * <p>
     * When you delete a stream, any shards in that stream are also deleted, and
     * any tags are dissociated from the stream.
     * </p>
     * <p>
     * You can use the <a>DescribeStream</a> operation to check the state of the
     * stream, which is returned in <code>StreamStatus</code>.
     * </p>
     * <p>
     * <a>DeleteStream</a> has a limit of 5 transactions per second per account.
     * </p>
     * 
     * @param deleteStreamRequest
     *        Represents the input for <a>DeleteStream</a>.
     * @throws ResourceNotFoundException
     *         The requested resource could not be found. The stream might not
     *         be specified correctly, or it might not be in the
     *         <code>ACTIVE</code> state if the operation requires it.
     * @throws LimitExceededException
     *         The requested resource exceeds the maximum number allowed, or the
     *         number of concurrent stream requests exceeds the maximum number
     *         allowed (5).
     * @sample AmazonKinesis.DeleteStream
     */
    void deleteStream(DeleteStreamRequest deleteStreamRequest);

    /**
     * Simplified method form for invoking the DeleteStream operation.
     *
     * @see #deleteStream(DeleteStreamRequest)
     */
    void deleteStream(String streamName);

    /**
     * <p>
     * Describes the specified Amazon Kinesis stream.
     * </p>
     * <p>
     * The information about the stream includes its current status, its Amazon
     * Resource Name (ARN), and an array of shard objects. For each shard
     * object, there is information about the hash key and sequence number
     * ranges that the shard spans, and the IDs of any earlier shards that
     * played in a role in creating the shard. A sequence number is the
     * identifier associated with every record ingested in the stream. The
     * sequence number is assigned when a record is put into the stream.
     * </p>
     * <p>
     * You can limit the number of returned shards using the <code>Limit</code>
     * parameter. The number of shards in a stream may be too large to return
     * from a single call to <code>DescribeStream</code>. You can detect this by
     * using the <code>HasMoreShards</code> flag in the returned output.
     * <code>HasMoreShards</code> is set to <code>true</code> when there is more
     * data available.
     * </p>
     * <p>
     * <code>DescribeStream</code> is a paginated operation. If there are more
     * shards available, you can request them using the shard ID of the last
     * shard returned. Specify this ID in the <code>ExclusiveStartShardId</code>
     * parameter in a subsequent request to <code>DescribeStream</code>.
     * </p>
     * <p>
     * There are no guarantees about the chronological order shards returned in
     * <code>DescribeStream</code> results. If you want to process shards in
     * chronological order, use <code>ParentShardId</code> to track lineage to
     * the oldest shard.
     * </p>
     * <p>
     * <a>DescribeStream</a> has a limit of 10 transactions per second per
     * account.
     * </p>
     * 
     * @param describeStreamRequest
     *        Represents the input for <code>DescribeStream</code>.
     * @return Result of the DescribeStream operation returned by the service.
     * @throws ResourceNotFoundException
     *         The requested resource could not be found. The stream might not
     *         be specified correctly, or it might not be in the
     *         <code>ACTIVE</code> state if the operation requires it.
     * @throws LimitExceededException
     *         The requested resource exceeds the maximum number allowed, or the
     *         number of concurrent stream requests exceeds the maximum number
     *         allowed (5).
     * @sample AmazonKinesis.DescribeStream
     */
    DescribeStreamResult describeStream(
            DescribeStreamRequest describeStreamRequest);

    /**
     * Simplified method form for invoking the DescribeStream operation.
     *
     * @see #describeStream(DescribeStreamRequest)
     */
    DescribeStreamResult describeStream(String streamName);

    /**
     * Simplified method form for invoking the DescribeStream operation.
     *
     * @see #describeStream(DescribeStreamRequest)
     */
    DescribeStreamResult describeStream(String streamName,
            String exclusiveStartShardId);

    /**
     * Simplified method form for invoking the DescribeStream operation.
     *
     * @see #describeStream(DescribeStreamRequest)
     */
    DescribeStreamResult describeStream(String streamName, Integer limit,
            String exclusiveStartShardId);

    /**
     * <p>
     * Disables enhanced monitoring.
     * </p>
     * 
     * @param disableEnhancedMonitoringRequest
     *        Represents the input for <a>DisableEnhancedMonitoring</a>.
     * @return Result of the DisableEnhancedMonitoring operation returned by the
     *         service.
     * @throws InvalidArgumentException
     *         A specified parameter exceeds its restrictions, is not supported,
     *         or can't be used. For more information, see the returned message.
     * @throws LimitExceededException
     *         The requested resource exceeds the maximum number allowed, or the
     *         number of concurrent stream requests exceeds the maximum number
     *         allowed (5).
     * @throws ResourceInUseException
     *         The resource is not available for this operation. For successful
     *         operation, the resource needs to be in the <code>ACTIVE</code>
     *         state.
     * @throws ResourceNotFoundException
     *         The requested resource could not be found. The stream might not
     *         be specified correctly, or it might not be in the
     *         <code>ACTIVE</code> state if the operation requires it.
     * @sample AmazonKinesis.DisableEnhancedMonitoring
     */
    DisableEnhancedMonitoringResult disableEnhancedMonitoring(
            DisableEnhancedMonitoringRequest disableEnhancedMonitoringRequest);

    /**
     * <p>
     * Enables enhanced Amazon Kinesis stream monitoring for shard-level
     * metrics.
     * </p>
     * 
     * @param enableEnhancedMonitoringRequest
     *        Represents the input for <a>EnableEnhancedMonitoring</a>.
     * @return Result of the EnableEnhancedMonitoring operation returned by the
     *         service.
     * @throws InvalidArgumentException
     *         A specified parameter exceeds its restrictions, is not supported,
     *         or can't be used. For more information, see the returned message.
     * @throws LimitExceededException
     *         The requested resource exceeds the maximum number allowed, or the
     *         number of concurrent stream requests exceeds the maximum number
     *         allowed (5).
     * @throws ResourceInUseException
     *         The resource is not available for this operation. For successful
     *         operation, the resource needs to be in the <code>ACTIVE</code>
     *         state.
     * @throws ResourceNotFoundException
     *         The requested resource could not be found. The stream might not
     *         be specified correctly, or it might not be in the
     *         <code>ACTIVE</code> state if the operation requires it.
     * @sample AmazonKinesis.EnableEnhancedMonitoring
     */
    EnableEnhancedMonitoringResult enableEnhancedMonitoring(
            EnableEnhancedMonitoringRequest enableEnhancedMonitoringRequest);

    /**
     * <p>
     * Gets data records from an Amazon Kinesis stream's shard.
     * </p>
     * <p>
     * Specify a shard iterator using the <code>ShardIterator</code> parameter.
     * The shard iterator specifies the position in the shard from which you
     * want to start reading data records sequentially. If there are no records
     * available in the portion of the shard that the iterator points to,
     * <a>GetRecords</a> returns an empty list. Note that it might take multiple
     * calls to get to a portion of the shard that contains records.
     * </p>
     * <p>
     * You can scale by provisioning multiple shards per stream while
     * considering service limits (for more information, see <a href=
     * "http://docs.aws.amazon.com/kinesis/latest/dev/service-sizes-and-limits.html"
     * >Streams Limits</a> in the <i>Amazon Kinesis Streams Developer
     * Guide</i>). Your application should have one thread per shard, each
     * reading continuously from its stream. To read from a stream continually,
     * call <a>GetRecords</a> in a loop. Use <a>GetShardIterator</a> to get the
     * shard iterator to specify in the first <a>GetRecords</a> call.
     * <a>GetRecords</a> returns a new shard iterator in
     * <code>NextShardIterator</code>. Specify the shard iterator returned in
     * <code>NextShardIterator</code> in subsequent calls to <a>GetRecords</a>.
     * Note that if the shard has been closed, the shard iterator can't return
     * more data and <a>GetRecords</a> returns <code>null</code> in
     * <code>NextShardIterator</code>. You can terminate the loop when the shard
     * is closed, or when the shard iterator reaches the record with the
     * sequence number or other attribute that marks it as the last record to
     * process.
     * </p>
     * <p>
     * Each data record can be up to 1 MB in size, and each shard can read up to
     * 2 MB per second. You can ensure that your calls don't exceed the maximum
     * supported size or throughput by using the <code>Limit</code> parameter to
     * specify the maximum number of records that <a>GetRecords</a> can return.
     * Consider your average record size when determining this limit.
     * </p>
     * <p>
     * The size of the data returned by <a>GetRecords</a> varies depending on
     * the utilization of the shard. The maximum size of data that
     * <a>GetRecords</a> can return is 10 MB. If a call returns this amount of
     * data, subsequent calls made within the next 5 seconds throw
     * <code>ProvisionedThroughputExceededException</code>. If there is
     * insufficient provisioned throughput on the shard, subsequent calls made
     * within the next 1 second throw
     * <code>ProvisionedThroughputExceededException</code>. Note that
     * <a>GetRecords</a> won't return any data when it throws an exception. For
     * this reason, we recommend that you wait one second between calls to
     * <a>GetRecords</a>; however, it's possible that the application will get
     * exceptions for longer than 1 second.
     * </p>
     * <p>
     * To detect whether the application is falling behind in processing, you
     * can use the <code>MillisBehindLatest</code> response attribute. You can
     * also monitor the stream using CloudWatch metrics and other mechanisms
     * (see <a
     * href="http://docs.aws.amazon.com/kinesis/latest/dev/monitoring.html"
     * >Monitoring</a> in the <i>Amazon Kinesis Streams Developer Guide</i>).
     * </p>
     * <p>
     * Each Amazon Kinesis record includes a value,
     * <code>ApproximateArrivalTimestamp</code>, that is set when a stream
     * successfully receives and stores a record. This is commonly referred to
     * as a server-side timestamp, whereas a client-side timestamp is set when a
     * data producer creates or sends the record to a stream (a data producer is
     * any data source putting data records into a stream, for example with
     * <a>PutRecords</a>). The timestamp has millisecond precision. There are no
     * guarantees about the timestamp accuracy, or that the timestamp is always
     * increasing. For example, records in a shard or across a stream might have
     * timestamps that are out of order.
     * </p>
     * 
     * @param getRecordsRequest
     *        Represents the input for <a>GetRecords</a>.
     * @return Result of the GetRecords operation returned by the service.
     * @throws ResourceNotFoundException
     *         The requested resource could not be found. The stream might not
     *         be specified correctly, or it might not be in the
     *         <code>ACTIVE</code> state if the operation requires it.
     * @throws InvalidArgumentException
     *         A specified parameter exceeds its restrictions, is not supported,
     *         or can't be used. For more information, see the returned message.
     * @throws ProvisionedThroughputExceededException
     *         The request rate for the stream is too high, or the requested
     *         data is too large for the available throughput. Reduce the
     *         frequency or size of your requests. For more information, see <a
     *         href=
     *         "http://docs.aws.amazon.com/kinesis/latest/dev/service-sizes-and-limits.html"
     *         >Streams Limits</a> in the <i>Amazon Kinesis Streams Developer
     *         Guide</i>, and <a href=
     *         "http://docs.aws.amazon.com/general/latest/gr/api-retries.html"
     *         >Error Retries and Exponential Backoff in AWS</a> in the <i>AWS
     *         General Reference</i>.
     * @throws ExpiredIteratorException
     *         The provided iterator exceeds the maximum age allowed.
     * @sample AmazonKinesis.GetRecords
     */
    GetRecordsResult getRecords(GetRecordsRequest getRecordsRequest);

    /**
     * <p>
     * Gets an Amazon Kinesis shard iterator. A shard iterator expires five
     * minutes after it is returned to the requester.
     * </p>
     * <p>
     * A shard iterator specifies the shard position from which to start reading
     * data records sequentially. The position is specified using the sequence
     * number of a data record in a shard. A sequence number is the identifier
     * associated with every record ingested in the stream, and is assigned when
     * a record is put into the stream. Each stream has one or more shards.
     * </p>
     * <p>
     * You must specify the shard iterator type. For example, you can set the
     * <code>ShardIteratorType</code> parameter to read exactly from the
     * position denoted by a specific sequence number by using the
     * <code>AT_SEQUENCE_NUMBER</code> shard iterator type, or right after the
     * sequence number by using the <code>AFTER_SEQUENCE_NUMBER</code> shard
     * iterator type, using sequence numbers returned by earlier calls to
     * <a>PutRecord</a>, <a>PutRecords</a>, <a>GetRecords</a>, or
     * <a>DescribeStream</a>. In the request, you can specify the shard iterator
     * type <code>AT_TIMESTAMP</code> to read records from an arbitrary point in
     * time, <code>TRIM_HORIZON</code> to cause <code>ShardIterator</code> to
     * point to the last untrimmed record in the shard in the system (the oldest
     * data record in the shard), or <code>LATEST</code> so that you always read
     * the most recent data in the shard.
     * </p>
     * <p>
     * When you read repeatedly from a stream, use a <a>GetShardIterator</a>
     * request to get the first shard iterator for use in your first
     * <a>GetRecords</a> request and for subsequent reads use the shard iterator
     * returned by the <a>GetRecords</a> request in
     * <code>NextShardIterator</code>. A new shard iterator is returned by every
     * <a>GetRecords</a> request in <code>NextShardIterator</code>, which you
     * use in the <code>ShardIterator</code> parameter of the next
     * <a>GetRecords</a> request.
     * </p>
     * <p>
     * If a <a>GetShardIterator</a> request is made too often, you receive a
     * <code>ProvisionedThroughputExceededException</code>. For more information
     * about throughput limits, see <a>GetRecords</a>, and <a href=
     * "http://docs.aws.amazon.com/kinesis/latest/dev/service-sizes-and-limits.html"
     * >Streams Limits</a> in the <i>Amazon Kinesis Streams Developer Guide</i>.
     * </p>
     * <p>
     * If the shard is closed, <a>GetShardIterator</a> returns a valid iterator
     * for the last sequence number of the shard. Note that a shard can be
     * closed as a result of using <a>SplitShard</a> or <a>MergeShards</a>.
     * </p>
     * <p>
     * <a>GetShardIterator</a> has a limit of 5 transactions per second per
     * account per open shard.
     * </p>
     * 
     * @param getShardIteratorRequest
     *        Represents the input for <code>GetShardIterator</code>.
     * @return Result of the GetShardIterator operation returned by the service.
     * @throws ResourceNotFoundException
     *         The requested resource could not be found. The stream might not
     *         be specified correctly, or it might not be in the
     *         <code>ACTIVE</code> state if the operation requires it.
     * @throws InvalidArgumentException
     *         A specified parameter exceeds its restrictions, is not supported,
     *         or can't be used. For more information, see the returned message.
     * @throws ProvisionedThroughputExceededException
     *         The request rate for the stream is too high, or the requested
     *         data is too large for the available throughput. Reduce the
     *         frequency or size of your requests. For more information, see <a
     *         href=
     *         "http://docs.aws.amazon.com/kinesis/latest/dev/service-sizes-and-limits.html"
     *         >Streams Limits</a> in the <i>Amazon Kinesis Streams Developer
     *         Guide</i>, and <a href=
     *         "http://docs.aws.amazon.com/general/latest/gr/api-retries.html"
     *         >Error Retries and Exponential Backoff in AWS</a> in the <i>AWS
     *         General Reference</i>.
     * @sample AmazonKinesis.GetShardIterator
     */
    GetShardIteratorResult getShardIterator(
            GetShardIteratorRequest getShardIteratorRequest);

    /**
     * Simplified method form for invoking the GetShardIterator operation.
     *
     * @see #getShardIterator(GetShardIteratorRequest)
     */
    GetShardIteratorResult getShardIterator(String streamName, String shardId,
            String shardIteratorType);

    /**
     * Simplified method form for invoking the GetShardIterator operation.
     *
     * @see #getShardIterator(GetShardIteratorRequest)
     */
    GetShardIteratorResult getShardIterator(String streamName, String shardId,
            String shardIteratorType, String startingSequenceNumber);

    /**
     * <p>
     * Increases the Amazon Kinesis stream's retention period, which is the
     * length of time data records are accessible after they are added to the
     * stream. The maximum value of a stream's retention period is 168 hours (7
     * days).
     * </p>
     * <p>
     * Upon choosing a longer stream retention period, this operation will
     * increase the time period records are accessible that have not yet
     * expired. However, it will not make previous data that has expired (older
     * than the stream's previous retention period) accessible after the
     * operation has been called. For example, if a stream's retention period is
     * set to 24 hours and is increased to 168 hours, any data that is older
     * than 24 hours will remain inaccessible to consumer applications.
     * </p>
     * 
     * @param increaseStreamRetentionPeriodRequest
     *        Represents the input for <a>IncreaseStreamRetentionPeriod</a>.
     * @throws ResourceInUseException
     *         The resource is not available for this operation. For successful
     *         operation, the resource needs to be in the <code>ACTIVE</code>
     *         state.
     * @throws ResourceNotFoundException
     *         The requested resource could not be found. The stream might not
     *         be specified correctly, or it might not be in the
     *         <code>ACTIVE</code> state if the operation requires it.
     * @throws LimitExceededException
     *         The requested resource exceeds the maximum number allowed, or the
     *         number of concurrent stream requests exceeds the maximum number
     *         allowed (5).
     * @throws InvalidArgumentException
     *         A specified parameter exceeds its restrictions, is not supported,
     *         or can't be used. For more information, see the returned message.
     * @sample AmazonKinesis.IncreaseStreamRetentionPeriod
     */
    void increaseStreamRetentionPeriod(
            IncreaseStreamRetentionPeriodRequest increaseStreamRetentionPeriodRequest);

    /**
     * <p>
     * Lists your Amazon Kinesis streams.
     * </p>
     * <p>
     * The number of streams may be too large to return from a single call to
     * <code>ListStreams</code>. You can limit the number of returned streams
     * using the <code>Limit</code> parameter. If you do not specify a value for
     * the <code>Limit</code> parameter, Amazon Kinesis uses the default limit,
     * which is currently 10.
     * </p>
     * <p>
     * You can detect if there are more streams available to list by using the
     * <code>HasMoreStreams</code> flag from the returned output. If there are
     * more streams available, you can request more streams by using the name of
     * the last stream returned by the <code>ListStreams</code> request in the
     * <code>ExclusiveStartStreamName</code> parameter in a subsequent request
     * to <code>ListStreams</code>. The group of stream names returned by the
     * subsequent request is then added to the list. You can continue this
     * process until all the stream names have been collected in the list.
     * </p>
     * <p>
     * <a>ListStreams</a> has a limit of 5 transactions per second per account.
     * </p>
     * 
     * @param listStreamsRequest
     *        Represents the input for <code>ListStreams</code>.
     * @return Result of the ListStreams operation returned by the service.
     * @throws LimitExceededException
     *         The requested resource exceeds the maximum number allowed, or the
     *         number of concurrent stream requests exceeds the maximum number
     *         allowed (5).
     * @sample AmazonKinesis.ListStreams
     */
    ListStreamsResult listStreams(ListStreamsRequest listStreamsRequest);

    /**
     * Simplified method form for invoking the ListStreams operation.
     *
     * @see #listStreams(ListStreamsRequest)
     */
    ListStreamsResult listStreams();

    /**
     * Simplified method form for invoking the ListStreams operation.
     *
     * @see #listStreams(ListStreamsRequest)
     */
    ListStreamsResult listStreams(String exclusiveStartStreamName);

    /**
     * Simplified method form for invoking the ListStreams operation.
     *
     * @see #listStreams(ListStreamsRequest)
     */
    ListStreamsResult listStreams(Integer limit, String exclusiveStartStreamName);

    /**
     * <p>
     * Lists the tags for the specified Amazon Kinesis stream.
     * </p>
     * 
     * @param listTagsForStreamRequest
     *        Represents the input for <code>ListTagsForStream</code>.
     * @return Result of the ListTagsForStream operation returned by the
     *         service.
     * @throws ResourceNotFoundException
     *         The requested resource could not be found. The stream might not
     *         be specified correctly, or it might not be in the
     *         <code>ACTIVE</code> state if the operation requires it.
     * @throws InvalidArgumentException
     *         A specified parameter exceeds its restrictions, is not supported,
     *         or can't be used. For more information, see the returned message.
     * @throws LimitExceededException
     *         The requested resource exceeds the maximum number allowed, or the
     *         number of concurrent stream requests exceeds the maximum number
     *         allowed (5).
     * @sample AmazonKinesis.ListTagsForStream
     */
    ListTagsForStreamResult listTagsForStream(
            ListTagsForStreamRequest listTagsForStreamRequest);

    /**
     * <p>
     * Merges two adjacent shards in an Amazon Kinesis stream and combines them
     * into a single shard to reduce the stream's capacity to ingest and
     * transport data. Two shards are considered adjacent if the union of the
     * hash key ranges for the two shards form a contiguous set with no gaps.
     * For example, if you have two shards, one with a hash key range of
     * 276...381 and the other with a hash key range of 382...454, then you
     * could merge these two shards into a single shard that would have a hash
     * key range of 276...454. After the merge, the single child shard receives
     * data for all hash key values covered by the two parent shards.
     * </p>
     * <p>
     * <code>MergeShards</code> is called when there is a need to reduce the
     * overall capacity of a stream because of excess capacity that is not being
     * used. You must specify the shard to be merged and the adjacent shard for
     * a stream. For more information about merging shards, see <a href=
     * "http://docs.aws.amazon.com/kinesis/latest/dev/kinesis-using-sdk-java-resharding-merge.html"
     * >Merge Two Shards</a> in the <i>Amazon Kinesis Streams Developer
     * Guide</i>.
     * </p>
     * <p>
     * If the stream is in the <code>ACTIVE</code> state, you can call
     * <code>MergeShards</code>. If a stream is in the <code>CREATING</code>,
     * <code>UPDATING</code>, or <code>DELETING</code> state,
     * <code>MergeShards</code> returns a <code>ResourceInUseException</code>.
     * If the specified stream does not exist, <code>MergeShards</code> returns
     * a <code>ResourceNotFoundException</code>.
     * </p>
     * <p>
     * You can use <a>DescribeStream</a> to check the state of the stream, which
     * is returned in <code>StreamStatus</code>.
     * </p>
     * <p>
     * <code>MergeShards</code> is an asynchronous operation. Upon receiving a
     * <code>MergeShards</code> request, Amazon Kinesis immediately returns a
     * response and sets the <code>StreamStatus</code> to <code>UPDATING</code>.
     * After the operation is completed, Amazon Kinesis sets the
     * <code>StreamStatus</code> to <code>ACTIVE</code>. Read and write
     * operations continue to work while the stream is in the
     * <code>UPDATING</code> state.
     * </p>
     * <p>
     * You use <a>DescribeStream</a> to determine the shard IDs that are
     * specified in the <code>MergeShards</code> request.
     * </p>
     * <p>
     * If you try to operate on too many streams in parallel using
     * <a>CreateStream</a>, <a>DeleteStream</a>, <code>MergeShards</code> or
     * <a>SplitShard</a>, you will receive a <code>LimitExceededException</code>
     * .
     * </p>
     * <p>
     * <code>MergeShards</code> has limit of 5 transactions per second per
     * account.
     * </p>
     * 
     * @param mergeShardsRequest
     *        Represents the input for <code>MergeShards</code>.
     * @throws ResourceNotFoundException
     *         The requested resource could not be found. The stream might not
     *         be specified correctly, or it might not be in the
     *         <code>ACTIVE</code> state if the operation requires it.
     * @throws ResourceInUseException
     *         The resource is not available for this operation. For successful
     *         operation, the resource needs to be in the <code>ACTIVE</code>
     *         state.
     * @throws InvalidArgumentException
     *         A specified parameter exceeds its restrictions, is not supported,
     *         or can't be used. For more information, see the returned message.
     * @throws LimitExceededException
     *         The requested resource exceeds the maximum number allowed, or the
     *         number of concurrent stream requests exceeds the maximum number
     *         allowed (5).
     * @sample AmazonKinesis.MergeShards
     */
    void mergeShards(MergeShardsRequest mergeShardsRequest);

    /**
     * Simplified method form for invoking the MergeShards operation.
     *
     * @see #mergeShards(MergeShardsRequest)
     */
    void mergeShards(String streamName, String shardToMerge,
            String adjacentShardToMerge);

    /**
     * <p>
     * Writes a single data record into an Amazon Kinesis stream. Call
     * <code>PutRecord</code> to send data into the stream for real-time
     * ingestion and subsequent processing, one record at a time. Each shard can
     * support writes up to 1,000 records per second, up to a maximum data write
     * total of 1 MB per second.
     * </p>
     * <p>
     * You must specify the name of the stream that captures, stores, and
     * transports the data; a partition key; and the data blob itself.
     * </p>
     * <p>
     * The data blob can be any type of data; for example, a segment from a log
     * file, geographic/location data, website clickstream data, and so on.
     * </p>
     * <p>
     * The partition key is used by Amazon Kinesis to distribute data across
     * shards. Amazon Kinesis segregates the data records that belong to a
     * stream into multiple shards, using the partition key associated with each
     * data record to determine which shard a given data record belongs to.
     * </p>
     * <p>
     * Partition keys are Unicode strings, with a maximum length limit of 256
     * characters for each key. An MD5 hash function is used to map partition
     * keys to 128-bit integer values and to map associated data records to
     * shards using the hash key ranges of the shards. You can override hashing
     * the partition key to determine the shard by explicitly specifying a hash
     * value using the <code>ExplicitHashKey</code> parameter. For more
     * information, see <a href=
     * "http://docs.aws.amazon.com/kinesis/latest/dev/developing-producers-with-sdk.html#kinesis-using-sdk-java-add-data-to-stream"
     * >Adding Data to a Stream</a> in the <i>Amazon Kinesis Streams Developer
     * Guide</i>.
     * </p>
     * <p>
     * <code>PutRecord</code> returns the shard ID of where the data record was
     * placed and the sequence number that was assigned to the data record.
     * </p>
     * <p>
     * Sequence numbers increase over time and are specific to a shard within a
     * stream, not across all shards within a stream. To guarantee strictly
     * increasing ordering, write serially to a shard and use the
     * <code>SequenceNumberForOrdering</code> parameter. For more information,
     * see <a href=
     * "http://docs.aws.amazon.com/kinesis/latest/dev/developing-producers-with-sdk.html#kinesis-using-sdk-java-add-data-to-stream"
     * >Adding Data to a Stream</a> in the <i>Amazon Kinesis Streams Developer
     * Guide</i>.
     * </p>
     * <p>
     * If a <code>PutRecord</code> request cannot be processed because of
     * insufficient provisioned throughput on the shard involved in the request,
     * <code>PutRecord</code> throws
     * <code>ProvisionedThroughputExceededException</code>.
     * </p>
     * <p>
     * Data records are accessible for only 24 hours from the time that they are
     * added to a stream.
     * </p>
     * 
     * @param putRecordRequest
     *        Represents the input for <code>PutRecord</code>.
     * @return Result of the PutRecord operation returned by the service.
     * @throws ResourceNotFoundException
     *         The requested resource could not be found. The stream might not
     *         be specified correctly, or it might not be in the
     *         <code>ACTIVE</code> state if the operation requires it.
     * @throws InvalidArgumentException
     *         A specified parameter exceeds its restrictions, is not supported,
     *         or can't be used. For more information, see the returned message.
     * @throws ProvisionedThroughputExceededException
     *         The request rate for the stream is too high, or the requested
     *         data is too large for the available throughput. Reduce the
     *         frequency or size of your requests. For more information, see <a
     *         href=
     *         "http://docs.aws.amazon.com/kinesis/latest/dev/service-sizes-and-limits.html"
     *         >Streams Limits</a> in the <i>Amazon Kinesis Streams Developer
     *         Guide</i>, and <a href=
     *         "http://docs.aws.amazon.com/general/latest/gr/api-retries.html"
     *         >Error Retries and Exponential Backoff in AWS</a> in the <i>AWS
     *         General Reference</i>.
     * @sample AmazonKinesis.PutRecord
     */
    PutRecordResult putRecord(PutRecordRequest putRecordRequest);

    /**
     * Simplified method form for invoking the PutRecord operation.
     *
     * @see #putRecord(PutRecordRequest)
     */
    PutRecordResult putRecord(String streamName, java.nio.ByteBuffer data,
            String partitionKey);

    /**
     * Simplified method form for invoking the PutRecord operation.
     *
     * @see #putRecord(PutRecordRequest)
     */
    PutRecordResult putRecord(String streamName, java.nio.ByteBuffer data,
            String partitionKey, String sequenceNumberForOrdering);

    /**
     * <p>
     * Writes multiple data records into an Amazon Kinesis stream in a single
     * call (also referred to as a <code>PutRecords</code> request). Use this
     * operation to send data into the stream for data ingestion and processing.
     * </p>
     * <p>
     * Each <code>PutRecords</code> request can support up to 500 records. Each
     * record in the request can be as large as 1 MB, up to a limit of 5 MB for
     * the entire request, including partition keys. Each shard can support
     * writes up to 1,000 records per second, up to a maximum data write total
     * of 1 MB per second.
     * </p>
     * <p>
     * You must specify the name of the stream that captures, stores, and
     * transports the data; and an array of request <code>Records</code>, with
     * each record in the array requiring a partition key and data blob. The
     * record size limit applies to the total size of the partition key and data
     * blob.
     * </p>
     * <p>
     * The data blob can be any type of data; for example, a segment from a log
     * file, geographic/location data, website clickstream data, and so on.
     * </p>
     * <p>
     * The partition key is used by Amazon Kinesis as input to a hash function
     * that maps the partition key and associated data to a specific shard. An
     * MD5 hash function is used to map partition keys to 128-bit integer values
     * and to map associated data records to shards. As a result of this hashing
     * mechanism, all data records with the same partition key map to the same
     * shard within the stream. For more information, see <a href=
     * "http://docs.aws.amazon.com/kinesis/latest/dev/developing-producers-with-sdk.html#kinesis-using-sdk-java-add-data-to-stream"
     * >Adding Data to a Stream</a> in the <i>Amazon Kinesis Streams Developer
     * Guide</i>.
     * </p>
     * <p>
     * Each record in the <code>Records</code> array may include an optional
     * parameter, <code>ExplicitHashKey</code>, which overrides the partition
     * key to shard mapping. This parameter allows a data producer to determine
     * explicitly the shard where the record is stored. For more information,
     * see <a href=
     * "http://docs.aws.amazon.com/kinesis/latest/dev/developing-producers-with-sdk.html#kinesis-using-sdk-java-putrecords"
     * >Adding Multiple Records with PutRecords</a> in the <i>Amazon Kinesis
     * Streams Developer Guide</i>.
     * </p>
     * <p>
     * The <code>PutRecords</code> response includes an array of response
     * <code>Records</code>. Each record in the response array directly
     * correlates with a record in the request array using natural ordering,
     * from the top to the bottom of the request and response. The response
     * <code>Records</code> array always includes the same number of records as
     * the request array.
     * </p>
     * <p>
     * The response <code>Records</code> array includes both successfully and
     * unsuccessfully processed records. Amazon Kinesis attempts to process all
     * records in each <code>PutRecords</code> request. A single record failure
     * does not stop the processing of subsequent records.
     * </p>
     * <p>
     * A successfully-processed record includes <code>ShardId</code> and
     * <code>SequenceNumber</code> values. The <code>ShardId</code> parameter
     * identifies the shard in the stream where the record is stored. The
     * <code>SequenceNumber</code> parameter is an identifier assigned to the
     * put record, unique to all records in the stream.
     * </p>
     * <p>
     * An unsuccessfully-processed record includes <code>ErrorCode</code> and
     * <code>ErrorMessage</code> values. <code>ErrorCode</code> reflects the
     * type of error and can be one of the following values:
     * <code>ProvisionedThroughputExceededException</code> or
     * <code>InternalFailure</code>. <code>ErrorMessage</code> provides more
     * detailed information about the
     * <code>ProvisionedThroughputExceededException</code> exception including
     * the account ID, stream name, and shard ID of the record that was
     * throttled. For more information about partially successful responses, see
     * <a href=
     * "http://docs.aws.amazon.com/kinesis/latest/dev/kinesis-using-sdk-java-add-data-to-stream.html#kinesis-using-sdk-java-putrecords"
     * >Adding Multiple Records with PutRecords</a> in the <i>Amazon Kinesis
     * Streams Developer Guide</i>.
     * </p>
     * <p>
     * By default, data records are accessible for only 24 hours from the time
     * that they are added to an Amazon Kinesis stream. This retention period
     * can be modified using the <a>DecreaseStreamRetentionPeriod</a> and
     * <a>IncreaseStreamRetentionPeriod</a> operations.
     * </p>
     * 
     * @param putRecordsRequest
     *        A <code>PutRecords</code> request.
     * @return Result of the PutRecords operation returned by the service.
     * @throws ResourceNotFoundException
     *         The requested resource could not be found. The stream might not
     *         be specified correctly, or it might not be in the
     *         <code>ACTIVE</code> state if the operation requires it.
     * @throws InvalidArgumentException
     *         A specified parameter exceeds its restrictions, is not supported,
     *         or can't be used. For more information, see the returned message.
     * @throws ProvisionedThroughputExceededException
     *         The request rate for the stream is too high, or the requested
     *         data is too large for the available throughput. Reduce the
     *         frequency or size of your requests. For more information, see <a
     *         href=
     *         "http://docs.aws.amazon.com/kinesis/latest/dev/service-sizes-and-limits.html"
     *         >Streams Limits</a> in the <i>Amazon Kinesis Streams Developer
     *         Guide</i>, and <a href=
     *         "http://docs.aws.amazon.com/general/latest/gr/api-retries.html"
     *         >Error Retries and Exponential Backoff in AWS</a> in the <i>AWS
     *         General Reference</i>.
     * @sample AmazonKinesis.PutRecords
     */
    PutRecordsResult putRecords(PutRecordsRequest putRecordsRequest);

    /**
     * <p>
     * Removes tags from the specified Amazon Kinesis stream. Removed tags are
     * deleted and cannot be recovered after this operation successfully
     * completes.
     * </p>
     * <p>
     * If you specify a tag that does not exist, it is ignored.
     * </p>
     * 
     * @param removeTagsFromStreamRequest
     *        Represents the input for <code>RemoveTagsFromStream</code>.
     * @throws ResourceNotFoundException
     *         The requested resource could not be found. The stream might not
     *         be specified correctly, or it might not be in the
     *         <code>ACTIVE</code> state if the operation requires it.
     * @throws ResourceInUseException
     *         The resource is not available for this operation. For successful
     *         operation, the resource needs to be in the <code>ACTIVE</code>
     *         state.
     * @throws InvalidArgumentException
     *         A specified parameter exceeds its restrictions, is not supported,
     *         or can't be used. For more information, see the returned message.
     * @throws LimitExceededException
     *         The requested resource exceeds the maximum number allowed, or the
     *         number of concurrent stream requests exceeds the maximum number
     *         allowed (5).
     * @sample AmazonKinesis.RemoveTagsFromStream
     */
    void removeTagsFromStream(
            RemoveTagsFromStreamRequest removeTagsFromStreamRequest);

    /**
     * <p>
     * Splits a shard into two new shards in the Amazon Kinesis stream to
     * increase the stream's capacity to ingest and transport data.
     * <code>SplitShard</code> is called when there is a need to increase the
     * overall capacity of a stream because of an expected increase in the
     * volume of data records being ingested.
     * </p>
     * <p>
     * You can also use <code>SplitShard</code> when a shard appears to be
     * approaching its maximum utilization; for example, the producers sending
     * data into the specific shard are suddenly sending more than previously
     * anticipated. You can also call <code>SplitShard</code> to increase stream
     * capacity, so that more Amazon Kinesis applications can simultaneously
     * read data from the stream for real-time processing.
     * </p>
     * <p>
     * You must specify the shard to be split and the new hash key, which is the
     * position in the shard where the shard gets split in two. In many cases,
     * the new hash key might simply be the average of the beginning and ending
     * hash key, but it can be any hash key value in the range being mapped into
     * the shard. For more information about splitting shards, see <a href=
     * "http://docs.aws.amazon.com/kinesis/latest/dev/kinesis-using-sdk-java-resharding-split.html"
     * >Split a Shard</a> in the <i>Amazon Kinesis Streams Developer Guide</i>.
     * </p>
     * <p>
     * You can use <a>DescribeStream</a> to determine the shard ID and hash key
     * values for the <code>ShardToSplit</code> and
     * <code>NewStartingHashKey</code> parameters that are specified in the
     * <code>SplitShard</code> request.
     * </p>
     * <p>
     * <code>SplitShard</code> is an asynchronous operation. Upon receiving a
     * <code>SplitShard</code> request, Amazon Kinesis immediately returns a
     * response and sets the stream status to <code>UPDATING</code>. After the
     * operation is completed, Amazon Kinesis sets the stream status to
     * <code>ACTIVE</code>. Read and write operations continue to work while the
     * stream is in the <code>UPDATING</code> state.
     * </p>
     * <p>
     * You can use <code>DescribeStream</code> to check the status of the
     * stream, which is returned in <code>StreamStatus</code>. If the stream is
     * in the <code>ACTIVE</code> state, you can call <code>SplitShard</code>.
     * If a stream is in <code>CREATING</code> or <code>UPDATING</code> or
     * <code>DELETING</code> states, <code>DescribeStream</code> returns a
     * <code>ResourceInUseException</code>.
     * </p>
     * <p>
     * If the specified stream does not exist, <code>DescribeStream</code>
     * returns a <code>ResourceNotFoundException</code>. If you try to create
     * more shards than are authorized for your account, you receive a
     * <code>LimitExceededException</code>.
     * </p>
     * <p>
     * For the default shard limit for an AWS account, see <a href=
     * "http://docs.aws.amazon.com/kinesis/latest/dev/service-sizes-and-limits.html"
     * >Streams Limits</a> in the <i>Amazon Kinesis Streams Developer Guide</i>.
     * If you need to increase this limit, <a href=
     * "http://docs.aws.amazon.com/general/latest/gr/aws_service_limits.html"
     * >contact AWS Support</a>.
     * </p>
     * <p>
     * If you try to operate on too many streams simultaneously using
     * <a>CreateStream</a>, <a>DeleteStream</a>, <a>MergeShards</a>, and/or
     * <a>SplitShard</a>, you receive a <code>LimitExceededException</code>.
     * </p>
     * <p>
     * <code>SplitShard</code> has limit of 5 transactions per second per
     * account.
     * </p>
     * 
     * @param splitShardRequest
     *        Represents the input for <code>SplitShard</code>.
     * @throws ResourceNotFoundException
     *         The requested resource could not be found. The stream might not
     *         be specified correctly, or it might not be in the
     *         <code>ACTIVE</code> state if the operation requires it.
     * @throws ResourceInUseException
     *         The resource is not available for this operation. For successful
     *         operation, the resource needs to be in the <code>ACTIVE</code>
     *         state.
     * @throws InvalidArgumentException
     *         A specified parameter exceeds its restrictions, is not supported,
     *         or can't be used. For more information, see the returned message.
     * @throws LimitExceededException
     *         The requested resource exceeds the maximum number allowed, or the
     *         number of concurrent stream requests exceeds the maximum number
     *         allowed (5).
     * @sample AmazonKinesis.SplitShard
     */
    void splitShard(SplitShardRequest splitShardRequest);

    /**
     * Simplified method form for invoking the SplitShard operation.
     *
     * @see #splitShard(SplitShardRequest)
     */
    void splitShard(String streamName, String shardToSplit,
            String newStartingHashKey);

    /**
     * Shuts down this client object, releasing any resources that might be held
     * open. This is an optional method, and callers are not expected to call
     * it, but can if they want to explicitly release any open resources. Once a
     * client has been shutdown, it should not be used to make any more
     * requests.
     */
    void shutdown();

    /**
     * Returns additional metadata for a previously executed successful request,
     * typically used for debugging issues where a service isn't acting as
     * expected. This data isn't considered part of the result data returned by
     * an operation, so it's available through this separate, diagnostic
     * interface.
     * <p>
     * Response metadata is only cached for a limited period of time, so if you
     * need to access this extra diagnostic information for an executed request,
     * you should use this method to retrieve it as soon as possible after
     * executing a request.
     *
     * @param request
     *        The originally executed request.
     *
     * @return The response metadata for the specified request, or null if none
     *         is available.
     */
    ResponseMetadata getCachedResponseMetadata(AmazonWebServiceRequest request);
}
