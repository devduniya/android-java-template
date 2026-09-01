package com.example.myapp.presentation;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapp.domain.entities.Greeting;
import com.example.myapp.domain.usecases.GetGreetingUseCase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The "VM" in MVVM. Calls the use case on a background executor and
 * exposes the result as LiveData — the Activity only ever observes
 * that LiveData, it never touches the use case or repository itself.
 */
public class GreetingViewModel extends ViewModel {

    private final GetGreetingUseCase getGreetingUseCase;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final MutableLiveData<String> greeting = new MutableLiveData<>();

    public GreetingViewModel(@NonNull GetGreetingUseCase getGreetingUseCase) {
        this.getGreetingUseCase = getGreetingUseCase;
        refresh();
    }

    public LiveData<String> getGreeting() {
        return greeting;
    }

    public void refresh() {
        executor.execute(() -> {
            try {
                // Simulate async work (e.g. I/O) without blocking the main thread.
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            Greeting result = getGreetingUseCase.execute();
            greeting.postValue(result.getMessage());
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        executor.shutdown();
    }
}
