package com.example.myapp.presentation;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapp.data.repositories.GreetingRepositoryImpl;
import com.example.myapp.domain.repositories.GreetingRepository;
import com.example.myapp.domain.usecases.GetGreetingUseCase;

/**
 * Manual ViewModelProvider.Factory — no DI framework. Wires the
 * concrete repository implementation and use case together and hands
 * the ViewModel its dependency.
 */
public class GreetingViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(GreetingViewModel.class)) {
            GreetingRepository repository = new GreetingRepositoryImpl();
            GetGreetingUseCase useCase = new GetGreetingUseCase(repository);
            return (T) new GreetingViewModel(useCase);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
