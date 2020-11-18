package com.covidtracker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.covidtracker.data.network.pojo.StatesPojo;

import java.util.List;

public class ServiceResponse {

    @SerializedName("data")
    @Expose
    public List<StatesPojo> data = null;
}
