package com.tz.personaidlclient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.tz.personaidlclient.databinding.ActivityMainBinding;
import com.tz.personaidlserver.IPersonManager;
import com.tz.personaidlserver.Person;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding mBinding;
    Intent intent;
    IPersonManager mIPersonManager;
    ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.e("zm","onServiceConnected");
            mIPersonManager = IPersonManager.Stub.asInterface(iBinder);

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mIPersonManager = null;

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        intent = new Intent();
        intent.setComponent(new ComponentName("com.tz.personaidlserver", "com.tz.personaidlserver.PersonAIDLService"));

        mBinding.btnBind.setOnClickListener(this);
        mBinding.btnUnbind.setOnClickListener(this);
        mBinding.btnAdd.setOnClickListener(this);
        mBinding.btnGet.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_bind:
                bindService(intent, mConnection, BIND_AUTO_CREATE);

                break;
            case R.id.btn_unbind://in 只允许客户端传递数据到服务端；
                try {
                    Person person=new Person(45, "leo");
                    String ddd=  mIPersonManager.addPerson(person);
                    Log.e("client-addPerson服务端信息",ddd);
                    Log.e("client-addPerson原理信息",person.toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_add://out 只允许服务端把数据传递到客户端，服务端传递数据到客户端不是通过返回值来传递的，传递数据的载体是对象本身；
                try {
                    Person person=new Person(46, "leo2");
                   String ddd=mIPersonManager.outPerson(person);
                    Log.e("client-outPerson服务端信息",ddd);
                    Log.e("client-outPerson原理信息",person.toString());

                } catch (RemoteException e) {
                    e.printStackTrace();
                }


                break;
            case R.id.btn_get://inout 双向通信
                try {
                    Person person=new Person(47, "leo3");
                    String ddd=mIPersonManager.inOutPerson(person);
                    Log.e("client-inOutPerson服务端信息",ddd);
                    Log.e("client-inOutPerson原理信息",person.toString());

                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}