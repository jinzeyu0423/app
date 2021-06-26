package com.example.WodeWordWorld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.test.ActivityKiller;


public class TestResultActivity extends AppCompatActivity {
private TextView tvresult;
private TextView tvsay;
private ImageView img_back;
private ImageView good;
private Button again;
private Button quit;

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(TestResultActivity.this,MainActivity.class);
        intent.putExtra("id",2);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testresult);
        ActivityKiller.getInstance().addActivity(this);
        tvresult = findViewById(R.id.tv_result);
        img_back = findViewById(R.id.img_testresultback);
        good = findViewById(R.id.result_good);
        again = findViewById(R.id.button_again);
        quit = findViewById(R.id.button_quit);
        Glide.with(this).load(getDrawable(R.drawable.ic_action_arrow_left)).centerCrop().into(img_back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final Intent intent = getIntent();
        final int s1= intent.getIntExtra("count",0);
        String ss = s1+"";
        tvresult.setText(ss);

        if(s1>=80){

            Glide.with(this).load(getDrawable(R.drawable.goodjob)).centerCrop().into(good);

        }else{

            Glide.with(this).load(getDrawable(R.drawable.bad)).centerCrop().into(good);
        }
        again.setText("再来一局");
        quit.setText("结束测试");

        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestResultActivity.this,ListTestActivity.class);
                startActivity(intent);
            }
        });
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestResultActivity.this,MainActivity.class);
                intent.putExtra("id",2);
                startActivity(intent);
            }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestResultActivity.this,MainActivity.class);
                intent.putExtra("id",2);
                startActivity(intent);
            }
        });

    }
}
