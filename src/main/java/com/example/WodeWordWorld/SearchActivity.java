package com.example.WodeWordWorld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;
import com.example.LitePal.HsWord;
import com.example.LitePal.Word;
import com.example.adapter.Rword;

import org.litepal.LitePal;

import java.util.ArrayList;

import java.util.List;

import static com.example.WodeWordWorld.WordAdapter2.*;

public class SearchActivity extends AppCompatActivity {
    private EditText editText1;
    private List<Rword> wordList = new ArrayList<Rword>();
    private ImageButton img_back;
    private ImageButton img_delete;
    static String big;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        LitePal.initialize(this);
        LitePal.getDatabase();
        final RecyclerView recyclerView =  findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        final WordAdapter2 wordAdapter = new WordAdapter2(wordList);
        recyclerView.setAdapter(wordAdapter);

        wordAdapter.setOnItemClick(new WordAdapter2.OnItemClickListener() {
            @Override
            public void OnItemClick(View v, int position, String id) {

                Rword rword = mRwordList2.get(position);
                HsWord hsWord = new HsWord();
                hsWord.setHschinese(rword.getRchinese());
                hsWord.setHsenglish(rword.getRenglish());
                hsWord.save();

                Intent intent = new Intent(SearchActivity.this, NetWordActivity.class);
                /*传ID在Main2Activity接受*/
                intent.putExtra("renglish", rword.getRenglish());
                intent.putExtra("rchinese", rword.getRchinese());
                intent.putExtra("activity","wode1");
                startActivity(intent);


            }

        });


        editText1 = findViewById(R.id.seaedit1);
        img_back =  findViewById(R.id.seaimg_back);
        img_delete =  findViewById(R.id.seaimg_delete);
        Glide.with(this).load(getDrawable(R.drawable.ic_action_arrow_left)).centerCrop().into(img_back);
        Glide.with(this).load(getDrawable(R.drawable.ic_action_cancel)).centerCrop().into(img_delete);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText1.setText("");
            }
        });

        editText1.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                recyclerView.removeAllViews();
                wordAdapter.notifyDataSetChanged();
                wordList.clear();
                img_delete.setVisibility(View.VISIBLE);

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String inputText = s.toString();
                big = inputText;
                if (TextUtils.isEmpty(s)) {
                    recyclerView.removeAllViews();
                    wordAdapter.notifyDataSetChanged();
                    wordList.clear();
                    img_delete.setVisibility(View.GONE);
                } else {
                    //用like查询进行自动补全
                    List<Word> words = LitePal.where("english like ?", inputText + "%").find(Word.class);
                    if(words.isEmpty()){

                    }else{

                    for (Word word : words) {
                        String chinese = word.getChinese();
                        String english = word.getEnglish();
                        Rword rword = new Rword(english, chinese);
                        wordList.add(rword);
                    }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s == null) {
                    wordList.clear();
                }
            }
        });
    }
}
