package com.prateek.github.githubapp.ui.home;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.prateek.github.githubapp.R;
import com.prateek.github.githubapp.databinding.ActivityMainBinding;
import com.prateek.github.githubapp.databinding.DialogViewBinding;
import com.prateek.github.githubapp.network.dto.CrashlyticsDto;

import java.util.ArrayList;

import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity implements IHomeContract.IMainView {

    private ActivityMainBinding mainBinding;

    @Inject
    HomeAdapter issuesListAdapter;

    @Inject
    HomePresenter homePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        this.setSupportActionBar(mainBinding.myToolbar);

        DaggerHomeComponent.builder().homeModule(new HomeModule(this)).build().inject(this);

        mainBinding.mainList.setLayoutManager(new LinearLayoutManager(this));
        mainBinding.mainList.setAdapter(issuesListAdapter);

        homePresenter.fetchIssuesList();
    }

    @Override
    public void startProgress() {
        mainBinding.mainProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopProgress() {
        mainBinding.mainProgress.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        Toast.makeText(HomeActivity.this, "Error Occurred", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateIssuesList(ArrayList<CrashlyticsDto> crashlyticsDtoArrayList) {
        issuesListAdapter.setList(crashlyticsDtoArrayList);
    }

    @Override
    public void showCommentsDialog(String body) {
        DialogViewBinding binding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.dialog_view, null, false);

        binding.dialogBody.setText(body);

        Dialog dialog = new Dialog(this);
        dialog.setCancelable(true);
        dialog.setContentView(binding.getRoot());

        dialog.setTitle(R.string.dialog_title);

        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_refresh:
                homePresenter.fetchIssuesList();
                break;
            default:
                break;
        }

        return true;
    }
}