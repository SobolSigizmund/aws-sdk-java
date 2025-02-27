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

import java.util.Map;
import java.util.List;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.simpleworkflow.model.*;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.BinaryUtils;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.IdempotentUtils;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.json.*;

/**
 * SignalExternalWorkflowExecutionFailedEventAttributesMarshaller
 */
public class SignalExternalWorkflowExecutionFailedEventAttributesJsonMarshaller {

    /**
     * Marshall the given parameter object, and output to a SdkJsonGenerator
     */
    public void marshall(
            SignalExternalWorkflowExecutionFailedEventAttributes signalExternalWorkflowExecutionFailedEventAttributes,
            StructuredJsonGenerator jsonGenerator) {

        if (signalExternalWorkflowExecutionFailedEventAttributes == null) {
            throw new AmazonClientException(
                    "Invalid argument passed to marshall(...)");
        }

        try {
            jsonGenerator.writeStartObject();

            if (signalExternalWorkflowExecutionFailedEventAttributes
                    .getWorkflowId() != null) {
                jsonGenerator.writeFieldName("workflowId").writeValue(
                        signalExternalWorkflowExecutionFailedEventAttributes
                                .getWorkflowId());
            }
            if (signalExternalWorkflowExecutionFailedEventAttributes.getRunId() != null) {
                jsonGenerator.writeFieldName("runId").writeValue(
                        signalExternalWorkflowExecutionFailedEventAttributes
                                .getRunId());
            }
            if (signalExternalWorkflowExecutionFailedEventAttributes.getCause() != null) {
                jsonGenerator.writeFieldName("cause").writeValue(
                        signalExternalWorkflowExecutionFailedEventAttributes
                                .getCause());
            }
            if (signalExternalWorkflowExecutionFailedEventAttributes
                    .getInitiatedEventId() != null) {
                jsonGenerator.writeFieldName("initiatedEventId").writeValue(
                        signalExternalWorkflowExecutionFailedEventAttributes
                                .getInitiatedEventId());
            }
            if (signalExternalWorkflowExecutionFailedEventAttributes
                    .getDecisionTaskCompletedEventId() != null) {
                jsonGenerator.writeFieldName("decisionTaskCompletedEventId")
                        .writeValue(
                                signalExternalWorkflowExecutionFailedEventAttributes
                                        .getDecisionTaskCompletedEventId());
            }
            if (signalExternalWorkflowExecutionFailedEventAttributes
                    .getControl() != null) {
                jsonGenerator.writeFieldName("control").writeValue(
                        signalExternalWorkflowExecutionFailedEventAttributes
                                .getControl());
            }

            jsonGenerator.writeEndObject();
        } catch (Throwable t) {
            throw new AmazonClientException(
                    "Unable to marshall request to JSON: " + t.getMessage(), t);
        }
    }

    private static SignalExternalWorkflowExecutionFailedEventAttributesJsonMarshaller instance;

    public static SignalExternalWorkflowExecutionFailedEventAttributesJsonMarshaller getInstance() {
        if (instance == null)
            instance = new SignalExternalWorkflowExecutionFailedEventAttributesJsonMarshaller();
        return instance;
    }

}
