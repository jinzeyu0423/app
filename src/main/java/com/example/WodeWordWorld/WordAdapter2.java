package com.example.WodeWordWorld;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adapter.Rword;

import java.util.List;

public class WordAdapter2 extends RecyclerView.Adapter<WordAdapter2.ViewHolder>{
    static List<Rword> mRwordList2;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView renglish;
        TextView rchinese;
        View wordView;

        public ViewHolder(View view) {
            super(view);
            wordView = view;
            renglish = view.findViewById(R.id.renglish);
            rchinese = view.findViewById(R.id.rchinese);
        }
    }
    OnItemClickListener listener;
    public interface OnItemClickListener{
        void OnItemClick(View v,int position,String id);
    }
    public void setOnItemClick(OnItemClickListener listener){
        this.listener=listener;
    }

    public WordAdapter2(List<Rword> wordList){
        mRwordList2 = wordList;
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wordh,parent,false);
        final  ViewHolder holder = new ViewHolder(view);
        holder.rchinese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Rword rword = mRwordList2.get(position);
            }
        });
        holder.renglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Rword rword = mRwordList2.get(position);


            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Rword rword = mRwordList2.get(position);
        holder.renglish.setText(rword.getRenglish());
        holder.rchinese.setText(rword.getRchinese());
        holder.renglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.OnItemClick(v,position,mRwordList2.get(position).getRenglish());
                }
            }
        });
        holder.rchinese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.OnItemClick(v,position,mRwordList2.get(position).getRchinese());

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mRwordList2.size();
    }

}
