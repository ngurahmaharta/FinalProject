package com.ngurah.finalproject.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ngurah.finalproject.R;

public class ComingSoonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coming_soon);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}