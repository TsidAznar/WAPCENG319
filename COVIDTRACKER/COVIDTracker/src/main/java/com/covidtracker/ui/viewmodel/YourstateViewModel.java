package com.covidtracker.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.covidtracker.data.StateRepository;
import com.covidtracker.data.database.entity.States;
import com.covidtracker.data.database.entity.YesStates;
import com.covidtracker.model.ServiceRequest;

import java.util.List;

public class YourstateViewModel extends ViewModel {

    private final StateRepository mRepository;
    private final LiveData<List<States>> mStatesData;
    private final LiveData<List<YesStates>> mYesStatesData;

    public YourstateViewModel(StateRepository mRepository) {
        this.mRepository = mRepository;
        this.mStatesData = mRepository.getStatesList();
        this.mYesStatesData = mRepository.getYesStatesList();

    }

    public LiveData<List<States>> getStatesList() {
        return mStatesData;
    }

    public LiveData<List<YesStates>> getYesStatesList() {
        return mYesStatesData;
    }

    public void postRequest(ServiceRequest serviceRequest) {
        mRepository.postServiceRequest(serviceRequest);
        mRepository.postServiceRequest1(serviceRequest);
    }
}
