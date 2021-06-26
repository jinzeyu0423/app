package com.example.version11;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.LitePal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorldFragment extends Fragment  implements View.OnClickListener{
private View view;
private ImageView imghead;
private ImageView imghistory;
private ImageView imgwb;
private ImageView imgtest;
private ImageView imgabout;
private ImageView outimg;
private ImageView imglgoin;
private ImageView imgreview;
private TextView tvreview;
private TextView tvwb;
private TextView tvhis;
private TextView tvtest;
private TextView tvabout;
private TextView show;
private TextView tvout;
private TextView tvlogin;
private TextView worldlogin;
private static final String TAG = "MainActivity";
private static final String APP_ID = "1108738063";
private Tencent mTencent;
private BaseUiListener mIUiListener;
private UserInfo mUserInfo;
static String url;

    @Override
    public void onClick(View v) {
        doLogin();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_world,container,false);
        mTencent = Tencent.createInstance(APP_ID,getActivity().getApplicationContext());
        mIUiListener = new BaseUiListener();

        imghead = view.findViewById(R.id.im_world);
        imghistory = view.findViewById(R.id.world_hsimg);
        imgwb = view.findViewById(R.id.world_wbimg);
        imgtest = view.findViewById(R.id.world_testimg);
        imgabout = view.findViewById(R.id.world_aboutimg);
        imglgoin = view.findViewById(R.id.world_loginimg);
        outimg = view.findViewById(R.id.world_outimg);
        imgreview = view.findViewById(R.id.world_reviewimg);

        worldlogin = view.findViewById(R.id.tv_worldlogin);
        tvhis = view.findViewById(R.id.tv_worldhistory);
        tvwb = view.findViewById(R.id.tv_worldwb);
        tvtest = view.findViewById(R.id.tv_worldtest);
        tvabout = view.findViewById(R.id.tv_worldhabout);
        tvout = view.findViewById(R.id.tv_out);
        tvlogin = view.findViewById(R.id.tv_worldlogin);
        show = view.findViewById(R.id.show);
        tvlogin.setOnClickListener(this);
        tvreview = view.findViewById(R.id.tv_worldreview);


        //Glide.with(getActivity()).load(url).centerCrop().into(imghead);
        Glide.with(getActivity()).load(getActivity().getDrawable(R.drawable.world2)).centerCrop().into(imghead);
        Glide.with(getActivity()).load(getActivity().getDrawable(R.drawable.ic_action_clock)).centerCrop().into(imghistory);
        Glide.with(getActivity()).load(getActivity().getDrawable(R.drawable.ic_document)).centerCrop().into(imgwb);
        Glide.with(getActivity()).load(getActivity().getDrawable(R.drawable.ic_action_info)).centerCrop().into(imgabout);
        Glide.with(getActivity()).load(getActivity().getDrawable(R.drawable.ic_action_emo_cry)).centerCrop().into(imgtest);
        Glide.with(getActivity()).load(getActivity().getDrawable(R.drawable.ic_action_save)).centerCrop().into(outimg);
        Glide.with(getActivity()).load(getActivity().getDrawable(R.drawable.qq90)).centerCrop().into(imglgoin);
        Glide.with(getActivity()).load(getActivity().getDrawable(R.drawable.ic_action_paste)).centerCrop().into(imgreview);

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
        tvhis.append("                                                  "+hsnum);

        List<WordBuilder> wbwords = LitePal.findAll(WordBuilder.class);
        String wbnum = String.valueOf(wbwords.size());
        tvwb.append("                                                      "+wbnum);

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
                startActivity(intent);
            }
        });
        tvtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),TestActivity.class);
                startActivity(intent);

            }
        });

        tvabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),ScoreActivity.class);
                startActivity(i);

            }
        });
        tvout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            List<WordBuilder> list = LitePal.findAll(WordBuilder.class);
            try {
                checkPermission();
                File file = new File("/sdcard/Download/生词本.txt");
                if(file.exists()){
                    file.delete();
                }
                FileOutputStream fos =new FileOutputStream(file);
                StringBuilder sb = new StringBuilder();
                for(int i = list.size()-1;i>=0;i--){
                    sb.append(list.get(i).getWbenglish()+"    "+list.get(i).getWbchinese()+"\n");
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
                List<WordBuilder> list = LitePal.findAll(WordBuilder.class);
                try {
                    checkPermission();
                    File file = new File("/sdcard/Download/生词本.txt");
                    if(file.exists()){
                        file.delete();
                    }
                    FileOutputStream fos =new FileOutputStream(file);
                    StringBuilder sb = new StringBuilder();
                    for(int i = list.size()-1;i>=0;i--){
                        sb.append(list.get(i).getWbenglish()+"    "+list.get(i).getWbchinese()+"\n"+list.get(i).getCreatedtime());
                    }
                    fos.write(sb.toString().getBytes());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                Toast.makeText(getActivity(),"生词本已经保存到Download文件夹",Toast.LENGTH_SHORT).show();
            }
        });
        tvreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ReviewActivity.class);
                startActivity(intent);
            }
        });

            //worldlogin.setText("登录");

        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Constants.REQUEST_LOGIN){

            Tencent.onActivityResultData(requestCode,resultCode,data,mIUiListener);

        }        super.onActivityResult(requestCode, resultCode, data);

    }
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
    class BaseUiListener implements IUiListener {

        List<String> list = new ArrayList<>();

        @Override
        public void onComplete(Object response) {
            Toast.makeText(getActivity(), "授权成功", Toast.LENGTH_SHORT).show();

            Log.e(TAG, "response:" + response);

            JSONObject obj = (JSONObject) response;

            try {

                final String openID = obj.getString("openid");

                String accessToken = obj.getString("access_token");

                String expires = obj.getString("expires_in");

                mTencent.setOpenId(openID);
                list.add(openID);

                mTencent.setAccessToken(accessToken,expires);

                QQToken qqToken = mTencent.getQQToken();

                mUserInfo = new UserInfo(getContext(),qqToken);
                mUserInfo.getUserInfo(new IUiListener() {

                    @Override

                    public void onComplete(Object response) {

                        JSONObject oo= (JSONObject) response;
                        try {
                            String nickname = oo.getString("nickname");
                            url = oo.getString("figureurl_qq_2");
                            //show.setText(openID);
                            worldlogin.setText(nickname);
                                String ff = "/data/data/com.example.version11/databases/mydict.db";
                                String out = "/data/data/com.example.version11/files/".concat(openID) + ".db";
                                copydb(ff,out);
//                                File file = new File(out);
//                                if(file.exists()){
//                                    copydb(out, ff);
//                                }else{
//                                    //CopyRawtoDB(R.raw.mydict,ff);
//                                }
                                //copydb("/data/data/com.example.version11/files/".concat(list.get(list.size() - 1)) + ".db", ff);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override

                    public void onError(UiError uiError) {

                        Log.e(TAG,"登录失败"+uiError.toString());

                    }

                    @Override

                    public void onCancel() {

                        Log.e(TAG,"登录取消");

                    }

                });

            } catch (JSONException e) {

                e.printStackTrace();

            }

        }

        @Override
        public void onError(UiError uiError) {
            Toast.makeText(getActivity(),"登录失败",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel() {
            Toast.makeText(getActivity(),"登录取消",Toast.LENGTH_SHORT).show();

        }
    }

    private void doLogin() {
        IUiListener listener = new BaseUiListener() {
        };
        mTencent.login(this, "all", listener);
    }

    public void copydb(String fileName,String outFileName) {
        try {

            FileInputStream fis = new FileInputStream(fileName);
            int size = fis.available();
            byte[] buffer = new byte[size];
            fis.read(buffer);
            fis.close();

            File file = new File(outFileName);

            if(file.exists()){
                file.delete();
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(buffer);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void CopyRawtoDB(int fileName,String outFileName) {
        try {
            InputStream is =getResources().openRawResource(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            File file = new File(outFileName);
            if(file.length()<20000){
                file.delete();
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(buffer);
                fos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
