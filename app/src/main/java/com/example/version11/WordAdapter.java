package com.example.version11;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder>{
    //ViewHolder holder;
    static List<Rword> mRwordList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView renglish;
        TextView rchinese;
        View wordView;

        public ViewHolder(View view) {
            super(view);
            wordView = view;
            renglish = (TextView)view.findViewById(R.id.renglish1);
            rchinese = (TextView)view.findViewById(R.id.rchinese1);
        }
    }
    OnItemClickListener listener;
    public interface OnItemClickListener{
        /*注意参数*/
        void OnItemClick(View v,int position,String id);
    }
    public void setOnItemClick(OnItemClickListener listener){
        this.listener=listener;
    }

    public WordAdapter(List<Rword> wordList){
        mRwordList = wordList;
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_wordrecite,parent,false);
        final  ViewHolder holder = new ViewHolder(view);
        holder.rchinese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Rword rword = mRwordList.get(position);
                //Toast.makeText(v.getContext(),rword.getRchinese(),Toast.LENGTH_SHORT).show();
            }
        });
        holder.renglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Rword rword = mRwordList.get(position);


            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Rword rword = mRwordList.get(position);
        holder.renglish.setText(rword.getRenglish());
        holder.rchinese.setText(rword.getRchinese());
        holder.renglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    /*注意参数*/
                    listener.OnItemClick(v,position,mRwordList.get(position).getRenglish());
                }
            }
        });
        holder.rchinese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    /*注意参数*/
                    listener.OnItemClick(v,position,mRwordList.get(position).getRchinese());

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mRwordList.size();
    }

}
