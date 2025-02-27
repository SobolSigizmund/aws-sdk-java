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
 * RespondDecisionTaskCompletedRequest Marshaller
 */
public class RespondDecisionTaskCompletedRequestMarshaller
        implements
        Marshaller<Request<RespondDecisionTaskCompletedRequest>, RespondDecisionTaskCompletedRequest> {

    public Request<RespondDecisionTaskCompletedRequest> marshall(
            RespondDecisionTaskCompletedRequest respondDecisionTaskCompletedRequest) {

        if (respondDecisionTaskCompletedRequest == null) {
            throw new AmazonClientException(
                    "Invalid argument passed to marshall(...)");
        }

        Request<RespondDecisionTaskCompletedRequest> request = new DefaultRequest<RespondDecisionTaskCompletedRequest>(
                respondDecisionTaskCompletedRequest, "AmazonSimpleWorkflow");
        request.addHeader("X-Amz-Target",
                "SimpleWorkflowService.RespondDecisionTaskCompleted");

        request.setHttpMethod(HttpMethodName.POST);

        request.setResourcePath("");

        try {
            final StructuredJsonGenerator jsonGenerator = SdkJsonProtocolFactory
                    .createWriter(false, "1.0");

            jsonGenerator.writeStartObject();

            if (respondDecisionTaskCompletedRequest.getTaskToken() != null) {
                jsonGenerator.writeFieldName("taskToken").writeValue(
                        respondDecisionTaskCompletedRequest.getTaskToken());
            }

            java.util.List<Decision> decisionsList = respondDecisionTaskCompletedRequest
                    .getDecisions();
            if (decisionsList != null) {
                jsonGenerator.writeFieldName("decisions");
                jsonGenerator.writeStartArray();
                for (Decision decisionsListValue : decisionsList) {
                    if (decisionsListValue != null) {

                        DecisionJsonMarshaller.getInstance().marshall(
                                decisionsListValue, jsonGenerator);
                    }
                }
                jsonGenerator.writeEndArray();
            }
            if (respondDecisionTaskCompletedRequest.getExecutionContext() != null) {
                jsonGenerator.writeFieldName("executionContext").writeValue(
                        respondDecisionTaskCompletedRequest
                                .getExecutionContext());
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
