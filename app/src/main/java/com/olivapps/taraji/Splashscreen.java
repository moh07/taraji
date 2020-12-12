package com.olivapps.taraji;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.olivapps.taraji.activity.signUp;

public class Splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashs_creen_layout);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                    Intent i = new Intent(Splashscreen.this, signUp.class);
                    startActivity(i);
                    finish();
            }
        }, 5000);
    }
}