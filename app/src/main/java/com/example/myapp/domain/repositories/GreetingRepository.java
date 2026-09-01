package com.example.myapp.domain.repositories;

import com.example.myapp.domain.entities.Greeting;

/**
 * Repository contract owned by the domain layer. The data layer
 * provides a concrete implementation; the domain layer never depends
 * on it directly.
 */
public interface GreetingRepository {

    Greeting getGreeting();
}
