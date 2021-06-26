package com.example.WodeWordWorld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.LitePal.Score;
import com.example.adapter.RScore;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import static com.example.WodeWordWorld.ListTestAdapter.mRScoreList;

public class ListTestActivity extends AppCompatActivity {
private List<RScore> RScoreList = new ArrayList<RScore>();
private ImageView imgback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listtest);
        imgback = findViewById(R.id.img_listtestback);
        Glide.with(this).load(getDrawable(R.drawable.ic_action_arrow_left)).centerCrop().into(imgback);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.listtestrec);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        ListTestAdapter adapter = new ListTestAdapter(RScoreList);
        recyclerView.setAdapter(adapter);
        //显示单词分组
        for(int i=1;i<56;i++){
            List<Score> score = LitePal.where("list = ?",
                    "list".concat(String.valueOf(i))).find(Score.class);
            if(!score.isEmpty()){
                RScore rScore = new RScore("list".concat(String.valueOf(i)),
                        "最高得分："+String.valueOf(score.get(0).getScore()),"点击卡片再次进行测试");
                RScoreList.add(rScore);
            }else{
                RScore rScore = new RScore("list".concat(String.valueOf(i)),
                        "","点击卡片进行测试");
                RScoreList.add(rScore);
            }
        }
        adapter.setOnItemClick(new ListTestAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View v, int position, String id) {
                RScore rScore = mRScoreList.get(position);
                Intent intent = new Intent(ListTestActivity.this,TestActivity.class);
                intent.putExtra("id","list");
                intent.putExtra("list",rScore.getList());
                startActivity(intent);

            }
        });

    }
}
