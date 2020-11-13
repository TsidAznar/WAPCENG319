package com.covidtracker.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.covidtracker.data.WorldRepository;
import com.covidtracker.data.database.entity.World;
import com.covidtracker.data.database.entity.YesWorld;
import com.covidtracker.model.ServiceRequest;

public class YourworldViewModel extends ViewModel {

    private final WorldRepository mRepository;
    private final LiveData<World> mWorldData;
    private final LiveData<YesWorld> mYesWorldData;

    public YourworldViewModel(WorldRepository mRepository) {
        this.mRepository = mRepository;
        this.mWorldData = mRepository.getWorld();
        this.mYesWorldData = mRepository.getYesWorld();
    }

    public LiveData<World> getWorld() {
        return mWorldData;
    }

    public void postRequest(ServiceRequest serviceRequest) {
        mRepository.postServiceRequest(serviceRequest);
        mRepository.postServiceRequest1(serviceRequest);
    }

    public LiveData<YesWorld> getYesWorld() {
        return mYesWorldData;
    }

}
