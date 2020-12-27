package com.mkmt.retrofitmvvm.network;

import com.mkmt.retrofitmvvm.utils.Credentials;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static retrofit2.Retrofit.Builder retrofitBuilder = new retrofit2.Retrofit.Builder()
            .baseUrl(Credentials.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static UsersApi usersApi = retrofit.create(UsersApi.class);

    public UsersApi getUsersApi() {
        return usersApi;
    }
}
