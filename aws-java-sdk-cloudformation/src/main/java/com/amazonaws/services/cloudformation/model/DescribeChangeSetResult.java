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

package com.amazonaws.services.cloudformation.model;

import java.io.Serializable;

/**
 * <p>
 * The output for the <a>DescribeChangeSet</a> action.
 * </p>
 */
public class DescribeChangeSetResult implements Serializable, Cloneable {

    /**
     * <p>
     * The name of the change set.
     * </p>
     */
    private String changeSetName;
    /**
     * <p>
     * The ARN of the change set.
     * </p>
     */
    private String changeSetId;
    /**
     * <p>
     * The ARN of the stack that is associated with the change set.
     * </p>
     */
    private String stackId;
    /**
     * <p>
     * The name of the stack that is associated with the change set.
     * </p>
     */
    private String stackName;
    /**
     * <p>
     * Information about the change set.
     * </p>
     */
    private String description;
    /**
     * <p>
     * A list of <code>Parameter</code> structures that describes the input
     * parameters and their values used to create the change set. For more
     * information, see the <a href=
     * "http://docs.aws.amazon.com/AWSCloudFormation/latest/APIReference/API_Parameter.html"
     * >Parameter</a> data type.
     * </p>
     */
    private com.amazonaws.internal.SdkInternalList<Parameter> parameters;
    /**
     * <p>
     * The start time when the change set was created, in UTC.
     * </p>
     */
    private java.util.Date creationTime;
    /**
     * <p>
     * The current status of the change set, such as
     * <code>CREATE_IN_PROGRESS</code>, <code>CREATE_COMPLETE</code>, or
     * <code>FAILED</code>.
     * </p>
     */
    private String status;
    /**
     * <p>
     * A description of the change set's status. For example, if your attempt to
     * create a change set failed, AWS CloudFormation shows the error message.
     * </p>
     */
    private String statusReason;
    /**
     * <p>
     * The ARNs of the Amazon Simple Notification Service (Amazon SNS) topics
     * that will be associated with the stack if you execute the change set.
     * </p>
     */
    private com.amazonaws.internal.SdkInternalList<String> notificationARNs;
    /**
     * <p>
     * If you execute the change set, the list of capabilities that were
     * explicitly acknowledged when the change set was created.
     * </p>
     */
    private com.amazonaws.internal.SdkInternalList<String> capabilities;
    /**
     * <p>
     * If you execute the change set, the tags that will be associated with the
     * stack.
     * </p>
     */
    private com.amazonaws.internal.SdkInternalList<Tag> tags;
    /**
     * <p>
     * A list of <code>Change</code> structures that describes the resources AWS
     * CloudFormation changes if you execute the change set.
     * </p>
     */
    private com.amazonaws.internal.SdkInternalList<Change> changes;
    /**
     * <p>
     * If the output exceeds 1 MB, a string that identifies the next page of
     * changes. If there is no additional page, this value is null.
     * </p>
     */
    private String nextToken;

    /**
     * <p>
     * The name of the change set.
     * </p>
     * 
     * @param changeSetName
     *        The name of the change set.
     */

    public void setChangeSetName(String changeSetName) {
        this.changeSetName = changeSetName;
    }

    /**
     * <p>
     * The name of the change set.
     * </p>
     * 
     * @return The name of the change set.
     */

    public String getChangeSetName() {
        return this.changeSetName;
    }

    /**
     * <p>
     * The name of the change set.
     * </p>
     * 
     * @param changeSetName
     *        The name of the change set.
     * @return Returns a reference to this object so that method calls can be
     *         chained together.
     */

    public DescribeChangeSetResult withChangeSetName(String changeSetName) {
        setChangeSetName(changeSetName);
        return this;
    }

    /**
     * <p>
     * The ARN of the change set.
     * </p>
     * 
     * @param changeSetId
     *        The ARN of the change set.
     */

    public void setChangeSetId(String changeSetId) {
        this.changeSetId = changeSetId;
    }

    /**
     * <p>
     * The ARN of the change set.
     * </p>
     * 
     * @return The ARN of the change set.
     */

    public String getChangeSetId() {
        return this.changeSetId;
    }

