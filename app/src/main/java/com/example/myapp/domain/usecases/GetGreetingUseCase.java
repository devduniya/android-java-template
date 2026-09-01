package com.example.myapp.domain.usecases;

import com.example.myapp.domain.entities.Greeting;
import com.example.myapp.domain.repositories.GreetingRepository;

/**
 * One class per use case. This one simply delegates to the repository,
 * but it is the seam presentation code calls through — never the
 * repository directly.
 */
public class GetGreetingUseCase {

    private final GreetingRepository repository;

    public GetGreetingUseCase(GreetingRepository repository) {
        this.repository = repository;
    }

    public Greeting execute() {
        return repository.getGreeting();
    }
}
