package com.covidtracker.data.network;

import com.covidtracker.data.database.entity.States;
import com.covidtracker.data.database.entity.World;
import com.covidtracker.data.database.entity.YesWorld;
import com.covidtracker.data.network.pojo.CountriesPojo;
import com.covidtracker.data.network.pojo.StatesPojo;
import com.covidtracker.data.network.pojo.YesCountriesPojo;
import com.covidtracker.data.network.pojo.YesStatesPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface RestApi {

    @Headers("Content-Type: application/json")
    @GET("v3/covid-19/states")
    Call<List<States>>  getStatess();

    @GET("v3/covid-19/states")
    Call<List<StatesPojo>>  getStates(@Query("yesterday") int y);

    @GET("v3/covid-19/states")
    Call<List<YesStatesPojo>>  getYesStates(@Query("yesterday") int y);

    @GET("/v3/covid-19/countries")
    Call<List<CountriesPojo>>  getCountries(@Query("yesterday") int y);

    @GET("/v3/covid-19/countries")
    Call<List<YesCountriesPojo>>  getYesCountries(@Query("yesterday") int y);

    @GET("v3/covid-19/all")
    Call<World> getWorld();

    @GET("v3/covid-19/all?yesterday=true")
    Call<YesWorld> getYesWorld();

    //countries/USA
    //yesterday/USA

    //

    //Observable<List<ServiceResponse>> getStates();
}