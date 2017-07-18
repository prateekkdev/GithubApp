package com.prateek.github.githubapp.network.dto;

/**
 * Created by prateek.kesarwani on 18/07/17.
 */

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.prateek.github.githubapp.utils.Utility;

import java.util.List;

public class CrashlyticsDto implements Comparable<CrashlyticsDto> {

    private String url;

    @SerializedName("repository_url")
    private String repositoryUrl;

    @SerializedName("labels_url")
    private String labelsUrl;

    @SerializedName("comments_url")
    private String commentsUrl;

    @SerializedName("events_url")
    private String eventsUrl;

    @SerializedName("html_url")
    private String htmlUrl;

    private Integer id;
    private Integer number;
    private String title;
    private UserDto userDto;
    private List<Object> labels = null;
    private String state;
    private Boolean locked;
    private Object assignee;
    private List<Object> assignees = null;
    private Object milestone;
    private Integer comments;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("closed_at")
    private Object closedAt;

    @SerializedName("pull_request")
    private PullRequestDto pullRequestDto;

    private String body;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    public void setRepositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
    }

    public String getLabelsUrl() {
        return labelsUrl;
    }

    public void setLabelsUrl(String labelsUrl) {
        this.labelsUrl = labelsUrl;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
    }

    public String getEventsUrl() {
        return eventsUrl;
    }

    public void setEventsUrl(String eventsUrl) {
        this.eventsUrl = eventsUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public List<Object> getLabels() {
        return labels;
    }

    public void setLabels(List<Object> labels) {
        this.labels = labels;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Object getAssignee() {
        return assignee;
    }

    public void setAssignee(Object assignee) {
        this.assignee = assignee;
    }

    public List<Object> getAssignees() {
        return assignees;
    }

    public void setAssignees(List<Object> assignees) {
        this.assignees = assignees;
    }

    public Object getMilestone() {
        return milestone;
    }

    public void setMilestone(Object milestone) {
        this.milestone = milestone;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(Object closedAt) {
        this.closedAt = closedAt;
    }

    public PullRequestDto getPullRequestDto() {
        return pullRequestDto;
    }

    public void setPullRequestDto(PullRequestDto pullRequestDto) {
        this.pullRequestDto = pullRequestDto;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public int compareTo(@NonNull CrashlyticsDto o) {
        long t1 = Utility.getTimestampFromDate(this.getUpdatedAt());
        long t2 = Utility.getTimestampFromDate(o.getUpdatedAt());

        if (t1 < t2) {
            return 1;
        } else if (t1 > t2) {
            return -1;
        }
        return 0;
    }
}

