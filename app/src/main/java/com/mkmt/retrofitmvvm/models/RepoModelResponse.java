package com.mkmt.retrofitmvvm.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RepoModelResponse {
    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("description")
    @Expose
    private String description;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "RepoModelResponse{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
