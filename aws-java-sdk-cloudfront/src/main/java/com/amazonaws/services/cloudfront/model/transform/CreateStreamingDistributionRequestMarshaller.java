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

package com.amazonaws.services.cloudfront.model.transform;

import static com.amazonaws.util.StringUtils.UTF8;

import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.AmazonClientException;
import com.amazonaws.Request;
import com.amazonaws.DefaultRequest;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.cloudfront.model.*;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.BinaryUtils;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.IdempotentUtils;
import com.amazonaws.util.XMLWriter;

/**
 * CreateStreamingDistributionRequest Marshaller
 */

public class CreateStreamingDistributionRequestMarshaller
        implements
        Marshaller<Request<CreateStreamingDistributionRequest>, CreateStreamingDistributionRequest> {

    public Request<CreateStreamingDistributionRequest> marshall(
            CreateStreamingDistributionRequest createStreamingDistributionRequest) {

        if (createStreamingDistributionRequest == null) {
            throw new AmazonClientException(
                    "Invalid argument passed to marshall(...)");
        }

        Request<CreateStreamingDistributionRequest> request = new DefaultRequest<CreateStreamingDistributionRequest>(
                createStreamingDistributionRequest, "AmazonCloudFront");

        request.setHttpMethod(HttpMethodName.POST);

        String uriResourcePath = "/2016-01-28/streaming-distribution";

        request.setResourcePath(uriResourcePath);

        try {
            StringWriter stringWriter = new StringWriter();
            XMLWriter xmlWriter = new XMLWriter(stringWriter,
                    "http://cloudfront.amazonaws.com/doc/2016-01-28/");

            StreamingDistributionConfig streamingDistributionConfig = createStreamingDistributionRequest
                    .getStreamingDistributionConfig();
            if (streamingDistributionConfig != null) {
                xmlWriter.startElement("StreamingDistributionConfig");

                if (streamingDistributionConfig.getCallerReference() != null) {
                    xmlWriter
                            .startElement("CallerReference")
                            .value(streamingDistributionConfig
                                    .getCallerReference()).endElement();
                }

                S3Origin s3Origin = streamingDistributionConfig.getS3Origin();
                if (s3Origin != null) {
                    xmlWriter.startElement("S3Origin");

                    if (s3Origin.getDomainName() != null) {
                        xmlWriter.startElement("DomainName")
                                .value(s3Origin.getDomainName()).endElement();
                    }

                    if (s3Origin.getOriginAccessIdentity() != null) {
                        xmlWriter.startElement("OriginAccessIdentity")
                                .value(s3Origin.getOriginAccessIdentity())
                                .endElement();
                    }
                    xmlWriter.endElement();
                }

                Aliases aliases = streamingDistributionConfig.getAliases();
                if (aliases != null) {
                    xmlWriter.startElement("Aliases");

                    if (aliases.getQuantity() != null) {
                        xmlWriter.startElement("Quantity")
                                .value(aliases.getQuantity()).endElement();
                    }

                    com.amazonaws.internal.SdkInternalList<String> aliasesItemsList = (com.amazonaws.internal.SdkInternalList<String>) aliases
                            .getItems();
                    if (!aliasesItemsList.isEmpty()
                            || !aliasesItemsList.isAutoConstruct()) {
                        xmlWriter.startElement("Items");

                        for (String aliasesItemsListValue : aliasesItemsList) {
                            xmlWriter.startElement("CNAME");
                            xmlWriter.value(aliasesItemsListValue);
                            xmlWriter.endElement();
                        }
                        xmlWriter.endElement();
                    }
                    xmlWriter.endElement();
                }

                if (streamingDistributionConfig.getComment() != null) {
                    xmlWriter.startElement("Comment")
                            .value(streamingDistributionConfig.getComment())
                            .endElement();
                }

                StreamingLoggingConfig logging = streamingDistributionConfig
                        .getLogging();
                if (logging != null) {
                    xmlWriter.startElement("Logging");

                    if (logging.getEnabled() != null) {
                        xmlWriter.startElement("Enabled")
                                .value(logging.getEnabled()).endElement();
                    }

                    if (logging.getBucket() != null) {
                        xmlWriter.startElement("Bucket")
                                .value(logging.getBucket()).endElement();
                    }

                    if (logging.getPrefix() != null) {
                        xmlWriter.startElement("Prefix")
                                .value(logging.getPrefix()).endElement();
                    }
                    xmlWriter.endElement();
                }

                TrustedSigners trustedSigners = streamingDistributionConfig
                        .getTrustedSigners();
                if (trustedSigners != null) {
                    xmlWriter.startElement("TrustedSigners");

                    if (trustedSigners.getEnabled() != null) {
                        xmlWriter.startElement("Enabled")
                                .value(trustedSigners.getEnabled())
                                .endElement();
                    }

                    if (trustedSigners.getQuantity() != null) {
                        xmlWriter.startElement("Quantity")
                                .value(trustedSigners.getQuantity())
                                .endElement();
                    }

                    com.amazonaws.internal.SdkInternalList<String> trustedSignersItemsList = (com.amazonaws.internal.SdkInternalList<String>) trustedSigners
                            .getItems();
                    if (!trustedSignersItemsList.isEmpty()
                            || !trustedSignersItemsList.isAutoConstruct()) {
                        xmlWriter.startElement("Items");

                        for (String trustedSignersItemsListValue : trustedSignersItemsList) {
                            xmlWriter.startElement("AwsAccountNumber");
                            xmlWriter.value(trustedSignersItemsListValue);
                            xmlWriter.endElement();
                        }
                        xmlWriter.endElement();
                    }
                    xmlWriter.endElement();
                }

                if (streamingDistributionConfig.getPriceClass() != null) {
                    xmlWriter.startElement("PriceClass")
                            .value(streamingDistributionConfig.getPriceClass())
                            .endElement();
                }

                if (streamingDistributionConfig.getEnabled() != null) {
                    xmlWriter.startElement("Enabled")
                            .value(streamingDistributionConfig.getEnabled())
                            .endElement();
                }
                xmlWriter.endElement();
            }

            request.setContent(new StringInputStream(stringWriter.getBuffer()
                    .toString()));
            request.addHeader(
                    "Content-Length",
                    Integer.toString(stringWriter.getBuffer().toString()
                            .getBytes(UTF8).length));
            if (!request.getHeaders().containsKey("Content-Type")) {
                request.addHeader("Content-Type", "application/xml");
            }
        } catch (Throwable t) {
            throw new AmazonClientException(
                    "Unable to marshall request to XML: " + t.getMessage(), t);
        }

        return request;
    }

}
