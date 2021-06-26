package com.example.version11;

import org.litepal.crud.LitePalSupport;

public class WordBuilder extends LitePalSupport {
    private String wbenglish;
    private String wbchinese;
    private String createdtime;

    public String getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(String createdtime) {
        this.createdtime = createdtime;
    }

    public String getWbenglish() {
        return wbenglish;
    }

    public void setWbenglish(String wbenglish) {
        this.wbenglish = wbenglish;
    }

    public String getWbchinese() {
        return wbchinese;
    }

    public void setWbchinese(String wbchinese) {
        this.wbchinese = wbchinese;
    }
}

