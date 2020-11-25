package com.covidtracker.utility;

import android.content.Context;

import com.covidtracker.AppExecutors;
import com.covidtracker.data.CountriesRepository;
import com.covidtracker.data.StateRepository;
import com.covidtracker.data.WorldRepository;
import com.covidtracker.data.database.CountriesDao;
import com.covidtracker.data.database.CountriesDatabase;
import com.covidtracker.data.database.StateDao;
import com.covidtracker.data.database.StatesDatabase;
import com.covidtracker.data.database.WorldDao;
import com.covidtracker.data.database.WorldDatabase;
import com.covidtracker.data.database.YesCountriesDao;
import com.covidtracker.data.database.YesCountriesDatabase;
import com.covidtracker.data.database.YesStateDao;
import com.covidtracker.data.database.YesStatesDatabase;
import com.covidtracker.data.database.YesWorldDao;
import com.covidtracker.data.database.YesWorldDatabase;
import com.covidtracker.data.network.UserNetworkDataSource;
import com.covidtracker.ui.viewmodel.yourCountriesViewModelFactory;
import com.covidtracker.ui.viewmodel.yourStateViewModelFactory;
import com.covidtracker.ui.viewmodel.yourWorldViewModelFactory;
import com.covidtracker.ui.viewmodel.yourrealcountryViewModelFactory;

public class InjectorUtils {

    public static StateRepository getStateRepository(Context context) {
        // getting all we need
        StateDao stateDao = StatesDatabase.getInstance(context).stateDao();
        YesStateDao yesStateDao = YesStatesDatabase.getInstance(context).yesstateDao();
        AppExecutors executors = AppExecutors.getInstance();
        UserNetworkDataSource networkDataSource = UserNetworkDataSource.getInstance(executors);

        return StateRepository.getInstance(stateDao, yesStateDao,  networkDataSource, executors);

    }

    public static CountriesRepository getCountriesRepository(Context context) {
        // Get all we need
        CountriesDao countriesDao = CountriesDatabase.getInstance(context).countriesDao();
        YesCountriesDao yescountriesDao = YesCountriesDatabase.getInstance(context).yescountriesDao();
        AppExecutors executors = AppExecutors.getInstance();
        UserNetworkDataSource networkDataSource = UserNetworkDataSource.getInstance(executors);

        return CountriesRepository.getInstance(countriesDao,
                  yescountriesDao,
                networkDataSource, executors);

    }

    public static WorldRepository getWorldRepository(Context context) {
        // Get all we need
        WorldDao worldDao = WorldDatabase.getInstance(context).worldDao();
        YesWorldDao yesworldDao = YesWorldDatabase.getInstance(context).yesworldDao();
        AppExecutors executors = AppExecutors.getInstance();
        UserNetworkDataSource networkDataSource = UserNetworkDataSource.getInstance(executors);

        return WorldRepository.getInstance(worldDao, yesworldDao, networkDataSource, executors);

    }

    public static yourWorldViewModelFactory getWorldViewModelFactory(Context context){
        WorldRepository repository = getWorldRepository(context);
        return new yourWorldViewModelFactory(repository);
    }
    public static yourStateViewModelFactory getStateViewModelFactory(Context context){
        StateRepository repository = getStateRepository(context);
        return new yourStateViewModelFactory(repository);
    }
    public static yourCountriesViewModelFactory getCountriesViewModelFactory(Context context){
        CountriesRepository repository = getCountriesRepository(context);
        return new yourCountriesViewModelFactory(repository);
    }
    public static yourrealcountryViewModelFactory getRealCountryViewModelFactory(Context context){
        CountriesRepository repository = getCountriesRepository(context);
        return new yourrealcountryViewModelFactory(repository);
    }

}