    /**
     * <p>
     * The ARN of the change set.
     * </p>
     * 
     * @param changeSetId
     *        The ARN of the change set.
     * @return Returns a reference to this object so that method calls can be
     *         chained together.
     */

    public DescribeChangeSetResult withChangeSetId(String changeSetId) {
        setChangeSetId(changeSetId);
        return this;
    }

    /**
     * <p>
     * The ARN of the stack that is associated with the change set.
     * </p>
     * 
     * @param stackId
     *        The ARN of the stack that is associated with the change set.
     */

    public void setStackId(String stackId) {
        this.stackId = stackId;
    }

    /**
     * <p>
     * The ARN of the stack that is associated with the change set.
     * </p>
     * 
     * @return The ARN of the stack that is associated with the change set.
     */

    public String getStackId() {
        return this.stackId;
    }

    /**
     * <p>
     * The ARN of the stack that is associated with the change set.
     * </p>
     * 
     * @param stackId
     *        The ARN of the stack that is associated with the change set.
     * @return Returns a reference to this object so that method calls can be
     *         chained together.
     */

    public DescribeChangeSetResult withStackId(String stackId) {
        setStackId(stackId);
        return this;
    }

    /**
     * <p>
     * The name of the stack that is associated with the change set.
     * </p>
     * 
     * @param stackName
     *        The name of the stack that is associated with the change set.
     */

    public void setStackName(String stackName) {
        this.stackName = stackName;
    }

    /**
     * <p>
     * The name of the stack that is associated with the change set.
     * </p>
     * 
     * @return The name of the stack that is associated with the change set.
     */

    public String getStackName() {
        return this.stackName;
    }

    /**
     * <p>
     * The name of the stack that is associated with the change set.
     * </p>
     * 
     * @param stackName
     *        The name of the stack that is associated with the change set.
     * @return Returns a reference to this object so that method calls can be
     *         chained together.
     */

    public DescribeChangeSetResult withStackName(String stackName) {
        setStackName(stackName);
        return this;
    }

    /**
     * <p>
     * Information about the change set.
     * </p>
     * 
     * @param description
     *        Information about the change set.
     */

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * <p>
     * Information about the change set.
     * </p>
     * 
     * @return Information about the change set.
     */

    public String getDescription() {
        return this.description;
    }

    /**
     * <p>
     * Information about the change set.
     * </p>
     * 
     * @param description
     *        Information about the change set.
     * @return Returns a reference to this object so that method calls can be
     *         chained together.
     */

    public DescribeChangeSetResult withDescription(String description) {
        setDescription(description);
        return this;
    }

    /**
     * <p>
     * A list of <code>Parameter</code> structures that describes the input
     * parameters and their values used to create the change set. For more
     * information, see the <a href=
     * "http://docs.aws.amazon.com/AWSCloudFormation/latest/APIReference/API_Parameter.html"
     * >Parameter</a> data type.
     * </p>
     * 
     * @return A list of <code>Parameter</code> structures that describes the
     *         input parameters and their values used to create the change set.
     *         For more information, see the <a href=
     *         "http://docs.aws.amazon.com/AWSCloudFormation/latest/APIReference/API_Parameter.html"
     *         >Parameter</a> data type.
     */

    public java.util.List<Parameter> getParameters() {
        if (parameters == null) {
            parameters = new com.amazonaws.internal.SdkInternalList<Parameter>();
        }
        return parameters;
    }

    /**
     * <p>
     * A list of <code>Parameter</code> structures that describes the input
     * parameters and their values used to create the change set. For more
     * information, see the <a href=
     * "http://docs.aws.amazon.com/AWSCloudFormation/latest/APIReference/API_Parameter.html"
     * >Parameter</a> data type.
     * </p>
     * 
     * @param parameters
     *        A list of <code>Parameter</code> structures that describes the
     *        input parameters and their values used to create the change set.
     *        For more information, see the <a href=
     *        "http://docs.aws.amazon.com/AWSCloudFormation/latest/APIReference/API_Parameter.html"
     *        >Parameter</a> data type.
     */

    public void setParameters(java.util.Collection<Parameter> parameters) {
        if (parameters == null) {
            this.parameters = null;
            return;
        }

        this.parameters = new com.amazonaws.internal.SdkInternalList<Parameter>(
                parameters);
    }

