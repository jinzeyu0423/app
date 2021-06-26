package com.example.version11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ReviewEmptyActivity extends AppCompatActivity {
private TextView emptyhint;
private ImageView emptyback;
private ImageView emptymemory;
private Button emptytotest;
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ReviewEmptyActivity.this,MainActivity.class);
        intent.putExtra("iiid",1);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_empty);
        emptyhint = findViewById(R.id.tv_emptyhint);
        emptyhint.append("\n快去把单词添加到生词本或者进行测试吧\n人的遗忘规律大致如下图所示，" +
                "按照规律复习可以让我们的记忆更加牢固");
        emptyback = findViewById(R.id.img_emptyback);
        emptymemory = findViewById(R.id.img_memory);
        emptytotest = findViewById(R.id.emptytotest);
        Glide.with(this).load(getDrawable(R.drawable.ic_action_arrow_left))
                .centerCrop().into(emptyback);
        Glide.with(this).load(getDrawable(R.drawable.memory))
                .centerCrop().into(emptymemory);
        emptyback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReviewEmptyActivity.this,MainActivity.class);
                intent.putExtra("iiid",1);
                startActivity(intent);
            }
        });
        emptytotest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReviewEmptyActivity.this,TestActivity.class);
                startActivity(intent);
            }
        });
    }
}
