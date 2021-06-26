package com.example.WodeWordWorld;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.LitePal.WordBuilder;
import org.litepal.LitePal;
import java.util.List;
interface onrelistener{
    void onclic();
}
public class ReviewPagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<String> mData;
    private List<String> mch;
    private List<Integer> mday;
    private onrelistener monrelistener;

    public ReviewPagerAdapter(Context context ,List<String> list,List<String> listch,
                              List<Integer> listday,onrelistener listener) {
        mContext = context;
        mData = list;
        mch = listch;
        mday = listday;
        monrelistener = listener;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = View.inflate(mContext, R.layout.reviewpage,null);
        TextView tv = view.findViewById(R.id.reviewword);
        tv.setText(mData.get(position));
        final TextView tvch = view.findViewById(R.id.reviewchinese);
        final TextView tvhint = view.findViewById(R.id.reviewhint);
        final Button know = view.findViewById(R.id.reviewknow);
        final Button dknow = view.findViewById(R.id.reviewdknow);
        final TextView reviewloud = view.findViewById(R.id.reviewloud);


        tvhint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvch.setVisibility(View.VISIBLE);
                tvch.setText(mch.get(position));
                know.setVisibility(View.VISIBLE);
                dknow.setVisibility(View.VISIBLE);
                tvhint.setVisibility(View.GONE);
                reviewloud.setVisibility(View.GONE);
            }
        });
        know.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monrelistener.onclic();

                if(mday.get(position)==1){
                    WordBuilder wordBuilder = new WordBuilder();
                    wordBuilder.setDay1(1);
                    wordBuilder.updateAll("wbenglish=?",mData.get(position));
                }else if(mday.get(position)==2){
                    WordBuilder wordBuilder = new WordBuilder();
                    wordBuilder.setDay2(1);
                    wordBuilder.updateAll("wbenglish=?",mData.get(position));
                }else if(mday.get(position)==4){
                    WordBuilder wordBuilder = new WordBuilder();
                    wordBuilder.setDay4(1);
                    wordBuilder.updateAll("wbenglish=?",mData.get(position));
                }else if(mday.get(position)==7){
                    WordBuilder wordBuilder = new WordBuilder();
                    wordBuilder.setDay7(1);
                    wordBuilder.updateAll("wbenglish=?",mData.get(position));
                }else{
                    WordBuilder wordBuilder = new WordBuilder();
                    wordBuilder.setDay15(1);
                    wordBuilder.updateAll("wbenglish=?",mData.get(position));
                }

            }
        });
        dknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monrelistener.onclic();
                LitePal.deleteAll(WordBuilder.class,"wbenglish=?",mData.get(position));
                WordBuilder wordBuilder = new WordBuilder();
                wordBuilder.setWbenglish(mData.get(position));
                wordBuilder.setWbchinese(mch.get(position));
                wordBuilder.save();
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}


