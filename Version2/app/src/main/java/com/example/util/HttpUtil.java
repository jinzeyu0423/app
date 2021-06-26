package com.example.util;

import com.example.version11.NetWordActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;

//import static com.example.version11.NetWordActivity.Ur;

public class HttpUtil {
//    /**
//     * 使用OkHttp网络工具发送网络请求
//     * */
//    public static void sendOkHttpRequest(final String address){
//    new Thread(new Runnable() {
//        @Override
//        public void run() {
//            try {
//                //创建OkHttpClient对象
//                OkHttpClient client = new OkHttpClient();
//
//                //创建Request对象，装上地址
//                Request request = new Request.Builder().url(address).build();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
////        //发送请求，返回数据需要自己重写回调方法
////        client.newCall(request).enqueue(callback);
//        }
//    }).start();
//
//
//}
public static void sendRequest(final String urll) {
    new Thread(new Runnable() {
        @Override
        public void run() {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {

                URL url = new URL(urll);
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
                if(urll.contains("json")){

                    //showResponse(response.toString());
                }else{
                   // showResponsex(response.toString());
                }

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


}