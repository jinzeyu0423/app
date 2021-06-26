package com.example.WodeWordWorld;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.fragment.WodeFragment;
import com.example.fragment.WordFragment;
import com.example.fragment.WorldFragment;
import com.example.util.MyDatabaseHelper;

import org.litepal.LitePal;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;


public class MainActivity extends FragmentActivity{
private ImageButton img_delete;
private EditText editText;
private MyDatabaseHelper dbhelper;
private long firstTime=0;

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch(keyCode){
            case KeyEvent.KEYCODE_BACK://点击返回键
                long secondTime = System.currentTimeMillis();//以毫秒为单位
                if(secondTime -firstTime>2000){
                    Toast.makeText(this, "再按一次返回退出程序", Toast.LENGTH_SHORT).show();
                    firstTime=secondTime;
                }else{
                    finish();
                    System.exit(0);
                }
                return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    String ff = "/data/data/com.example.version11/databases/mydict.db";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //拷贝数据库到指定文件夹
        dbhelper = new MyDatabaseHelper(this,"mydict.db",null,36);
        dbhelper.getWritableDatabase();
        CopyRawtoDB(R.raw.mydict,ff);
        LitePal.initialize(this);

        replaceFrag(new WodeFragment());
        img_delete = findViewById(R.id.wodeimg_delete);

        Glide.with(this).load(getDrawable(R.drawable.ic_action_cancel)).centerCrop().into(img_delete);
        SQLiteStudioService.instance().start(this);

        BottomNavigationView navigation =  findViewById(R.id.bottomNavigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        editText = findViewById(R.id.wodeedit1);
        editText.setFocusable(false);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });


        int id = getIntent().getIntExtra("id", 0);
        if(id==1){
            replaceFrag(new HistoryFragment());
        }else if(id==2){
            replaceFrag(new WorldFragment());
        }else{
            replaceFrag(new WodeFragment());
        }

        img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });

    }
    //数据库拷贝方法
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
    //切换不同的fragment
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            switch (item.getItemId()) {

                case R.id.world:
                    replaceFrag(new WorldFragment());
                    return true;
                case R.id.wode:
                    replaceFrag(new WodeFragment());
                    return true;
                case R.id.word:
                    replaceFrag(new WordFragment());
                    return true;
                case R.id.review:
                    Intent intent = new Intent(MainActivity.this,ReviewActivity.class);
                    startActivity(intent);
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
