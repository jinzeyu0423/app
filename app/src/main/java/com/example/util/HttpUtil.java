package com.example.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

//import static com.example.version11.NetWordActivity.Ur;

public class HttpUtil {

    /**
     * 使用OkHttp网络工具发送网络请求
     * */
    public static void sendOkHttpRequest(final String address){
    new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                //创建OkHttpClient对象
                OkHttpClient client = new OkHttpClient();

                //创建Request对象，装上地址
                Request request = new Request.Builder().url(address).build();
            } catch (Exception e) {
                e.printStackTrace();
            }

//        //发送请求，返回数据需要自己重写回调方法
//        client.newCall(request).enqueue(callback);
        }
    }).start();


}

}