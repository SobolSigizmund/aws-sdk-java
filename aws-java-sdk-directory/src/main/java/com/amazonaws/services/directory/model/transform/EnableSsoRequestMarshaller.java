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

package com.amazonaws.services.directory.model.transform;

import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.util.Map;
import java.util.List;
import java.util.regex.Pattern;

import com.amazonaws.AmazonClientException;
import com.amazonaws.Request;
import com.amazonaws.DefaultRequest;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.directory.model.*;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.BinaryUtils;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.IdempotentUtils;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.json.*;

/**
 * EnableSsoRequest Marshaller
 */
public class EnableSsoRequestMarshaller implements
        Marshaller<Request<EnableSsoRequest>, EnableSsoRequest> {

    public Request<EnableSsoRequest> marshall(EnableSsoRequest enableSsoRequest) {

        if (enableSsoRequest == null) {
            throw new AmazonClientException(
                    "Invalid argument passed to marshall(...)");
        }

        Request<EnableSsoRequest> request = new DefaultRequest<EnableSsoRequest>(
                enableSsoRequest, "AWSDirectoryService");
        request.addHeader("X-Amz-Target", "DirectoryService_20150416.EnableSso");

        request.setHttpMethod(HttpMethodName.POST);

        request.setResourcePath("");

        try {
            final StructuredJsonGenerator jsonGenerator = SdkJsonProtocolFactory
                    .createWriter(false, "1.1");

            jsonGenerator.writeStartObject();

            if (enableSsoRequest.getDirectoryId() != null) {
                jsonGenerator.writeFieldName("DirectoryId").writeValue(
                        enableSsoRequest.getDirectoryId());
            }
            if (enableSsoRequest.getUserName() != null) {
                jsonGenerator.writeFieldName("UserName").writeValue(
                        enableSsoRequest.getUserName());
            }
            if (enableSsoRequest.getPassword() != null) {
                jsonGenerator.writeFieldName("Password").writeValue(
                        enableSsoRequest.getPassword());
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
