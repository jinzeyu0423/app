package com.example.util;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.version11.MyApplication;
import com.example.version11.TagsBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
//import com.my.wordbar.activity.MyApplication;
//import com.my.wordbar.gson.JinshanChineseToEnglishPartBean;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class KingParse {

    private final static String TAG = "金山解析工具";

    /**
     * 判断一段字符串是否是纯英文
     * */
    public static boolean isEnglish(String content){

        if(content == null){                    //获取内容为空则返回false
            return false;
        }

        content = content.replace(" ","");      //去掉内容中的空格

        return content.matches("^[a-zA-Z]*");   //判断是否是全英文，是则返回true，反之返回false

    }

    /**
     * 英译汉时使用。查词
     * 使用pull方式解析金山词霸返回的XML数据。
     * */
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
                                        exampleText += xmlPullParser.nextText();
                                        exampleText = exampleText.substring(0,exampleText.length()-1);
                                        break;
                                    case "trans":
                                        exampleText += xmlPullParser.nextText();
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
            exampleText = exampleText.substring(1,exampleText.length());

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

        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "解析过程中出错！！！");
        }

    }

    /**
     * 汉译英时使用。查词
     * 使用Gson解析金山词霸返回的json数据。
     *
     * ====这里只是解析了查询的文本、拼音、发音地址、基本释义。例句部分由XMl数据解析完成。====
     *
     * */
    public static void paresE2Cjson(String result) {

        try {
            Gson gson = new Gson();
            King translate = gson.fromJson(result, King.class);

            String ph_am = "";
            String ph_en = "";
            String ph_am_mp3 = "";
            String ph_en_mp3 = "";
            String meanType = "";
            String mean = "";
            for (King.SymbolsBean voiceMsg : translate.getSymbols()){
                ph_am += voiceMsg.getPh_am();
                ph_en += voiceMsg.getPh_en();
                ph_am_mp3 += voiceMsg.getPh_am_mp3();
                ph_en_mp3 += voiceMsg.getPh_en_mp3();
                for(King.SymbolsBean.PartsBean meanMsg : voiceMsg.getParts()) {
                    meanType += meanMsg.getPart();
                    mean += meanMsg.getMeans();
//                    for (String mean1: meanMsg.getMeans()){
//
//                        mean += mean1+";";
//                    }


                    //List<King.SymbolsBean.PartsBean> mean = King.SymbolsBean.PartsBean
                }
            }





//            meanType = meanType.substring(0,meanType.length()-1);

//            voiceText = voiceText.substring(0,voiceText.length()-3);
//
//            if(voiceText.equals("")){
//                voiceText = "空";
//            }
//
////            if(voiceText.trim().equals(",")){
////                voiceText = "空";
////            }
//            if(voiceUrlText.equals("")){
//                voiceUrlText = "空";
//            }
//            if(meanType.charAt(0) == ':'){
//                meanType = meanType.substring(2,meanType.length());
//            }

            //创建SharedPreferences.Editor对象，指定文件名为
            SharedPreferences.Editor editor = MyApplication.getContext().getSharedPreferences("KingC2E",MODE_PRIVATE).edit();

            editor.clear();

            editor.putString("ph_am",ph_am);
            editor.putString("ph_en",ph_en);
            editor.putString("ph_am_mp3",ph_am_mp3);
            editor.putString("ph_en_mp3",ph_en_mp3);
            editor.putString("meanType",meanType);
            editor.putString("mean",mean);
            editor.apply();

        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "解析过程中出错！！！");
        }

    }

    /**
     * 汉译英时使用，查词
     * 使用Pull方式解析金山词霸返回的XML数据。
     *
     * ====这里只解析了例句，其他相关释义由json数据解析完成====
     *
     * */
    public static String parseC2EXML(String result) {

        String exampleText = "";    //例句信息
        try {

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(result));
            int eventType = xmlPullParser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = xmlPullParser.getName();
                switch (eventType) {
                    //开始解析
                    case XmlPullParser.START_TAG: {
                        switch (nodeName) {
                            case "orig":
                                exampleText += xmlPullParser.nextText();
                                exampleText = exampleText.substring(0,exampleText.length()-1);
                                break;
                            case "trans":
                                exampleText += xmlPullParser.nextText();
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
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "解析过程中出错！！！");
        }

        exampleText = exampleText.substring(1,exampleText.length());

        return exampleText;

    }

}