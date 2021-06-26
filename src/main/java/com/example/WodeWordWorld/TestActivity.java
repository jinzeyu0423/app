package com.example.WodeWordWorld;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.LitePal.Score;
import com.example.LitePal.Word;
import com.example.LitePal.WordsBuilder;
import com.example.test.NoScrollViewPager;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.WodeWordWorld.TestPagerAdapter.count1;

public  class TestActivity extends AppCompatActivity implements onlistener{

public Word word;
public WordsBuilder wordsBuilder;
private ImageView imgback;
private TextView testhead;
static String lists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        imgback = findViewById(R.id.img_testback);
        testhead = findViewById(R.id.tv_testhead);
        LitePal.initialize(this);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        if(id.equals("list")){
            setListVp();
        }else{
            testhead.setText("随机测试");
            setVp();
        }
        NoScrollViewPager vp = findViewById(R.id.vp1);
        vp.setAdapter(new TestPagerAdapter(this,list,listresA,listresB,listresC,listresD,listCh,this,progress));
        Glide.with(this).load(getDrawable(R.drawable.ic_action_arrow_left)).centerCrop().into(imgback);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backauth();
            }
        });
    }

    List<String> list = new ArrayList<>();
    List<String> listCh = new ArrayList<>();
    List<String> listresA = new ArrayList<>();
    List<String> listresB = new ArrayList<>();
    List<String> listresC = new ArrayList<>();
    List<String> listresD = new ArrayList<>();
    List<String> progress = new ArrayList<>();
    List<String> resultlist = new ArrayList<>();
    List<Word> words = LitePal.findAll(Word.class);


    //单元测试
    private void setListVp() {

        Intent intent = getIntent();
        lists = intent.getStringExtra("list");
        testhead.setText("   "+lists);
        List<WordsBuilder> words = LitePal.where("list=?",lists).find(WordsBuilder.class);
        List<Integer> list100 = new ArrayList<>();
        for(int i1=0;list100.size()<100;i1++){
            int r = new Random().nextInt(100);
            if(!list100.contains(r)){
                list100.add(r);
            }
        }
        for (int i = 0; i < 100; i++) {
            wordsBuilder = words.get(list100.get(i));
            list.add(wordsBuilder.getWbenglish());
            listCh.add(wordsBuilder.getWbchinese());
            setitem(wordsBuilder.getWbchinese());
            progress.add(String.valueOf(i+1));

        }

    }
    //随机测试
    private void setVp() {

        for (int i = 0; i < 100; i++) {
            word = words.get(new Random().nextInt(words.size()-1));
            list.add(word.getEnglish());
            listCh.add(word.getChinese());
            progress.add(String.valueOf(i+1));
            setitem(word.getChinese());
        }

    }
    //设置选项
    private void setitem(String s) {
        String r1 = words.get(new Random().nextInt(words.size()-1)).getChinese();
        String r2 = words.get(new Random().nextInt(words.size()-1)).getChinese();
        String r3 = words.get(new Random().nextInt(words.size()-1)).getChinese();

        resultlist.add(0,r1);
        resultlist.add(1,r2);
        resultlist.add(2,r3);
        resultlist.add(3,s);

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

    @Override
    public void onBackPressed() {
        backauth();
    } //退出确认

    private void backauth() {
        new AlertDialog.Builder(TestActivity.this).setTitle("确认退出吗？退出之后本次测试成绩将不会保存")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("返回", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }

    @Override
    public void onclic() {

        NoScrollViewPager vp = findViewById(R.id.vp1);
        if(vp.getCurrentItem()==99){
            setScore();
            count1 = 100;
        }else{
        vp.setCurrentItem(vp.getCurrentItem()+1,true);

        }
    }
    //设置得分
    private void setScore() {

        Intent intent1 = getIntent();
        String id = intent1.getStringExtra("id");
        Score score = new Score();
        if(id.equals("list")){
            List<Score> list = LitePal.where("list=?",lists).find(Score.class);
            if((!list.isEmpty())&&list.get(0).getScore()<count1){
                LitePal.deleteAll(Score.class,"list=?",lists);
                score.setScore(count1);
                score.setList(lists);
            }else{
                score.setScore(count1);
                score.setList(lists);
            }
        }else{
           score.setScore(count1);
        }
        score.save();
        Intent intent =new Intent(TestActivity.this,TestResultActivity.class);
        intent.putExtra("count",count1);
        startActivity(intent);
    }
}



