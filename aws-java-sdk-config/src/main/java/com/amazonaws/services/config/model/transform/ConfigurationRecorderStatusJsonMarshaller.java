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

package com.amazonaws.services.config.model.transform;

import java.util.Map;
import java.util.List;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.config.model.*;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.BinaryUtils;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.IdempotentUtils;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.json.*;

/**
 * ConfigurationRecorderStatusMarshaller
 */
public class ConfigurationRecorderStatusJsonMarshaller {

    /**
     * Marshall the given parameter object, and output to a SdkJsonGenerator
     */
    public void marshall(
            ConfigurationRecorderStatus configurationRecorderStatus,
            StructuredJsonGenerator jsonGenerator) {

        if (configurationRecorderStatus == null) {
            throw new AmazonClientException(
                    "Invalid argument passed to marshall(...)");
        }

        try {
            jsonGenerator.writeStartObject();

            if (configurationRecorderStatus.getName() != null) {
                jsonGenerator.writeFieldName("name").writeValue(
                        configurationRecorderStatus.getName());
            }
            if (configurationRecorderStatus.getLastStartTime() != null) {
                jsonGenerator.writeFieldName("lastStartTime").writeValue(
                        configurationRecorderStatus.getLastStartTime());
            }
            if (configurationRecorderStatus.getLastStopTime() != null) {
                jsonGenerator.writeFieldName("lastStopTime").writeValue(
                        configurationRecorderStatus.getLastStopTime());
            }
            if (configurationRecorderStatus.getRecording() != null) {
                jsonGenerator.writeFieldName("recording").writeValue(
                        configurationRecorderStatus.getRecording());
            }
            if (configurationRecorderStatus.getLastStatus() != null) {
                jsonGenerator.writeFieldName("lastStatus").writeValue(
                        configurationRecorderStatus.getLastStatus());
            }
            if (configurationRecorderStatus.getLastErrorCode() != null) {
                jsonGenerator.writeFieldName("lastErrorCode").writeValue(
                        configurationRecorderStatus.getLastErrorCode());
            }
            if (configurationRecorderStatus.getLastErrorMessage() != null) {
                jsonGenerator.writeFieldName("lastErrorMessage").writeValue(
                        configurationRecorderStatus.getLastErrorMessage());
            }
            if (configurationRecorderStatus.getLastStatusChangeTime() != null) {
                jsonGenerator.writeFieldName("lastStatusChangeTime")
                        .writeValue(
                                configurationRecorderStatus
                                        .getLastStatusChangeTime());
            }

            jsonGenerator.writeEndObject();
        } catch (Throwable t) {
            throw new AmazonClientException(
                    "Unable to marshall request to JSON: " + t.getMessage(), t);
        }
    }

    private static ConfigurationRecorderStatusJsonMarshaller instance;

    public static ConfigurationRecorderStatusJsonMarshaller getInstance() {
        if (instance == null)
            instance = new ConfigurationRecorderStatusJsonMarshaller();
        return instance;
    }

}
