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
package com.amazonaws.services.elasticbeanstalk;

import com.amazonaws.services.elasticbeanstalk.model.*;
import com.amazonaws.*;

/**
 * Abstract implementation of {@code AWSElasticBeanstalk}. Convenient method
 * forms pass through to the corresponding overload that takes a request object,
 * which throws an {@code UnsupportedOperationException}.
 */
public class AbstractAWSElasticBeanstalk implements AWSElasticBeanstalk {

    protected AbstractAWSElasticBeanstalk() {
    }

    @Override
    public void setEndpoint(String endpoint) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public void setRegion(com.amazonaws.regions.Region region) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public void abortEnvironmentUpdate(AbortEnvironmentUpdateRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public void abortEnvironmentUpdate() {
        abortEnvironmentUpdate(new AbortEnvironmentUpdateRequest());
    }

    @Override
    public ApplyEnvironmentManagedActionResult applyEnvironmentManagedAction(
            ApplyEnvironmentManagedActionRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public CheckDNSAvailabilityResult checkDNSAvailability(
            CheckDNSAvailabilityRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public ComposeEnvironmentsResult composeEnvironments(
            ComposeEnvironmentsRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public CreateApplicationResult createApplication(
            CreateApplicationRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public CreateApplicationVersionResult createApplicationVersion(
            CreateApplicationVersionRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public CreateConfigurationTemplateResult createConfigurationTemplate(
            CreateConfigurationTemplateRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public CreateEnvironmentResult createEnvironment(
            CreateEnvironmentRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public CreateStorageLocationResult createStorageLocation(
            CreateStorageLocationRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public CreateStorageLocationResult createStorageLocation() {
        return createStorageLocation(new CreateStorageLocationRequest());
    }

    @Override
    public void deleteApplication(DeleteApplicationRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public void deleteApplicationVersion(DeleteApplicationVersionRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public void deleteConfigurationTemplate(
            DeleteConfigurationTemplateRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public void deleteEnvironmentConfiguration(
            DeleteEnvironmentConfigurationRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public DescribeApplicationVersionsResult describeApplicationVersions(
            DescribeApplicationVersionsRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public DescribeApplicationVersionsResult describeApplicationVersions() {
        return describeApplicationVersions(new DescribeApplicationVersionsRequest());
    }

    @Override
    public DescribeApplicationsResult describeApplications(
            DescribeApplicationsRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public DescribeApplicationsResult describeApplications() {
        return describeApplications(new DescribeApplicationsRequest());
    }

    @Override
    public DescribeConfigurationOptionsResult describeConfigurationOptions(
            DescribeConfigurationOptionsRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public DescribeConfigurationSettingsResult describeConfigurationSettings(
            DescribeConfigurationSettingsRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public DescribeEnvironmentHealthResult describeEnvironmentHealth(
            DescribeEnvironmentHealthRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public DescribeEnvironmentManagedActionHistoryResult describeEnvironmentManagedActionHistory(
            DescribeEnvironmentManagedActionHistoryRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public DescribeEnvironmentManagedActionsResult describeEnvironmentManagedActions(
            DescribeEnvironmentManagedActionsRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public DescribeEnvironmentResourcesResult describeEnvironmentResources(
            DescribeEnvironmentResourcesRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public DescribeEnvironmentsResult describeEnvironments(
            DescribeEnvironmentsRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public DescribeEnvironmentsResult describeEnvironments() {
        return describeEnvironments(new DescribeEnvironmentsRequest());
    }

    @Override
    public DescribeEventsResult describeEvents(DescribeEventsRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public DescribeEventsResult describeEvents() {
        return describeEvents(new DescribeEventsRequest());
    }

    @Override
    public DescribeInstancesHealthResult describeInstancesHealth(
            DescribeInstancesHealthRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public ListAvailableSolutionStacksResult listAvailableSolutionStacks(
            ListAvailableSolutionStacksRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public ListAvailableSolutionStacksResult listAvailableSolutionStacks() {
        return listAvailableSolutionStacks(new ListAvailableSolutionStacksRequest());
    }

    @Override
    public void rebuildEnvironment(RebuildEnvironmentRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public void requestEnvironmentInfo(RequestEnvironmentInfoRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public void restartAppServer(RestartAppServerRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public RetrieveEnvironmentInfoResult retrieveEnvironmentInfo(
            RetrieveEnvironmentInfoRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public void swapEnvironmentCNAMEs(SwapEnvironmentCNAMEsRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public void swapEnvironmentCNAMEs() {
        swapEnvironmentCNAMEs(new SwapEnvironmentCNAMEsRequest());
    }

    @Override
    public TerminateEnvironmentResult terminateEnvironment(
            TerminateEnvironmentRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public UpdateApplicationResult updateApplication(
            UpdateApplicationRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public UpdateApplicationVersionResult updateApplicationVersion(
            UpdateApplicationVersionRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public UpdateConfigurationTemplateResult updateConfigurationTemplate(
            UpdateConfigurationTemplateRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public UpdateEnvironmentResult updateEnvironment(
            UpdateEnvironmentRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public ValidateConfigurationSettingsResult validateConfigurationSettings(
            ValidateConfigurationSettingsRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public void shutdown() {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public com.amazonaws.ResponseMetadata getCachedResponseMetadata(
            com.amazonaws.AmazonWebServiceRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }
}
