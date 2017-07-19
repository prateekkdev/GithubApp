package com.prateek.github.githubapp.ui.home;

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

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by prateek.kesarwani on 18/07/17.
 */

public class HomePresenter implements IHomeContract.IMainPresenter {

    private IHomeContract.IMainView mainView;

    GithubService githubService;

    private Observable<ArrayList<CrashlyticsDto>> sortedCrashlyticsIssuesObservable;

    private LruCache<String, String> commentsCache;
    private static final int COMMENTS_CACHE_SIZE = 10;

    public HomePresenter(IHomeContract.IMainView mainView) {
        this.mainView = mainView;

        githubService = PApp.getApp().githubService();

        commentsCache = new LruCache<>(COMMENTS_CACHE_SIZE);
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

        // TODO Observable should be disposed when presenter not goes out or else might leak for a little while until request is running and then might throw IllegalStateException
        // TODO But would do for elaboration.
        getSortedCrashlyticsIssuesObservable()
                .subscribe(new Observer<ArrayList<CrashlyticsDto>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mainView.startProgress();
                    }

                    @Override
                    public void onNext(ArrayList<CrashlyticsDto> crashlyticsModelList) {
                        mainView.stopProgress();
                        mainView.updateIssuesList(crashlyticsModelList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mainView.stopProgress();
                        mainView.showError();
                    }

                    @Override
                    public void onComplete() {
                        // Not required
                    }
                });

    }

    @Override
    public void fetchComments(String commentsUrl) {
        Log.e("Prateek", "adapter, githubservice: " + githubService.toString());

        String commentCacheString = commentsCache.get(commentsUrl);

        if (!TextUtils.isEmpty(commentCacheString)) {
            mainView.showCommentsDialog(commentCacheString);
            return;
        }

        // TODO Observable should be disposed when presenter goes out or else might leak for a little while until request is running and then might throw IllegalStateException
        // TODO But would do for elaboration.
        githubService.listComments(commentsUrl)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<CommentsDto>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mainView.startProgress();
                    }

                    @Override
                    public void onNext(ArrayList<CommentsDto> commentsDtos) {

                        StringBuilder comments = new StringBuilder("");

                        for (CommentsDto commentsDto : commentsDtos) {

                            if (commentsDto.getUserDto() != null) {
                                comments.append("\n" + PApp.getApp().getResources().getString(R.string.author_title) + " " + commentsDto.getUserDto().getLogin());
                            }

                            comments.append("\n" + PApp.getApp().getResources().getString(R.string.comment_title) + " " + commentsDto.getBody());
                        }

                        if (TextUtils.isEmpty(comments)) {
                            comments.append(PApp.getApp().getResources().getString(R.string.comments_no_text));
                        } else {

                            // If network gives comments, then only put it to cache.
                            commentsCache.put(commentsUrl, comments.toString());
                        }

                        mainView.stopProgress();

                        mainView.showCommentsDialog(comments.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        mainView.stopProgress();
                        mainView.showError();
                    }

                    @Override
                    public void onComplete() {
                        // Not required
                    }
                });
    }
}