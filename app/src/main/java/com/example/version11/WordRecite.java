package com.example.version11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import static android.app.PendingIntent.getActivity;

public class WordRecite extends AppCompatActivity {
private List<Rword> wordList = new ArrayList<>();
private ImageButton imgback;
private ImageButton imgsearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wordrecite);
        imgback = (ImageButton)findViewById(R.id.img_back);
        imgsearch = (ImageButton)findViewById(R.id.img_search);
        Glide.with(this).load(getDrawable(R.drawable.ic_action_arrow_left)).centerCrop().into(imgback);
        Glide.with(this).load(getDrawable(R.drawable.ic_action_search)).centerCrop().into(imgsearch);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        final WordReciteAdapter wordReciteAdapter = new WordReciteAdapter(wordList);
        recyclerView.setAdapter(wordReciteAdapter);
        List<WordBuilder> words = LitePal.findAll(WordBuilder.class);


        for(int i = words.size()-1;i>=0;i--){
            Rword rword = new Rword(words.get(i).getWbenglish(),words.get(i).getWbchinese());
            wordList.add(rword);
        }
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        imgsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WordRecite.this,MainActivity.class);
                intent.putExtra("id",1);
                startActivity(intent);

            }
        });

    }
}
