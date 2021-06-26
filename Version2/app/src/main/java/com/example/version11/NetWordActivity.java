package com.example.version11;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.util.HttpUtil;
import com.example.util.King;

import com.google.gson.Gson;

import org.litepal.LitePal;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NetWordActivity extends AppCompatActivity {
private TextView bigword;
private ImageButton wordBuilder;
private ImageButton imgback;
private ImageButton imen;
private ImageButton imam;
private ImageButton red;
//private EditText editText;
private TextView tv_am;
private TextView tv_en;
private TextView mean;
private TextView sentence;
static String am ;
static String Urr;
static String Urrr;
static String Ur;
static String U;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_netword);
        bigword = (TextView)findViewById(R.id.bigWord);
        wordBuilder = (ImageButton)findViewById(R.id.wordBuilder);
        //editText = (EditText)findViewById(R.id.edit2);
        imgback = (ImageButton)findViewById(R.id.img_back);
        imen =(ImageButton)findViewById(R.id.imen);
        imam =(ImageButton)findViewById(R.id.imam);
        tv_am = (TextView)findViewById(R.id.tvam);
        tv_en = (TextView)findViewById(R.id.tven);
        mean = (TextView)findViewById(R.id.mean);
        sentence = (TextView) findViewById(R.id.sentence);
        red = (ImageButton)findViewById(R.id.red);

        Intent intent = getIntent();
        final String renglish = intent.getStringExtra("renglish");
        final String rchinese = intent.getStringExtra("rchinese");
        final String activity = intent.getStringExtra("activity");

        //final String exampleText = intent.getStringExtra("exampleText");
        final String url1 = "http://dict-co.iciba.com/api/dictionary.php?w=";
        final String url2 = "&type=json&key=09E6F32147F3C92C67BFF5FFC409CD13";
        final String url3 ="http://dict-co.iciba.com/api/dictionary.php?w=";
        final String url4 ="&key=09E6F32147F3C92C67BFF5FFC409CD13";

        final String wrongword = intent.getStringExtra("wrongword");
        Urrr = url3+renglish+url4;
        Urr = url1+renglish+url2;
        Ur = url1+wrongword+url2;
        U = url3+wrongword+url4;


        if(activity.equals("Adapter")){ //通过activity判断数据来源

            final List<Word> listw = LitePal.where("english=?",wrongword).find(Word.class);
            final String wrongchinese = listw.get(0).getChinese();
            bigword.setText(wrongword);
            sendRequest(Ur);
            sendRequest(U);
            List<String> list1 = new ArrayList<>();
                    List<WordBuilder> list = LitePal.findAll(WordBuilder.class);
                    for (WordBuilder builder :list)
                        {
                            list1.add(builder.getWbenglish());
                        }
                        if(!list1.contains(wrongword)){
                        wordBuilder.setVisibility(View.VISIBLE);
                        wordBuilder.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                WordBuilder wordBuilder1 = new WordBuilder();
                                wordBuilder1.setWbenglish(wrongword);
                                wordBuilder1.setWbchinese(wrongchinese);
                                wordBuilder1.save();
                                Toast.makeText(NetWordActivity.this,wrongword+"已加入生词本",Toast.LENGTH_SHORT).show();
                                wordBuilder.setVisibility(View.GONE);
                            }
                        });
                        }

        }else{
            bigword.setText(renglish);
            sendRequest(Urr);
            sendRequest(Urrr);
            List<String> list2 = new ArrayList<>();
            List<WordBuilder> list = LitePal.findAll(WordBuilder.class);
            for (WordBuilder builder :list)
                {
                    list2.add(builder.getWbenglish());
                }
                if(!list2.contains(renglish)){
                wordBuilder.setVisibility(View.VISIBLE);
                wordBuilder.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            WordBuilder wordBuilder2 = new WordBuilder();
                            wordBuilder2.setWbenglish(renglish);
                            wordBuilder2.setWbchinese(rchinese);
                            wordBuilder2.save();
                            Toast.makeText(NetWordActivity.this,renglish+"已加入生词本",Toast.LENGTH_SHORT).show();
                            wordBuilder.setVisibility(View.GONE);

                        }
                    });

                }
        }


        Glide.with(this).load(getDrawable(R.drawable.ic_action_list_2)).centerCrop().into(wordBuilder);
        Glide.with(this).load(getDrawable(R.drawable.ic_action_arrow_left)).centerCrop().into(imgback);
        Glide.with(this).load(getDrawable(R.drawable.imagebutton_sentence_play)).centerCrop().into(imen);
        Glide.with(this).load(getDrawable(R.drawable.imagebutton_sentence_play)).centerCrop().into(imam);
        Glide.with(this).load(getDrawable(R.drawable.ic_action_search)).centerCrop().into(red);


        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });





        imen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            SharedPreferences pref = getSharedPreferences("KingC2E",MODE_PRIVATE);
                MediaPlayer mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(pref.getString("ph_en_mp3",""));
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaPlayer.start();
            }
        });

        imam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences pref = getSharedPreferences("KingC2E",MODE_PRIVATE);
                MediaPlayer mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(pref.getString("ph_am_mp3",""));
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaPlayer.start();
            }



        });

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(NetWordActivity.this,WodeActivity1.class);
                //intent1.putExtra("iid",1);
                startActivity(intent1);

            }
        });




    }
    private void setdata(){
             SharedPreferences pref = getSharedPreferences("KingE2C",MODE_PRIVATE);
             sentence.setText(pref.getString("exampleText",""));
             //Log.d("NetWordActivity", "parseE2CXML: "+am);
             SharedPreferences pref1 = getSharedPreferences("KingC2E",MODE_PRIVATE);
             String am1 ="美["+pref1.getString("ph_am","")+"]";
             String en1 ="英["+pref1.getString("ph_en","")+"]";
             tv_am.setText(am1);
             tv_en.setText(en1);
             mean.setText(pref1.getString("mean",""));

    }

    private void sendRequest(final String urll) {
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
                         showResponsejson(response.toString());
                    }else{
                        showResponsexml(response.toString());
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



    public void showResponsejson(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                parseJson(response);
                setdata();
            }
        });
    }
    private void showResponsexml(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                parseE2CXML(response);
                setdata();
            }
        });
    }


    public void parseJson(String jsonData){

        try {
            Gson gson = new Gson();
            King translate = gson.fromJson(jsonData, King.class);

            String ph_am = "";
            String ph_en = "";
            String ph_am_mp3 = "";
            String ph_en_mp3 = "";
            String meanType = "";
            String mean="";
            String he="";
            List list = new ArrayList();
            for (King.SymbolsBean voiceMsg : translate.getSymbols()) {
                ph_am += voiceMsg.getPh_am();
                ph_en += voiceMsg.getPh_en();
                ph_am_mp3 += voiceMsg.getPh_am_mp3();
                ph_en_mp3 += voiceMsg.getPh_en_mp3();

                for (King.SymbolsBean.PartsBean meanMsg : voiceMsg.getParts()) {
                    meanType = meanMsg.getPart();
                    list = meanMsg.getMeans();
                    he = list.toString();
                    mean +=meanType+"   "+he.substring(1,he.length()-1)+";"+"\n\n";
                }
            }


            //创建SharedPreferences.Editor对象，指定文件名为
            SharedPreferences.Editor editor = MyApplication.getContext()
                    .getSharedPreferences("KingC2E",MODE_PRIVATE).edit();
            editor.clear();
            editor.putString("ph_am",ph_am);
            editor.putString("ph_en",ph_en);
            editor.putString("ph_am_mp3",ph_am_mp3);
            editor.putString("ph_en_mp3",ph_en_mp3);
            editor.putString("mean",mean);
            editor.apply();

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("NetWordActivity", "解析过程中出错！！！");
        }


    }
    public static void parseE2CXML(String result) {

        try {

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(result));
            int eventType = xmlPullParser.getEventType();

            String queryText = "";      //查询文本
            String voiceText = "";      //发音信息
            String voiceUrlText = "";   //发音地址信息
            String meanText = "";       //基本释义信息
            String exampleText = "";    //例句信息

            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = xmlPullParser.getName();
                switch (eventType) {
                    //开始解析
                    case XmlPullParser.START_TAG: {
                        switch (nodeName) {
                            case "key":
                                queryText += xmlPullParser.nextText();
                                break;
                            case "ps":
                                voiceText += xmlPullParser.nextText() + "|";
                                break;
                            case "pron":
                                voiceUrlText += xmlPullParser.nextText() + "|";
                                break;
                            case "pos":
                                meanText += xmlPullParser.nextText() + "  ";
                                break;
                            case "acceptation":
                                meanText += xmlPullParser.nextText();
                                break;
                            case "orig":
                                exampleText += xmlPullParser.nextText()+"\n";

                                break;
                            case "trans":
                                exampleText += xmlPullParser.nextText()+"\n\n";
                                break;
                            default:
                                break;
                        }
                    }
                    default:
                        break;
                }
                eventType = xmlPullParser.next();
            }

            String[] voiceArray = voiceText.split("\\|");
            String[] voiceUrlArray = voiceUrlText.split("\\|");
            meanText = meanText.substring(0,meanText.length()-1);
            //创建SharedPreferences.Editor对象，指定文件名为
            SharedPreferences.Editor editor = MyApplication.getContext().getSharedPreferences("KingE2C",MODE_PRIVATE).edit();

            editor.clear();

            editor.putString("queryText",queryText);
            editor.putString("voiceEnText","["+voiceArray[0]+"]");
            editor.putString("voiceEnUrlText",voiceUrlArray[0]);
            editor.putString("voiceAmText","["+voiceArray[1]+"]");
            editor.putString("voiceAmUrlText",voiceUrlArray[1]);
            editor.putString("meanText",meanText);
            editor.putString("exampleText",exampleText);

            editor.apply();
            SharedPreferences pref =MyApplication.getContext().getSharedPreferences("KingE2C",MODE_PRIVATE);
            am = pref.getString("exampleText","");
            //Log.d("TAG", "parseE2CXML: "+am);


        } catch (Exception e) {
            e.printStackTrace();
            Log.d("NetWordActivity", "解析过程中出错！！！");
        }

    }




 }



