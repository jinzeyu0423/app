package com.example.LitePal;

import org.litepal.crud.LitePalSupport;

public class WordsBuilder extends LitePalSupport {
    private String wbenglish;
    private String wbchinese;
    private String createdtime;
    private String list;

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

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
