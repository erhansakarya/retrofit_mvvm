package com.mkmt.retrofitmvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mkmt.retrofitmvvm.models.RepoModelResponse;
import com.mkmt.retrofitmvvm.models.ReposModelResponse;
import com.mkmt.retrofitmvvm.network.RepoServiceInterface;
import com.mkmt.retrofitmvvm.network.RetrofitInstance;
import com.mkmt.retrofitmvvm.utils.Credentials;
import com.mkmt.retrofitmvvm.viewmodels.ReposViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText searchText;
    private Button getReposButton;
    private ReposViewModel reposViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchText = findViewById(R.id.editTextSearch);
        getReposButton = findViewById(R.id.buttonGetRepos);
        reposViewModel = new ViewModelProvider(this).get(ReposViewModel.class);

        getReposButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchTextString = searchText.getText().toString();
                getRepos(Credentials.API_KEY, searchTextString);
            }
        });
    }

    private void ObserveRepos() {
        reposViewModel.getRepos().observe(this, new Observer<List<RepoModelResponse>>() {
            @Override
            public void onChanged(List<RepoModelResponse> repoModelResponses) {

            }
        });
    }

    private void getRepos(String token, String userName) {
        RepoServiceInterface repoServiceInterface = RetrofitInstance.getRetrofitRepoService().create(RepoServiceInterface.class);

        Call<List<RepoModelResponse>> responseCall = repoServiceInterface.getRepos(token, userName);

        Log.d("url called: ", responseCall.request().url().toString());

        responseCall.enqueue(new Callback<List<RepoModelResponse>>() {
            @Override
            public void onResponse(Call<List<RepoModelResponse>> call, Response<List<RepoModelResponse>> response) {
                if (response.code() == 200) {
                    Log.d("response: ", response.body().toString());

                    List<RepoModelResponse> repos = response.body();
                    for (RepoModelResponse repo: repos) {
                        Log.d("repo: ", repo.toString());
                    }
                } else {
                    try {
                        Log.d("repo: ", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<RepoModelResponse>> call, Throwable t) {
                Log.d("onFailure", "failure");
            }
        });
    }
}