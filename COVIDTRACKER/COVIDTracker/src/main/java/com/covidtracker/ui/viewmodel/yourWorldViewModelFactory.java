package com.covidtracker.ui.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.covidtracker.data.StateRepository;
import com.covidtracker.data.WorldRepository;

/**
 * Factory method that allows us to create a ViewModel with a constructor that takes a
 * {@link StateRepository}
 */
public class yourWorldViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final WorldRepository worldRepository;

    public yourWorldViewModelFactory(WorldRepository worldRepository) {
        this.worldRepository = worldRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //
        return (T) new YourworldViewModel(worldRepository);
    }
}
