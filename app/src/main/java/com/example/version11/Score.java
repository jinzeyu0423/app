package com.example.version11;

import org.litepal.crud.LitePalSupport;

public class Score extends LitePalSupport {
    private String stime;
    private int score;

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
