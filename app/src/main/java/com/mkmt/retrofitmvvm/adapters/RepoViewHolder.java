package com.mkmt.retrofitmvvm.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mkmt.retrofitmvvm.R;

public class RepoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView name, description;

    OnRepoListener onRepoListener;

    public RepoViewHolder(@NonNull View itemView, OnRepoListener onRepoListener) {
        super(itemView);

        this.onRepoListener = onRepoListener;

        name = itemView.findViewById(R.id.repo_name);
        description = itemView.findViewById(R.id.repo_desc);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onRepoListener.onRepoClick(getAdapterPosition());
    }
}
