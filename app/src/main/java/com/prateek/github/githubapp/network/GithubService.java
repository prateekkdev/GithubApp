package com.prateek.github.githubapp.network;

import com.prateek.github.githubapp.network.dto.CommentsDto;
import com.prateek.github.githubapp.network.dto.CrashlyticsDto;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by prateek.kesarwani on 18/07/17.
 */

public interface GithubService {

    String SERVICE_ENDPOINT = "https://api.github.com";

    @GET("repos/crashlytics/secureudid/issues")
    Observable<ArrayList<CrashlyticsDto>> listIssues();

    @GET
    Observable<ArrayList<CommentsDto>> listComments(@Url String url);
}