package com.mkmt.retrofitmvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchText = findViewById(R.id.editTextSearch);
        getReposButton = findViewById(R.id.buttonGetRepos);

        getReposButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchTextString = searchText.getText().toString();
                Intent intent = new Intent(MainActivity.this, SearchRepoActivity.class);
                intent.putExtra("search_text", searchTextString);
                startActivity(intent);
            }
        });
    }
}