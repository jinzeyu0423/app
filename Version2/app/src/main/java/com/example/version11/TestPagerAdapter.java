package com.example.version11;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
interface onlistener{
    void onclic();
}

public class TestPagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<String> mData;
    private List<String> mResultA;
    private List<String> mResultB;
    private List<String> mResultC;
    private List<String> mResultD;
    private List<String> mChinese;
    static int count1;
    private onlistener monlistener;


    public TestPagerAdapter(Context context ,List<String> list,List<String> listresA,
                            List<String> listresB,List<String> listresC,List<String> listresD,List<String> listCh,onlistener listener) {
        mContext = context;
        mData = list;
        mResultA =listresA;
        mResultB =listresB;
        mResultC =listresC;
        mResultD =listresD;
        mChinese = listCh;
        monlistener=listener;
    }

    @Override
    public int getCount() {
        return mData.size();

    }


    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {



        final View view = View.inflate(mContext, R.layout.testpage,null);
        final TextView tvword = view.findViewById(R.id.test_words);
        tvword.setText(mData.get(position));
        final Button tsetA = view.findViewById(R.id.testA);

        tsetA.setText(mResultA.get(position));
        tsetA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mResultA.get(position).equals(mChinese.get(position))){
                    tsetA.setBackground(mContext.getDrawable(R.drawable.buttoncorrectshape));
                    monlistener.onclic();
                count1 = count1 + 1;
                }else{
                 tsetA.setBackground(mContext.getDrawable(R.drawable.buttonwrongshape));
                    Intent intent = new Intent(mContext,NetWordActivity.class);
                    intent.putExtra("wrongword",mData.get(position));
                    intent.putExtra("activity","Adapter");
                    mContext.startActivity(intent);
                    WordBuilder wordBuilder = new WordBuilder();
                    wordBuilder.setWbenglish(mData.get(position));
                    wordBuilder.setWbchinese(mChinese.get(position));
                    wordBuilder.save();
                    count1 = count1 - 1;

                }
            }
        });

        final Button tsetB = view.findViewById(R.id.testB);
        tsetB.setText(mResultB.get(position));
        tsetB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mResultB.get(position).equals(mChinese.get(position))){
                    tsetB.setBackground(mContext.getDrawable(R.drawable.buttoncorrectshape));
                    monlistener.onclic();
                    count1 = count1 + 1;
                }else{
                    tsetB.setBackground(mContext.getDrawable(R.drawable.buttonwrongshape));
                    Intent intent = new Intent(mContext,NetWordActivity.class);
                    intent.putExtra("wrongword",mData.get(position));
                    intent.putExtra("activity","Adapter");
                    mContext.startActivity(intent);
                    WordBuilder wordBuilder = new WordBuilder();
                    wordBuilder.setWbenglish(mData.get(position));
                    wordBuilder.setWbchinese(mChinese.get(position));
                    wordBuilder.save();
                    count1 = count1 - 1;

                }
            }
        });

        final Button tsetC = view.findViewById(R.id.testC);
        tsetC.setText(mResultC.get(position));
        tsetC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mResultC.get(position).equals(mChinese.get(position))){
                    tsetC.setBackground(mContext.getDrawable(R.drawable.buttoncorrectshape));
                    monlistener.onclic();
                    count1 = count1 + 1;
                }else{
                    tsetC.setBackground(mContext.getDrawable(R.drawable.buttonwrongshape));
                    Intent intent = new Intent(mContext,NetWordActivity.class);
                    intent.putExtra("wrongword",mData.get(position));
                    intent.putExtra("activity","Adapter");
                    mContext.startActivity(intent);
                    WordBuilder wordBuilder = new WordBuilder();
                    wordBuilder.setWbenglish(mData.get(position));
                    wordBuilder.setWbchinese(mChinese.get(position));
                    wordBuilder.save();
                    count1 = count1 - 1;

                }
            }
        });


        final Button tsetD = view.findViewById(R.id.testD);
        tsetD.setText(mResultD.get(position));
        tsetD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mResultD.get(position).equals(mChinese.get(position))){
                    tsetD.setBackground(mContext.getDrawable(R.drawable.buttoncorrectshape));
                    monlistener.onclic();
                    count1 = count1 + 1;
                }else{
                    tsetD.setBackground(mContext.getDrawable(R.drawable.buttonwrongshape));
                    Intent intent = new Intent(mContext,NetWordActivity.class);
                    intent.putExtra("wrongword",mData.get(position));
                    intent.putExtra("activity","Adapter");
                    mContext.startActivity(intent);
                    WordBuilder wordBuilder = new WordBuilder();
                    wordBuilder.setWbenglish(mData.get(position));
                    wordBuilder.setWbchinese(mChinese.get(position));
                    wordBuilder.save();
                    count1 = count1 - 1;

                }
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


