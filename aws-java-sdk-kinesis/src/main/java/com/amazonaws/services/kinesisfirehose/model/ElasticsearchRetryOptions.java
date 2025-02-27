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

package com.amazonaws.services.kinesisfirehose.model;

import java.io.Serializable;

/**
 * <p>
 * Configures retry behavior in the event that Firehose is unable to deliver
 * documents to Amazon ES.
 * </p>
 */
public class ElasticsearchRetryOptions implements Serializable, Cloneable {

    /**
     * <p>
     * After an initial failure to deliver to Amazon ES, the total amount of
     * time during which Firehose re-attempts delivery. After this time has
     * elapsed, the failed documents are written to Amazon S3. Default value is
     * 300 seconds. A value of 0 (zero) results in no retries.
     * </p>
     */
    private Integer durationInSeconds;

    /**
     * <p>
     * After an initial failure to deliver to Amazon ES, the total amount of
     * time during which Firehose re-attempts delivery. After this time has
     * elapsed, the failed documents are written to Amazon S3. Default value is
     * 300 seconds. A value of 0 (zero) results in no retries.
     * </p>
     * 
     * @param durationInSeconds
     *        After an initial failure to deliver to Amazon ES, the total amount
     *        of time during which Firehose re-attempts delivery. After this
     *        time has elapsed, the failed documents are written to Amazon S3.
     *        Default value is 300 seconds. A value of 0 (zero) results in no
     *        retries.
     */

    public void setDurationInSeconds(Integer durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }

    /**
     * <p>
     * After an initial failure to deliver to Amazon ES, the total amount of
     * time during which Firehose re-attempts delivery. After this time has
     * elapsed, the failed documents are written to Amazon S3. Default value is
     * 300 seconds. A value of 0 (zero) results in no retries.
     * </p>
     * 
     * @return After an initial failure to deliver to Amazon ES, the total
     *         amount of time during which Firehose re-attempts delivery. After
     *         this time has elapsed, the failed documents are written to Amazon
     *         S3. Default value is 300 seconds. A value of 0 (zero) results in
     *         no retries.
     */

    public Integer getDurationInSeconds() {
        return this.durationInSeconds;
    }

    /**
     * <p>
     * After an initial failure to deliver to Amazon ES, the total amount of
     * time during which Firehose re-attempts delivery. After this time has
     * elapsed, the failed documents are written to Amazon S3. Default value is
     * 300 seconds. A value of 0 (zero) results in no retries.
     * </p>
     * 
     * @param durationInSeconds
     *        After an initial failure to deliver to Amazon ES, the total amount
     *        of time during which Firehose re-attempts delivery. After this
     *        time has elapsed, the failed documents are written to Amazon S3.
     *        Default value is 300 seconds. A value of 0 (zero) results in no
     *        retries.
     * @return Returns a reference to this object so that method calls can be
     *         chained together.
     */

    public ElasticsearchRetryOptions withDurationInSeconds(
            Integer durationInSeconds) {
        setDurationInSeconds(durationInSeconds);
        return this;
    }

    /**
     * Returns a string representation of this object; useful for testing and
     * debugging.
     *
     * @return A string representation of this object.
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getDurationInSeconds() != null)
            sb.append("DurationInSeconds: " + getDurationInSeconds());
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;

        if (obj instanceof ElasticsearchRetryOptions == false)
            return false;
        ElasticsearchRetryOptions other = (ElasticsearchRetryOptions) obj;
        if (other.getDurationInSeconds() == null
                ^ this.getDurationInSeconds() == null)
            return false;
        if (other.getDurationInSeconds() != null
                && other.getDurationInSeconds().equals(
                        this.getDurationInSeconds()) == false)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;

        hashCode = prime
                * hashCode
                + ((getDurationInSeconds() == null) ? 0
                        : getDurationInSeconds().hashCode());
        return hashCode;
    }

    @Override
    public ElasticsearchRetryOptions clone() {
        try {
            return (ElasticsearchRetryOptions) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException(
                    "Got a CloneNotSupportedException from Object.clone() "
                            + "even though we're Cloneable!", e);
        }
    }
}
