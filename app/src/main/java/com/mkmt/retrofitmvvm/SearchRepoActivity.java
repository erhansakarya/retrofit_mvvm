package com.mkmt.retrofitmvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.mkmt.retrofitmvvm.adapters.OnRepoListener;
import com.mkmt.retrofitmvvm.adapters.RepoRecyclerView;
import com.mkmt.retrofitmvvm.models.RepoModelResponse;
import com.mkmt.retrofitmvvm.utils.Credentials;
import com.mkmt.retrofitmvvm.viewmodels.ReposViewModel;

import java.util.List;

public class SearchRepoActivity extends AppCompatActivity implements OnRepoListener {
    private ReposViewModel reposViewModel;
    private RecyclerView recyclerView;
    private RepoRecyclerView repoRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_repo);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SetupSearchView();

        recyclerView = findViewById(R.id.recyclerView);

        reposViewModel = new ViewModelProvider(this).get(ReposViewModel.class);

        ConfigureRecyclerView();

        // call the observers
        ObserveRepos();

        Intent intent = getIntent();
        String searchText = intent.getStringExtra("search_text");
        if (searchText != null) {
            getReposApi(Credentials.API_KEY, searchText);
        }
    }

    private void ObserveRepos() {
        reposViewModel.getRepos().observe(this, new Observer<List<RepoModelResponse>>() {
            @Override
            public void onChanged(List<RepoModelResponse> repoModelResponses) {
                if (repoModelResponses != null) {
                    for (RepoModelResponse repoModelResponse : repoModelResponses) {
                        Log.d("response: ", repoModelResponse.toString());

                        repoRecyclerViewAdapter.setRepos(repoModelResponses);
                    }
                }
            }
        });
    }

    // calling method in activity
    private void getReposApi(String token, String username) {
        reposViewModel.getReposApi(token, username);
    }

    private void ConfigureRecyclerView() {
        repoRecyclerViewAdapter = new RepoRecyclerView(this);

        recyclerView.setAdapter(repoRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onRepoClick(int position) {
        Toast.makeText(getApplicationContext(), "position: " + position, Toast.LENGTH_LONG).show();
    }

    private void SetupSearchView() {
        final SearchView searchView = findViewById(R.id.search_keyword);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}