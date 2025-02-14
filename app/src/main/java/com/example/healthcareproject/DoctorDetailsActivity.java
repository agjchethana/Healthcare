package com.example.healthcareproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;  // Log import එක add කළා
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    // Doctor arrays ඒ විදිහටම තියාගන්න
    private String[][] family_physicians = {
            {"Doctor Name : Suresh Perera", "Hospital Address : Anuradhapura", "Experience : 6 years", "Mobile No : 0763902045", "600"},
            {"Doctor Name : Deepthi Fernando", "Hospital Address : Matara", "Experience : 9 years", "Mobile No : 0784072913", "500"},
            {"Doctor Name : Ajith Rajapaksha", "Hospital Address : Kalutara", "Experience : 10 years", "Mobile No : 0723497105", "800"},
            {"Doctor Name : Chethana Disanayaka", "Hospital Address : Colombo", "Experience : 13 years", "Mobile No : 0778923897", "900"},
            {"Doctor Name : Dilan Gamage", "Hospital Address : Galle", "Experience : 8 years", "Mobile No : 0714902345", "300"}

    };
    private String[][] dentist = {
            {"Doctor Name : Kasun Rathnayake", "Hospital Address : Colombo", "Experience : 8 years", "Mobile No : 0763841045", "800"},
            {"Doctor Name : Malini Silva", "Hospital Address : Kandy", "Experience : 12 years", "Mobile No : 0787972958", "1000"},
            {"Doctor Name : Priyantha Perera", "Hospital Address : Galle", "Experience : 6 years", "Mobile No : 0726495105", "600"},
            {"Doctor Name : Chamari Fernando", "Hospital Address : Kurunegala", "Experience : 15 years", "Mobile No : 0777983997", "1200"},
            {"Doctor Name : Dilshan Mendis", "Hospital Address : Matara", "Experience : 10 years", "Mobile No : 0714802315", "900"},
            // existing data
    };
    private String[][] cardiologists = {
            {"Doctor Name : Vihanga Edirisooriya", "Hospital Address : Kandy", "Experience : 5 years", "Mobile No : 0763802045", "700"},
            {"Doctor Name : Hiran Ramanayaka", "Hospital Address : Rathnapura", "Experience : 4 years", "Mobile No : 0744082923", "700"},
            {"Doctor Name : Dinusha Weerasekara", "Hospital Address : Batticaloa", "Experience : 14 years", "Mobile No : 0713498107", "1000"},
            {"Doctor Name : Keshara Dunuwila", "Hospital Address : Kalutara", "Experience : 12 years", "Mobile No : 0775923297", "900"},
            {"Doctor Name : Milinda Abegunawardhana", "Hospital Address : Colombo", "Experience : 10 years", "Mobile No : 0754902845", "800"}
            // existing data
    };

    private TextView tv;
    private Button btn;
    private String[][] doctor_details = {};
    private ArrayList<HashMap<String, String>> list;
    private SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv = findViewById(R.id.textView2DDTitle);
        btn = findViewById(R.id.buttonDDBack);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        Log.d("DoctorApp", "Received title: " + title);
        tv.setText(title);

        if (title != null) {
            if (title.equals("Family Physicians")) {
                doctor_details = family_physicians;
                Log.d("DoctorApp", "Selected Family Physicians");
            } else if (title.equals("Dentist")) {
                doctor_details = dentist;
                Log.d("DoctorApp", "Selected Dentist");
            } else if (title.equals("Cardiologists")) {
                doctor_details = cardiologists;
                Log.d("DoctorApp", "Selected Cardiologists");
            }
            Log.d("DoctorApp", "Doctor details array length: " + doctor_details.length);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindADoctorActivity.class));
                finish();
            }
        });

        list = new ArrayList<>();

        if (doctor_details != null && doctor_details.length > 0) {
            for (String[] doctorDetail : doctor_details) {
                HashMap<String, String> item = new HashMap<>();
                item.put("line1", doctorDetail[0]);
                item.put("line2", doctorDetail[1]);
                item.put("line3", doctorDetail[2]);
                item.put("line4", doctorDetail[3]);
                item.put("line5", "Cons Fees: Rs." + doctorDetail[4] + "/-");
                list.add(item);
            }
        }

        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e}
        );

        ListView lst = findViewById(R.id.listViewDD);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);
                it.putExtra("text1", title);
                it.putExtra("text2", doctor_details[i][0]);
                it.putExtra("text3", doctor_details[i][1]);
                it.putExtra("text4", doctor_details[i][3]);
                it.putExtra("text5", doctor_details[i][4]);
                startActivity(it);
            }
        });
    }
}