package com.example.fragment;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.util.Kingciba;
import com.example.util.TagsBean;
import com.example.WodeWordWorld.R;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WodeFragment extends Fragment {
    private View view;
    private TextView tv1 ;
    private TextView tv2 ;
    private ImageView img2;
    private ImageView img3;
    private SimpleDateFormat format;
    @Override

    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_wode,container,false);
        tv1 = view.findViewById(R.id.tv1);
        tv2 = view.findViewById(R.id.tv2);
        img2 =view.findViewById(R.id.img2);
        img3 =view.findViewById(R.id.tts);
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().
                getSystemService(Context.CONNECTIVITY_SERVICE);
        //得到NetWorkInfo
        NetworkInfo NetworkInfo = connectivityManager.getActiveNetworkInfo();
        //如果activeNetworkInfo为空,说明没网络
        if (NetworkInfo == null) {
            Glide.with(this).load(getActivity().getDrawable(R.drawable.echo)).apply(new RequestOptions()
                    .transforms(new CenterCrop(),new RoundedCorners(20))).into(img2);

            format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            tv1.setText(format.format(date));
            tv2.setText("一个人至少拥有一个梦想，\n\n有一个理由去坚强。\n\n"  +
                    "心若没有栖息的地方，\n\n到哪里都是在流浪。");
        }else{
            sendRequest();
        }

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(url);
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaPlayer.start();
            }
        });
        return view;
    }
    //发送网络请求
    private void sendRequest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("http://open.iciba.com/dsapi");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    // 下面对获取到的输入流进行读取
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    showResponse(response.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }



    private void showResponse(final String response) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                parseJson(response);
            }
        });
    }
    static String url;
    //解析每日一句
    public void parseJson(String jsonData){
        Gson gson = new Gson();
        Kingciba jdata = gson.fromJson(jsonData,Kingciba.class);
        List<TagsBean> beanList = jdata.getTags();
        url = jdata.getTts();
        tv1.setText("Daily Sentence"+"    "+jdata.getDateline());
        Glide.with(this).load(jdata.getPicture2()).apply(new RequestOptions()
                .transforms(new CenterCrop(),new RoundedCorners(20))).into(img2);
        Glide.with(this).load(getActivity().getDrawable(R.drawable.imagebutton_sentence_play)).into(img3);
        tv2.setText(jdata.getContent()+"\n\n"+jdata.getNote());

    }
}