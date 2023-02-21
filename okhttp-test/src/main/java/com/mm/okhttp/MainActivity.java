package com.mm.okhttp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.mm.okhttp.databinding.ActivityMainBinding;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
@MyAnn(value = "ssss")
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    void initOKHttp() throws IOException {
//        OkHttpClient client=new OkHttpClient();
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        Request request = new Request.Builder()
//                .addHeader()
                .url("").

                build();
        Call call = client.newCall(request);
        //同步请求
        Response response = call.execute();
        //异步请求
//        Response response=call.enqueue();
    }

    void testThreadPool() {
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(1);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, arrayBlockingQueue);
        executor.execute(() -> {
            System.out.println("线程1");
            while (true) {

            }

        });
        executor.execute(() -> {
            System.out.println("线程12");

        });
    }

    void testRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("")

                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IshareListService ishareListService = retrofit.create(IshareListService.class);
        retrofit2.Call<String> sss = ishareListService.checkLogin("", "");
        sss.enqueue(new Callback<String>() {
            @Override
            public void onResponse(retrofit2.Call<String> call, retrofit2.Response<String> response) {

            }

            @Override
            public void onFailure(retrofit2.Call<String> call, Throwable t) {

            }

            ;
        });
    }
}