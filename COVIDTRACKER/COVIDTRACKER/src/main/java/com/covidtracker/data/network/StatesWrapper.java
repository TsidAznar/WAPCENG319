package com.covidtracker.data.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.covidtracker.data.database.entity.States;

import java.util.List;

class StatesWrapper {

    @SerializedName("states")
    @Expose
    private List<States> states = null;

    public List<States> getStates() {
        return states;
    }

    public void setStates(List<States> states) {
        this.states = states;
    }

}

