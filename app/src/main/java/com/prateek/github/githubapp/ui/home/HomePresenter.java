package com.prateek.github.githubapp.ui.home;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.LruCache;

import com.prateek.github.githubapp.R;
import com.prateek.github.githubapp.application.PApp;
import com.prateek.github.githubapp.network.GithubService;
import com.prateek.github.githubapp.network.dto.CommentsDto;
import com.prateek.github.githubapp.network.dto.CrashlyticsDto;

import java.util.ArrayList;
import java.util.Collections;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by prateek.kesarwani on 18/07/17.
 */

public class HomePresenter implements IHomeContract.IHomePresenter {

    private IHomeContract.IHomeView mainView;

    @Inject
    public GithubService githubService;

    @Inject
    public LruCache<String, String> commentsCache;

    @Inject
    public Context appContext;

    private Observable<ArrayList<CrashlyticsDto>> sortedCrashlyticsIssuesObservable;

    private CompositeDisposable disposableList = new CompositeDisposable();

    public HomePresenter(IHomeContract.IHomeView mainView) {
        this.mainView = mainView;

        PApp.getAppComponent().inject(this);
        DaggerHomeComponent.builder().build().inject(this);
    }

    private Observable<ArrayList<CrashlyticsDto>> getSortedCrashlyticsIssuesObservable() {
        if (sortedCrashlyticsIssuesObservable == null) {
            sortedCrashlyticsIssuesObservable = githubService.listIssues().subscribeOn(Schedulers.io())
                    .map(list -> {
                        Collections.sort(list);
                        return list;
                    })
                    .observeOn(AndroidSchedulers.mainThread());
        }

        return sortedCrashlyticsIssuesObservable;
    }


    @Override
    public void fetchIssuesList() {
        Log.e("Prateek", "presenter, githubservice: " + githubService.toString());

        disposableList.add(getSortedCrashlyticsIssuesObservable()
                .doOnSubscribe(disposable -> mainView.startProgress())
                .doOnError(error -> {
                            mainView.stopProgress();
                            mainView.showError();
                        }
                ).doOnNext(crashlyticsModelList -> {
                    mainView.stopProgress();
                    mainView.updateIssuesList(crashlyticsModelList);

                }).subscribe());
    }

    @Override
    public void onStopCalled() {
        disposableList.dispose();
        mainView.stopProgress();
    }

    @Override
    public void fetchComments(String commentsUrl) {
        Log.e("Prateek", "adapter, githubservice: " + githubService.toString());

        String commentCacheString = commentsCache.get(commentsUrl);

        if (!TextUtils.isEmpty(commentCacheString)) {
            mainView.showCommentsDialog(commentCacheString);
            return;
        }

        disposableList.add(
                githubService.listComments(commentsUrl)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable -> mainView.startProgress())
                        .doOnNext(commentsDtos -> {
                            StringBuilder comments = new StringBuilder("");

                            for (CommentsDto commentsDto : commentsDtos) {

                                if (commentsDto.getUserDto() != null) {
                                    comments.append("\n" + appContext.getResources().getString(R.string.author_title) + " " + commentsDto.getUserDto().getLogin());
                                }

                                comments.append("\n" + appContext.getResources().getString(R.string.comment_title) + " " + commentsDto.getBody());
                            }

                            if (TextUtils.isEmpty(comments)) {
                                comments.append(appContext.getResources().getString(R.string.comments_no_text));
                            } else {

                                // If network gives comments, then only put it to cache.
                                commentsCache.put(commentsUrl, comments.toString());
                            }

                            mainView.stopProgress();

                            mainView.showCommentsDialog(comments.toString());
                        })
                        .doOnError(error -> {
                            mainView.stopProgress();
                            mainView.showError();
                        }).subscribe());
    }
}