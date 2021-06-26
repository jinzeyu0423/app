package com.example.fragment;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.LitePal.HsWord;
import com.example.LitePal.Word;
import com.example.LitePal.WordBuilder;
import com.example.LitePal.WordsBuilder;
import com.example.test.AboutActivity;
import com.example.WodeWordWorld.HistoryFragment;
import com.example.WodeWordWorld.ListTestActivity;
import com.example.WodeWordWorld.MainActivity;
import com.example.WodeWordWorld.R;
import com.example.WodeWordWorld.TestActivity;

import org.litepal.LitePal;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorldFragment extends Fragment {
private View view;
private ImageView imghead;
private ImageView imghistory;
private ImageView imgwb;
private ImageView imgtest;
private ImageView imgabout;
private ImageView outimg;
private ImageView imglisttest;
private TextView tvwb;
private TextView tvhis;
private TextView tvtest;
private TextView tvabout;
private TextView tvout;
private TextView listtest;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_world,container,false);


        imghead = view.findViewById(R.id.im_world);
        imghistory = view.findViewById(R.id.world_hsimg);
        imgwb = view.findViewById(R.id.world_wbimg);
        imgtest = view.findViewById(R.id.world_testimg);
        imgabout = view.findViewById(R.id.world_aboutimg);
        outimg = view.findViewById(R.id.world_outimg);
        imglisttest = view.findViewById(R.id.world_listtest);

        listtest = view.findViewById(R.id.tv_worldlisttest);
        tvhis = view.findViewById(R.id.tv_worldhistory);
        tvwb = view.findViewById(R.id.tv_worldwb);
        tvtest = view.findViewById(R.id.tv_worldtest);
        tvabout = view.findViewById(R.id.tv_worldhabout);
        tvout = view.findViewById(R.id.tv_out);


        Glide.with(getActivity()).load(getActivity().getDrawable(R.drawable.world2)).centerCrop().into(imghead);
        Glide.with(getActivity()).load(getActivity().getDrawable(R.drawable.ic_action_clock)).centerCrop().into(imghistory);
        Glide.with(getActivity()).load(getActivity().getDrawable(R.drawable.ic_document)).centerCrop().into(imgwb);
        Glide.with(getActivity()).load(getActivity().getDrawable(R.drawable.ic_action_info)).centerCrop().into(imgabout);
        Glide.with(getActivity()).load(getActivity().getDrawable(R.drawable.ic_action_emo_cry)).centerCrop().into(imgtest);
        Glide.with(getActivity()).load(getActivity().getDrawable(R.drawable.ic_action_save)).centerCrop().into(outimg);
        Glide.with(getActivity()).load(getActivity().getDrawable(R.drawable.sort1)).centerCrop().into(imglisttest);

        imghistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity)getActivity();
                activity.replaceFrag(new HistoryFragment());
            }
        });
        imgwb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity)getActivity();
                activity.replaceFrag(new WordFragment());
            }

        });
        tvhis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity)getActivity();
                activity.replaceFrag(new HistoryFragment());
            }

        });
        List<HsWord> hswords = LitePal.findAll(HsWord.class);
        String hsnum = String.valueOf(hswords.size());
        List<WordBuilder> wbwords = LitePal.findAll(WordBuilder.class);
        String wbnum = String.valueOf(wbwords.size());
        tvhis.setText("历史记录"+"                                 "+hsnum);
        tvwb.append("                                    "+wbnum);

        tvwb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity)getActivity();
                activity.replaceFrag(new WordFragment());
            }

        });


        imgtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(),TestActivity.class);
                intent.putExtra("id","");
                startActivity(intent);
            }
        });
        tvtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),TestActivity.class);
                intent.putExtra("id","");
                startActivity(intent);

            }
        });

        tvabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),AboutActivity.class);
                startActivity(i);


            }
        });
        tvout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            List<HsWord> list = LitePal.findAll(HsWord.class);
            try {
                checkPermission();
                File file = new File("/sdcard/Download/生词本.txt");
                if(file.exists()){
                    file.delete();
                }
                FileOutputStream fos =new FileOutputStream(file);
                StringBuilder sb = new StringBuilder();
                for(int i = list.size()-1;i>=0;i--){

                    sb.append(list.get(i).getHsenglish()+"\n");

                }
                fos.write(sb.toString().getBytes());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                Toast.makeText(getActivity(),"生词本已经保存到Download文件夹",Toast.LENGTH_SHORT).show();
            }
        });
        outimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Word> list = LitePal.findAll(Word.class);
                try {
                    checkPermission();
                    File file = new File("/sdcard/Download/生词本.txt");
                    if(file.exists()){
                        file.delete();
                    }
                    FileOutputStream fos =new FileOutputStream(file);
                    StringBuilder sb = new StringBuilder();
                    for(int i = list.size()-1;i>=0;i--){
                        sb.append(list.get(i).getEnglish()+"    "+list.get(i).getChinese()+"\n");
                    }
                    fos.write(sb.toString().getBytes());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                Toast.makeText(getActivity(),"生词本已经保存到Download文件夹",Toast.LENGTH_SHORT).show();
            }
        });


        listtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ListTestActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
    //获取读写权限
    public void checkPermission() {
        boolean isGranted = true;
            if (getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                //如果没有写sd卡权限
                isGranted = false;
            }
            if (getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                isGranted = false;
            }
            if (!isGranted) {
                getActivity().requestPermissions(
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission
                                .ACCESS_FINE_LOCATION,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        102);
            }
    }

}
