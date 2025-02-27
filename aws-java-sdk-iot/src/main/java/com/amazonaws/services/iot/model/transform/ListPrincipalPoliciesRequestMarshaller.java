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

package com.amazonaws.services.iot.model.transform;

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
import com.amazonaws.services.iot.model.*;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.BinaryUtils;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.IdempotentUtils;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.json.*;

/**
 * ListPrincipalPoliciesRequest Marshaller
 */
public class ListPrincipalPoliciesRequestMarshaller
        implements
        Marshaller<Request<ListPrincipalPoliciesRequest>, ListPrincipalPoliciesRequest> {

    private static final String DEFAULT_CONTENT_TYPE = "";

    public Request<ListPrincipalPoliciesRequest> marshall(
            ListPrincipalPoliciesRequest listPrincipalPoliciesRequest) {

        if (listPrincipalPoliciesRequest == null) {
            throw new AmazonClientException(
                    "Invalid argument passed to marshall(...)");
        }

        Request<ListPrincipalPoliciesRequest> request = new DefaultRequest<ListPrincipalPoliciesRequest>(
                listPrincipalPoliciesRequest, "AWSIot");

        request.setHttpMethod(HttpMethodName.GET);

        if (listPrincipalPoliciesRequest.getPrincipal() != null) {
            request.addHeader("x-amzn-iot-principal", StringUtils
                    .fromString(listPrincipalPoliciesRequest.getPrincipal()));
        }

        String uriResourcePath = "/principal-policies";

        request.setResourcePath(uriResourcePath);

        if (listPrincipalPoliciesRequest.getMarker() != null) {
            request.addParameter("marker", StringUtils
                    .fromString(listPrincipalPoliciesRequest.getMarker()));
        }

        if (listPrincipalPoliciesRequest.getPageSize() != null) {
            request.addParameter("pageSize", StringUtils
                    .fromInteger(listPrincipalPoliciesRequest.getPageSize()));
        }

        if (listPrincipalPoliciesRequest.getAscendingOrder() != null) {
            request.addParameter("isAscendingOrder", StringUtils
                    .fromBoolean(listPrincipalPoliciesRequest
                            .getAscendingOrder()));
        }

        request.setContent(new ByteArrayInputStream(new byte[0]));
        if (!request.getHeaders().containsKey("Content-Type")) {
            request.addHeader("Content-Type", DEFAULT_CONTENT_TYPE);
        }

        return request;
    }

}
