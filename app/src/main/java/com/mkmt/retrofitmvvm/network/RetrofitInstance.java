package com.mkmt.retrofitmvvm.network;

import com.mkmt.retrofitmvvm.utils.Credentials;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofitRepoService;
    private static String REPO_SERVICE_BASE_URL = "https://api.github.com/users/";

    public static Retrofit getRetrofitRepoService(){
        if (retrofitRepoService == null) {
            retrofitRepoService = new Retrofit.Builder()
                    .baseUrl(REPO_SERVICE_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitRepoService;
    }
}
