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

package com.amazonaws.services.apigateway.model.transform;

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
import com.amazonaws.services.apigateway.model.*;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.BinaryUtils;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.IdempotentUtils;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.json.*;

/**
 * TestInvokeAuthorizerRequest Marshaller
 */
public class TestInvokeAuthorizerRequestMarshaller
        implements
        Marshaller<Request<TestInvokeAuthorizerRequest>, TestInvokeAuthorizerRequest> {

    private static final String DEFAULT_CONTENT_TYPE = "";

    public Request<TestInvokeAuthorizerRequest> marshall(
            TestInvokeAuthorizerRequest testInvokeAuthorizerRequest) {

        if (testInvokeAuthorizerRequest == null) {
            throw new AmazonClientException(
                    "Invalid argument passed to marshall(...)");
        }

        Request<TestInvokeAuthorizerRequest> request = new DefaultRequest<TestInvokeAuthorizerRequest>(
                testInvokeAuthorizerRequest, "AmazonApiGateway");

        request.setHttpMethod(HttpMethodName.POST);

        String uriResourcePath = "/restapis/{restapi_id}/authorizers/{authorizer_id}";

        uriResourcePath = uriResourcePath
                .replace(
                        "{restapi_id}",
                        (testInvokeAuthorizerRequest.getRestApiId() != null) ? StringUtils
                                .fromString(testInvokeAuthorizerRequest
                                        .getRestApiId()) : "");
        uriResourcePath = uriResourcePath
                .replace(
                        "{authorizer_id}",
                        (testInvokeAuthorizerRequest.getAuthorizerId() != null) ? StringUtils
                                .fromString(testInvokeAuthorizerRequest
                                        .getAuthorizerId()) : "");
        request.setResourcePath(uriResourcePath);

        try {
            final SdkJsonGenerator jsonGenerator = new SdkJsonGenerator();

            jsonGenerator.writeStartObject();

            java.util.Map<String, String> headersMap = testInvokeAuthorizerRequest
                    .getHeaders();
            if (headersMap != null) {
                jsonGenerator.writeFieldName("headers");
                jsonGenerator.writeStartObject();

                for (Map.Entry<String, String> headersMapValue : headersMap
                        .entrySet()) {
                    if (headersMapValue.getValue() != null) {
                        jsonGenerator.writeFieldName(headersMapValue.getKey());

                        jsonGenerator.writeValue(headersMapValue.getValue());
                    }
                }
                jsonGenerator.writeEndObject();
            }
            if (testInvokeAuthorizerRequest.getPathWithQueryString() != null) {
                jsonGenerator.writeFieldName("pathWithQueryString").writeValue(
                        testInvokeAuthorizerRequest.getPathWithQueryString());
            }
            if (testInvokeAuthorizerRequest.getBody() != null) {
                jsonGenerator.writeFieldName("body").writeValue(
                        testInvokeAuthorizerRequest.getBody());
            }

            java.util.Map<String, String> stageVariablesMap = testInvokeAuthorizerRequest
                    .getStageVariables();
            if (stageVariablesMap != null) {
                jsonGenerator.writeFieldName("stageVariables");
                jsonGenerator.writeStartObject();

                for (Map.Entry<String, String> stageVariablesMapValue : stageVariablesMap
                        .entrySet()) {
                    if (stageVariablesMapValue.getValue() != null) {
                        jsonGenerator.writeFieldName(stageVariablesMapValue
                                .getKey());

                        jsonGenerator.writeValue(stageVariablesMapValue
                                .getValue());
                    }
                }
                jsonGenerator.writeEndObject();
            }

            java.util.Map<String, String> additionalContextMap = testInvokeAuthorizerRequest
                    .getAdditionalContext();
            if (additionalContextMap != null) {
                jsonGenerator.writeFieldName("additionalContext");
                jsonGenerator.writeStartObject();

                for (Map.Entry<String, String> additionalContextMapValue : additionalContextMap
                        .entrySet()) {
                    if (additionalContextMapValue.getValue() != null) {
                        jsonGenerator.writeFieldName(additionalContextMapValue
                                .getKey());

                        jsonGenerator.writeValue(additionalContextMapValue
                                .getValue());
                    }
                }
                jsonGenerator.writeEndObject();
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
