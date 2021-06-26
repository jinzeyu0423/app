package com.example.LitePal;

import org.litepal.crud.LitePalSupport;

public class HsWord extends LitePalSupport {
    private String hsenglish;
    private String hschinese;

    public String getHsenglish() {
        return hsenglish;
    }

    public void setHsenglish(String hsenglish) {
        this.hsenglish = hsenglish;
    }

    public String getHschinese() {
        return hschinese;
    }

    public void setHschinese(String hschinese) {
        this.hschinese = hschinese;
    }
}
