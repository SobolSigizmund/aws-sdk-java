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

package com.amazonaws.services.simpleworkflow.model.transform;

import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.util.Map;
import java.util.List;
import java.util.regex.Pattern;

import com.amazonaws.AmazonClientException;
import com.amazonaws.Request;
import com.amazonaws.DefaultRequest;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.simpleworkflow.model.*;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.BinaryUtils;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.IdempotentUtils;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.json.*;

/**
 * ListActivityTypesRequest Marshaller
 */
public class ListActivityTypesRequestMarshaller implements
        Marshaller<Request<ListActivityTypesRequest>, ListActivityTypesRequest> {

    public Request<ListActivityTypesRequest> marshall(
            ListActivityTypesRequest listActivityTypesRequest) {

        if (listActivityTypesRequest == null) {
            throw new AmazonClientException(
                    "Invalid argument passed to marshall(...)");
        }

        Request<ListActivityTypesRequest> request = new DefaultRequest<ListActivityTypesRequest>(
                listActivityTypesRequest, "AmazonSimpleWorkflow");
        request.addHeader("X-Amz-Target",
                "SimpleWorkflowService.ListActivityTypes");

        request.setHttpMethod(HttpMethodName.POST);

        request.setResourcePath("");

        try {
            final StructuredJsonGenerator jsonGenerator = SdkJsonProtocolFactory
                    .createWriter(false, "1.0");

            jsonGenerator.writeStartObject();

            if (listActivityTypesRequest.getDomain() != null) {
                jsonGenerator.writeFieldName("domain").writeValue(
                        listActivityTypesRequest.getDomain());
            }
            if (listActivityTypesRequest.getName() != null) {
                jsonGenerator.writeFieldName("name").writeValue(
                        listActivityTypesRequest.getName());
            }
            if (listActivityTypesRequest.getRegistrationStatus() != null) {
                jsonGenerator.writeFieldName("registrationStatus").writeValue(
                        listActivityTypesRequest.getRegistrationStatus());
            }
            if (listActivityTypesRequest.getNextPageToken() != null) {
                jsonGenerator.writeFieldName("nextPageToken").writeValue(
                        listActivityTypesRequest.getNextPageToken());
            }
            if (listActivityTypesRequest.getMaximumPageSize() != null) {
                jsonGenerator.writeFieldName("maximumPageSize").writeValue(
                        listActivityTypesRequest.getMaximumPageSize());
            }
            if (listActivityTypesRequest.getReverseOrder() != null) {
                jsonGenerator.writeFieldName("reverseOrder").writeValue(
                        listActivityTypesRequest.getReverseOrder());
            }

            jsonGenerator.writeEndObject();

            byte[] content = jsonGenerator.getBytes();
            request.setContent(new ByteArrayInputStream(content));
            request.addHeader("Content-Length",
                    Integer.toString(content.length));
            request.addHeader("Content-Type", jsonGenerator.getContentType());
        } catch (Throwable t) {
            throw new AmazonClientException(
                    "Unable to marshall request to JSON: " + t.getMessage(), t);
        }

        return request;
    }

}
