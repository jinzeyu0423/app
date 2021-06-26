package com.example.version11;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

class LocalReceiver extends BroadcastReceiver {
    static String amm;
private SharedPreferences sharedPreferences;
    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences pref = MyApplication.getContext().getSharedPreferences("KingE2C",MODE_PRIVATE);
        amm = pref.getString("exampleText","");
    }
}
