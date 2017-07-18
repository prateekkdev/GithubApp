package com.prateek.github.githubapp.ui.home;

import com.prateek.github.githubapp.network.dto.CrashlyticsDto;

import java.util.ArrayList;

/**
 * Created by prateek.kesarwani on 18/07/17.
 */

public interface IHomeContract {

    interface IMainView {
        void updateIssuesList(ArrayList<CrashlyticsDto> crashlyticsDtoArrayList);

        void startProgress();

        void stopProgress();

        void showError();

        void showCommentsDialog(String body);
    }

    interface IMainPresenter {
        void fetchIssuesList();

        void fetchComments(String commentsUrl);
    }
}
