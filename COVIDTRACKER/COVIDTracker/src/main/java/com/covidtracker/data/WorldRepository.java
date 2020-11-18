package com.covidtracker.data;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.covidtracker.AppExecutors;
import com.covidtracker.data.database.WorldDao;
import com.covidtracker.data.database.YesWorldDao;
import com.covidtracker.data.database.entity.World;
import com.covidtracker.data.database.entity.YesWorld;
import com.covidtracker.data.network.UserNetworkDataSource;
import com.covidtracker.model.ServiceRequest;

/*
 * This class is responsible for handling data operations. This is the mediator between different
 * data sources
 */
public class WorldRepository {
    private static final String LOG_TAG = WorldRepository.class.getSimpleName();

    private WorldDao mWorldDao;
    private YesWorldDao mYesWorldDao;
    private UserNetworkDataSource mNetworkDataSource;


    private static final Object LOCK = new Object();
    private static WorldRepository sInstance;

    public WorldRepository(WorldDao worldDao, YesWorldDao yesworldDao, UserNetworkDataSource networkDataSource, AppExecutors
            executors) {
        this.mWorldDao = worldDao;
        this.mYesWorldDao = yesworldDao;
        this.mNetworkDataSource = networkDataSource;

        // when repository is created and as long as the repository exists, observe the network LiveData.
        // If that LiveData changes, it updates the database.
        mNetworkDataSource.getWorld().observeForever(world -> {
            executors.diskIO().execute(() -> {

                Log.d(LOG_TAG, "world table is updating");
                mWorldDao.updateAll(world);
            });
        });

        mNetworkDataSource.getYesWorld().observeForever(yesworld -> {
            executors.diskIO().execute(() -> {

                Log.d(LOG_TAG, "yes world table is updating");
                mYesWorldDao.updateAll(yesworld);
            });
        });
    }

    public static WorldRepository getInstance(WorldDao worldDao, YesWorldDao yesworldDao, UserNetworkDataSource
            networkDataSource, AppExecutors executors) {
        Log.d(LOG_TAG, "Getting the repository");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new WorldRepository(worldDao, yesworldDao, networkDataSource, executors);
                Log.d(LOG_TAG, "Made new world repository");
            }
        }
        return sInstance;
    }

    public LiveData<World> getWorld() {
        LiveData<World> foo = mWorldDao.getWorld();
        return foo;
    }

    public LiveData<YesWorld> getYesWorld() {
        LiveData<YesWorld> foo = mYesWorldDao.getYesWorld();
        return foo;
    }

    public void postServiceRequest(ServiceRequest serviceRequest) {
        mNetworkDataSource.fetchWorldData(serviceRequest);
    }

    public void postServiceRequest1(ServiceRequest serviceRequest) {
        mNetworkDataSource.fetchYesWorldData(serviceRequest);
    }

}
