package com.covidtracker.ui.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.covidtracker.data.CountriesRepository;

/**
 * Factory method that allows us to create a ViewModel with a constructor that takes a
 * {@link CountriesRepository}
 */
public class yourrealcountryViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final CountriesRepository countriesRepository;

    public yourrealcountryViewModelFactory(CountriesRepository countriesRepository) {
        this.countriesRepository = countriesRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //
        return (T) new YourrealcountryViewModel(countriesRepository);
    }
}
