package com.example.WodeWordWorld;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.LitePal.WordBuilder;
import com.example.test.NoScrollViewPager;

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
    private int ls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
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
    List<Integer> listday = new ArrayList<>();
    List<WordBuilder> list1 = LitePal.findAll(WordBuilder.class);

    private void setVp() {
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

            if((day>1&&list1.get(i).getDay1()==0)||(day>2&&list1.get(i).getDay2()==0)||
                    (day>4&&list1.get(i).getDay4()==0)||(day>7&&list1.get(i).getDay7()==0)
                    ||(day>15&&list1.get(i).getDay15()==0)){

                WordBuilder wb = new WordBuilder();
                wb.setCreatedtime(format.format(date));
                wb.updateAll("wbenglish=?",list1.get(i).getWbenglish());
            }
            //如果时间满足1，2，4，7，15但是没有复习，添加进列表
            if((day==1&&list1.get(i).getDay1()==0)
                    ||(day==2&&list1.get(i).getDay2()==0)
                    ||(day==4&&list1.get(i).getDay4()==0)
                    ||(day==7&&list1.get(i).getDay7()==0)
                    ||(day==15&&list1.get(i).getDay15()==0)){
                list.add(list1.get(i).getWbenglish());
                listch.add(list1.get(i).getWbchinese());

                if((day==1&&list1.get(i).getDay1()==0)){
                    listday.add(1);
                }else if((day==2&&list1.get(i).getDay2()==0)){
                    listday.add(2);
                }else if((day==4&&list1.get(i).getDay4()==0)){
                    listday.add(4);
                }else if((day==7&&list1.get(i).getDay7()==0)){
                    listday.add(7);
                }else{
                    listday.add(15);
                }
            }
           //时间大于15天将单词从数据库删除
           if(day>15&&list1.get(i).getDay15()==1){
                LitePal.deleteAll(WordBuilder.class,"wbenglish=?",
                        list1.get(i).getWbenglish());
           }
        }

        if(list.size()==0){
            Intent intent = new Intent(ReviewActivity.this,ReviewEmptyActivity.class);
            startActivity(intent);
        }

        ls = list.size() - 1;
        ViewPager vp = findViewById(R.id.vp2);
        vp.setAdapter(new ReviewPagerAdapter(this, list,listch,listday, this));
    }

    @Override
    public void onclic() {

        NoScrollViewPager vp = findViewById(R.id.vp2);
        if (vp.getCurrentItem()==ls){
            Intent intent = new Intent(ReviewActivity.this,ReviewEmptyActivity.class);
            startActivity(intent);
        }else {
            vp.setCurrentItem(vp.getCurrentItem() + 1);
        }
    }
}
