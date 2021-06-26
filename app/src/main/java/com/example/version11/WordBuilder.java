package com.example.version11;

import org.litepal.crud.LitePalSupport;

public class WordBuilder extends LitePalSupport {
    private String wbenglish;
    private String wbchinese;

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