    /**
     * <p>
     * A list of <code>Parameter</code> structures that describes the input
     * parameters and their values used to create the change set. For more
     * information, see the <a href=
     * "http://docs.aws.amazon.com/AWSCloudFormation/latest/APIReference/API_Parameter.html"
     * >Parameter</a> data type.
     * </p>
     * <p>
     * <b>NOTE:</b> This method appends the values to the existing list (if
     * any). Use {@link #setParameters(java.util.Collection)} or
     * {@link #withParameters(java.util.Collection)} if you want to override the
     * existing values.
     * </p>
     * 
     * @param parameters
     *        A list of <code>Parameter</code> structures that describes the
     *        input parameters and their values used to create the change set.
     *        For more information, see the <a href=
     *        "http://docs.aws.amazon.com/AWSCloudFormation/latest/APIReference/API_Parameter.html"
     *        >Parameter</a> data type.
     * @return Returns a reference to this object so that method calls can be
     *         chained together.
     */

    public DescribeChangeSetResult withParameters(Parameter... parameters) {
        if (this.parameters == null) {
            setParameters(new com.amazonaws.internal.SdkInternalList<Parameter>(
                    parameters.length));
        }
        for (Parameter ele : parameters) {
            this.parameters.add(ele);
        }
        return this;
    }

    /**
     * <p>
     * A list of <code>Parameter</code> structures that describes the input
     * parameters and their values used to create the change set. For more
     * information, see the <a href=
     * "http://docs.aws.amazon.com/AWSCloudFormation/latest/APIReference/API_Parameter.html"
     * >Parameter</a> data type.
     * </p>
     * 
     * @param parameters
     *        A list of <code>Parameter</code> structures that describes the
     *        input parameters and their values used to create the change set.
     *        For more information, see the <a href=
     *        "http://docs.aws.amazon.com/AWSCloudFormation/latest/APIReference/API_Parameter.html"
     *        >Parameter</a> data type.
     * @return Returns a reference to this object so that method calls can be
     *         chained together.
     */

    public DescribeChangeSetResult withParameters(
            java.util.Collection<Parameter> parameters) {
        setParameters(parameters);
        return this;
    }

    /**
     * <p>
     * The start time when the change set was created, in UTC.
     * </p>
     * 
     * @param creationTime
     *        The start time when the change set was created, in UTC.
     */

    public void setCreationTime(java.util.Date creationTime) {
        this.creationTime = creationTime;
    }

    /**
     * <p>
     * The start time when the change set was created, in UTC.
     * </p>
     * 
     * @return The start time when the change set was created, in UTC.
     */

    public java.util.Date getCreationTime() {
        return this.creationTime;
    }

    /**
     * <p>
     * The start time when the change set was created, in UTC.
     * </p>
     * 
     * @param creationTime
     *        The start time when the change set was created, in UTC.
     * @return Returns a reference to this object so that method calls can be
     *         chained together.
     */

    public DescribeChangeSetResult withCreationTime(java.util.Date creationTime) {
        setCreationTime(creationTime);
        return this;
    }

    /**
     * <p>
     * The current status of the change set, such as
     * <code>CREATE_IN_PROGRESS</code>, <code>CREATE_COMPLETE</code>, or
     * <code>FAILED</code>.
     * </p>
     * 
     * @param status
     *        The current status of the change set, such as
     *        <code>CREATE_IN_PROGRESS</code>, <code>CREATE_COMPLETE</code>, or
     *        <code>FAILED</code>.
     * @see ChangeSetStatus
     */

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * <p>
     * The current status of the change set, such as
     * <code>CREATE_IN_PROGRESS</code>, <code>CREATE_COMPLETE</code>, or
     * <code>FAILED</code>.
     * </p>
     * 
     * @return The current status of the change set, such as
     *         <code>CREATE_IN_PROGRESS</code>, <code>CREATE_COMPLETE</code>, or
     *         <code>FAILED</code>.
     * @see ChangeSetStatus
     */

    public String getStatus() {
        return this.status;
    }

