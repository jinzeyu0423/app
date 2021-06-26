package com.example.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.WodeWordWorld.R;

import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder>{
    static List<Rword> mRwordList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView renglish;
        TextView rchinese;
        View wordView;

        public ViewHolder(View view) {
            super(view);
            wordView = view;
            renglish = view.findViewById(R.id.renglish1);
            rchinese = view.findViewById(R.id.rchinese1);
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
                    listener.OnItemClick(v,position,mRwordList.get(position).getRenglish());
                }
            }
        });
        holder.rchinese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
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
