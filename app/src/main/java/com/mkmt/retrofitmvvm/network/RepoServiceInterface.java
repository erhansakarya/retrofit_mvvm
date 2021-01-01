package com.mkmt.retrofitmvvm.network;

import com.mkmt.retrofitmvvm.models.RepoModelResponse;
import com.mkmt.retrofitmvvm.models.ReposModelResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface RepoServiceInterface {
    @GET("{user_name}/repos")
    Call<List<RepoModelResponse>> getRepos(@Header("Authorization") String token, @Path("user_name") String username);
}
