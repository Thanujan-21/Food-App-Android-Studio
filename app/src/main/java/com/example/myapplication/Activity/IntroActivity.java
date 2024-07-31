package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.R;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        ConstraintLayout startBtn=findViewById(R.id.startBtn);
        startBtn.setOnClickListener(v -> startActivity(new Intent(IntroActivity.this,MainActivity.class)));
    }
}