package com.prateek.github.githubapp.ui.home;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prateek.github.githubapp.R;
import com.prateek.github.githubapp.databinding.IssuesItemBinding;
import com.prateek.github.githubapp.network.dto.CrashlyticsDto;

import java.util.ArrayList;

/**
 * Created by prateek.kesarwani on 18/07/17.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private ArrayList<CrashlyticsDto> list;

    private HomeActivity homeActivity;

    public HomeAdapter(HomeActivity homeActivity) {
        this.homeActivity = homeActivity;
    }

    public void setList(ArrayList<CrashlyticsDto> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.issues_item, parent, false);
        HomeAdapter.ViewHolder viewHolder = new HomeAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HomeAdapter.ViewHolder holder, final int position) {
        final CrashlyticsDto model = list.get(position);
        holder.issuesItemBinding.issuesItemText.setText(model.getTitle());

        holder.issuesItemBinding.issuesItemText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                // TODO This should be done in a better way.
                homeActivity.homePresenter.fetchComments(model.getCommentsUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }

        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private IssuesItemBinding issuesItemBinding;

        public ViewHolder(View itemView) {
            super(itemView);
            issuesItemBinding = DataBindingUtil.bind(itemView);
        }
    }
}