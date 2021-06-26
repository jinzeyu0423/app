package com.example.version11;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class WodeFragment extends Fragment {
    private View view;
    private TextView tv1 ;
    private TextView tv2 ;
    private ImageView img2;
    private ImageView img3;
    //MainActivity mainActivity = (MainActivity)getActivity();
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_wode,container,false);
        sendRequest();
        tv1 = view.findViewById(R.id.tv1);
        tv2 = view.findViewById(R.id.tv2);
        img2 =view.findViewById(R.id.img2) ;
        img3 =view.findViewById(R.id.tts);

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