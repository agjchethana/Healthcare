package com.example.healthcareproject;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.Calendar;

public class BookAppointmentActivity extends AppCompatActivity {
    EditText editTextAppFullName, editTextAppAddress, editTextAppContactNumber, editTextAppFees;
    Button buttonAppDate, buttonAppTime, buttonBookAppoinmentAmount, buttonAppBack;
    TextView textViewHADTitle, textViewAppTitle;
    private String selectedDate = "";
    private String selectedTime = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        // Initialize all views
        textViewHADTitle = findViewById(R.id.textViewHADTitle);
        textViewAppTitle = findViewById(R.id.textViewAppTitle);
        editTextAppFullName = findViewById(R.id.editTextAppFullName);
        editTextAppAddress = findViewById(R.id.editTextAppAddress);
        editTextAppContactNumber = findViewById(R.id.editTextAppContactNumber);
        editTextAppFees = findViewById(R.id.editTextAppFees);
        buttonAppDate = findViewById(R.id.buttonAppDate);
        buttonAppTime = findViewById(R.id.buttonAppTime);
        buttonBookAppoinmentAmount = findViewById(R.id.buttonBookAppoinmentAmount);
        buttonAppBack = findViewById(R.id.buttonAppBack);

        // Make fees field read-only
        editTextAppFees.setKeyListener(null);

        // Get username from shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        // Get fees from previous activity
        Intent intent = getIntent();
        String fees = intent.getStringExtra("fees");
        editTextAppFees.setText(fees);

        // Date button click listener
        buttonAppDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        BookAppointmentActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                selectedDate = String.format("%02d/%02d/%04d", dayOfMonth, monthOfYear + 1, year);
                                buttonAppDate.setText(selectedDate);
                            }
                        },
                        year, month, day);

                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });

        // Time button click listener
        buttonAppTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        BookAppointmentActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                selectedTime = String.format("%02d:%02d", hourOfDay, minute);
                                buttonAppTime.setText(selectedTime);
                            }
                        },
                        hour, minute, true);

                timePickerDialog.show();
            }
        });

        // Book appointment button click listener
        buttonBookAppoinmentAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Validation
                if(editTextAppFullName.getText().toString().isEmpty() ||
                        editTextAppAddress.getText().toString().isEmpty() ||
                        editTextAppContactNumber.getText().toString().isEmpty() ||
                        selectedDate.isEmpty() ||
                        selectedTime.isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(getApplicationContext(), "Your appointment is done successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(BookAppointmentActivity.this, HomeActivity.class));
            }
        });

        // Back button click listener
        buttonAppBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BookAppointmentActivity.this, FindADoctorActivity.class));
            }
        });
    }
}