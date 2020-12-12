package com.olivapps.taraji.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.olivapps.taraji.MainActivity;
import com.olivapps.taraji.R;
import com.olivapps.taraji.Splashscreen;
import com.olivapps.taraji.databinding.SignUpLayoutBinding;

public class signUp extends AppCompatActivity {
    SignUpLayoutBinding SignUpLayoutBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SignUpLayoutBinding = DataBindingUtil.setContentView(this, R.layout.sign_up_layout);

        SignUpLayoutBinding.createCompte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(signUp.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        SignUpLayoutBinding.seConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(signUp.this, login.class);
                startActivity(i);
                finish();
            }
        });
    }
}