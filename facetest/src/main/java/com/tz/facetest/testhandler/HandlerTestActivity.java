package com.tz.facetest.testhandler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.tz.facetest.R;
import com.tz.facetest.databinding.ActivityHandlerTestBinding;


public class HandlerTestActivity extends AppCompatActivity {
    ActivityHandlerTestBinding mBinding;
    Handler sonHandler;
    //匿名内部类
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    mBinding.tvDesc.setText("ssss---" + msg.what);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_handler_test);
        mBinding.btnHandler.setOnClickListener(v -> {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Looper.prepare();
                    sonHandler = new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            super.handleMessage(msg);
                            Log.d("zhangmin", "子线程");
                            mBinding.tvDesc.setText("子线程");
                        }
                    };
                    Looper.loop();
                }
            }).start();
        });
        mBinding.sunHandler.setOnClickListener(v -> {
            sonHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
//                    runOnUiThread(() -> mBinding.tvDesc.setText("子线程"));
                    mBinding.tvDesc.setText("子线程");
                }
            }, 100L);
//            mHandler.sendEmptyMessage(0);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeMessages(0);
    }
}