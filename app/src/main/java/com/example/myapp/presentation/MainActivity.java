package com.example.myapp.presentation;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapp.R;

public class MainActivity extends AppCompatActivity {

    private GreetingViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this, new GreetingViewModelFactory())
                .get(GreetingViewModel.class);

        TextView greetingText = findViewById(R.id.greetingText);
        Button refreshButton = findViewById(R.id.refreshButton);

        viewModel.getGreeting().observe(this, greetingText::setText);

        refreshButton.setOnClickListener(v -> viewModel.refresh());
    }
}
