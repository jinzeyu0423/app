package com.example.WodeWordWorld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.LitePal.WordBuilder;

import org.litepal.LitePal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReviewEmptyActivity extends AppCompatActivity {
private TextView futurereview;
private ImageView emptyback;
private ImageView emptymemory;
private SimpleDateFormat format;
private int num;
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ReviewEmptyActivity.this,MainActivity.class);
        intent.putExtra("id",2);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_empty);
        futurereview = findViewById(R.id.tv_futurereview);
        emptyback = findViewById(R.id.img_emptyback);
        emptymemory = findViewById(R.id.img_memory);
        Glide.with(this).load(getDrawable(R.drawable.ic_action_arrow_left))
                .centerCrop().into(emptyback);
        Glide.with(this).load(getDrawable(R.drawable.ywqx))
                .into(emptymemory);
        emptyback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReviewEmptyActivity.this,MainActivity.class);
                intent.putExtra("id",2);
                startActivity(intent);
            }
        });

        List<WordBuilder> list = LitePal.findAll(WordBuilder.class);

        for(int i =0;i<list.size();i++){
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        Date d1 = null;//后的时间
        try {
            d1 = format.parse(format.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date d2 = null; //前的时间
        try {
            d2 = format.parse(list.get(i).getCreatedtime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Long diff = d1.getTime() - d2.getTime();
        Long day = diff / (1000 * 60 * 60 * 24);
        //计算第二天需要复习的单词数量
        if(day==0||day==3||day==6||day==14){
            num= num+1;
        }
        }
        futurereview.append(String.valueOf("今天的复习任务已经完成"+"\n"+"明天需要复习的单词数量："+num));
    }
}
