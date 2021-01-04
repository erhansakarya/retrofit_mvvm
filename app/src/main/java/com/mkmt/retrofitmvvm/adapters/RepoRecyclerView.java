package com.mkmt.retrofitmvvm.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mkmt.retrofitmvvm.R;
import com.mkmt.retrofitmvvm.models.RepoModelResponse;

import java.util.List;

public class RepoRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<RepoModelResponse> repos;
    private OnRepoListener onRepoListener;

    public RepoRecyclerView(OnRepoListener onRepoListener) {
        this.onRepoListener = onRepoListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_list_item, parent, false);

        return new RepoViewHolder(view, onRepoListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((RepoViewHolder)holder).name.setText(repos.get(position).getName());
        ((RepoViewHolder)holder).description.setText(repos.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        if (repos != null) {
            return repos.size();
        }

        return 0;
    }

    public void setRepos(List<RepoModelResponse> repos) {
        this.repos = repos;
        notifyDataSetChanged();
    }
}
