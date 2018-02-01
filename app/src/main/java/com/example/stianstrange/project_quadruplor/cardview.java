package com.example.stianstrange.project_quadruplor;

/**
 * Created by stian on 01.02.2018.
 */


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class cardview extends Activity {

    TextView personName;
    TextView personAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cardview_activity);
        personName = findViewById(R.id.person_name);
        personAge = findViewById(R.id.person_age);

        personName.setText("Emma Wilson");
        personAge.setText("23 years old");
    }
}