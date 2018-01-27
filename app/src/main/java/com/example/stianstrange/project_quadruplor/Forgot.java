package com.example.stianstrange.project_quadruplor;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot extends AppCompatActivity implements View.OnClickListener {

    //Declarations
    FirebaseAuth mAuth;
    EditText editTextEmail;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        //Initialize FireBase Instance
        mAuth = FirebaseAuth.getInstance();
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        findViewById(R.id.buttonForgot).setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        final String emailforgot = editTextEmail.getText().toString().trim();
        mAuth.sendPasswordResetEmail(emailforgot).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {

                            startActivity(new Intent(Forgot.this, main.class));
                            Toast.makeText(getApplicationContext(), "Email Sendt", Toast.LENGTH_SHORT).show();
                            String TAG = "Forgot";
                            Log.d(TAG, "Email Sendt to "+emailforgot+getApplicationContext());
                            finish();

                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    }
                });

    }
}

