package com.example.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.LitePal.WordBuilder;
import com.example.adapter.WordReciteAdapter;
import com.example.WodeWordWorld.R;
import com.example.adapter.Rword;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;


public class WordFragment extends Fragment {
    private List<Rword> wordList = new ArrayList<>();
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_wordrecite, container, false);
        final RecyclerView recyclerView =view.findViewById(R.id.reciterecycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        final WordReciteAdapter wordReciteAdapter = new WordReciteAdapter(wordList);
        recyclerView.setAdapter(wordReciteAdapter);
        List<WordBuilder> words = LitePal.findAll(WordBuilder.class);

        for (int i = words.size() - 1; i >= 0; i--) {
            Rword rword = new Rword(words.get(i).getWbenglish(), words.get(i).getWbchinese());
            wordList.add(rword);
        }

        return view;
    }
}

