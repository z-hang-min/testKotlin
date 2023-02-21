package com.tz.javalibrary.receiver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.activity_main);
        TestReceiver receiver = new TestReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.zm.test");

        registerReceiver(receiver, intentFilter);
        Intent intent = new Intent();
        intent.setAction("com.zm.test");
        sendBroadcast(intent);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile("", options);
        int w = options.outWidth;
        int h = options.outHeight;
        options.inSampleSize = 2;//采样率，大小变成原来的1/2，内存缩小1/4
        options.inPreferredConfig = Bitmap.Config.RGB_565;//
        //可变的bitmap
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            options.inMutable = true;
        }
        //设置成能复用，可实现第二张图片复用第一张图片的内存
//        options.inBitmap = ;
        options.inJustDecodeBounds = false;
//        Bitmap bitmap=BitmapFactory.decodeResource(,,options);
        HandlerThread handlerThread = new HandlerThread("");
        handlerThread.start();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.CUPCAKE) {
            Handler.Callback callback = new Handler.Callback() {
                @Override
                public boolean handleMessage(@NonNull Message msg) {
                    return false;
                }
            };
        }

        Handler handler=new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
            }
        };
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        },1000);

  }

}