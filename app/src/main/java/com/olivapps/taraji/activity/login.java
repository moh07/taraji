package com.olivapps.taraji.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.olivapps.taraji.MainActivity;
import com.olivapps.taraji.R;
import com.olivapps.taraji.databinding.LoginLayoutBinding;

public class login extends AppCompatActivity {
    LoginLayoutBinding LoginLayoutBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginLayoutBinding = DataBindingUtil.setContentView(this, R.layout.login_layout);

        LoginLayoutBinding.seConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(login.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}