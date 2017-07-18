package com.prateek.github.githubapp.application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.prateek.github.githubapp.network.GithubService;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by prateek.kesarwani on 18/07/17.
 */

@Module
public class PAppModule {

    @Provides
    @PAppScope
    public GithubService githubService(Retrofit retrofit) {
        return retrofit.create(GithubService.class);
    }

    @Provides
    @PAppScope
    public Retrofit retrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(GithubService.SERVICE_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @PAppScope
    public OkHttpClient okHttpClient(HttpLoggingInterceptor loggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @Provides
    @PAppScope
    public HttpLoggingInterceptor httpLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }

    @Provides
    @PAppScope
    public Gson gson() {
        return new GsonBuilder().create();
    }
}