package com.mkmt.retrofitmvvm.clients;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mkmt.retrofitmvvm.Executors;
import com.mkmt.retrofitmvvm.models.RepoModelResponse;
import com.mkmt.retrofitmvvm.network.RepoServiceInterface;
import com.mkmt.retrofitmvvm.network.RetrofitInstance;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class ReposClient {
    // LiveData
    private MutableLiveData<List<RepoModelResponse>> repos;

    private static ReposClient reposClientInstance;

    // global runnable
    private FetchReposRunnable fetchReposRunnable;

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

    public void getReposApi(String token, String username) {
        if (fetchReposRunnable != null) {
            fetchReposRunnable = null;
        }

        fetchReposRunnable = new FetchReposRunnable(token, username);

        final Future handler = Executors.getExecutorsInstance().getScheduledExecutorService().submit(fetchReposRunnable);

        Executors.getExecutorsInstance().getScheduledExecutorService().schedule(new Runnable() {
            @Override
            public void run() {
                // cancelling the retrofit call
                handler.cancel(true);
            }
        }, 5, TimeUnit.MINUTES);
    }


    // fetch data from rest api by runnable class
    private class FetchReposRunnable implements Runnable {
        private String token;
        private String username;
        boolean cancelRequest;

        public FetchReposRunnable(String token, String username) {
            this.token = token;
            this.username = username;
            cancelRequest = false;
        }

        @Override
        public void run() {
            try {
                Response response = getRepos(token, username).execute();
                if (cancelRequest) {
                    return;
                }
                if (response.code() == 200) {
                    List<RepoModelResponse> reposList = (List<RepoModelResponse>) response.body();
                    // sending data to live data
                    // postValue: used for background thread
                    // setValue: not for background thread
                    repos.postValue(reposList);
                } else {
                    String error = response.errorBody().toString();
                    Log.d("error: ", error);
                    repos.postValue(null);
                }

            } catch (IOException e) {
                e.printStackTrace();
                repos.postValue(null);
            }
        }

        private Call<List<RepoModelResponse>> getRepos(String token, String userName) {
            RepoServiceInterface repoServiceInterface = RetrofitInstance.getRetrofitRepoService().create(RepoServiceInterface.class);

            return repoServiceInterface.getRepos(token, userName);
        }

        private void cancelRequest() {
            Log.d("request ", " was canceled");
            cancelRequest = true;
        }
    }
}