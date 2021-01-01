package com.mkmt.retrofitmvvm.clients;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mkmt.retrofitmvvm.models.RepoModelResponse;

import java.util.List;

public class ReposClient {
    // LiveData
    private MutableLiveData<List<RepoModelResponse>> repos;

    private static ReposClient reposClientInstance;

    public static ReposClient getInstance() {
        if (reposClientInstance == null) {
            reposClientInstance = new ReposClient();
        }
        return reposClientInstance;
    }

    private ReposClient() {
        repos = new MutableLiveData<>();
    }

    public LiveData<List<RepoModelResponse>> getRepos() {
        return repos;
    }
}
