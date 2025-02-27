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
package com.amazonaws.services.cognitoidp;

import com.amazonaws.services.cognitoidp.model.*;
import com.amazonaws.annotation.ThreadSafe;

/**
 * Interface for accessing Amazon Cognito Identity Provider asynchronously. Each
 * asynchronous method will return a Java Future object representing the
 * asynchronous operation; overloads which accept an {@code AsyncHandler} can be
 * used to receive notification when an asynchronous operation completes.
 * <p>
 * <p>
 * You can create a user pool in Amazon Cognito Identity to manage directories
 * and users. You can authenticate a user to obtain tokens related to user
 * identity and access policies.
 * </p>
 * <p>
 * This API reference provides information about user pools in Amazon Cognito
 * Identity, which is a new capability that is available as a beta.
 * </p>
 */
@ThreadSafe
public class AWSCognitoIdentityProviderAsyncClient extends
        AWSCognitoIdentityProviderClient implements
        AWSCognitoIdentityProviderAsync {

    private static final int DEFAULT_THREAD_POOL_SIZE = 50;

    private final java.util.concurrent.ExecutorService executorService;

    /**
     * Constructs a new asynchronous client to invoke service methods on Amazon
     * Cognito Identity Provider. A credentials provider chain will be used that
     * searches for credentials in this order:
     * <ul>
     * <li>Environment Variables - AWS_ACCESS_KEY_ID and AWS_SECRET_KEY</li>
     * <li>Java System Properties - aws.accessKeyId and aws.secretKey</li>
     * <li>Credential profiles file at the default location (~/.aws/credentials)
     * shared by all AWS SDKs and the AWS CLI</li>
     * <li>Instance profile credentials delivered through the Amazon EC2
     * metadata service</li>
     * </ul>
     * <p>
     * Asynchronous methods are delegated to a fixed-size thread pool containing
     * 50 threads (to match the default maximum number of concurrent connections
     * to the service).
     *
     * @see com.amazonaws.auth.DefaultAWSCredentialsProviderChain
     * @see java.util.concurrent.Executors#newFixedThreadPool(int)
     */
    public AWSCognitoIdentityProviderAsyncClient() {
        this(new com.amazonaws.auth.DefaultAWSCredentialsProviderChain());
    }

    /**
     * Constructs a new asynchronous client to invoke service methods on Amazon
     * Cognito Identity Provider. A credentials provider chain will be used that
     * searches for credentials in this order:
     * <ul>
     * <li>Environment Variables - AWS_ACCESS_KEY_ID and AWS_SECRET_KEY</li>
     * <li>Java System Properties - aws.accessKeyId and aws.secretKey</li>
     * <li>Credential profiles file at the default location (~/.aws/credentials)
     * shared by all AWS SDKs and the AWS CLI</li>
     * <li>Instance profile credentials delivered through the Amazon EC2
     * metadata service</li>
     * </ul>
     * <p>
     * Asynchronous methods are delegated to a fixed-size thread pool containing
     * a number of threads equal to the maximum number of concurrent connections
     * configured via {@code ClientConfiguration.getMaxConnections()}.
     *
     * @param clientConfiguration
     *        The client configuration options controlling how this client
     *        connects to Amazon Cognito Identity Provider (ex: proxy settings,
     *        retry counts, etc).
     *
     * @see com.amazonaws.auth.DefaultAWSCredentialsProviderChain
     * @see java.util.concurrent.Executors#newFixedThreadPool(int)
     */
    public AWSCognitoIdentityProviderAsyncClient(
            com.amazonaws.ClientConfiguration clientConfiguration) {
        this(new com.amazonaws.auth.DefaultAWSCredentialsProviderChain(),
                clientConfiguration, java.util.concurrent.Executors
                        .newFixedThreadPool(clientConfiguration
                                .getMaxConnections()));
    }

    /**
     * Constructs a new asynchronous client to invoke service methods on Amazon
     * Cognito Identity Provider using the specified AWS account credentials.
     * <p>
     * Asynchronous methods are delegated to a fixed-size thread pool containing
     * 50 threads (to match the default maximum number of concurrent connections
     * to the service).
     *
     * @param awsCredentials
     *        The AWS credentials (access key ID and secret key) to use when
     *        authenticating with AWS services.
     * @see java.util.concurrent.Executors#newFixedThreadPool(int)
     */
    public AWSCognitoIdentityProviderAsyncClient(
            com.amazonaws.auth.AWSCredentials awsCredentials) {
        this(awsCredentials, java.util.concurrent.Executors
                .newFixedThreadPool(DEFAULT_THREAD_POOL_SIZE));
    }

    /**
     * Constructs a new asynchronous client to invoke service methods on Amazon
     * Cognito Identity Provider using the specified AWS account credentials and
     * executor service. Default client settings will be used.
     *
     * @param awsCredentials
     *        The AWS credentials (access key ID and secret key) to use when
     *        authenticating with AWS services.
     * @param executorService
     *        The executor service by which all asynchronous requests will be
     *        executed.
     */
    public AWSCognitoIdentityProviderAsyncClient(
            com.amazonaws.auth.AWSCredentials awsCredentials,
            java.util.concurrent.ExecutorService executorService) {

        this(awsCredentials, configFactory.getConfig(), executorService);
    }

    /**
     * Constructs a new asynchronous client to invoke service methods on Amazon
     * Cognito Identity Provider using the specified AWS account credentials,
     * executor service, and client configuration options.
     *
     * @param awsCredentials
     *        The AWS credentials (access key ID and secret key) to use when
     *        authenticating with AWS services.
     * @param clientConfiguration
     *        Client configuration options (ex: max retry limit, proxy settings,
     *        etc).
     * @param executorService
     *        The executor service by which all asynchronous requests will be
     *        executed.
     */
    public AWSCognitoIdentityProviderAsyncClient(
            com.amazonaws.auth.AWSCredentials awsCredentials,
            com.amazonaws.ClientConfiguration clientConfiguration,
            java.util.concurrent.ExecutorService executorService) {

        super(awsCredentials, clientConfiguration);
        this.executorService = executorService;
    }

    /**
     * Constructs a new asynchronous client to invoke service methods on Amazon
     * Cognito Identity Provider using the specified AWS account credentials
     * provider. Default client settings will be used.
     * <p>
     * Asynchronous methods are delegated to a fixed-size thread pool containing
     * 50 threads (to match the default maximum number of concurrent connections
     * to the service).
     *
     * @param awsCredentialsProvider
     *        The AWS credentials provider which will provide credentials to
     *        authenticate requests with AWS services.
     * @see java.util.concurrent.Executors#newFixedThreadPool(int)
     */
    public AWSCognitoIdentityProviderAsyncClient(
            com.amazonaws.auth.AWSCredentialsProvider awsCredentialsProvider) {
        this(awsCredentialsProvider, java.util.concurrent.Executors
                .newFixedThreadPool(DEFAULT_THREAD_POOL_SIZE));
    }

    /**
     * Constructs a new asynchronous client to invoke service methods on Amazon
     * Cognito Identity Provider using the provided AWS account credentials
     * provider and client configuration options.
     * <p>
     * Asynchronous methods are delegated to a fixed-size thread pool containing
     * a number of threads equal to the maximum number of concurrent connections
     * configured via {@code ClientConfiguration.getMaxConnections()}.
     *
     * @param awsCredentialsProvider
     *        The AWS credentials provider which will provide credentials to
     *        authenticate requests with AWS services.
     * @param clientConfiguration
     *        Client configuration options (ex: max retry limit, proxy settings,
     *        etc).
     *
     * @see com.amazonaws.auth.DefaultAWSCredentialsProviderChain
     * @see java.util.concurrent.Executors#newFixedThreadPool(int)
     */
    public AWSCognitoIdentityProviderAsyncClient(
            com.amazonaws.auth.AWSCredentialsProvider awsCredentialsProvider,
            com.amazonaws.ClientConfiguration clientConfiguration) {

        this(awsCredentialsProvider, clientConfiguration,
                java.util.concurrent.Executors
                        .newFixedThreadPool(clientConfiguration
                                .getMaxConnections()));
    }

    /**
     * Constructs a new asynchronous client to invoke service methods on Amazon
     * Cognito Identity Provider using the specified AWS account credentials
     * provider and executor service. Default client settings will be used.
     *
     * @param awsCredentialsProvider
     *        The AWS credentials provider which will provide credentials to
     *        authenticate requests with AWS services.
     * @param executorService
     *        The executor service by which all asynchronous requests will be
     *        executed.
     */
    public AWSCognitoIdentityProviderAsyncClient(
            com.amazonaws.auth.AWSCredentialsProvider awsCredentialsProvider,
            java.util.concurrent.ExecutorService executorService) {

        this(awsCredentialsProvider, configFactory.getConfig(), executorService);
    }

    /**
     * Constructs a new asynchronous client to invoke service methods on Amazon
     * Cognito Identity Provider using the specified AWS account credentials
     * provider, executor service, and client configuration options.
     *
     * @param awsCredentialsProvider
     *        The AWS credentials provider which will provide credentials to
     *        authenticate requests with AWS services.
     * @param clientConfiguration
     *        Client configuration options (ex: max retry limit, proxy settings,
     *        etc).
     * @param executorService
     *        The executor service by which all asynchronous requests will be
     *        executed.
     */
    public AWSCognitoIdentityProviderAsyncClient(
            com.amazonaws.auth.AWSCredentialsProvider awsCredentialsProvider,
            com.amazonaws.ClientConfiguration clientConfiguration,
            java.util.concurrent.ExecutorService executorService) {

        super(awsCredentialsProvider, clientConfiguration);
        this.executorService = executorService;
    }

    /**
     * Returns the executor service used by this client to execute async
     * requests.
     *
     * @return The executor service used by this client to execute async
     *         requests.
     */
    public java.util.concurrent.ExecutorService getExecutorService() {
        return executorService;
    }

    @Override
    public java.util.concurrent.Future<AddCustomAttributesResult> addCustomAttributesAsync(
            AddCustomAttributesRequest request) {

        return addCustomAttributesAsync(request, null);
    }

    @Override
    public java.util.concurrent.Future<AddCustomAttributesResult> addCustomAttributesAsync(
            final AddCustomAttributesRequest request,
            final com.amazonaws.handlers.AsyncHandler<AddCustomAttributesRequest, AddCustomAttributesResult> asyncHandler) {

        return executorService
                .submit(new java.util.concurrent.Callable<AddCustomAttributesResult>() {
                    @Override
                    public AddCustomAttributesResult call() throws Exception {
                        AddCustomAttributesResult result;

                        try {
                            result = addCustomAttributes(request);
                        } catch (Exception ex) {
                            if (asyncHandler != null) {
                                asyncHandler.onError(ex);
                            }
                            throw ex;
                        }

                        if (asyncHandler != null) {
                            asyncHandler.onSuccess(request, result);
                        }
                        return result;
                    }
                });
    }

    @Override
    public java.util.concurrent.Future<AdminConfirmSignUpResult> adminConfirmSignUpAsync(
            AdminConfirmSignUpRequest request) {

        return adminConfirmSignUpAsync(request, null);
    }

    @Override
    public java.util.concurrent.Future<AdminConfirmSignUpResult> adminConfirmSignUpAsync(
            final AdminConfirmSignUpRequest request,
            final com.amazonaws.handlers.AsyncHandler<AdminConfirmSignUpRequest, AdminConfirmSignUpResult> asyncHandler) {

        return executorService
                .submit(new java.util.concurrent.Callable<AdminConfirmSignUpResult>() {
                    @Override
                    public AdminConfirmSignUpResult call() throws Exception {
                        AdminConfirmSignUpResult result;

                        try {
                            result = adminConfirmSignUp(request);
                        } catch (Exception ex) {
                            if (asyncHandler != null) {
                                asyncHandler.onError(ex);
                            }
                            throw ex;
                        }

                        if (asyncHandler != null) {
                            asyncHandler.onSuccess(request, result);
                        }
                        return result;
                    }
                });
    }

    @Override
    public java.util.concurrent.Future<Void> adminDeleteUserAsync(
            AdminDeleteUserRequest request) {

        return adminDeleteUserAsync(request, null);
    }

    @Override
    public java.util.concurrent.Future<Void> adminDeleteUserAsync(
            final AdminDeleteUserRequest request,
            final com.amazonaws.handlers.AsyncHandler<AdminDeleteUserRequest, Void> asyncHandler) {

        return executorService
                .submit(new java.util.concurrent.Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        Void result;

                        try {
                            adminDeleteUser(request);
                            result = null;
                        } catch (Exception ex) {
                            if (asyncHandler != null) {
                                asyncHandler.onError(ex);
                            }
                            throw ex;
                        }

                        if (asyncHandler != null) {
                            asyncHandler.onSuccess(request, result);
                        }
                        return result;
                    }
                });
    }

    @Override
    public java.util.concurrent.Future<AdminDeleteUserAttributesResult> adminDeleteUserAttributesAsync(
            AdminDeleteUserAttributesRequest request) {

        return adminDeleteUserAttributesAsync(request, null);
    }

    @Override
    public java.util.concurrent.Future<AdminDeleteUserAttributesResult> adminDeleteUserAttributesAsync(
            final AdminDeleteUserAttributesRequest request,
            final com.amazonaws.handlers.AsyncHandler<AdminDeleteUserAttributesRequest, AdminDeleteUserAttributesResult> asyncHandler) {

        return executorService
                .submit(new java.util.concurrent.Callable<AdminDeleteUserAttributesResult>() {
                    @Override
                    public AdminDeleteUserAttributesResult call()
                            throws Exception {
                        AdminDeleteUserAttributesResult result;

                        try {
                            result = adminDeleteUserAttributes(request);
                        } catch (Exception ex) {
                            if (asyncHandler != null) {
                                asyncHandler.onError(ex);
                            }
                            throw ex;
                        }

                        if (asyncHandler != null) {
                            asyncHandler.onSuccess(request, result);
                        }
                        return result;
                    }
                });
    }

    @Override
    public java.util.concurrent.Future<AdminDisableUserResult> adminDisableUserAsync(
            AdminDisableUserRequest request) {

        return adminDisableUserAsync(request, null);
    }

    @Override
    public java.util.concurrent.Future<AdminDisableUserResult> adminDisableUserAsync(
            final AdminDisableUserRequest request,
            final com.amazonaws.handlers.AsyncHandler<AdminDisableUserRequest, AdminDisableUserResult> asyncHandler) {

        return executorService
                .submit(new java.util.concurrent.Callable<AdminDisableUserResult>() {
                    @Override
                    public AdminDisableUserResult call() throws Exception {
                        AdminDisableUserResult result;

                        try {
                            result = adminDisableUser(request);
                        } catch (Exception ex) {
                            if (asyncHandler != null) {
                                asyncHandler.onError(ex);
                            }
                            throw ex;
                        }

                        if (asyncHandler != null) {
                            asyncHandler.onSuccess(request, result);
                        }
                        return result;
                    }
                });
    }

    @Override
    public java.util.concurrent.Future<AdminEnableUserResult> adminEnableUserAsync(
            AdminEnableUserRequest request) {

        return adminEnableUserAsync(request, null);
    }

    @Override
    public java.util.concurrent.Future<AdminEnableUserResult> adminEnableUserAsync(
            final AdminEnableUserRequest request,
            final com.amazonaws.handlers.AsyncHandler<AdminEnableUserRequest, AdminEnableUserResult> asyncHandler) {

        return executorService
                .submit(new java.util.concurrent.Callable<AdminEnableUserResult>() {
                    @Override
                    public AdminEnableUserResult call() throws Exception {
                        AdminEnableUserResult result;

                        try {
                            result = adminEnableUser(request);
                        } catch (Exception ex) {
                            if (asyncHandler != null) {
                                asyncHandler.onError(ex);
                            }
                            throw ex;
                        }

                        if (asyncHandler != null) {
                            asyncHandler.onSuccess(request, result);
                        }
                        return result;
                    }
                });
    }

    @Override
    public java.util.concurrent.Future<AdminGetUserResult> adminGetUserAsync(
            AdminGetUserRequest request) {

        return adminGetUserAsync(request, null);
    }

    @Override
    public java.util.concurrent.Future<AdminGetUserResult> adminGetUserAsync(
            final AdminGetUserRequest request,
            final com.amazonaws.handlers.AsyncHandler<AdminGetUserRequest, AdminGetUserResult> asyncHandler) {

        return executorService
                .submit(new java.util.concurrent.Callable<AdminGetUserResult>() {
                    @Override
                    public AdminGetUserResult call() throws Exception {
                        AdminGetUserResult result;

                        try {
                            result = adminGetUser(request);
                        } catch (Exception ex) {
                            if (asyncHandler != null) {
                                asyncHandler.onError(ex);
                            }
                            throw ex;
                        }

                        if (asyncHandler != null) {
                            asyncHandler.onSuccess(request, result);
                        }
                        return result;
                    }
                });
    }

    @Override
    public java.util.concurrent.Future<AdminResetUserPasswordResult> adminResetUserPasswordAsync(
            AdminResetUserPasswordRequest request) {

        return adminResetUserPasswordAsync(request, null);
    }

    @Override
    public java.util.concurrent.Future<AdminResetUserPasswordResult> adminResetUserPasswordAsync(
            final AdminResetUserPasswordRequest request,
            final com.amazonaws.handlers.AsyncHandler<AdminResetUserPasswordRequest, AdminResetUserPasswordResult> asyncHandler) {

        return executorService
                .submit(new java.util.concurrent.Callable<AdminResetUserPasswordResult>() {
                    @Override
                    public AdminResetUserPasswordResult call() throws Exception {
                        AdminResetUserPasswordResult result;

                        try {
                            result = adminResetUserPassword(request);
                        } catch (Exception ex) {
                            if (asyncHandler != null) {
                                asyncHandler.onError(ex);
                            }
                            throw ex;
                        }

                        if (asyncHandler != null) {
                            asyncHandler.onSuccess(request, result);
                        }
                        return result;
                    }
                });
    }

    @Override
    public java.util.concurrent.Future<AdminSetUserSettingsResult> adminSetUserSettingsAsync(
            AdminSetUserSettingsRequest request) {

        return adminSetUserSettingsAsync(request, null);
    }

    @Override
    public java.util.concurrent.Future<AdminSetUserSettingsResult> adminSetUserSettingsAsync(
            final AdminSetUserSettingsRequest request,
            final com.amazonaws.handlers.AsyncHandler<AdminSetUserSettingsRequest, AdminSetUserSettingsResult> asyncHandler) {

        return executorService
                .submit(new java.util.concurrent.Callable<AdminSetUserSettingsResult>() {
                    @Override
                    public AdminSetUserSettingsResult call() throws Exception {
                        AdminSetUserSettingsResult result;

                        try {
                            result = adminSetUserSettings(request);
                        } catch (Exception ex) {
                            if (asyncHandler != null) {
                                asyncHandler.onError(ex);
                            }
                            throw ex;
                        }

                        if (asyncHandler != null) {
                            asyncHandler.onSuccess(request, result);
                        }
                        return result;
                    }
                });
    }

    @Override
    public java.util.concurrent.Future<AdminUpdateUserAttributesResult> adminUpdateUserAttributesAsync(
            AdminUpdateUserAttributesRequest request) {

        return adminUpdateUserAttributesAsync(request, null);
    }

    @Override
    public java.util.concurrent.Future<AdminUpdateUserAttributesResult> adminUpdateUserAttributesAsync(
            final AdminUpdateUserAttributesRequest request,
            final com.amazonaws.handlers.AsyncHandler<AdminUpdateUserAttributesRequest, AdminUpdateUserAttributesResult> asyncHandler) {

        return executorService
                .submit(new java.util.concurrent.Callable<AdminUpdateUserAttributesResult>() {
                    @Override
                    public AdminUpdateUserAttributesResult call()
                            throws Exception {
                        AdminUpdateUserAttributesResult result;

                        try {
                            result = adminUpdateUserAttributes(request);
                        } catch (Exception ex) {
                            if (asyncHandler != null) {
                                asyncHandler.onError(ex);
                            }
                            throw ex;
                        }

                        if (asyncHandler != null) {
                            asyncHandler.onSuccess(request, result);
                        }
                        return result;
                    }
                });
    }

    @Override
    public java.util.concurrent.Future<ChangePasswordResult> changePasswordAsync(
            ChangePasswordRequest request) {

        return changePasswordAsync(request, null);
    }

    @Override
    public java.util.concurrent.Future<ChangePasswordResult> changePasswordAsync(
            final ChangePasswordRequest request,
            final com.amazonaws.handlers.AsyncHandler<ChangePasswordRequest, ChangePasswordResult> asyncHandler) {

        return executorService
                .submit(new java.util.concurrent.Callable<ChangePasswordResult>() {
                    @Override
                    public ChangePasswordResult call() throws Exception {
                        ChangePasswordResult result;

                        try {
                            result = changePassword(request);
                        } catch (Exception ex) {
                            if (asyncHandler != null) {
                                asyncHandler.onError(ex);
                            }
                            throw ex;
                        }

                        if (asyncHandler != null) {
                            asyncHandler.onSuccess(request, result);
                        }
                        return result;
                    }
                });
    }

    @Override
    public java.util.concurrent.Future<ConfirmForgotPasswordResult> confirmForgotPasswordAsync(
            ConfirmForgotPasswordRequest request) {

        return confirmForgotPasswordAsync(request, null);
    }

    @Override
    public java.util.concurrent.Future<ConfirmForgotPasswordResult> confirmForgotPasswordAsync(
            final ConfirmForgotPasswordRequest request,
            final com.amazonaws.handlers.AsyncHandler<ConfirmForgotPasswordRequest, ConfirmForgotPasswordResult> asyncHandler) {

        return executorService
                .submit(new java.util.concurrent.Callable<ConfirmForgotPasswordResult>() {
                    @Override
                    public ConfirmForgotPasswordResult call() throws Exception {
                        ConfirmForgotPasswordResult result;

                        try {
                            result = confirmForgotPassword(request);
                        } catch (Exception ex) {
                            if (asyncHandler != null) {
                                asyncHandler.onError(ex);
                            }
                            throw ex;
                        }

                        if (asyncHandler != null) {
                            asyncHandler.onSuccess(request, result);
                        }
                        return result;
                    }
                });
    }

    @Override
    public java.util.concurrent.Future<ConfirmSignUpResult> confirmSignUpAsync(
            ConfirmSignUpRequest request) {

        return confirmSignUpAsync(request, null);
    }

    @Override
    public java.util.concurrent.Future<ConfirmSignUpResult> confirmSignUpAsync(
            final ConfirmSignUpRequest request,
            final com.amazonaws.handlers.AsyncHandler<ConfirmSignUpRequest, ConfirmSignUpResult> asyncHandler) {

        return executorService
                .submit(new java.util.concurrent.Callable<ConfirmSignUpResult>() {
                    @Override
                    public ConfirmSignUpResult call() throws Exception {
                        ConfirmSignUpResult result;

                        try {
                            result = confirmSignUp(request);
                        } catch (Exception ex) {
                            if (asyncHandler != null) {
                                asyncHandler.onError(ex);
                            }
                            throw ex;
                        }

                        if (asyncHandler != null) {
                            asyncHandler.onSuccess(request, result);
                        }
                        return result;
                    }
                });
    }

    @Override
    public java.util.concurrent.Future<CreateUserPoolResult> createUserPoolAsync(
            CreateUserPoolRequest request) {

        return createUserPoolAsync(request, null);
    }

    @Override
    public java.util.concurrent.Future<CreateUserPoolResult> createUserPoolAsync(
            final CreateUserPoolRequest request,
            final com.amazonaws.handlers.AsyncHandler<CreateUserPoolRequest, CreateUserPoolResult> asyncHandler) {

        return executorService
                .submit(new java.util.concurrent.Callable<CreateUserPoolResult>() {
                    @Override
                    public CreateUserPoolResult call() throws Exception {
                        CreateUserPoolResult result;

                        try {
                            result = createUserPool(request);
                        } catch (Exception ex) {
                            if (asyncHandler != null) {
                                asyncHandler.onError(ex);
                            }
                            throw ex;
                        }

                        if (asyncHandler != null) {
                            asyncHandler.onSuccess(request, result);
                        }
                        return result;
                    }
                });
    }

    @Override
    public java.util.concurrent.Future<CreateUserPoolClientResult> createUserPoolClientAsync(
            CreateUserPoolClientRequest request) {

        return createUserPoolClientAsync(request, null);
    }

    @Override
    public java.util.concurrent.Future<CreateUserPoolClientResult> createUserPoolClientAsync(
            final CreateUserPoolClientRequest request,
            final com.amazonaws.handlers.AsyncHandler<CreateUserPoolClientRequest, CreateUserPoolClientResult> asyncHandler) {

        return executorService
                .submit(new java.util.concurrent.Callable<CreateUserPoolClientResult>() {
                    @Override
                    public CreateUserPoolClientResult call() throws Exception {
                        CreateUserPoolClientResult result;

                        try {
                            result = createUserPoolClient(request);
                        } catch (Exception ex) {
                            if (asyncHandler != null) {
                                asyncHandler.onError(ex);
                            }
                            throw ex;
                        }

                        if (asyncHandler != null) {
                            asyncHandler.onSuccess(request, result);
                        }
                        return result;
                    }
                });
    }

    @Override
    public java.util.concurrent.Future<Void> deleteUserAsync(
            DeleteUserRequest request) {

        return deleteUserAsync(request, null);
    }

    @Override
    public java.util.concurrent.Future<Void> deleteUserAsync(
            final DeleteUserRequest request,
            final com.amazonaws.handlers.AsyncHandler<DeleteUserRequest, Void> asyncHandler) {

        return executorService
                .submit(new java.util.concurrent.Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        Void result;

                        try {
                            deleteUser(request);
                            result = null;
                        } catch (Exception ex) {
                            if (asyncHandler != null) {
                                asyncHandler.onError(ex);
                            }
                            throw ex;
                        }

                        if (asyncHandler != null) {
                            asyncHandler.onSuccess(request, result);
                        }
                        return result;
                    }
                });
    }

    @Override
    public java.util.concurrent.Future<DeleteUserAttributesResult> deleteUserAttributesAsync(
            DeleteUserAttributesRequest request) {

        return deleteUserAttributesAsync(request, null);
    }

    @Override
    public java.util.concurrent.Future<DeleteUserAttributesResult> deleteUserAttributesAsync(
            final DeleteUserAttributesRequest request,
            final com.amazonaws.handlers.AsyncHandler<DeleteUserAttributesRequest, DeleteUserAttributesResult> asyncHandler) {

        return executorService
                .submit(new java.util.concurrent.Callable<DeleteUserAttributesResult>() {
                    @Override
                    public DeleteUserAttributesResult call() throws Exception {
                        DeleteUserAttributesResult result;

                        try {
                            result = deleteUserAttributes(request);
                        } catch (Exception ex) {
                            if (asyncHandler != null) {
                                asyncHandler.onError(ex);
                            }
                            throw ex;
                        }

                        if (asyncHandler != null) {
                            asyncHandler.onSuccess(request, result);
                        }
                        return result;
                    }
                });
    }

    @Override
    public java.util.concurrent.Future<Void> deleteUserPoolAsync(
            DeleteUserPoolRequest request) {

        return deleteUserPoolAsync(request, null);
    }

    @Override
    public java.util.concurrent.Future<Void> deleteUserPoolAsync(
            final DeleteUserPoolRequest request,
            final com.amazonaws.handlers.AsyncHandler<DeleteUserPoolRequest, Void> asyncHandler) {

        return executorService
                .submit(new java.util.concurrent.Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        Void result;

                        try {
                            deleteUserPool(request);
                            result = null;
                        } catch (Exception ex) {
                            if (asyncHandler != null) {
                                asyncHandler.onError(ex);
                            }
                            throw ex;
                        }

                        if (asyncHandler != null) {
                            asyncHandler.onSuccess(request, result);
                        }
                        return result;
                    }
                });
    }

    @Override
    public java.util.concurrent.Future<Void> deleteUserPoolClientAsync(
            DeleteUserPoolClientRequest request) {

        return deleteUserPoolClientAsync(request, null);
    }

    @Override
    public java.util.concurrent.Future<Void> deleteUserPoolClientAsync(
            final DeleteUserPoolClientRequest request,
            final com.amazonaws.handlers.AsyncHandler<DeleteUserPoolClientRequest, Void> asyncHandler) {

        return executorService
                .submit(new java.util.concurrent.Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        Void result;

                        try {
                            deleteUserPoolClient(request);
                            result = null;
                        } catch (Exception ex) {
                            if (asyncHandler != null) {
                                asyncHandler.onError(ex);
                            }
                            throw ex;
                        }

                        if (asyncHandler != null) {
                            asyncHandler.onSuccess(request, result);
                        }
                        return result;
                    }
                });
    }

    @Override
    public java.util.concurrent.Future<DescribeUserPoolResult> describeUserPoolAsync(
            DescribeUserPoolRequest request) {

        return describeUserPoolAsync(request, null);
    }

    @Override
    public java.util.concurrent.Future<DescribeUserPoolResult> describeUserPoolAsync(
            final DescribeUserPoolRequest request,
            final com.amazonaws.handlers.AsyncHandler<DescribeUserPoolRequest, DescribeUserPoolResult> asyncHandler) {

        return executorService
                .submit(new java.util.concurrent.Callable<DescribeUserPoolResult>() {
                    @Override
                    public DescribeUserPoolResult call() throws Exception {
                        DescribeUserPoolResult result;

                        try {
                            result = describeUserPool(request);
                        } catch (Exception ex) {
                            if (asyncHandler != null) {
                                asyncHandler.onError(ex);
                            }
                            throw ex;
                        }

                        if (asyncHandler != null) {
                            asyncHandler.onSuccess(request, result);
                        }
                        return result;
                    }
                });
    }

    @Override
    public java.util.concurrent.Future<DescribeUserPoolClientResult> describeUserPoolClientAsync(
            DescribeUserPoolClientRequest request) {

        return describeUserPoolClientAsync(request, null);
    }

    @Override
    public java.util.concurrent.Future<DescribeUserPoolClientResult> describeUserPoolClientAsync(
            final DescribeUserPoolClientRequest request,
            final com.amazonaws.handlers.AsyncHandler<DescribeUserPoolClientRequest, DescribeUserPoolClientResult> asyncHandler) {

        return executorService
                .submit(new java.util.concurrent.Callable<DescribeUserPoolClientResult>() {
                    @Override
                    public DescribeUserPoolClientResult call() throws Exception {
                        DescribeUserPoolClientResult result;

                        try {
                            result = describeUserPoolClient(request);
                        } catch (Exception ex) {
                            if (asyncHandler != null) {
                                asyncHandler.onError(ex);
                            }
                            throw ex;
                        }

                        if (asyncHandler != null) {
                            asyncHandler.onSuccess(request, result);
                        }
                        return result;
                    }
                });
    }

    @Override
    public java.util.concurrent.Future<ForgotPasswordResult> forgotPasswordAsync(
            ForgotPasswordRequest request) {

        return forgotPasswordAsync(request, null);
    }

    @Override
    public java.util.concurrent.Future<ForgotPasswordResult> forgotPasswordAsync(
            final ForgotPasswordRequest request,
            final com.amazonaws.handlers.AsyncHandler<ForgotPasswordRequest, ForgotPasswordResult> asyncHandler) {

        return executorService
                .submit(new java.util.concurrent.Callable<ForgotPasswordResult>() {
                    @Override
                    public ForgotPasswordResult call() throws Exception {
                        ForgotPasswordResult result;

                        try {
                            result = forgotPassword(request);
                        } catch (Exception ex) {
                            if (asyncHandler != null) {
                                asyncHandler.onError(ex);
                            }
                            throw ex;
                        }

                        if (asyncHandler != null) {
                            asyncHandler.onSuccess(request, result);
                        }
                        return result;
                    }
                });
    }

    @Override
    public java.util.concurrent.Future<GetUserResult> getUserAsync(
            GetUserRequest request) {

        return getUserAsync(request, null);
    }

    @Override
    public java.util.concurrent.Future<GetUserResult> getUserAsync(
            final GetUserRequest request,
            final com.amazonaws.handlers.AsyncHandler<GetUserRequest, GetUserResult> asyncHandler) {

        return executorService
                .submit(new java.util.concurrent.Callable<GetUserResult>() {
                    @Override
                    public GetUserResult call() throws Exception {
                        GetUserResult result;

                        try {
                            result = getUser(request);
                        } catch (Exception ex) {
                            if (asyncHandler != null) {
                                asyncHandler.onError(ex);
                            }
                            throw ex;
                        }

                        if (asyncHandler != null) {
                            asyncHandler.onSuccess(request, result);
                        }
                        return result;
                    }
                });
    }

    @Override
    public java.util.concurrent.Future<GetUserAttributeVerificationCodeResult> getUserAttributeVerificationCodeAsync(
            GetUserAttributeVerificationCodeRequest request) {

        return getUserAttributeVerificationCodeAsync(request, null);
    }

    @Override
    public java.util.concurrent.Future<GetUserAttributeVerificationCodeResult> getUserAttributeVerificationCodeAsync(
            final GetUserAttributeVerificationCodeRequest request,
            final com.amazonaws.handlers.AsyncHandler<GetUserAttributeVerificationCodeRequest, GetUserAttributeVerificationCodeResult> asyncHandler) {

        return executorService
                .submit(new java.util.concurrent.Callable<GetUserAttributeVerificationCodeResult>() {
                    @Override
                    public GetUserAttributeVerificationCodeResult call()
                            throws Exception {
                        GetUserAttributeVerificationCodeResult result;

                        try {
                            result = getUserAttributeVerificationCode(request);
                        } catch (Exception ex) {
                            if (asyncHandler != null) {
                                asyncHandler.onError(ex);
                            }
                            throw ex;
                        }

                        if (asyncHandler != null) {
                            asyncHandler.onSuccess(request, result);
                        }
                        return result;
                    }
                });
    }

    @Override
    public java.util.concurrent.Future<ListUserPoolClientsResult> listUserPoolClientsAsync(
            ListUserPoolClientsRequest request) {

        return listUserPoolClientsAsync(request, null);
    }

    @Override
    public java.util.concurrent.Future<ListUserPoolClientsResult> listUserPoolClientsAsync(
            final ListUserPoolClientsRequest request,
            final com.amazonaws.handlers.AsyncHandler<ListUserPoolClientsRequest, ListUserPoolClientsResult> asyncHandler) {

        return executorService
                .submit(new java.util.concurrent.Callable<ListUserPoolClientsResult>() {
                    @Override
                    public ListUserPoolClientsResult call() throws Exception {
                        ListUserPoolClientsResult result;

                        try {
                            result = listUserPoolClients(request);
                        } catch (Exception ex) {
                            if (asyncHandler != null) {
                                asyncHandler.onError(ex);
                            }
                            throw ex;
                        }

                        if (asyncHandler != null) {
                            asyncHandler.onSuccess(request, result);
                        }
                        return result;
                    }
                });
    }

    @Override
    public java.util.concurrent.Future<ListUserPoolsResult> listUserPoolsAsync(
            ListUserPoolsRequest request) {

        return listUserPoolsAsync(request, null);
    }

    @Override
    public java.util.concurrent.Future<ListUserPoolsResult> listUserPoolsAsync(
            final ListUserPoolsRequest request,
            final com.amazonaws.handlers.AsyncHandler<ListUserPoolsRequest, ListUserPoolsResult> asyncHandler) {

        return executorService
                .submit(new java.util.concurrent.Callable<ListUserPoolsResult>() {
                    @Override
                    public ListUserPoolsResult call() throws Exception {
                        ListUserPoolsResult result;

                        try {
                            result = listUserPools(request);
                        } catch (Exception ex) {
                            if (asyncHandler != null) {
                                asyncHandler.onError(ex);
                            }
                            throw ex;
                        }

                        if (asyncHandler != null) {
                            asyncHandler.onSuccess(request, result);
                        }
                        return result;
                    }
                });
    }

    @Override
    public java.util.concurrent.Future<ListUsersResult> listUsersAsync(
            ListUsersRequest request) {

        return listUsersAsync(request, null);
    }

    @Override
    public java.util.concurrent.Future<ListUsersResult> listUsersAsync(
            final ListUsersRequest request,
            final com.amazonaws.handlers.AsyncHandler<ListUsersRequest, ListUsersResult> asyncHandler) {

        return executorService
                .submit(new java.util.concurrent.Callable<ListUsersResult>() {
                    @Override
                    public ListUsersResult call() throws Exception {
                        ListUsersResult result;

                        try {
                            result = listUsers(request);
                        } catch (Exception ex) {
                            if (asyncHandler != null) {
                                asyncHandler.onError(ex);
                            }
                            throw ex;
                        }

                        if (asyncHandler != null) {
                            asyncHandler.onSuccess(request, result);
                        }
                        return result;
                    }
                });
    }

    @Override
    public java.util.concurrent.Future<ResendConfirmationCodeResult> resendConfirmationCodeAsync(
            ResendConfirmationCodeRequest request) {

        return resendConfirmationCodeAsync(request, null);
    }

    @Override
    public java.util.concurrent.Future<ResendConfirmationCodeResult> resendConfirmationCodeAsync(
            final ResendConfirmationCodeRequest request,
            final com.amazonaws.handlers.AsyncHandler<ResendConfirmationCodeRequest, ResendConfirmationCodeResult> asyncHandler) {

        return executorService
                .submit(new java.util.concurrent.Callable<ResendConfirmationCodeResult>() {
                    @Override
                    public ResendConfirmationCodeResult call() throws Exception {
                        ResendConfirmationCodeResult result;

                        try {
                            result = resendConfirmationCode(request);
                        } catch (Exception ex) {
                            if (asyncHandler != null) {
                                asyncHandler.onError(ex);
                            }
                            throw ex;
                        }

                        if (asyncHandler != null) {
                            asyncHandler.onSuccess(request, result);
                        }
                        return result;
                    }
                });
    }

    @Override
    public java.util.concurrent.Future<SetUserSettingsResult> setUserSettingsAsync(
            SetUserSettingsRequest request) {

        return setUserSettingsAsync(request, null);
    }

    @Override
    public java.util.concurrent.Future<SetUserSettingsResult> setUserSettingsAsync(
            final SetUserSettingsRequest request,
            final com.amazonaws.handlers.AsyncHandler<SetUserSettingsRequest, SetUserSettingsResult> asyncHandler) {

        return executorService
                .submit(new java.util.concurrent.Callable<SetUserSettingsResult>() {
                    @Override
                    public SetUserSettingsResult call() throws Exception {
                        SetUserSettingsResult result;

                        try {
                            result = setUserSettings(request);
                        } catch (Exception ex) {
                            if (asyncHandler != null) {
                                asyncHandler.onError(ex);
                            }
                            throw ex;
                        }

                        if (asyncHandler != null) {
                            asyncHandler.onSuccess(request, result);
                        }
                        return result;
                    }
                });
    }

    @Override
    public java.util.concurrent.Future<SignUpResult> signUpAsync(
            SignUpRequest request) {

        return signUpAsync(request, null);
    }

    @Override
    public java.util.concurrent.Future<SignUpResult> signUpAsync(
            final SignUpRequest request,
            final com.amazonaws.handlers.AsyncHandler<SignUpRequest, SignUpResult> asyncHandler) {

        return executorService
                .submit(new java.util.concurrent.Callable<SignUpResult>() {
                    @Override
                    public SignUpResult call() throws Exception {
                        SignUpResult result;

                        try {
                            result = signUp(request);
                        } catch (Exception ex) {
                            if (asyncHandler != null) {
                                asyncHandler.onError(ex);
                            }
                            throw ex;
                        }

                        if (asyncHandler != null) {
                            asyncHandler.onSuccess(request, result);
                        }
                        return result;
                    }
                });
    }

    @Override
    public java.util.concurrent.Future<UpdateUserAttributesResult> updateUserAttributesAsync(
            UpdateUserAttributesRequest request) {

        return updateUserAttributesAsync(request, null);
    }

    @Override
    public java.util.concurrent.Future<UpdateUserAttributesResult> updateUserAttributesAsync(
            final UpdateUserAttributesRequest request,
            final com.amazonaws.handlers.AsyncHandler<UpdateUserAttributesRequest, UpdateUserAttributesResult> asyncHandler) {

        return executorService
                .submit(new java.util.concurrent.Callable<UpdateUserAttributesResult>() {
                    @Override
                    public UpdateUserAttributesResult call() throws Exception {
                        UpdateUserAttributesResult result;

                        try {
                            result = updateUserAttributes(request);
                        } catch (Exception ex) {
                            if (asyncHandler != null) {
                                asyncHandler.onError(ex);
                            }
                            throw ex;
                        }

                        if (asyncHandler != null) {
                            asyncHandler.onSuccess(request, result);
                        }
                        return result;
                    }
                });
    }

    @Override
    public java.util.concurrent.Future<UpdateUserPoolResult> updateUserPoolAsync(
            UpdateUserPoolRequest request) {

        return updateUserPoolAsync(request, null);
    }

    @Override
    public java.util.concurrent.Future<UpdateUserPoolResult> updateUserPoolAsync(
            final UpdateUserPoolRequest request,
            final com.amazonaws.handlers.AsyncHandler<UpdateUserPoolRequest, UpdateUserPoolResult> asyncHandler) {

        return executorService
                .submit(new java.util.concurrent.Callable<UpdateUserPoolResult>() {
                    @Override
                    public UpdateUserPoolResult call() throws Exception {
                        UpdateUserPoolResult result;

                        try {
                            result = updateUserPool(request);
                        } catch (Exception ex) {
                            if (asyncHandler != null) {
                                asyncHandler.onError(ex);
                            }
                            throw ex;
                        }

                        if (asyncHandler != null) {
                            asyncHandler.onSuccess(request, result);
                        }
                        return result;
                    }
                });
    }

    @Override
    public java.util.concurrent.Future<UpdateUserPoolClientResult> updateUserPoolClientAsync(
            UpdateUserPoolClientRequest request) {

        return updateUserPoolClientAsync(request, null);
    }

    @Override
    public java.util.concurrent.Future<UpdateUserPoolClientResult> updateUserPoolClientAsync(
            final UpdateUserPoolClientRequest request,
            final com.amazonaws.handlers.AsyncHandler<UpdateUserPoolClientRequest, UpdateUserPoolClientResult> asyncHandler) {

        return executorService
                .submit(new java.util.concurrent.Callable<UpdateUserPoolClientResult>() {
                    @Override
                    public UpdateUserPoolClientResult call() throws Exception {
                        UpdateUserPoolClientResult result;

                        try {
                            result = updateUserPoolClient(request);
                        } catch (Exception ex) {
                            if (asyncHandler != null) {
                                asyncHandler.onError(ex);
                            }
                            throw ex;
                        }

                        if (asyncHandler != null) {
                            asyncHandler.onSuccess(request, result);
                        }
                        return result;
                    }
                });
    }

    @Override
    public java.util.concurrent.Future<VerifyUserAttributeResult> verifyUserAttributeAsync(
            VerifyUserAttributeRequest request) {

        return verifyUserAttributeAsync(request, null);
    }

    @Override
    public java.util.concurrent.Future<VerifyUserAttributeResult> verifyUserAttributeAsync(
            final VerifyUserAttributeRequest request,
            final com.amazonaws.handlers.AsyncHandler<VerifyUserAttributeRequest, VerifyUserAttributeResult> asyncHandler) {

        return executorService
                .submit(new java.util.concurrent.Callable<VerifyUserAttributeResult>() {
                    @Override
                    public VerifyUserAttributeResult call() throws Exception {
                        VerifyUserAttributeResult result;

                        try {
                            result = verifyUserAttribute(request);
                        } catch (Exception ex) {
                            if (asyncHandler != null) {
                                asyncHandler.onError(ex);
                            }
                            throw ex;
                        }

                        if (asyncHandler != null) {
                            asyncHandler.onSuccess(request, result);
                        }
                        return result;
                    }
                });
    }

    /**
     * Shuts down the client, releasing all managed resources. This includes
     * forcibly terminating all pending asynchronous service calls. Clients who
     * wish to give pending asynchronous service calls time to complete should
     * call {@code getExecutorService().shutdown()} followed by
     * {@code getExecutorService().awaitTermination()} prior to calling this
     * method.
     */
    @Override
    public void shutdown() {
        super.shutdown();
        executorService.shutdownNow();
    }
}
