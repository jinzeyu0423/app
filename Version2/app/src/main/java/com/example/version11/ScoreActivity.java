package com.example.version11;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.TextView;

import org.litepal.LitePal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ScoreActivity extends AppCompatActivity {
private TextView score;
private SimpleDateFormat format;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        score = findViewById(R.id.tvs_score);
        List<Score> list = LitePal.findAll(Score.class);
        StringBuilder sb = new StringBuilder();
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for(int i = list.size()-1;i>=0;i--){
            sb.append("时间： "+ list.get(i).getCreatedtime()+"  " +"成绩： "+list.get(i).getScore()+"\n");
        }
        score.setText(sb);

    }
}
