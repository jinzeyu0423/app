package com.example.WodeWordWorld;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.LitePal.HsWord;
import com.example.adapter.Rword;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import static com.example.WodeWordWorld.WordAdapter2.mRwordList2;


/**
 * 历史记录fragment
 */
public class HistoryFragment extends Fragment implements FragmentBackHandler{
private View view;
private List<Rword> RwordList = new ArrayList<Rword>();
private TextView hsdelete;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_history,container,false);
        final RecyclerView recyclerView = view.findViewById(R.id.search_hisrec);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        final WordAdapter2 wordAdapter = new WordAdapter2(RwordList);
        recyclerView.setAdapter(wordAdapter);
        List<HsWord> words = LitePal.findAll(HsWord.class);
        hsdelete = view.findViewById(R.id.hs_delete);
        hsdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.initialize(getActivity());
                LitePal.deleteAll(HsWord.class);
                recyclerView.removeAllViews();
                wordAdapter.notifyDataSetChanged();
                RwordList.clear();

            }
        });
        //倒序输出
        for(int i = words.size()-1;i>=0;i--){
                Rword rword = new Rword(words.get(i).getHsenglish(),words.get(i).getHschinese());
                RwordList.add(rword);
        }

        wordAdapter.setOnItemClick(new WordAdapter2.OnItemClickListener() {
            @Override
            public void OnItemClick(View v, int position, String id) {
                Rword rword = mRwordList2.get(position);
                Intent intent = new Intent(getActivity(), NetWordActivity.class);
                intent.putExtra("renglish",rword.getRenglish());
                intent.putExtra("rchinese",rword.getRchinese());
                intent.putExtra("activity","History");
                startActivity(intent);

            }

        });
        return view;
    }

    @Override
    public boolean onBackPressed() {
        return BackHandlerHelper.handleBackPress(this);
    }
}
