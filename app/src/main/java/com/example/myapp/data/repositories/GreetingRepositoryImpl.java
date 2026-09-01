package com.example.myapp.data.repositories;

import com.example.myapp.domain.entities.Greeting;
import com.example.myapp.domain.repositories.GreetingRepository;

/**
 * Concrete implementation of {@link GreetingRepository}. Returns a
 * static local greeting — no network call, no networking library.
 * That is intentional: this is a worked example, not a real feature.
 */
public class GreetingRepositoryImpl implements GreetingRepository {

    @Override
    public Greeting getGreeting() {
        return new Greeting("Hello from the domain layer!");
    }
}
