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

import com.amazonaws.*;
import com.amazonaws.regions.*;

import com.amazonaws.services.cognitoidp.model.*;

/**
 * Interface for accessing Amazon Cognito Identity Provider.
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
public interface AWSCognitoIdentityProvider {

    /**
     * Overrides the default endpoint for this client
     * ("https://cognito-idp.us-east-1.amazonaws.com"). Callers can use this
     * method to control which AWS region they want to work with.
     * <p>
     * Callers can pass in just the endpoint (ex:
     * "cognito-idp.us-east-1.amazonaws.com") or a full URL, including the
     * protocol (ex: "https://cognito-idp.us-east-1.amazonaws.com"). If the
     * protocol is not specified here, the default protocol from this client's
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
     *        The endpoint (ex: "cognito-idp.us-east-1.amazonaws.com") or a full
     *        URL, including the protocol (ex:
     *        "https://cognito-idp.us-east-1.amazonaws.com") of the region
     *        specific AWS endpoint this client will communicate with.
     */
    void setEndpoint(String endpoint);

    /**
     * An alternative to {@link AWSCognitoIdentityProvider#setEndpoint(String)},
     * sets the regional endpoint for this client's service calls. Callers can
     * use this method to control which AWS region they want to work with.
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
     * Adds additional user attributes to the user pool schema.
     * </p>
     * 
     * @param addCustomAttributesRequest
     *        Represents the request to add custom attributes.
     * @return Result of the AddCustomAttributes operation returned by the
     *         service.
     * @throws ResourceNotFoundException
     *         This exception is thrown when the Amazon Cognito service cannot
     *         find the requested resource.
     * @throws InvalidParameterException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid parameter.
     * @throws TooManyRequestsException
     *         This exception gets thrown when the user has made too many
     *         requests for a given operation.
     * @throws InternalErrorException
     *         This exception is thrown when Amazon Cognito encounters an
     *         internal error.
     * @sample AWSCognitoIdentityProvider.AddCustomAttributes
     */
    AddCustomAttributesResult addCustomAttributes(
            AddCustomAttributesRequest addCustomAttributesRequest);

    /**
     * <p>
     * Confirms user registration as an admin without using a confirmation code.
     * Works on any user.
     * </p>
     * 
     * @param adminConfirmSignUpRequest
     *        Represents the request to confirm user registration.
     * @return Result of the AdminConfirmSignUp operation returned by the
     *         service.
     * @throws ResourceNotFoundException
     *         This exception is thrown when the Amazon Cognito service cannot
     *         find the requested resource.
     * @throws InvalidParameterException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid parameter.
     * @throws UnexpectedLambdaException
     *         This exception gets thrown when the Amazon Cognito service
     *         encounters an unexpected exception with the AWS Lambda service.
     * @throws UserLambdaValidationException
     *         This exception gets thrown when the Amazon Cognito service
     *         encounters a user validation exception with the AWS Lambda
     *         service.
     * @throws NotAuthorizedException
     *         This exception gets thrown when a user is not authorized.
     * @throws TooManyFailedAttemptsException
     *         This exception gets thrown when the user has made too many failed
     *         attempts for a given action (e.g., sign in).
     * @throws InvalidLambdaResponseException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid AWS Lambda response.
     * @throws TooManyRequestsException
     *         This exception gets thrown when the user has made too many
     *         requests for a given operation.
     * @throws LimitExceededException
     *         This exception is thrown when a user exceeds the limit for a
     *         requested AWS resource.
     * @throws InternalErrorException
     *         This exception is thrown when Amazon Cognito encounters an
     *         internal error.
     * @sample AWSCognitoIdentityProvider.AdminConfirmSignUp
     */
    AdminConfirmSignUpResult adminConfirmSignUp(
            AdminConfirmSignUpRequest adminConfirmSignUpRequest);

    /**
     * <p>
     * Deletes a user as an administrator. Works on any user.
     * </p>
     * 
     * @param adminDeleteUserRequest
     *        Represents the request to delete a user as an administrator.
     * @throws ResourceNotFoundException
     *         This exception is thrown when the Amazon Cognito service cannot
     *         find the requested resource.
     * @throws InvalidParameterException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid parameter.
     * @throws TooManyRequestsException
     *         This exception gets thrown when the user has made too many
     *         requests for a given operation.
     * @throws InternalErrorException
     *         This exception is thrown when Amazon Cognito encounters an
     *         internal error.
     * @sample AWSCognitoIdentityProvider.AdminDeleteUser
     */
    void adminDeleteUser(AdminDeleteUserRequest adminDeleteUserRequest);

    /**
     * <p>
     * Deletes the user attributes in a user pool as an administrator. Works on
     * any user.
     * </p>
     * 
     * @param adminDeleteUserAttributesRequest
     *        Represents the request to delete user attributes as an
     *        administrator.
     * @return Result of the AdminDeleteUserAttributes operation returned by the
     *         service.
     * @throws ResourceNotFoundException
     *         This exception is thrown when the Amazon Cognito service cannot
     *         find the requested resource.
     * @throws InvalidParameterException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid parameter.
     * @throws TooManyRequestsException
     *         This exception gets thrown when the user has made too many
     *         requests for a given operation.
     * @throws InternalErrorException
     *         This exception is thrown when Amazon Cognito encounters an
     *         internal error.
     * @sample AWSCognitoIdentityProvider.AdminDeleteUserAttributes
     */
    AdminDeleteUserAttributesResult adminDeleteUserAttributes(
            AdminDeleteUserAttributesRequest adminDeleteUserAttributesRequest);

    /**
     * <p>
     * Disables the specified user as an administrator. Works on any user.
     * </p>
     * 
     * @param adminDisableUserRequest
     *        Represents the request to disable any user as an administrator.
     * @return Result of the AdminDisableUser operation returned by the service.
     * @throws ResourceNotFoundException
     *         This exception is thrown when the Amazon Cognito service cannot
     *         find the requested resource.
     * @throws InvalidParameterException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid parameter.
     * @throws TooManyRequestsException
     *         This exception gets thrown when the user has made too many
     *         requests for a given operation.
     * @throws InternalErrorException
     *         This exception is thrown when Amazon Cognito encounters an
     *         internal error.
     * @sample AWSCognitoIdentityProvider.AdminDisableUser
     */
    AdminDisableUserResult adminDisableUser(
            AdminDisableUserRequest adminDisableUserRequest);

    /**
     * <p>
     * Enables the specified user as an administrator. Works on any user.
     * </p>
     * 
     * @param adminEnableUserRequest
     *        Represents the request that enables the user as an administrator.
     * @return Result of the AdminEnableUser operation returned by the service.
     * @throws ResourceNotFoundException
     *         This exception is thrown when the Amazon Cognito service cannot
     *         find the requested resource.
     * @throws InvalidParameterException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid parameter.
     * @throws TooManyRequestsException
     *         This exception gets thrown when the user has made too many
     *         requests for a given operation.
     * @throws InternalErrorException
     *         This exception is thrown when Amazon Cognito encounters an
     *         internal error.
     * @sample AWSCognitoIdentityProvider.AdminEnableUser
     */
    AdminEnableUserResult adminEnableUser(
            AdminEnableUserRequest adminEnableUserRequest);

    /**
     * <p>
     * Gets the specified user by user name in a user pool as an administrator.
     * Works on any user.
     * </p>
     * 
     * @param adminGetUserRequest
     *        Represents the request to get the specified user as an
     *        administrator.
     * @return Result of the AdminGetUser operation returned by the service.
     * @throws ResourceNotFoundException
     *         This exception is thrown when the Amazon Cognito service cannot
     *         find the requested resource.
     * @throws InvalidParameterException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid parameter.
     * @throws TooManyRequestsException
     *         This exception gets thrown when the user has made too many
     *         requests for a given operation.
     * @throws InternalErrorException
     *         This exception is thrown when Amazon Cognito encounters an
     *         internal error.
     * @sample AWSCognitoIdentityProvider.AdminGetUser
     */
    AdminGetUserResult adminGetUser(AdminGetUserRequest adminGetUserRequest);

    /**
     * <p>
     * Resets the specified user's password in a user pool as an administrator.
     * Works on any user.
     * </p>
     * 
     * @param adminResetUserPasswordRequest
     *        Represents the request to reset a user's password as an
     *        administrator.
     * @return Result of the AdminResetUserPassword operation returned by the
     *         service.
     * @throws ResourceNotFoundException
     *         This exception is thrown when the Amazon Cognito service cannot
     *         find the requested resource.
     * @throws InvalidParameterException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid parameter.
     * @throws UnexpectedLambdaException
     *         This exception gets thrown when the Amazon Cognito service
     *         encounters an unexpected exception with the AWS Lambda service.
     * @throws UserLambdaValidationException
     *         This exception gets thrown when the Amazon Cognito service
     *         encounters a user validation exception with the AWS Lambda
     *         service.
     * @throws NotAuthorizedException
     *         This exception gets thrown when a user is not authorized.
     * @throws InvalidLambdaResponseException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid AWS Lambda response.
     * @throws TooManyRequestsException
     *         This exception gets thrown when the user has made too many
     *         requests for a given operation.
     * @throws LimitExceededException
     *         This exception is thrown when a user exceeds the limit for a
     *         requested AWS resource.
     * @throws InternalErrorException
     *         This exception is thrown when Amazon Cognito encounters an
     *         internal error.
     * @sample AWSCognitoIdentityProvider.AdminResetUserPassword
     */
    AdminResetUserPasswordResult adminResetUserPassword(
            AdminResetUserPasswordRequest adminResetUserPasswordRequest);

    /**
     * <p>
     * Sets all the user settings for a specified user name. Works on any user.
     * </p>
     * 
     * @param adminSetUserSettingsRequest
     *        Represents the request to set user settings as an administrator.
     * @return Result of the AdminSetUserSettings operation returned by the
     *         service.
     * @throws ResourceNotFoundException
     *         This exception is thrown when the Amazon Cognito service cannot
     *         find the requested resource.
     * @throws InvalidParameterException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid parameter.
     * @throws NotAuthorizedException
     *         This exception gets thrown when a user is not authorized.
     * @sample AWSCognitoIdentityProvider.AdminSetUserSettings
     */
    AdminSetUserSettingsResult adminSetUserSettings(
            AdminSetUserSettingsRequest adminSetUserSettingsRequest);

    /**
     * <p>
     * Updates the specified user's attributes, including developer attributes,
     * as an administrator. Works on any user.
     * </p>
     * 
     * @param adminUpdateUserAttributesRequest
     *        Represents the request to update the user's attributes as an
     *        administrator.
     * @return Result of the AdminUpdateUserAttributes operation returned by the
     *         service.
     * @throws ResourceNotFoundException
     *         This exception is thrown when the Amazon Cognito service cannot
     *         find the requested resource.
     * @throws InvalidParameterException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid parameter.
     * @throws UnexpectedLambdaException
     *         This exception gets thrown when the Amazon Cognito service
     *         encounters an unexpected exception with the AWS Lambda service.
     * @throws UserLambdaValidationException
     *         This exception gets thrown when the Amazon Cognito service
     *         encounters a user validation exception with the AWS Lambda
     *         service.
     * @throws InvalidLambdaResponseException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid AWS Lambda response.
     * @throws AliasExistsException
     *         This exception is thrown when a user tries to confirm the account
     *         with an email or phone number that has already been supplied as
     *         an alias from a different account. This exception tells user that
     *         an account with this email or phone already exists.
     * @throws TooManyRequestsException
     *         This exception gets thrown when the user has made too many
     *         requests for a given operation.
     * @throws InternalErrorException
     *         This exception is thrown when Amazon Cognito encounters an
     *         internal error.
     * @sample AWSCognitoIdentityProvider.AdminUpdateUserAttributes
     */
    AdminUpdateUserAttributesResult adminUpdateUserAttributes(
            AdminUpdateUserAttributesRequest adminUpdateUserAttributesRequest);

    /**
     * <p>
     * Changes the password for a specified user in a user pool.
     * </p>
     * 
     * @param changePasswordRequest
     *        Represents the request to change a user password.
     * @return Result of the ChangePassword operation returned by the service.
     * @throws ResourceNotFoundException
     *         This exception is thrown when the Amazon Cognito service cannot
     *         find the requested resource.
     * @throws InvalidParameterException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid parameter.
     * @throws InvalidPasswordException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid password.
     * @throws NotAuthorizedException
     *         This exception gets thrown when a user is not authorized.
     * @throws TooManyRequestsException
     *         This exception gets thrown when the user has made too many
     *         requests for a given operation.
     * @throws InternalErrorException
     *         This exception is thrown when Amazon Cognito encounters an
     *         internal error.
     * @sample AWSCognitoIdentityProvider.ChangePassword
     */
    ChangePasswordResult changePassword(
            ChangePasswordRequest changePasswordRequest);

    /**
     * <p>
     * Allows a user to enter a code provided when they reset their password to
     * update their password.
     * </p>
     * 
     * @param confirmForgotPasswordRequest
     *        The request representing the confirmation for a password reset.
     * @return Result of the ConfirmForgotPassword operation returned by the
     *         service.
     * @throws ResourceNotFoundException
     *         This exception is thrown when the Amazon Cognito service cannot
     *         find the requested resource.
     * @throws UnexpectedLambdaException
     *         This exception gets thrown when the Amazon Cognito service
     *         encounters an unexpected exception with the AWS Lambda service.
     * @throws UserLambdaValidationException
     *         This exception gets thrown when the Amazon Cognito service
     *         encounters a user validation exception with the AWS Lambda
     *         service.
     * @throws InvalidParameterException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid parameter.
     * @throws InvalidPasswordException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid password.
     * @throws NotAuthorizedException
     *         This exception gets thrown when a user is not authorized.
     * @throws CodeMismatchException
     *         This exception is thrown if the provided code does not match what
     *         the server was expecting.
     * @throws ExpiredCodeException
     *         This exception is thrown if a code has expired.
     * @throws TooManyFailedAttemptsException
     *         This exception gets thrown when the user has made too many failed
     *         attempts for a given action (e.g., sign in).
     * @throws InvalidLambdaResponseException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid AWS Lambda response.
     * @throws TooManyRequestsException
     *         This exception gets thrown when the user has made too many
     *         requests for a given operation.
     * @throws LimitExceededException
     *         This exception is thrown when a user exceeds the limit for a
     *         requested AWS resource.
     * @throws InternalErrorException
     *         This exception is thrown when Amazon Cognito encounters an
     *         internal error.
     * @sample AWSCognitoIdentityProvider.ConfirmForgotPassword
     */
    ConfirmForgotPasswordResult confirmForgotPassword(
            ConfirmForgotPasswordRequest confirmForgotPasswordRequest);

    /**
     * <p>
     * Confirms registration of a user and handles the existing alias from a
     * previous user.
     * </p>
     * 
     * @param confirmSignUpRequest
     *        Represents the request to confirm registration of a user.
     * @return Result of the ConfirmSignUp operation returned by the service.
     * @throws ResourceNotFoundException
     *         This exception is thrown when the Amazon Cognito service cannot
     *         find the requested resource.
     * @throws InvalidParameterException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid parameter.
     * @throws UnexpectedLambdaException
     *         This exception gets thrown when the Amazon Cognito service
     *         encounters an unexpected exception with the AWS Lambda service.
     * @throws UserLambdaValidationException
     *         This exception gets thrown when the Amazon Cognito service
     *         encounters a user validation exception with the AWS Lambda
     *         service.
     * @throws NotAuthorizedException
     *         This exception gets thrown when a user is not authorized.
     * @throws TooManyFailedAttemptsException
     *         This exception gets thrown when the user has made too many failed
     *         attempts for a given action (e.g., sign in).
     * @throws CodeMismatchException
     *         This exception is thrown if the provided code does not match what
     *         the server was expecting.
     * @throws ExpiredCodeException
     *         This exception is thrown if a code has expired.
     * @throws InvalidLambdaResponseException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid AWS Lambda response.
     * @throws AliasExistsException
     *         This exception is thrown when a user tries to confirm the account
     *         with an email or phone number that has already been supplied as
     *         an alias from a different account. This exception tells user that
     *         an account with this email or phone already exists.
     * @throws TooManyRequestsException
     *         This exception gets thrown when the user has made too many
     *         requests for a given operation.
     * @throws LimitExceededException
     *         This exception is thrown when a user exceeds the limit for a
     *         requested AWS resource.
     * @throws InternalErrorException
     *         This exception is thrown when Amazon Cognito encounters an
     *         internal error.
     * @sample AWSCognitoIdentityProvider.ConfirmSignUp
     */
    ConfirmSignUpResult confirmSignUp(ConfirmSignUpRequest confirmSignUpRequest);

    /**
     * <p>
     * Creates a new Amazon Cognito user pool and sets the password policy for
     * the pool.
     * </p>
     * 
     * @param createUserPoolRequest
     *        Represents the request to create a user pool.
     * @return Result of the CreateUserPool operation returned by the service.
     * @throws InvalidParameterException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid parameter.
     * @throws TooManyRequestsException
     *         This exception gets thrown when the user has made too many
     *         requests for a given operation.
     * @throws InternalErrorException
     *         This exception is thrown when Amazon Cognito encounters an
     *         internal error.
     * @throws LimitExceededException
     *         This exception is thrown when a user exceeds the limit for a
     *         requested AWS resource.
     * @sample AWSCognitoIdentityProvider.CreateUserPool
     */
    CreateUserPoolResult createUserPool(
            CreateUserPoolRequest createUserPoolRequest);

    /**
     * <p>
     * Creates the user pool client.
     * </p>
     * 
     * @param createUserPoolClientRequest
     *        Represents the request to create a user pool client.
     * @return Result of the CreateUserPoolClient operation returned by the
     *         service.
     * @throws InvalidParameterException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid parameter.
     * @throws ResourceNotFoundException
     *         This exception is thrown when the Amazon Cognito service cannot
     *         find the requested resource.
     * @throws TooManyRequestsException
     *         This exception gets thrown when the user has made too many
     *         requests for a given operation.
     * @throws LimitExceededException
     *         This exception is thrown when a user exceeds the limit for a
     *         requested AWS resource.
     * @throws InternalErrorException
     *         This exception is thrown when Amazon Cognito encounters an
     *         internal error.
     * @sample AWSCognitoIdentityProvider.CreateUserPoolClient
     */
    CreateUserPoolClientResult createUserPoolClient(
            CreateUserPoolClientRequest createUserPoolClientRequest);

    /**
     * <p>
     * Allows a user to delete one's self.
     * </p>
     * 
     * @param deleteUserRequest
     *        Represents the request to delete a user.
     * @throws ResourceNotFoundException
     *         This exception is thrown when the Amazon Cognito service cannot
     *         find the requested resource.
     * @throws InvalidParameterException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid parameter.
     * @throws NotAuthorizedException
     *         This exception gets thrown when a user is not authorized.
     * @throws TooManyRequestsException
     *         This exception gets thrown when the user has made too many
     *         requests for a given operation.
     * @throws InternalErrorException
     *         This exception is thrown when Amazon Cognito encounters an
     *         internal error.
     * @sample AWSCognitoIdentityProvider.DeleteUser
     */
    void deleteUser(DeleteUserRequest deleteUserRequest);

    /**
     * <p>
     * Deletes the attributes for a user.
     * </p>
     * 
     * @param deleteUserAttributesRequest
     *        Represents the request to delete user attributes.
     * @return Result of the DeleteUserAttributes operation returned by the
     *         service.
     * @throws ResourceNotFoundException
     *         This exception is thrown when the Amazon Cognito service cannot
     *         find the requested resource.
     * @throws InvalidParameterException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid parameter.
     * @throws NotAuthorizedException
     *         This exception gets thrown when a user is not authorized.
     * @throws TooManyRequestsException
     *         This exception gets thrown when the user has made too many
     *         requests for a given operation.
     * @throws InternalErrorException
     *         This exception is thrown when Amazon Cognito encounters an
     *         internal error.
     * @sample AWSCognitoIdentityProvider.DeleteUserAttributes
     */
    DeleteUserAttributesResult deleteUserAttributes(
            DeleteUserAttributesRequest deleteUserAttributesRequest);

    /**
     * <p>
     * Deletes the specified Amazon Cognito user pool.
     * </p>
     * 
     * @param deleteUserPoolRequest
     *        Represents the request to delete a user pool.
     * @throws ResourceNotFoundException
     *         This exception is thrown when the Amazon Cognito service cannot
     *         find the requested resource.
     * @throws InvalidParameterException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid parameter.
     * @throws TooManyRequestsException
     *         This exception gets thrown when the user has made too many
     *         requests for a given operation.
     * @throws InternalErrorException
     *         This exception is thrown when Amazon Cognito encounters an
     *         internal error.
     * @sample AWSCognitoIdentityProvider.DeleteUserPool
     */
    void deleteUserPool(DeleteUserPoolRequest deleteUserPoolRequest);

    /**
     * <p>
     * Allows the developer to delete the user pool client.
     * </p>
     * 
     * @param deleteUserPoolClientRequest
     *        Represents the request to delete a user pool client.
     * @throws ResourceNotFoundException
     *         This exception is thrown when the Amazon Cognito service cannot
     *         find the requested resource.
     * @throws InvalidParameterException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid parameter.
     * @throws TooManyRequestsException
     *         This exception gets thrown when the user has made too many
     *         requests for a given operation.
     * @throws InternalErrorException
     *         This exception is thrown when Amazon Cognito encounters an
     *         internal error.
     * @sample AWSCognitoIdentityProvider.DeleteUserPoolClient
     */
    void deleteUserPoolClient(
            DeleteUserPoolClientRequest deleteUserPoolClientRequest);

    /**
     * <p>
     * Returns the configuration information and metadata of the specified user
     * pool.
     * </p>
     * 
     * @param describeUserPoolRequest
     *        Represents the request to describe the user pool.
     * @return Result of the DescribeUserPool operation returned by the service.
     * @throws ResourceNotFoundException
     *         This exception is thrown when the Amazon Cognito service cannot
     *         find the requested resource.
     * @throws InvalidParameterException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid parameter.
     * @throws TooManyRequestsException
     *         This exception gets thrown when the user has made too many
     *         requests for a given operation.
     * @throws InternalErrorException
     *         This exception is thrown when Amazon Cognito encounters an
     *         internal error.
     * @sample AWSCognitoIdentityProvider.DescribeUserPool
     */
    DescribeUserPoolResult describeUserPool(
            DescribeUserPoolRequest describeUserPoolRequest);

    /**
     * <p>
     * Client method for returning the configuration information and metadata of
     * the specified user pool client.
     * </p>
     * 
     * @param describeUserPoolClientRequest
     *        Represents the request to describe a user pool client.
     * @return Result of the DescribeUserPoolClient operation returned by the
     *         service.
     * @throws ResourceNotFoundException
     *         This exception is thrown when the Amazon Cognito service cannot
     *         find the requested resource.
     * @throws InvalidParameterException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid parameter.
     * @throws TooManyRequestsException
     *         This exception gets thrown when the user has made too many
     *         requests for a given operation.
     * @throws InternalErrorException
     *         This exception is thrown when Amazon Cognito encounters an
     *         internal error.
     * @sample AWSCognitoIdentityProvider.DescribeUserPoolClient
     */
    DescribeUserPoolClientResult describeUserPoolClient(
            DescribeUserPoolClientRequest describeUserPoolClientRequest);

    /**
     * <p>
     * Retrieves the password for the specified client ID or username.
     * </p>
     * 
     * @param forgotPasswordRequest
     *        Represents the request to reset a user's password.
     * @return Result of the ForgotPassword operation returned by the service.
     * @throws ResourceNotFoundException
     *         This exception is thrown when the Amazon Cognito service cannot
     *         find the requested resource.
     * @throws InvalidParameterException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid parameter.
     * @throws UnexpectedLambdaException
     *         This exception gets thrown when the Amazon Cognito service
     *         encounters an unexpected exception with the AWS Lambda service.
     * @throws UserLambdaValidationException
     *         This exception gets thrown when the Amazon Cognito service
     *         encounters a user validation exception with the AWS Lambda
     *         service.
     * @throws NotAuthorizedException
     *         This exception gets thrown when a user is not authorized.
     * @throws InvalidLambdaResponseException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid AWS Lambda response.
     * @throws TooManyRequestsException
     *         This exception gets thrown when the user has made too many
     *         requests for a given operation.
     * @throws LimitExceededException
     *         This exception is thrown when a user exceeds the limit for a
     *         requested AWS resource.
     * @throws InternalErrorException
     *         This exception is thrown when Amazon Cognito encounters an
     *         internal error.
     * @sample AWSCognitoIdentityProvider.ForgotPassword
     */
    ForgotPasswordResult forgotPassword(
            ForgotPasswordRequest forgotPasswordRequest);

    /**
     * <p>
     * Gets the user attributes and metadata for a user.
     * </p>
     * 
     * @param getUserRequest
     *        Represents the request to get information about the user.
     * @return Result of the GetUser operation returned by the service.
     * @throws ResourceNotFoundException
     *         This exception is thrown when the Amazon Cognito service cannot
     *         find the requested resource.
     * @throws InvalidParameterException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid parameter.
     * @throws NotAuthorizedException
     *         This exception gets thrown when a user is not authorized.
     * @throws TooManyRequestsException
     *         This exception gets thrown when the user has made too many
     *         requests for a given operation.
     * @throws InternalErrorException
     *         This exception is thrown when Amazon Cognito encounters an
     *         internal error.
     * @sample AWSCognitoIdentityProvider.GetUser
     */
    GetUserResult getUser(GetUserRequest getUserRequest);

    /**
     * <p>
     * Gets the user attribute verification code for the specified attribute
     * name.
     * </p>
     * 
     * @param getUserAttributeVerificationCodeRequest
     *        Represents the request to get user attribute verification.
     * @return Result of the GetUserAttributeVerificationCode operation returned
     *         by the service.
     * @throws ResourceNotFoundException
     *         This exception is thrown when the Amazon Cognito service cannot
     *         find the requested resource.
     * @throws InvalidParameterException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid parameter.
     * @throws TooManyRequestsException
     *         This exception gets thrown when the user has made too many
     *         requests for a given operation.
     * @throws NotAuthorizedException
     *         This exception gets thrown when a user is not authorized.
     * @throws UnexpectedLambdaException
     *         This exception gets thrown when the Amazon Cognito service
     *         encounters an unexpected exception with the AWS Lambda service.
     * @throws UserLambdaValidationException
     *         This exception gets thrown when the Amazon Cognito service
     *         encounters a user validation exception with the AWS Lambda
     *         service.
     * @throws InvalidLambdaResponseException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid AWS Lambda response.
     * @throws InternalErrorException
     *         This exception is thrown when Amazon Cognito encounters an
     *         internal error.
     * @sample AWSCognitoIdentityProvider.GetUserAttributeVerificationCode
     */
    GetUserAttributeVerificationCodeResult getUserAttributeVerificationCode(
            GetUserAttributeVerificationCodeRequest getUserAttributeVerificationCodeRequest);

    /**
     * <p>
     * Lists the clients that have been created for the specified user pool.
     * </p>
     * 
     * @param listUserPoolClientsRequest
     *        Represents the request to list the user pool clients.
     * @return Result of the ListUserPoolClients operation returned by the
     *         service.
     * @throws InvalidParameterException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid parameter.
     * @throws ResourceNotFoundException
     *         This exception is thrown when the Amazon Cognito service cannot
     *         find the requested resource.
     * @throws TooManyRequestsException
     *         This exception gets thrown when the user has made too many
     *         requests for a given operation.
     * @throws InternalErrorException
     *         This exception is thrown when Amazon Cognito encounters an
     *         internal error.
     * @sample AWSCognitoIdentityProvider.ListUserPoolClients
     */
    ListUserPoolClientsResult listUserPoolClients(
            ListUserPoolClientsRequest listUserPoolClientsRequest);

    /**
     * <p>
     * Lists the user pools associated with an AWS account.
     * </p>
     * 
     * @param listUserPoolsRequest
     *        Represents the request to list user pools.
     * @return Result of the ListUserPools operation returned by the service.
     * @throws InvalidParameterException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid parameter.
     * @throws TooManyRequestsException
     *         This exception gets thrown when the user has made too many
     *         requests for a given operation.
     * @throws InternalErrorException
     *         This exception is thrown when Amazon Cognito encounters an
     *         internal error.
     * @sample AWSCognitoIdentityProvider.ListUserPools
     */
    ListUserPoolsResult listUserPools(ListUserPoolsRequest listUserPoolsRequest);

    /**
     * <p>
     * Lists the users in the Amazon Cognito user pool.
     * </p>
     * 
     * @param listUsersRequest
     *        Represents the request to list users.
     * @return Result of the ListUsers operation returned by the service.
     * @throws InvalidParameterException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid parameter.
     * @throws ResourceNotFoundException
     *         This exception is thrown when the Amazon Cognito service cannot
     *         find the requested resource.
     * @throws TooManyRequestsException
     *         This exception gets thrown when the user has made too many
     *         requests for a given operation.
     * @throws InternalErrorException
     *         This exception is thrown when Amazon Cognito encounters an
     *         internal error.
     * @sample AWSCognitoIdentityProvider.ListUsers
     */
    ListUsersResult listUsers(ListUsersRequest listUsersRequest);

    /**
     * <p>
     * Resends the confirmation (for confirmation of registration) to a specific
     * user in the user pool.
     * </p>
     * 
     * @param resendConfirmationCodeRequest
     *        Represents the request to resend the confirmation code.
     * @return Result of the ResendConfirmationCode operation returned by the
     *         service.
     * @throws ResourceNotFoundException
     *         This exception is thrown when the Amazon Cognito service cannot
     *         find the requested resource.
     * @throws InvalidParameterException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid parameter.
     * @throws UnexpectedLambdaException
     *         This exception gets thrown when the Amazon Cognito service
     *         encounters an unexpected exception with the AWS Lambda service.
     * @throws UserLambdaValidationException
     *         This exception gets thrown when the Amazon Cognito service
     *         encounters a user validation exception with the AWS Lambda
     *         service.
     * @throws NotAuthorizedException
     *         This exception gets thrown when a user is not authorized.
     * @throws InvalidLambdaResponseException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid AWS Lambda response.
     * @throws TooManyRequestsException
     *         This exception gets thrown when the user has made too many
     *         requests for a given operation.
     * @throws LimitExceededException
     *         This exception is thrown when a user exceeds the limit for a
     *         requested AWS resource.
     * @throws InternalErrorException
     *         This exception is thrown when Amazon Cognito encounters an
     *         internal error.
     * @sample AWSCognitoIdentityProvider.ResendConfirmationCode
     */
    ResendConfirmationCodeResult resendConfirmationCode(
            ResendConfirmationCodeRequest resendConfirmationCodeRequest);

    /**
     * <p>
     * Sets the user settings like multi-factor authentication (MFA). If MFA is
     * to be removed for a particular attribute pass the attribute with code
     * delivery as null. If null list is passed, all MFA options are removed.
     * </p>
     * 
     * @param setUserSettingsRequest
     *        Represents the request to set user settings.
     * @return Result of the SetUserSettings operation returned by the service.
     * @throws ResourceNotFoundException
     *         This exception is thrown when the Amazon Cognito service cannot
     *         find the requested resource.
     * @throws InvalidParameterException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid parameter.
     * @throws NotAuthorizedException
     *         This exception gets thrown when a user is not authorized.
     * @sample AWSCognitoIdentityProvider.SetUserSettings
     */
    SetUserSettingsResult setUserSettings(
            SetUserSettingsRequest setUserSettingsRequest);

    /**
     * <p>
     * Registers the user in the specified user pool and creates a user name,
     * password, and user attributes.
     * </p>
     * 
     * @param signUpRequest
     *        Represents the request to register a user.
     * @return Result of the SignUp operation returned by the service.
     * @throws ResourceNotFoundException
     *         This exception is thrown when the Amazon Cognito service cannot
     *         find the requested resource.
     * @throws InvalidParameterException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid parameter.
     * @throws UnexpectedLambdaException
     *         This exception gets thrown when the Amazon Cognito service
     *         encounters an unexpected exception with the AWS Lambda service.
     * @throws UserLambdaValidationException
     *         This exception gets thrown when the Amazon Cognito service
     *         encounters a user validation exception with the AWS Lambda
     *         service.
     * @throws NotAuthorizedException
     *         This exception gets thrown when a user is not authorized.
     * @throws InvalidPasswordException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid password.
     * @throws InvalidLambdaResponseException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid AWS Lambda response.
     * @throws UsernameExistsException
     *         This exception is thrown when Amazon Cognito encounters a user
     *         name that already exists in the user pool.
     * @throws TooManyRequestsException
     *         This exception gets thrown when the user has made too many
     *         requests for a given operation.
     * @throws InternalErrorException
     *         This exception is thrown when Amazon Cognito encounters an
     *         internal error.
     * @sample AWSCognitoIdentityProvider.SignUp
     */
    SignUpResult signUp(SignUpRequest signUpRequest);

    /**
     * <p>
     * Allows a user to update a specific attribute (one at a time).
     * </p>
     * 
     * @param updateUserAttributesRequest
     *        Represents the request to update user attributes.
     * @return Result of the UpdateUserAttributes operation returned by the
     *         service.
     * @throws ResourceNotFoundException
     *         This exception is thrown when the Amazon Cognito service cannot
     *         find the requested resource.
     * @throws InvalidParameterException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid parameter.
     * @throws CodeMismatchException
     *         This exception is thrown if the provided code does not match what
     *         the server was expecting.
     * @throws ExpiredCodeException
     *         This exception is thrown if a code has expired.
     * @throws NotAuthorizedException
     *         This exception gets thrown when a user is not authorized.
     * @throws UnexpectedLambdaException
     *         This exception gets thrown when the Amazon Cognito service
     *         encounters an unexpected exception with the AWS Lambda service.
     * @throws UserLambdaValidationException
     *         This exception gets thrown when the Amazon Cognito service
     *         encounters a user validation exception with the AWS Lambda
     *         service.
     * @throws InvalidLambdaResponseException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid AWS Lambda response.
     * @throws TooManyRequestsException
     *         This exception gets thrown when the user has made too many
     *         requests for a given operation.
     * @throws AliasExistsException
     *         This exception is thrown when a user tries to confirm the account
     *         with an email or phone number that has already been supplied as
     *         an alias from a different account. This exception tells user that
     *         an account with this email or phone already exists.
     * @throws InternalErrorException
     *         This exception is thrown when Amazon Cognito encounters an
     *         internal error.
     * @sample AWSCognitoIdentityProvider.UpdateUserAttributes
     */
    UpdateUserAttributesResult updateUserAttributes(
            UpdateUserAttributesRequest updateUserAttributesRequest);

    /**
     * <p>
     * Updates the specified user pool with the specified attributes.
     * </p>
     * 
     * @param updateUserPoolRequest
     *        Represents the request to update the user pool.
     * @return Result of the UpdateUserPool operation returned by the service.
     * @throws ResourceNotFoundException
     *         This exception is thrown when the Amazon Cognito service cannot
     *         find the requested resource.
     * @throws InvalidParameterException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid parameter.
     * @throws ConcurrentModificationException
     *         This exception is thrown if two or more modifications are
     *         happening concurrently.
     * @throws TooManyRequestsException
     *         This exception gets thrown when the user has made too many
     *         requests for a given operation.
     * @throws InternalErrorException
     *         This exception is thrown when Amazon Cognito encounters an
     *         internal error.
     * @sample AWSCognitoIdentityProvider.UpdateUserPool
     */
    UpdateUserPoolResult updateUserPool(
            UpdateUserPoolRequest updateUserPoolRequest);

    /**
     * <p>
     * Allows the developer to update the specified user pool client and
     * password policy.
     * </p>
     * 
     * @param updateUserPoolClientRequest
     *        Represents the request to update the user pool client.
     * @return Result of the UpdateUserPoolClient operation returned by the
     *         service.
     * @throws ResourceNotFoundException
     *         This exception is thrown when the Amazon Cognito service cannot
     *         find the requested resource.
     * @throws InvalidParameterException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid parameter.
     * @throws TooManyRequestsException
     *         This exception gets thrown when the user has made too many
     *         requests for a given operation.
     * @throws InternalErrorException
     *         This exception is thrown when Amazon Cognito encounters an
     *         internal error.
     * @sample AWSCognitoIdentityProvider.UpdateUserPoolClient
     */
    UpdateUserPoolClientResult updateUserPoolClient(
            UpdateUserPoolClientRequest updateUserPoolClientRequest);

    /**
     * <p>
     * Verifies the specified user attributes in the user pool.
     * </p>
     * 
     * @param verifyUserAttributeRequest
     *        Represents the request to verify user attributes.
     * @return Result of the VerifyUserAttribute operation returned by the
     *         service.
     * @throws ResourceNotFoundException
     *         This exception is thrown when the Amazon Cognito service cannot
     *         find the requested resource.
     * @throws InvalidParameterException
     *         This exception is thrown when the Amazon Cognito service
     *         encounters an invalid parameter.
     * @throws CodeMismatchException
     *         This exception is thrown if the provided code does not match what
     *         the server was expecting.
     * @throws ExpiredCodeException
     *         This exception is thrown if a code has expired.
     * @throws NotAuthorizedException
     *         This exception gets thrown when a user is not authorized.
     * @throws TooManyRequestsException
     *         This exception gets thrown when the user has made too many
     *         requests for a given operation.
     * @throws InternalErrorException
     *         This exception is thrown when Amazon Cognito encounters an
     *         internal error.
     * @sample AWSCognitoIdentityProvider.VerifyUserAttribute
     */
    VerifyUserAttributeResult verifyUserAttribute(
            VerifyUserAttributeRequest verifyUserAttributeRequest);

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
