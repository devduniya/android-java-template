package com.example.myapp.domain.entities;

/**
 * Plain domain entity. No Android or AndroidX imports here — the
 * domain layer knows nothing about the platform it runs on.
 */
public class Greeting {

    private final String message;

    public Greeting(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
