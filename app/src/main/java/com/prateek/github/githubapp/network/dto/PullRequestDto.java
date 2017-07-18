package com.prateek.github.githubapp.network.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by prateek.kesarwani on 18/07/17.
 */
class PullRequestDto {

    private String url;

    @SerializedName("html_url")
    private String htmlUrl;

    @SerializedName("diff_url")
    private String diffUrl;

    @SerializedName("patch_url")
    private String patchUrl;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getDiffUrl() {
        return diffUrl;
    }

    public void setDiffUrl(String diffUrl) {
        this.diffUrl = diffUrl;
    }

    public String getPatchUrl() {
        return patchUrl;
    }

    public void setPatchUrl(String patchUrl) {
        this.patchUrl = patchUrl;
    }
}