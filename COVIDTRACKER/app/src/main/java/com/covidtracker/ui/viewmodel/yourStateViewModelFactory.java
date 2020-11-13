package com.covidtracker.ui.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.covidtracker.data.StateRepository;

/**
 * Factory method that allows us to create a ViewModel with a constructor that takes a
 * {@link StateRepository}
 */
public class yourStateViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final StateRepository stateRepository;

    public yourStateViewModelFactory(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //
        return (T) new YourstateViewModel(stateRepository);
    }
}
