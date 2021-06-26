package com.example.adapter;

public class RScore {
    private String list;
    private String score;
    private String test;

    public String getList() {
        return list;
    }

    public String getScore() {
        return score;
    }

    public String getTest() {
        return test;
    }

    public RScore(String list, String score, String test) {

        this.list = list;
        this.score = score;
        this.test = test;
    }
}
