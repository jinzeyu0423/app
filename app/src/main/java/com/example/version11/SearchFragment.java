package com.example.version11;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import static com.example.version11.WordAdapter.mRwordList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements ontextlistener {
private View view;
private List<Rword> wordList = new ArrayList<Rword>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_search,container,false);
//        final MainActivity activity = new MainActivity();
//        //activity.setMontextlistener(new ontextlistener() {
//            @Override
//            public void listener() {
//                //activity.dosth();
//
//            }
//        });

//        LitePal.initialize(getActivity());
//        final RecyclerView recyclerView = view.findViewById(R.id.searec);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(linearLayoutManager);
//        final WordAdapter wordAdapter = new WordAdapter(wordList);
//        recyclerView.setAdapter(wordAdapter);
////        recyclerView.removeAllViews();
////        wordAdapter.notifyDataSetChanged();
////        wordList.clear();
//
//        wordAdapter.setOnItemClick(new WordAdapter.OnItemClickListener() {
//            @Override
//            public void OnItemClick(View v, int position, String id) {
//
//                Rword rword = mRwordList.get(position);
//                WordBuilder wbWord = new WordBuilder();
//                wbWord.setWbchinese(rword.getRchinese());
//                wbWord.setWbenglish(rword.getRenglish());
//                wbWord.save();
//
//                Intent intent = new Intent(getActivity(),NetWordActivity.class);
//                startActivity(intent);
//
//            }
//
//        });
//



//
//
                //    editText =findViewById(R.id.wodeedit1);
//
                //      editText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                replaceFrag(new HistoryFragment());
//            }
//        });
//
//        editText.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                replaceFrag(new SearchFragment());
//                recyclerView.removeAllViews();
//                wordAdapter.notifyDataSetChanged();
//                wordList.clear();
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                String inputText = s.toString();
//                big = inputText;
//                if (TextUtils.isEmpty(s)) {
//                    replaceFrag(new HistoryFragment());
//
//
//                    recyclerView.removeAllViews();
//                    wordAdapter.notifyDataSetChanged();
//                    wordList.clear();
//
//                } else {
//                    List<Word> words = LitePal.where("english like ?", inputText + "%").find(Word.class);
//
//                    for (Word word : words) {
//                        String chinese = word.getChinese();
//                        String english = word.getEnglish();
//                        Rword rword = new Rword(english, chinese);
//                        wordList.add(rword);
//                    }
//                }
//            }
//            @Override
//            public void afterTextChanged(Editable s) {
//                if(s==null){
//                    wordList.clear();
//
//                }
//
//            }
//        });


        return view;
    }
public static void method1(){


}
    @Override
    public void listener() {
        Toast.makeText(getActivity(),"hhh",Toast.LENGTH_SHORT).show();
    }


//        LitePal.initialize(getActivity());
//        final RecyclerView recyclerView = view.findViewById(R.id.searec);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(linearLayoutManager);
//        final WordAdapter wordAdapter = new WordAdapter(wordList);
//        recyclerView.setAdapter(wordAdapter);
//        recyclerView.removeAllViews();
//        wordAdapter.notifyDataSetChanged();
//        wordList.clear();
//
//        wordAdapter.setOnItemClick(new WordAdapter.OnItemClickListener() {
//            @Override
//            public void OnItemClick(View v, int position, String id) {
//
//                Rword rword = mRwordList.get(position);
//                WordBuilder wbWord = new WordBuilder();
//                wbWord.setWbchinese(rword.getRchinese());
//                wbWord.setWbenglish(rword.getRenglish());
//                wbWord.save();
//
//                Intent intent = new Intent(getActivity(),NetWordActivity.class);
//                startActivity(intent);
//
//            }
//
//        });
    }

