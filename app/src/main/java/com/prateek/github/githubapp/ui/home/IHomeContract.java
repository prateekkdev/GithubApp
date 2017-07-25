package com.prateek.github.githubapp.ui.home;

import com.prateek.github.githubapp.network.dto.CrashlyticsDto;

import java.util.ArrayList;

/**
 * Created by prateek.kesarwani on 18/07/17.
 */

public interface IHomeContract {

    interface IHomeView {
        void updateIssuesList(ArrayList<CrashlyticsDto> crashlyticsDtoArrayList);

        void startProgress();

        void stopProgress();

        void showError();

        void showCommentsDialog(String body);
    }

    interface IHomePresenter {
        void fetchIssuesList();

        void fetchComments(String commentsUrl);

        void onStopCalled();
    }
}
