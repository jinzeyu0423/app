package com.example.version11;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

public class AboutActivity extends AppCompatActivity {
private ImageView potrait;
private ImageButton aboutback;
private LinearLayout line;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        potrait = findViewById(R.id.aboutportrait);
        aboutback = findViewById(R.id.aboutback);
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
