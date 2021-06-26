package com.example.WodeWordWorld;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.LitePal.WordBuilder;

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
    private List<String> mProgress;
    static int count1 = 100;
    private int flag;
    private onlistener monlistener;


    public TestPagerAdapter(Context context ,List<String> list,List<String> listresA,
                            List<String> listresB,List<String> listresC,List<String> listresD,
                            List<String> listCh,onlistener listener,List<String> progress) {
        mContext = context;
        mData = list;
        mResultA =listresA;
        mResultB =listresB;
        mResultC =listresC;
        mResultD =listresD;
        mChinese = listCh;
        monlistener=listener;
        mProgress = progress;
    }

    @Override
    public int getCount() {
        return mData.size();

    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {

        class Onclicked{
            private void init(){
                Intent intent = new Intent(mContext,NetWordActivity.class);
                intent.putExtra("wrongword",mData.get(position));
                intent.putExtra("activity","Adapter");
                mContext.startActivity(intent);
                WordBuilder wordBuilder = new WordBuilder();
                wordBuilder.setWbenglish(mData.get(position));
                wordBuilder.setWbchinese(mChinese.get(position));
                wordBuilder.save();
                flag=flag+1;
                if(flag==1){
                    count1 = count1 - 1;
                }
            }
        }

        final View view = View.inflate(mContext, R.layout.testpage,null);
        final TextView tvword = view.findViewById(R.id.test_words);
        final TextView tvprogress = view.findViewById(R.id.testprogress);
        final Button tsetA = view.findViewById(R.id.testA);
        final Button tsetB = view.findViewById(R.id.testB);
        final Button tsetC = view.findViewById(R.id.testC);
        final Button tsetD = view.findViewById(R.id.testD);
        tvword.setText(mData.get(position));
        tvprogress.setText(mProgress.get(position)+"/100");
        tsetA.setText(mResultA.get(position));
        tsetA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mResultA.get(position).equals(mChinese.get(position))){
                    flag = 0;
                    tsetA.setBackground(mContext.getDrawable(R.drawable.buttoncorrectshape));
                    monlistener.onclic();
                }else{
                    tsetA.setBackground(mContext.getDrawable(R.drawable.buttonwrongshape));
                    Onclicked onclicked = new Onclicked();
                    onclicked.init();
                }
            }
        });

        tsetB.setText(mResultB.get(position));
        tsetB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mResultB.get(position).equals(mChinese.get(position))){
                    flag = 0;
                    tsetB.setBackground(mContext.getDrawable(R.drawable.buttoncorrectshape));
                    monlistener.onclic();
                }else{
                    tsetB.setBackground(mContext.getDrawable(R.drawable.buttonwrongshape));
                    Onclicked onclicked = new Onclicked();
                    onclicked.init();

                }
            }
        });

        tsetC.setText(mResultC.get(position));
        tsetC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mResultC.get(position).equals(mChinese.get(position))){
                    flag = 0;
                    tsetC.setBackground(mContext.getDrawable(R.drawable.buttoncorrectshape));
                    monlistener.onclic();
                }else{
                    tsetC.setBackground(mContext.getDrawable(R.drawable.buttonwrongshape));
                    Onclicked onclicked = new Onclicked();
                    onclicked.init();

                }
            }
        });


        tsetD.setText(mResultD.get(position));
        tsetD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mResultD.get(position).equals(mChinese.get(position))){
                    flag = 0;
                    tsetD.setBackground(mContext.getDrawable(R.drawable.buttoncorrectshape));
                    monlistener.onclic();

                }else{
                    tsetD.setBackground(mContext.getDrawable(R.drawable.buttonwrongshape));
                    Onclicked onclicked = new Onclicked();
                    onclicked.init();
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


