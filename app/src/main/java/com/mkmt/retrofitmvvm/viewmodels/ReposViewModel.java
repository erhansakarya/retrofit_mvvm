package com.mkmt.retrofitmvvm.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mkmt.retrofitmvvm.models.RepoModelResponse;
import com.mkmt.retrofitmvvm.repositories.ReposRepository;

import java.util.List;

public class ReposViewModel extends ViewModel {
    private ReposRepository reposRepository;

    // constructor
    public ReposViewModel() {
        reposRepository = ReposRepository.getReposRepositoryInstance();
    }

    public LiveData<List<RepoModelResponse>> getRepos() {
        return reposRepository.getRepos();
    }
}
