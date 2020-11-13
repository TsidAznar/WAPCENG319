package com.covidtracker.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.covidtracker.data.CountriesRepository;
import com.covidtracker.data.database.entity.Countries;
import com.covidtracker.data.database.entity.YesCountries;
import com.covidtracker.model.ServiceRequest;

import java.util.List;

public class YourcountriesViewModel extends ViewModel {

    private final CountriesRepository mRepository;
    private final LiveData<List<Countries>> mCountriesData;
    private final LiveData<List<YesCountries>> mYesCountriesData;

    public YourcountriesViewModel(CountriesRepository mRepository) {
        this.mRepository = mRepository;
        this.mCountriesData = mRepository.getCountriesList();
        this.mYesCountriesData = mRepository.getYesCountriesList();

    }

    public LiveData<List<Countries>> getCountriesList() {
        return mCountriesData;
    }

    public LiveData<List<YesCountries>> getYesCountriesList() {
        return mYesCountriesData;
    }

    public void postRequest(ServiceRequest serviceRequest) {
        mRepository.postServiceRequest(serviceRequest);
        mRepository.postServiceRequest1(serviceRequest);
    }
}
