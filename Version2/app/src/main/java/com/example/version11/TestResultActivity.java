package com.example.version11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.litepal.LitePal;


public class TestResultActivity extends AppCompatActivity {
private TextView tvresult;
private TextView tvsay;
private ImageButton img_back;
private ImageView good;
private Button again;
private Button quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testresult);
        ActivityKiller.getInstance().addActivity(this);
        tvresult = findViewById(R.id.tv_result);
        img_back = findViewById(R.id.imgtestresult_back);
        good = findViewById(R.id.result_good);
        again = findViewById(R.id.button_again);
        quit = findViewById(R.id.button_quit);
        Glide.with(this).load(getDrawable(R.drawable.ic_action_arrow_left_balck)).centerCrop().into(img_back);
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
        //final String s2 = intent.getStringExtra("time");

        if(s1>=80){

            Glide.with(this).load(getDrawable(R.drawable.goodjob)).centerCrop().into(good);

        }else{

            Glide.with(this).load(getDrawable(R.drawable.bad)).centerCrop().into(good);
        }
        again.setText("来吧，让我们再战三百回合");
        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        quit.setText("去你的，爸爸不玩了");
        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestResultActivity.this,TestActivity.class);
                startActivity(intent);
            }
        });
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestResultActivity.this,MainActivity.class);
                intent.putExtra("iiid",1);
                startActivity(intent);
            }
        });
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestResultActivity.this,MainActivity.class);
                intent.putExtra("iiid",1);
                startActivity(intent);
            }
        });

    }
}
