package com.mkmt.retrofitmvvm.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mkmt.retrofitmvvm.clients.ReposClient;
import com.mkmt.retrofitmvvm.models.RepoModelResponse;

import java.util.List;

public class ReposRepository {
    private static ReposRepository reposRepositoryInstance;

    private ReposClient reposClient;

    public static ReposRepository getReposRepositoryInstance() {
        if (reposRepositoryInstance == null) {
            reposRepositoryInstance = new ReposRepository();
        }
        return reposRepositoryInstance;
    }

    private ReposRepository() {
        reposClient = ReposClient.getInstance();
    }

    public LiveData<List<RepoModelResponse>> getRepos() {
        return reposClient.getRepos();
    }
}
