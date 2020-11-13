package com.covidtracker.data;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.covidtracker.AppExecutors;
import com.covidtracker.data.database.StateDao;
import com.covidtracker.data.database.YesStateDao;
import com.covidtracker.data.database.entity.States;
import com.covidtracker.data.database.entity.YesStates;
import com.covidtracker.data.network.UserNetworkDataSource;
import com.covidtracker.model.ServiceRequest;

import java.util.List;

/*
 * This class is responsible for handling data operations. This is the mediator between different
 * data sources
 */
public class StateRepository {
    private static final String LOG_TAG = StateRepository.class.getSimpleName();

    private StateDao mStatesDao;
    private YesStateDao mYesStatesDao;
    private UserNetworkDataSource mNetworkDataSource;


    private static final Object LOCK = new Object();
    private static StateRepository sInstance;

    public StateRepository(StateDao stateDao,
                               YesStateDao yesstatesDao,
                               UserNetworkDataSource networkDataSource, AppExecutors
                                       executors) {
        this.mStatesDao = stateDao;
        this.mYesStatesDao = yesstatesDao;
        this.mNetworkDataSource = networkDataSource;

        // when repository is created and as long as the repository exists, observe the network LiveData.
        // If that LiveData changes, it updates the database.
        mNetworkDataSource.getStatesList().observeForever(states -> {
            executors.diskIO().execute(() -> {

                Log.d(LOG_TAG, "states table is updating");
                mStatesDao.updateAll(states);
            });
        });


        mNetworkDataSource.getYesStatesList().observeForever(yesstates -> {
            executors.diskIO().execute(() -> {

                Log.d(LOG_TAG, "yes states table is updating");
                mYesStatesDao.updateAll(yesstates);
            });
        });


    }

    public static StateRepository getInstance(StateDao stateDao,
                                                  YesStateDao yesStateDao,
                                                  UserNetworkDataSource
                                                          networkDataSource, AppExecutors executors) {
        Log.d(LOG_TAG, "Getting the yes states repository");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new StateRepository(stateDao,
                        yesStateDao,
                        networkDataSource, executors);
                Log.d(LOG_TAG, "Made new states repository");
            }
        }
        return sInstance;
    }

    public LiveData<List<States>> getStatesList() {
        LiveData<List<States>> foo = mStatesDao.getStatesList();
        return foo;
    }

    public void postServiceRequest(ServiceRequest serviceRequest) {
        mNetworkDataSource.fetchStatesData(serviceRequest);
    }

    public LiveData<List<YesStates>> getYesStatesList() {
        LiveData<List<YesStates>> fie = mYesStatesDao.getYesStatesList();
        return fie;
    }

    public void postServiceRequest1(ServiceRequest serviceRequest) {
        mNetworkDataSource.fetchYesStatesData(serviceRequest);
    }
}
