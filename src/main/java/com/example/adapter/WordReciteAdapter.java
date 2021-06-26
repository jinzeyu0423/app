package com.example.adapter;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.LitePal.WordBuilder;
import com.example.WodeWordWorld.R;

import org.litepal.LitePal;

import java.util.List;

public class WordReciteAdapter extends RecyclerView.Adapter<WordReciteAdapter.ViewHolder>{
    ViewHolder holder;
    static List<Rword> mRwordList;





    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView renglish;
        TextView rchinese;
        View wordView;
        TextView see;
        TextView delwb;
        public ViewHolder(View view) {
            super(view);
            wordView = view;
            renglish = (TextView)view.findViewById(R.id.renglish);
            rchinese = (TextView)view.findViewById(R.id.rchinese);
            see = (TextView)view.findViewById(R.id.see);
            delwb = (TextView)view.findViewById(R.id.delwb);

        }
    }
    OnItemClickListener listener;
    OnItemLongClickListener longlistener;

    public interface OnItemClickListener{
        /*注意参数*/
        void OnItemClick(View v,int position,String id);
    }

    public void setOnItemClick(OnItemClickListener listener){
        this.listener=listener;
    }
    public interface OnItemLongClickListener{
        /*注意参数*/
        void OnItemLONGClick(View v,int position,String id);
    }
    public void setOnItemLongClick(OnItemLongClickListener longlistener){
        this.longlistener=longlistener;
    }

    public WordReciteAdapter(List<Rword> wordList){
        mRwordList = wordList;
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reciteword,parent,false);
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
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Rword rword = mRwordList.get(position);
        holder.renglish.setText(rword.getRenglish());

        holder.renglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    /*注意参数*/
                    //listener.OnItemClick(v,position,mRwordList.get(position).getRenglish());
                }
            }
        });
        holder.rchinese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    /*注意参数*/
                    //listener.OnItemClick(v,position,mRwordList.get(position).getRchinese());

                }
            }
        });
        holder.renglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Rword rword = mRwordList.get(position);
            }
        });
        holder.see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.rchinese.setText(rword.getRchinese());
                holder.see.setText("");
                holder.delwb.setVisibility(View.VISIBLE);

            }
        });

        final String re = rword.getRenglish();
        final String ch = rword.getRchinese();
        holder.renglish.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                LitePal.deleteAll(WordBuilder.class,"wbenglish=?",re);
                mRwordList.remove(position);
                //删除动画
                notifyItemRemoved(position);
                notifyDataSetChanged();

                Snackbar.make(view,re+"已从生词本移除",Snackbar.LENGTH_LONG).
                        setAction("撤销", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                WordBuilder wordBuilder = new WordBuilder();
                                wordBuilder.setWbenglish(re);
                                wordBuilder.setWbchinese(ch);
                                wordBuilder.save();
                                mRwordList.add(position, rword);
                                //添加动画
                                notifyItemInserted(position);
                                notifyItemRangeChanged(position,mRwordList.size()-position);//通知数据与界面重新绑定

                            }
                        }).show();

                return true;
            }
        });
        holder.rchinese.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                Snackbar.make(view,re+"已从生词本删除",Snackbar.LENGTH_LONG).
                        setAction("撤销", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                WordBuilder wordBuilder = new WordBuilder();
                                wordBuilder.setWbenglish(rword.getRenglish());
                                wordBuilder.setWbchinese(rword.getRchinese());
                                wordBuilder.save();
                                mRwordList.add(position, rword);
                                //添加动画
                                notifyItemInserted(position);
                                notifyItemRangeChanged(position,mRwordList.size()-position);//通知数据与界面重新绑定
                            }
                        }).show();
                LitePal.deleteAll(WordBuilder.class,"wbenglish=?",rword.getRenglish());
                mRwordList.remove(position);
                //删除动画
                notifyItemRemoved(position);
                notifyDataSetChanged();
                return true;
            }
        });
        holder.delwb.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                Snackbar.make(view,re+"已从生词本删除",Snackbar.LENGTH_LONG).
                        setAction("撤销", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                WordBuilder wordBuilder = new WordBuilder();
                                wordBuilder.setWbenglish(rword.getRenglish());
                                wordBuilder.setWbchinese(rword.getRchinese());
                                wordBuilder.save();
                                mRwordList.add(position, rword);
                                //添加动画
                                notifyItemInserted(position);
                                notifyItemRangeChanged(position,mRwordList.size()-position);//通知数据与界面重新绑定


                            }
                        }).show();
                LitePal.deleteAll(WordBuilder.class,"wbenglish=?",rword.getRenglish());
                mRwordList.remove(position);
                //删除动画
                notifyItemRemoved(position);
                notifyDataSetChanged();
                return true;
            }
        });

    }


    @Override
    public int getItemCount() {
        return mRwordList.size();
    }

}
