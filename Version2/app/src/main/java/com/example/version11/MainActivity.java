package com.example.version11;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.LitePal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static com.example.version11.WordAdapter.mRwordList;


public class MainActivity extends FragmentActivity{

String ff = "/data/data/com.example.version11/databases/mydict.db";

    public void onBackPressed() {

        if (!BackHandlerHelper.handleBackPress(this)) {
            Fragment current = getSupportFragmentManager().findFragmentById(R.id.frag_wode);
            if(current instanceof WodeFragment){//判断当前是哪个fragment
                //ActivityKiller.getInstance().exit();
                finish();
            }else {
                replaceFrag(new WodeFragment());
            }
        }
    }


    private ImageButton img_delete;
    private EditText editText;


    private MyDatabaseHelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //拷贝数据库到指定文件夹

        dbhelper = new MyDatabaseHelper(this,"mydict.db",null,27);
        dbhelper.getWritableDatabase();
        CopyRawtoDB(R.raw.mydict,ff);
        LitePal.initialize(this);

        replaceFrag(new WodeFragment());
        img_delete = findViewById(R.id.wodeimg_delete);

        Glide.with(this).load(getDrawable(R.drawable.ic_action_cancel)).centerCrop().into(img_delete);
        SQLiteStudioService.instance().start(this);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        editText = findViewById(R.id.wodeedit1);
        editText.setFocusable(false);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,WodeActivity1.class);
                startActivity(intent);
            }
        });


        int id = getIntent().getIntExtra("id", 0);
        if(id==1){
            replaceFrag(new HistoryFragment());

        }
        int iid = getIntent().getIntExtra("iid",0);
        if(iid==1){
            replaceFrag(new HistoryFragment());
        }
        int iiid = getIntent().getIntExtra("iiid",0);

        if(iiid==1){
            replaceFrag(new WorldFragment());
        }
        img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });

    }

    public void CopyRawtoDB(int fileName,String outFileName) {
        try {
            InputStream is =getResources().openRawResource(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            File file = new File(outFileName);
            if(file.length()<50000){
                file.delete();
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(buffer);
                fos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.wode:
                    replaceFrag(new WodeFragment());
                    return true;
                case R.id.word:
                    replaceFrag(new WordFragment());
                    return true;
                case R.id.world:
                    replaceFrag(new WorldFragment());
                    return true;

            }
            return false;
        }

    };

    public void replaceFrag(Fragment fragment) {
        FragmentManager fragmentManager =getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frag_wode,fragment);
        transaction.commit();
    }


}
