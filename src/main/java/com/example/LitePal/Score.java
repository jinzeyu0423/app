package com.example.LitePal;

import org.litepal.crud.LitePalSupport;

public class Score extends LitePalSupport {
    private String list;
    private int score;
    private String createdtime;

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



    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
