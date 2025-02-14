package com.example.healthcareproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FindADoctorActivity extends AppCompatActivity {
    private static final String EXTRA_TITLE = "title";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_find_adoctor);

        setupClickListeners();
        setupWindowInsets();
    }

    private void setupClickListeners() {
        // Back button click listener
        findViewById(R.id.cardFDBack).setOnClickListener(view -> {
            startActivity(new Intent(FindADoctorActivity.this, HomeActivity.class));
            finish(); // Optional: closes the current activity
        });

        findViewById(R.id.cardFDFamilyPhysician).setOnClickListener(view ->
                navigateToDoctorDetails("Family Physicians"));

        findViewById(R.id.cardFDDentist).setOnClickListener(view -> {
            Log.d("DoctorApp", "Dentist card clicked, sending title: Dentist");
            navigateToDoctorDetails("Dentist");
        });

        findViewById(R.id.cardFDCardiologists).setOnClickListener(view ->
                navigateToDoctorDetails("Cardiologists"));
    }

    private void navigateToDoctorDetails(String title) {
        Intent intent = new Intent(this, DoctorDetailsActivity.class);
        intent.putExtra(EXTRA_TITLE, title);
        startActivity(intent);
    }

    private void setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}