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
package com.amazonaws.services.certificatemanager;

import com.amazonaws.services.certificatemanager.model.*;
import com.amazonaws.*;

/**
 * Abstract implementation of {@code AWSCertificateManager}. Convenient method
 * forms pass through to the corresponding overload that takes a request object,
 * which throws an {@code UnsupportedOperationException}.
 */
public class AbstractAWSCertificateManager implements AWSCertificateManager {

    protected AbstractAWSCertificateManager() {
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
    public void addTagsToCertificate(AddTagsToCertificateRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public void deleteCertificate(DeleteCertificateRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public DescribeCertificateResult describeCertificate(
            DescribeCertificateRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public GetCertificateResult getCertificate(GetCertificateRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public ListCertificatesResult listCertificates(
            ListCertificatesRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public ListTagsForCertificateResult listTagsForCertificate(
            ListTagsForCertificateRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public void removeTagsFromCertificate(
            RemoveTagsFromCertificateRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public RequestCertificateResult requestCertificate(
            RequestCertificateRequest request) {
        throw new java.lang.UnsupportedOperationException();
    }

    @Override
    public void resendValidationEmail(ResendValidationEmailRequest request) {
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
