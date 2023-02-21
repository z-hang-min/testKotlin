package com.tz.facetest.fansheannotaion;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.tz.facetest.R;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AnnotationTestActivity extends AppCompatActivity {
    @InjectView(value = R.id.tv_annotation, txt = R.string.app_name)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation_test);
        try {
            InjectUtils.injectView(this);
            tv.setText("gggg");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(this, FansheActivity.class);
        intent.putExtra("name", "hhh");
        startActivity(intent);
        Object o = Proxy.newProxyInstance(AnnotationTestActivity.class.getClassLoader(),
                new Class[]{}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        return method.invoke(null, args);
                    }
                });
    }
    void testThreadPool(){
        ExecutorService executor= Executors.newFixedThreadPool(3) ;

    }
}