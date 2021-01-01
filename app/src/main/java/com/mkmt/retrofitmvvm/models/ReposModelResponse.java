package com.mkmt.retrofitmvvm.models;

import java.util.ArrayList;
import java.util.List;

public class ReposModelResponse {
    private List<RepoModelResponse> repos;

    public List<RepoModelResponse> getRepos() {
        return repos;
    }

    @Override
    public String toString() {
        return "ReposModelResponse{" +
                "repos=" + repos +
                '}';
    }
}
