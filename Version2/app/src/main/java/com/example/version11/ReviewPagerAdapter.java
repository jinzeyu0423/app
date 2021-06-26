package com.example.version11;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.litepal.LitePal;

import java.util.List;
interface onrelistener{
    void onclic();
}
public class ReviewPagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<String> mData;
    private List<String> mch;
    private onrelistener monrelistener;

    public ReviewPagerAdapter(Context context ,List<String> list,List<String> listch, onrelistener listener) {
        mContext = context;
        mData = list;
        mch = listch;
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

        tvhint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvch.setVisibility(View.VISIBLE);
                tvch.setText(mch.get(position));
                know.setVisibility(View.VISIBLE);
                dknow.setVisibility(View.VISIBLE);
                tvhint.setVisibility(View.GONE);
            }
        });
        know.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monrelistener.onclic();
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


