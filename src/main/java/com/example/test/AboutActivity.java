package com.example.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.WodeWordWorld.R;

public class AboutActivity extends AppCompatActivity {
private ImageView potrait;
private ImageButton aboutback;
private LinearLayout line;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        potrait = findViewById(R.id.aboutportrait);
        line = findViewById(R.id.abouthead);
        RoundedCorners roundedCorners= new RoundedCorners(6);

        Glide.with(this).load(getDrawable(R.drawable.ic_action_arrow_left)).fitCenter().into(aboutback);
        //Glide.with(this).load(getDrawable(R.drawable.sky)).apply(new RequestOptions().
        //        transform(new CenterCrop(),new RoundedCorners(6))).into(potrait);

        aboutback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
