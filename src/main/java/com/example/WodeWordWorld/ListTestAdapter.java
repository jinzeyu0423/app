package com.example.WodeWordWorld;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adapter.RScore;

import java.util.List;

public class ListTestAdapter extends RecyclerView.Adapter<ListTestAdapter.ViewHolder>{
    static List<RScore> mRScoreList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView list;
        TextView score;
        TextView test;
        View wordView;

        public ViewHolder(View view) {
            super(view);
            wordView = view;
            list = view.findViewById(R.id.list);
            score = view.findViewById(R.id.score);
            test = view.findViewById(R.id.test);

        }
    }
    OnItemClickListener listener;
    public interface OnItemClickListener{
        void OnItemClick(View v,int position,String id);
    }
    public void setOnItemClick(OnItemClickListener listener){
        this.listener=listener;
    }

    public ListTestAdapter(List<RScore> wordList){
        mRScoreList = wordList;
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listtest,parent,false);
        final  ViewHolder holder = new ViewHolder(view);
        holder.list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                RScore rScore = mRScoreList.get(position);
            }
        });
        holder.score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                RScore rScore = mRScoreList.get(position);
            }
        });
        holder.test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                RScore rScore = mRScoreList.get(position);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final RScore rScore = mRScoreList.get(position);
        holder.score.setText(rScore.getScore());
        holder.list.setText(rScore.getList());
        holder.test.setText(rScore.getTest());

        holder.score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.OnItemClick(v,position,mRScoreList.get(position).getScore());
                }
            }
        });
        holder.list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.OnItemClick(v,position,mRScoreList.get(position).getList());
                }
            }
        });
        holder.test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.OnItemClick(v,position,mRScoreList.get(position).getList());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mRScoreList.size();
    }

}
