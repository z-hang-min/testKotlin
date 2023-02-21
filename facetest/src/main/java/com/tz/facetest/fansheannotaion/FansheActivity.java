package com.tz.facetest.fansheannotaion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.tz.facetest.R;

public class FansheActivity extends AppCompatActivity {
    @InjectExtra("myname")
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fanshe);
        try {
            InjectUtils.injectVExtra(this);
            String ddd=getIntent().getStringExtra(id);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}