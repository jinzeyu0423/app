package com.example.LitePal;

import org.litepal.crud.LitePalSupport;

public class WordBuilder extends LitePalSupport {
    private String wbenglish;
    private String wbchinese;
    private String createdtime;
    private int day1;
    private int day2;
    private int day4;
    private int day7;

    public int getDay1() {
        return day1;
    }

    public void setDay1(int day1) {
        this.day1 = day1;
    }

    public int getDay2() {
        return day2;
    }

    public void setDay2(int day2) {
        this.day2 = day2;
    }

    public int getDay4() {
        return day4;
    }

    public void setDay4(int day4) {
        this.day4 = day4;
    }

    public int getDay7() {
        return day7;
    }

    public void setDay7(int day7) {
        this.day7 = day7;
    }

    public int getDay15() {
        return day15;
    }

    public void setDay15(int day15) {
        this.day15 = day15;
    }

    private int day15;

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

