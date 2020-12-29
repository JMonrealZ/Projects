package com.example.contraintlayout.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.contraintlayout.R;
import com.example.contraintlayout.ui.DashboardActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void btnLogin_Click(View view){
        Intent i = new Intent(this, DashboardActivity.class);
        startActivity(i);
    }
}