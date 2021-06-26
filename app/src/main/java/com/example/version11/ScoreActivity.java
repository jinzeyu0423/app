package com.example.version11;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.TextView;


public class ScoreActivity extends AppCompatActivity {
private TextView score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        score = findViewById(R.id.tvs_score);




    }
}