    /**
     * <p>
     * The current status of the change set, such as
     * <code>CREATE_IN_PROGRESS</code>, <code>CREATE_COMPLETE</code>, or
     * <code>FAILED</code>.
     * </p>
     * 
     * @param status
     *        The current status of the change set, such as
     *        <code>CREATE_IN_PROGRESS</code>, <code>CREATE_COMPLETE</code>, or
     *        <code>FAILED</code>.
     * @return Returns a reference to this object so that method calls can be
     *         chained together.
     * @see ChangeSetStatus
     */

    public DescribeChangeSetResult withStatus(String status) {
        setStatus(status);
        return this;
    }

    /**
     * <p>
     * The current status of the change set, such as
     * <code>CREATE_IN_PROGRESS</code>, <code>CREATE_COMPLETE</code>, or
     * <code>FAILED</code>.
     * </p>
     * 
     * @param status
     *        The current status of the change set, such as
     *        <code>CREATE_IN_PROGRESS</code>, <code>CREATE_COMPLETE</code>, or
     *        <code>FAILED</code>.
     * @see ChangeSetStatus
     */

    public void setStatus(ChangeSetStatus status) {
        this.status = status.toString();
    }

    /**
     * <p>
     * The current status of the change set, such as
     * <code>CREATE_IN_PROGRESS</code>, <code>CREATE_COMPLETE</code>, or
     * <code>FAILED</code>.
     * </p>
     * 
     * @param status
     *        The current status of the change set, such as
     *        <code>CREATE_IN_PROGRESS</code>, <code>CREATE_COMPLETE</code>, or
     *        <code>FAILED</code>.
     * @return Returns a reference to this object so that method calls can be
     *         chained together.
     * @see ChangeSetStatus
     */

    public DescribeChangeSetResult withStatus(ChangeSetStatus status) {
        setStatus(status);
        return this;
    }

