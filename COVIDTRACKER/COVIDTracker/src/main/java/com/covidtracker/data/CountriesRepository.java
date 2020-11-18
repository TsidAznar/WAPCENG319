package com.covidtracker.data;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.covidtracker.AppExecutors;
import com.covidtracker.data.database.CountriesDao;
import com.covidtracker.data.database.YesCountriesDao;
import com.covidtracker.data.database.entity.Countries;
import com.covidtracker.data.database.entity.YesCountries;
import com.covidtracker.data.network.UserNetworkDataSource;
import com.covidtracker.model.ServiceRequest;

import java.util.List;


public class CountriesRepository {
    private static final String LOG_TAG = CountriesRepository.class.getSimpleName();

    private CountriesDao mCountriesDao;
    private YesCountriesDao mYesCountriesDao;
    private UserNetworkDataSource mNetworkDataSource;


    private static final Object LOCK = new Object();
    private static CountriesRepository sInstance;

    public CountriesRepository(CountriesDao countriesDao,
                               YesCountriesDao yescountriesDao,
                               UserNetworkDataSource networkDataSource, AppExecutors
            executors) {
        this.mCountriesDao = countriesDao;
       this.mYesCountriesDao = yescountriesDao;
        this.mNetworkDataSource = networkDataSource;

        // when repository is created and as long as the repository exists, observe the network LiveData.
        // If that LiveData changes, it updates the database.
        mNetworkDataSource.getCountriesList().observeForever(countries -> {
            executors.diskIO().execute(() -> {

                Log.d(LOG_TAG, "countries table is updating");
                mCountriesDao.updateAll(countries);
            });
        });


        mNetworkDataSource.getYesCountriesList().observeForever(yescountries -> {
            executors.diskIO().execute(() -> {

                Log.d(LOG_TAG, "yes countries table is updating");
                mYesCountriesDao.updateAll(yescountries);
            });
        });


    }

    public static CountriesRepository getInstance(CountriesDao countriesDao,
                                                 YesCountriesDao yesCountriesDao,
                                                  UserNetworkDataSource
            networkDataSource, AppExecutors executors) {
        Log.d(LOG_TAG, "Getting the yes countries repository");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new CountriesRepository(countriesDao,
                        yesCountriesDao,
                        networkDataSource, executors);
                Log.d(LOG_TAG, "Made new countries repository");
            }
        }
        return sInstance;
    }

    public LiveData<List<Countries>> getCountriesList() {
        LiveData<List<Countries>> foo = mCountriesDao.getCountriesList();
        return foo;
    }

    public void postServiceRequest(ServiceRequest serviceRequest) {
        mNetworkDataSource.fetchCountriesData(serviceRequest);
    }

    public LiveData<List<YesCountries>> getYesCountriesList() {
        LiveData<List<YesCountries>> fie = mYesCountriesDao.getYesCountriesList();
        return fie;
    }

    public void postServiceRequest1(ServiceRequest serviceRequest) {
        mNetworkDataSource.fetchYesCountriesData(serviceRequest);
    }
}
