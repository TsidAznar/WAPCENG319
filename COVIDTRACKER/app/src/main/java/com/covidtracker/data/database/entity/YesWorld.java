package com.covidtracker.data.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "yesworld")
public class YesWorld {
    @PrimaryKey
    @NonNull
    public int cases;
    public int deaths;
    public int recovered;
    public long updated;
    public int active;
    public int affectedCountries;

}
