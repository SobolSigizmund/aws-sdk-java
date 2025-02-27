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

package com.amazonaws.services.lambda.model.transform;

import static com.amazonaws.util.StringUtils.UTF8;
import static com.amazonaws.util.StringUtils.COMMA_SEPARATOR;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.regex.Pattern;

import com.amazonaws.AmazonClientException;
import com.amazonaws.Request;
import com.amazonaws.DefaultRequest;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.lambda.model.*;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.BinaryUtils;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.IdempotentUtils;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.json.*;

/**
 * UpdateEventSourceMappingRequest Marshaller
 */
public class UpdateEventSourceMappingRequestMarshaller
        implements
        Marshaller<Request<UpdateEventSourceMappingRequest>, UpdateEventSourceMappingRequest> {

    private static final String DEFAULT_CONTENT_TYPE = "";

    public Request<UpdateEventSourceMappingRequest> marshall(
            UpdateEventSourceMappingRequest updateEventSourceMappingRequest) {

        if (updateEventSourceMappingRequest == null) {
            throw new AmazonClientException(
                    "Invalid argument passed to marshall(...)");
        }

        Request<UpdateEventSourceMappingRequest> request = new DefaultRequest<UpdateEventSourceMappingRequest>(
                updateEventSourceMappingRequest, "AWSLambda");

        request.setHttpMethod(HttpMethodName.PUT);

        String uriResourcePath = "/2015-03-31/event-source-mappings/{UUID}";

        uriResourcePath = uriResourcePath
                .replace(
                        "{UUID}",
                        (updateEventSourceMappingRequest.getUUID() != null) ? StringUtils
                                .fromString(updateEventSourceMappingRequest
                                        .getUUID()) : "");
        request.setResourcePath(uriResourcePath);

        try {
            final SdkJsonGenerator jsonGenerator = new SdkJsonGenerator();

            jsonGenerator.writeStartObject();

            if (updateEventSourceMappingRequest.getFunctionName() != null) {
                jsonGenerator.writeFieldName("FunctionName").writeValue(
                        updateEventSourceMappingRequest.getFunctionName());
            }
            if (updateEventSourceMappingRequest.getEnabled() != null) {
                jsonGenerator.writeFieldName("Enabled").writeValue(
                        updateEventSourceMappingRequest.getEnabled());
            }
            if (updateEventSourceMappingRequest.getBatchSize() != null) {
                jsonGenerator.writeFieldName("BatchSize").writeValue(
                        updateEventSourceMappingRequest.getBatchSize());
            }

            jsonGenerator.writeEndObject();

            byte[] content = jsonGenerator.getBytes();
            request.setContent(new ByteArrayInputStream(content));
            request.addHeader("Content-Length",
                    Integer.toString(content.length));
            if (!request.getHeaders().containsKey("Content-Type")) {
                request.addHeader("Content-Type", DEFAULT_CONTENT_TYPE);
            }
        } catch (Throwable t) {
            throw new AmazonClientException(
                    "Unable to marshall request to JSON: " + t.getMessage(), t);
        }

        return request;
    }

}
