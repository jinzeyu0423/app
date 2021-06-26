package com.example.version11;

import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.litepal.LitePal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReviewActivity extends AppCompatActivity implements onrelistener{
    private SimpleDateFormat format;
    private TextView tvreview;
    private TextView tvhint;
    private ImageView imgback;
    static int LSize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        tvreview = findViewById(R.id.tv_worldreview);
        tvhint = findViewById(R.id.reviewhint);
        imgback = findViewById(R.id.img_reviewback);
        Glide.with(this).load(getDrawable(R.drawable.ic_action_arrow_left)).centerCrop().into(imgback);
        setVp();

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReviewActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
    List<String> listch = new ArrayList<>();
    List<String> list = new ArrayList<>();
    List<WordBuilder> list1 = LitePal.findAll(WordBuilder.class);
    private void setVp() {

        LSize = list1.size() - 1;
           for (int i = 0; i < list1.size(); i++) {
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
                d2 = format.parse(list1.get(i).getCreatedtime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Long diff = d1.getTime() - d2.getTime();
            Long day = diff / (1000 * 60 * 60 * 24);
            if(day==1||day==2||day==4||day==7||day==15) {

                list.add(list1.get(i).getWbenglish());
                listch.add(list1.get(i).getWbchinese());
              }
              if(day>15){
                    LitePal.deleteAll(WordBuilder.class,"wbenglish=?",
                            list1.get(i).getWbenglish());
               }
              // list.add(list1.get(i).getWbenglish());
              // listch.add(list1.get(i).getWbchinese());
            }

            if(list.size()==0){
                Intent intent = new Intent(ReviewActivity.this,ReviewEmptyActivity.class);
                startActivity(intent);
            }
            ViewPager vp = findViewById(R.id.vp2);
            vp.setAdapter(new ReviewPagerAdapter(this, list, listch, this));
        }


    @Override
    public void onclic() {

        NoScrollViewPager vp = findViewById(R.id.vp2);
        if (vp.getCurrentItem()==LSize){
            Intent intent = new Intent(ReviewActivity.this,MainActivity.class);
            startActivity(intent);
        }else {
            vp.setCurrentItem(vp.getCurrentItem() + 1);
        }
//        if(day==15){
//        LitePal.deleteAll(WordBuilder.class,"wbenglish=?",list1.get(i).getWbenglish());
//
//        }
    }
}