    /**
     * <p>
     * A description of the change set's status. For example, if your attempt to
     * create a change set failed, AWS CloudFormation shows the error message.
     * </p>
     * 
     * @param statusReason
     *        A description of the change set's status. For example, if your
     *        attempt to create a change set failed, AWS CloudFormation shows
     *        the error message.
     */

    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }

    /**
     * <p>
     * A description of the change set's status. For example, if your attempt to
     * create a change set failed, AWS CloudFormation shows the error message.
     * </p>
     * 
     * @return A description of the change set's status. For example, if your
     *         attempt to create a change set failed, AWS CloudFormation shows
     *         the error message.
     */

    public String getStatusReason() {
        return this.statusReason;
    }

    /**
     * <p>
     * A description of the change set's status. For example, if your attempt to
     * create a change set failed, AWS CloudFormation shows the error message.
     * </p>
     * 
     * @param statusReason
     *        A description of the change set's status. For example, if your
     *        attempt to create a change set failed, AWS CloudFormation shows
     *        the error message.
     * @return Returns a reference to this object so that method calls can be
     *         chained together.
     */

    public DescribeChangeSetResult withStatusReason(String statusReason) {
        setStatusReason(statusReason);
        return this;
    }

    /**
     * <p>
     * The ARNs of the Amazon Simple Notification Service (Amazon SNS) topics
     * that will be associated with the stack if you execute the change set.
     * </p>
     * 
     * @return The ARNs of the Amazon Simple Notification Service (Amazon SNS)
     *         topics that will be associated with the stack if you execute the
     *         change set.
     */

    public java.util.List<String> getNotificationARNs() {
        if (notificationARNs == null) {
            notificationARNs = new com.amazonaws.internal.SdkInternalList<String>();
        }
        return notificationARNs;
    }

    /**
     * <p>
     * The ARNs of the Amazon Simple Notification Service (Amazon SNS) topics
     * that will be associated with the stack if you execute the change set.
     * </p>
     * 
     * @param notificationARNs
     *        The ARNs of the Amazon Simple Notification Service (Amazon SNS)
     *        topics that will be associated with the stack if you execute the
     *        change set.
     */

    public void setNotificationARNs(
            java.util.Collection<String> notificationARNs) {
        if (notificationARNs == null) {
            this.notificationARNs = null;
            return;
        }

        this.notificationARNs = new com.amazonaws.internal.SdkInternalList<String>(
                notificationARNs);
    }

    /**
     * <p>
     * The ARNs of the Amazon Simple Notification Service (Amazon SNS) topics
     * that will be associated with the stack if you execute the change set.
     * </p>
     * <p>
     * <b>NOTE:</b> This method appends the values to the existing list (if
     * any). Use {@link #setNotificationARNs(java.util.Collection)} or
     * {@link #withNotificationARNs(java.util.Collection)} if you want to
     * override the existing values.
     * </p>
     * 
     * @param notificationARNs
     *        The ARNs of the Amazon Simple Notification Service (Amazon SNS)
     *        topics that will be associated with the stack if you execute the
     *        change set.
     * @return Returns a reference to this object so that method calls can be
     *         chained together.
     */

    public DescribeChangeSetResult withNotificationARNs(
            String... notificationARNs) {
        if (this.notificationARNs == null) {
            setNotificationARNs(new com.amazonaws.internal.SdkInternalList<String>(
                    notificationARNs.length));
        }
        for (String ele : notificationARNs) {
            this.notificationARNs.add(ele);
        }
        return this;
    }

    /**
     * <p>
     * The ARNs of the Amazon Simple Notification Service (Amazon SNS) topics
     * that will be associated with the stack if you execute the change set.
     * </p>
     * 
     * @param notificationARNs
     *        The ARNs of the Amazon Simple Notification Service (Amazon SNS)
     *        topics that will be associated with the stack if you execute the
     *        change set.
     * @return Returns a reference to this object so that method calls can be
     *         chained together.
     */

    public DescribeChangeSetResult withNotificationARNs(
            java.util.Collection<String> notificationARNs) {
        setNotificationARNs(notificationARNs);
        return this;
    }

    /**
     * <p>
     * If you execute the change set, the list of capabilities that were
     * explicitly acknowledged when the change set was created.
     * </p>
     * 
     * @return If you execute the change set, the list of capabilities that were
     *         explicitly acknowledged when the change set was created.
     * @see Capability
     */

    public java.util.List<String> getCapabilities() {
        if (capabilities == null) {
            capabilities = new com.amazonaws.internal.SdkInternalList<String>();
        }
        return capabilities;
    }

    /**
     * <p>
     * If you execute the change set, the list of capabilities that were
     * explicitly acknowledged when the change set was created.
     * </p>
     * 
     * @param capabilities
     *        If you execute the change set, the list of capabilities that were
     *        explicitly acknowledged when the change set was created.
     * @see Capability
     */

    public void setCapabilities(java.util.Collection<String> capabilities) {
        if (capabilities == null) {
            this.capabilities = null;
            return;
        }

        this.capabilities = new com.amazonaws.internal.SdkInternalList<String>(
                capabilities);
    }

    /**
     * <p>
     * If you execute the change set, the list of capabilities that were
     * explicitly acknowledged when the change set was created.
     * </p>
     * <p>
     * <b>NOTE:</b> This method appends the values to the existing list (if
     * any). Use {@link #setCapabilities(java.util.Collection)} or
     * {@link #withCapabilities(java.util.Collection)} if you want to override
     * the existing values.
     * </p>
     * 
     * @param capabilities
     *        If you execute the change set, the list of capabilities that were
     *        explicitly acknowledged when the change set was created.
     * @return Returns a reference to this object so that method calls can be
     *         chained together.
     * @see Capability
     */

    public DescribeChangeSetResult withCapabilities(String... capabilities) {
        if (this.capabilities == null) {
            setCapabilities(new com.amazonaws.internal.SdkInternalList<String>(
                    capabilities.length));
        }
        for (String ele : capabilities) {
            this.capabilities.add(ele);
        }
        return this;
    }

    /**
     * <p>
     * If you execute the change set, the list of capabilities that were
     * explicitly acknowledged when the change set was created.
     * </p>
     * 
     * @param capabilities
     *        If you execute the change set, the list of capabilities that were
     *        explicitly acknowledged when the change set was created.
     * @return Returns a reference to this object so that method calls can be
     *         chained together.
     * @see Capability
     */

    public DescribeChangeSetResult withCapabilities(
            java.util.Collection<String> capabilities) {
        setCapabilities(capabilities);
        return this;
    }

    /**
     * <p>
     * If you execute the change set, the list of capabilities that were
     * explicitly acknowledged when the change set was created.
     * </p>
     * 
     * @param capabilities
     *        If you execute the change set, the list of capabilities that were
     *        explicitly acknowledged when the change set was created.
     * @return Returns a reference to this object so that method calls can be
     *         chained together.
     * @see Capability
     */

    public DescribeChangeSetResult withCapabilities(Capability... capabilities) {
        com.amazonaws.internal.SdkInternalList<String> capabilitiesCopy = new com.amazonaws.internal.SdkInternalList<String>(
                capabilities.length);
        for (Capability value : capabilities) {
            capabilitiesCopy.add(value.toString());
        }
        if (getCapabilities() == null) {
            setCapabilities(capabilitiesCopy);
        } else {
            getCapabilities().addAll(capabilitiesCopy);
        }
        return this;
    }

    /**
     * <p>
     * If you execute the change set, the tags that will be associated with the
     * stack.
     * </p>
     * 
     * @return If you execute the change set, the tags that will be associated
     *         with the stack.
     */

    public java.util.List<Tag> getTags() {
        if (tags == null) {
            tags = new com.amazonaws.internal.SdkInternalList<Tag>();
        }
        return tags;
    }

    /**
     * <p>
     * If you execute the change set, the tags that will be associated with the
     * stack.
     * </p>
     * 
     * @param tags
     *        If you execute the change set, the tags that will be associated
     *        with the stack.
     */

    public void setTags(java.util.Collection<Tag> tags) {
        if (tags == null) {
            this.tags = null;
            return;
        }

        this.tags = new com.amazonaws.internal.SdkInternalList<Tag>(tags);
    }

    /**
     * <p>
     * If you execute the change set, the tags that will be associated with the
     * stack.
     * </p>
     * <p>
     * <b>NOTE:</b> This method appends the values to the existing list (if
     * any). Use {@link #setTags(java.util.Collection)} or
     * {@link #withTags(java.util.Collection)} if you want to override the
     * existing values.
     * </p>
     * 
     * @param tags
     *        If you execute the change set, the tags that will be associated
     *        with the stack.
     * @return Returns a reference to this object so that method calls can be
     *         chained together.
     */

    public DescribeChangeSetResult withTags(Tag... tags) {
        if (this.tags == null) {
            setTags(new com.amazonaws.internal.SdkInternalList<Tag>(tags.length));
        }
        for (Tag ele : tags) {
            this.tags.add(ele);
        }
        return this;
    }

    /**
     * <p>
     * If you execute the change set, the tags that will be associated with the
     * stack.
     * </p>
     * 
     * @param tags
     *        If you execute the change set, the tags that will be associated
     *        with the stack.
     * @return Returns a reference to this object so that method calls can be
     *         chained together.
     */

    public DescribeChangeSetResult withTags(java.util.Collection<Tag> tags) {
        setTags(tags);
        return this;
    }

    /**
     * <p>
     * A list of <code>Change</code> structures that describes the resources AWS
     * CloudFormation changes if you execute the change set.
     * </p>
     * 
     * @return A list of <code>Change</code> structures that describes the
     *         resources AWS CloudFormation changes if you execute the change
     *         set.
     */

    public java.util.List<Change> getChanges() {
        if (changes == null) {
            changes = new com.amazonaws.internal.SdkInternalList<Change>();
        }
        return changes;
    }

    /**
     * <p>
     * A list of <code>Change</code> structures that describes the resources AWS
     * CloudFormation changes if you execute the change set.
     * </p>
     * 
     * @param changes
     *        A list of <code>Change</code> structures that describes the
     *        resources AWS CloudFormation changes if you execute the change
     *        set.
     */

    public void setChanges(java.util.Collection<Change> changes) {
        if (changes == null) {
            this.changes = null;
            return;
        }

        this.changes = new com.amazonaws.internal.SdkInternalList<Change>(
                changes);
    }

    /**
     * <p>
     * A list of <code>Change</code> structures that describes the resources AWS
     * CloudFormation changes if you execute the change set.
     * </p>
     * <p>
     * <b>NOTE:</b> This method appends the values to the existing list (if
     * any). Use {@link #setChanges(java.util.Collection)} or
     * {@link #withChanges(java.util.Collection)} if you want to override the
     * existing values.
     * </p>
     * 
     * @param changes
     *        A list of <code>Change</code> structures that describes the
     *        resources AWS CloudFormation changes if you execute the change
     *        set.
     * @return Returns a reference to this object so that method calls can be
     *         chained together.
     */

    public DescribeChangeSetResult withChanges(Change... changes) {
        if (this.changes == null) {
            setChanges(new com.amazonaws.internal.SdkInternalList<Change>(
                    changes.length));
        }
        for (Change ele : changes) {
            this.changes.add(ele);
        }
        return this;
    }

    /**
     * <p>
     * A list of <code>Change</code> structures that describes the resources AWS
     * CloudFormation changes if you execute the change set.
     * </p>
     * 
     * @param changes
     *        A list of <code>Change</code> structures that describes the
     *        resources AWS CloudFormation changes if you execute the change
     *        set.
     * @return Returns a reference to this object so that method calls can be
     *         chained together.
     */

    public DescribeChangeSetResult withChanges(
            java.util.Collection<Change> changes) {
        setChanges(changes);
        return this;
    }

    /**
     * <p>
     * If the output exceeds 1 MB, a string that identifies the next page of
     * changes. If there is no additional page, this value is null.
     * </p>
     * 
     * @param nextToken
     *        If the output exceeds 1 MB, a string that identifies the next page
     *        of changes. If there is no additional page, this value is null.
     */

    public void setNextToken(String nextToken) {
        this.nextToken = nextToken;
    }

    /**
     * <p>
     * If the output exceeds 1 MB, a string that identifies the next page of
     * changes. If there is no additional page, this value is null.
     * </p>
     * 
     * @return If the output exceeds 1 MB, a string that identifies the next
     *         page of changes. If there is no additional page, this value is
     *         null.
     */

    public String getNextToken() {
        return this.nextToken;
    }

    /**
     * <p>
     * If the output exceeds 1 MB, a string that identifies the next page of
     * changes. If there is no additional page, this value is null.
     * </p>
     * 
     * @param nextToken
     *        If the output exceeds 1 MB, a string that identifies the next page
     *        of changes. If there is no additional page, this value is null.
     * @return Returns a reference to this object so that method calls can be
     *         chained together.
     */

    public DescribeChangeSetResult withNextToken(String nextToken) {
        setNextToken(nextToken);
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
        if (getChangeSetName() != null)
            sb.append("ChangeSetName: " + getChangeSetName() + ",");
        if (getChangeSetId() != null)
            sb.append("ChangeSetId: " + getChangeSetId() + ",");
        if (getStackId() != null)
            sb.append("StackId: " + getStackId() + ",");
        if (getStackName() != null)
            sb.append("StackName: " + getStackName() + ",");
        if (getDescription() != null)
            sb.append("Description: " + getDescription() + ",");
        if (getParameters() != null)
            sb.append("Parameters: " + getParameters() + ",");
        if (getCreationTime() != null)
            sb.append("CreationTime: " + getCreationTime() + ",");
        if (getStatus() != null)
            sb.append("Status: " + getStatus() + ",");
        if (getStatusReason() != null)
            sb.append("StatusReason: " + getStatusReason() + ",");
        if (getNotificationARNs() != null)
            sb.append("NotificationARNs: " + getNotificationARNs() + ",");
        if (getCapabilities() != null)
            sb.append("Capabilities: " + getCapabilities() + ",");
        if (getTags() != null)
            sb.append("Tags: " + getTags() + ",");
        if (getChanges() != null)
            sb.append("Changes: " + getChanges() + ",");
        if (getNextToken() != null)
            sb.append("NextToken: " + getNextToken());
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;

        if (obj instanceof DescribeChangeSetResult == false)
            return false;
        DescribeChangeSetResult other = (DescribeChangeSetResult) obj;
        if (other.getChangeSetName() == null ^ this.getChangeSetName() == null)
            return false;
        if (other.getChangeSetName() != null
                && other.getChangeSetName().equals(this.getChangeSetName()) == false)
            return false;
        if (other.getChangeSetId() == null ^ this.getChangeSetId() == null)
            return false;
        if (other.getChangeSetId() != null
                && other.getChangeSetId().equals(this.getChangeSetId()) == false)
            return false;
        if (other.getStackId() == null ^ this.getStackId() == null)
            return false;
        if (other.getStackId() != null
                && other.getStackId().equals(this.getStackId()) == false)
            return false;
        if (other.getStackName() == null ^ this.getStackName() == null)
            return false;
        if (other.getStackName() != null
                && other.getStackName().equals(this.getStackName()) == false)
            return false;
        if (other.getDescription() == null ^ this.getDescription() == null)
            return false;
        if (other.getDescription() != null
                && other.getDescription().equals(this.getDescription()) == false)
            return false;
        if (other.getParameters() == null ^ this.getParameters() == null)
            return false;
        if (other.getParameters() != null
                && other.getParameters().equals(this.getParameters()) == false)
            return false;
        if (other.getCreationTime() == null ^ this.getCreationTime() == null)
            return false;
        if (other.getCreationTime() != null
                && other.getCreationTime().equals(this.getCreationTime()) == false)
            return false;
        if (other.getStatus() == null ^ this.getStatus() == null)
            return false;
        if (other.getStatus() != null
                && other.getStatus().equals(this.getStatus()) == false)
            return false;
        if (other.getStatusReason() == null ^ this.getStatusReason() == null)
            return false;
        if (other.getStatusReason() != null
                && other.getStatusReason().equals(this.getStatusReason()) == false)
            return false;
        if (other.getNotificationARNs() == null
                ^ this.getNotificationARNs() == null)
            return false;
        if (other.getNotificationARNs() != null
                && other.getNotificationARNs().equals(
                        this.getNotificationARNs()) == false)
            return false;
        if (other.getCapabilities() == null ^ this.getCapabilities() == null)
            return false;
        if (other.getCapabilities() != null
                && other.getCapabilities().equals(this.getCapabilities()) == false)
            return false;
        if (other.getTags() == null ^ this.getTags() == null)
            return false;
        if (other.getTags() != null
                && other.getTags().equals(this.getTags()) == false)
            return false;
        if (other.getChanges() == null ^ this.getChanges() == null)
            return false;
        if (other.getChanges() != null
                && other.getChanges().equals(this.getChanges()) == false)
            return false;
        if (other.getNextToken() == null ^ this.getNextToken() == null)
            return false;
        if (other.getNextToken() != null
                && other.getNextToken().equals(this.getNextToken()) == false)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;

        hashCode = prime
                * hashCode
                + ((getChangeSetName() == null) ? 0 : getChangeSetName()
                        .hashCode());
        hashCode = prime
                * hashCode
                + ((getChangeSetId() == null) ? 0 : getChangeSetId().hashCode());
        hashCode = prime * hashCode
                + ((getStackId() == null) ? 0 : getStackId().hashCode());
        hashCode = prime * hashCode
                + ((getStackName() == null) ? 0 : getStackName().hashCode());
        hashCode = prime
                * hashCode
                + ((getDescription() == null) ? 0 : getDescription().hashCode());
        hashCode = prime * hashCode
                + ((getParameters() == null) ? 0 : getParameters().hashCode());
        hashCode = prime
                * hashCode
                + ((getCreationTime() == null) ? 0 : getCreationTime()
                        .hashCode());
        hashCode = prime * hashCode
                + ((getStatus() == null) ? 0 : getStatus().hashCode());
        hashCode = prime
                * hashCode
                + ((getStatusReason() == null) ? 0 : getStatusReason()
                        .hashCode());
        hashCode = prime
                * hashCode
                + ((getNotificationARNs() == null) ? 0 : getNotificationARNs()
                        .hashCode());
        hashCode = prime
                * hashCode
                + ((getCapabilities() == null) ? 0 : getCapabilities()
                        .hashCode());
        hashCode = prime * hashCode
                + ((getTags() == null) ? 0 : getTags().hashCode());
        hashCode = prime * hashCode
                + ((getChanges() == null) ? 0 : getChanges().hashCode());
        hashCode = prime * hashCode
                + ((getNextToken() == null) ? 0 : getNextToken().hashCode());
        return hashCode;
    }

    @Override
    public DescribeChangeSetResult clone() {
        try {
            return (DescribeChangeSetResult) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException(
                    "Got a CloneNotSupportedException from Object.clone() "
                            + "even though we're Cloneable!", e);
        }
    }
}
