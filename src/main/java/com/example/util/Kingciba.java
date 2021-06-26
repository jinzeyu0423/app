package com.example.util;

import java.util.List;

//{
//        "sid": "3345",
//        "tts": "http://news.iciba.com/admin/tts/2019-04-01-day.mp3",
//        "content": "The people who get on in this world are the people who get up and look for circumstances they want, and if they cannot find them, make them.",
//        "note": "在这个世界上取得成就的人，都努力去寻找他们想要的机会，如果找不到机会，他们便自己创造机会。",
//        "love": "690",
//        "translation": "小编的话：机会或许会在意想不到的时候到来，对于我们来说，不仅要有创造机会的能力，还要有等待机会的勇气，就像在漫漫长夜等待黎明，太阳总是会在最黑暗的时刻之后升起。",
//        "picture": "http://cdn.iciba.com/news/word/20190401.jpg",
//        "picture2": "http://cdn.iciba.com/news/word/big_20190401b.jpg",
//        "caption": "词霸每日一句",
//        "dateline": "2019-04-01",
//        "s_pv": "0",
//        "sp_pv": "0",
//        "tags": [
//        {
//        "id": null,
//        "name": null
//        }
//        ],
//        "fenxiang_img": "http://cdn.iciba.com/web/news/longweibo/imag/2019-04-01.jpg"
//        }
public class Kingciba {
    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getTts() {
        return tts;
    }

    public void setTts(String tts) {
        this.tts = tts;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getLove() {
        return love;
    }

    public void setLove(String love) {
        this.love = love;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPicture2() {
        return picture2;
    }

    public void setPicture2(String picture2) {
        this.picture2 = picture2;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }

    public String getS_pv() {
        return s_pv;
    }

    public void setS_pv(String s_pv) {
        this.s_pv = s_pv;
    }

    public String getSp_pv() {
        return sp_pv;
    }

    public void setSp_pv(String sp_pv) {
        this.sp_pv = sp_pv;
    }

    public List<TagsBean> getTags() {
        return tags;
    }

    public void setTags(List<TagsBean> tags) {
        this.tags = tags;
    }
    public String getFenxiang_img() {
        return fenxiang_img;
    }

    public void setFenxiang_img(String fenxiang_img) {
        this.fenxiang_img = fenxiang_img;
    }

    private String sid;
    private String tts;
    private String content;
    private String note;
    private String love;
    private String translation;
    private String picture;
    private String picture2;
    private String caption;
    private String dateline;
    private String s_pv;
    private String sp_pv;
    private List<TagsBean> tags;
    private String fenxiang_img;

}
