package com.prateek.github.githubapp.ui.home;

import android.text.TextUtils;
import android.util.Log;

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

    private Observable<ArrayList<CrashlyticsDto>> crashlyticsModelObservable;

    public HomePresenter(IHomeContract.IMainView mainView) {
        this.mainView = mainView;

        githubService = PApp.getApp().githubService();
    }

    @Override
    public void fetchIssuesList() {
        Log.e("Prateek", "presenter, githubservice: " + githubService.toString());
        crashlyticsModelObservable = githubService.listIssues().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(list -> {
                    Collections.sort(list);
                    return list;
                });

        crashlyticsModelObservable
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
                        mainView.stopProgress();

                        StringBuilder comments = new StringBuilder("");

                        for (CommentsDto commentsDto : commentsDtos) {

                            if (commentsDto.getUserDto() != null) {
                                comments.append("\n" + PApp.getApp().getResources().getString(R.string.author_title) + " " + commentsDto.getUserDto().getLogin());
                            }

                            comments.append("\n" + PApp.getApp().getResources().getString(R.string.comment_title) + " " + commentsDto.getBody());
                        }

                        if (TextUtils.isEmpty(comments)) {
                            comments.append(PApp.getApp().getResources().getString(R.string.comments_no_text));
                        }

                        mainView.showCommentsDialog(comments.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        mainView.showError();
                    }

                    @Override
                    public void onComplete() {
                        // Not required
                    }
                });
    }
}
