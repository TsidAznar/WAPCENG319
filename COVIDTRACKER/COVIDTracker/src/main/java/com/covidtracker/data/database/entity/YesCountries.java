package com.covidtracker.data.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "yescountries")
public class YesCountries {
    @PrimaryKey
    @NonNull
    public String country;
    public int cases;
    public int active;
    public int todayCases;
    public int deaths;
    public int todayDeaths;
    public int recovered;
    public int tests;
    public int testsPerMillion;
    public String flag;
    public int critical;
    public long updated;


}

