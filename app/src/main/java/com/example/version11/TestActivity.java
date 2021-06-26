package com.example.version11;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.litepal.LitePal;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static com.example.version11.TestPagerAdapter.count1;

public  class TestActivity extends AppCompatActivity implements onlistener{

private ImageView imgselect;
public Word word;
private ImageView imgback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        LitePal.initialize(this);
        setVp();
        imgselect = findViewById(R.id.img_testselect);
        Glide.with(this).load(getDrawable(R.drawable.ic_action_settings)).centerCrop().into(imgselect);

        imgback = findViewById(R.id.img_testback);
        Glide.with(this).load(getDrawable(R.drawable.ic_action_arrow_left)).centerCrop().into(imgback);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imgselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu pop = new PopupMenu(TestActivity.this, v);
                pop.getMenuInflater().inflate(R.menu.switchorigenmenu,pop.getMenu());
                pop.show();
                pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.origenWord:

                        }
                        return false;
                    }
                });
            }
        });

    }


    private void setVp() {
        List<String> list = new ArrayList<>();
        List<String> listresA = new ArrayList<>();
        List<String> listresB = new ArrayList<>();
        List<String> listresC = new ArrayList<>();
        List<String> listresD = new ArrayList<>();
        List<String> listCh = new ArrayList<>();

        List<Word> words = LitePal.findAll(Word.class);
        for (int i = 0; i < 5; i++) {
            word = words.get(new Random().nextInt(5494));
            list.add(word.getEnglish());
            listCh.add(word.getChinese());


            String r1 = words.get(new Random().nextInt(5494)).getChinese();
            String r2 = words.get(new Random().nextInt(5494)).getChinese();
            String r3 = words.get(new Random().nextInt(5494)).getChinese();

            List<String> resultlist = new ArrayList<>();

            resultlist.add(0,r1);
            resultlist.add(1,r2);
            resultlist.add(2,r3);
            resultlist.add(3,word.getChinese());



             List<Integer> list1 = new ArrayList<>();
             for(int i1=0;list1.size()<4;i1++){
                 int r = new Random().nextInt(4);
                 if(!list1.contains(r)){
                     list1.add(r);
                 }
             }
            listresA.add(resultlist.get(list1.get(0)));
            listresB.add(resultlist.get(list1.get(1)));
            listresC.add(resultlist.get(list1.get(2)));
            listresD.add(resultlist.get(list1.get(3)));
        }
        NoScrollViewPager vp = findViewById(R.id.vp1);
        vp.setAdapter(new TestPagerAdapter(this,list,listresA,listresB,listresC,listresD,listCh,this));


    }

    @Override
    public void onclic() {

        NoScrollViewPager vp = findViewById(R.id.vp1);

        if(vp.getCurrentItem()==4){
            Calendar calendar = Calendar.getInstance();
            int m = calendar.get(Calendar.MONTH)+1;
            String s = String.valueOf(calendar.get(Calendar.YEAR)+"年"+m+"月"+
                    calendar.get(Calendar.DAY_OF_MONTH)+"日   "+calendar.get(Calendar.HOUR_OF_DAY)+":"+
                    calendar.get(Calendar.MINUTE));
            Intent intent =new Intent(TestActivity.this,TestResultActivity.class);
            intent.putExtra("count",5*count1);
            intent.putExtra("time",s);
            startActivity(intent);
            count1 = 0;
        }else{
        vp.setCurrentItem(vp.getCurrentItem()+1,true);}
    }


}



