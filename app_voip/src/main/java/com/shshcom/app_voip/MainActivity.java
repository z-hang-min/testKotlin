package com.shshcom.app_voip;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.shshcom.SHVoIPListener;
import com.shshcom.SHVoIPSDK;
import com.shshcom.util.SHAudioManager;
import com.shshcom.voip.SHVoIPAccount;
import com.shshcom.voip.SipCode;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements SHVoIPListener, View.OnClickListener {

    private LinearLayout ll_newcall;
    private TextView tv_status;
    private EditText et_callee;
    private String callee;
    private boolean isSpeaker;
    private boolean isMute;
    private boolean isHold;

    SHVoIPSDK shVoIPSDK;

    // ================================================

//    String server = "请设置 server地址 ip:port";// 服务器地址
//    String username = "请设置 sip 账号";
//    String password = "请填写sip 密码";


    String server = "call-toolbar.95013.com:6080";// 服务器地址
    String username = "957400000037";
    String password = "xl8eny8b";


    // ================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initVoIP();

        login();


    }

    /**
     * 只初始化一次
     */
    private void initVoIP() {
        shVoIPSDK = SHVoIPSDK.getInstance();
        /**
         * 第一步，初始化
         *
         * 设置 log 等级， 默认 5
         *
         * Level 0 Display fatal error only.
         * Level 1 Display error messages and more severe verbosity level only.
         * Level 2 Display Warning messages and more severe verbosity level only.
         * Level 3 Info verbosity (normally used by applications).
         * Level 4 Important VoIP events.
         * Level 5 Detailed VoIP events.
         * Level 6 Very detailed VoIP events.
         */
        shVoIPSDK.init(this);
        shVoIPSDK.setLogLevel(5);

        /**
         * 第二步，设置监听，确保 SHVoIPListener 的实现类 不被销毁，否则将导致无法收到回调
         * 推荐在 Application 中实现 SHVoIPListener
         *
         * 此demo中仅为了简易演示
         */
        shVoIPSDK.setSHVoIPListener(this);



    }

    private void login() {

        /**
         * 第三步，登录，启动VoIP
         * 设置接口访问地址帐号信息，server，username，password
         */

        shVoIPSDK.setAccount(username, password,
                server, "", false);

    }


    private void initView() {
        tv_status = (TextView) findViewById(R.id.tv_status);
        et_callee = (EditText) findViewById(R.id.et_callee);
        ll_newcall = (LinearLayout) findViewById(R.id.ll_newcall);

        tv_status.setText("welcome");

        findViewById(R.id.btn_call).setOnClickListener(this);
        findViewById(R.id.btn_hangup).setOnClickListener(this);
        findViewById(R.id.btn_answer).setOnClickListener(this);

        findViewById(R.id.btn_reject).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
        findViewById(R.id.btn_0).setOnClickListener(this);
        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);
        findViewById(R.id.btn_4).setOnClickListener(this);
        findViewById(R.id.btn_5).setOnClickListener(this);
        findViewById(R.id.btn_6).setOnClickListener(this);
        findViewById(R.id.btn_7).setOnClickListener(this);
        findViewById(R.id.btn_8).setOnClickListener(this);
        findViewById(R.id.btn_9).setOnClickListener(this);
        findViewById(R.id.btn_star).setOnClickListener(this);
        findViewById(R.id.btn_pound).setOnClickListener(this);
        findViewById(R.id.btn_mute).setOnClickListener(this);
        findViewById(R.id.btn_speaker).setOnClickListener(this);
        findViewById(R.id.btn_hold).setOnClickListener(this);

        findViewById(R.id.btn_delete).setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                et_callee.setText("");
                return false;
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO}, 100);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_call:
                callOut();
                break;
            case R.id.btn_hangup:
                shVoIPSDK.hangup();
                ll_newcall.setVisibility(LinearLayout.GONE);
                break;
            case R.id.btn_answer:
                answer();
                break;
            case R.id.btn_reject:
                shVoIPSDK.hangup();
                ll_newcall.setVisibility(LinearLayout.GONE);
                break;
            case R.id.btn_delete:
                callee = et_callee.getText().toString().trim();
                if (!TextUtils.isEmpty(callee)) {
                    callee = callee.substring(0, callee.length() - 1);
                    et_callee.setText(callee);
                }
                break;
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_star:
            case R.id.btn_pound:
                updatePhoneNumber(v);
                break;
            case R.id.btn_speaker:
                isSpeaker = !isSpeaker;
                shVoIPSDK.setSpeaker(isSpeaker);
                break;
            case R.id.btn_mute:
                isMute = !isMute;
                shVoIPSDK.setMute(isMute);
                break;
            case R.id.btn_hold:
                isHold = !isHold;
                shVoIPSDK.setHoldOn(isHold);
                break;
            default:
                break;
        }
    }


    private void updatePhoneNumber(View view) {
        String tag = (String) view.getTag();

        callee = et_callee.getText().toString().trim();
        callee = callee + view.getTag();
        et_callee.setText(callee);
        shVoIPSDK.sendDTMF(tag);
    }

    private void callOut() {
        callee = et_callee.getText().toString().trim();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "请打开麦克风、录音权限", Toast.LENGTH_SHORT).show();
            return;
        }
        shVoIPSDK.callOut(callee);
    }


    private void answer() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "请打开麦克风、录音权限", Toast.LENGTH_SHORT).show();
            return;
        }
        shVoIPSDK.answerCall();
        ll_newcall.setVisibility(LinearLayout.GONE);
    }


    @Override
    public void onRegisterSuccess(SHVoIPAccount account) {
        String status = String.format(Locale.getDefault(),
                "onRegisterSuccess------> account: %s \n info: %s\n",
                account.getAccountInfo().getUri(), account.getState());

        tv_status.setText(status);
    }

    @Override
    public void onRegisterFail(SHVoIPAccount account, SipCode sipCode) {
        String status = String.format(Locale.getDefault(),
                "onRegisterFail------> account: %s\n  info: %s,  code = %s\n",
                account.getAccountInfo().getUri(), account.getState(), sipCode.getValue());

        tv_status.setText(status);
    }

    @Override
    public void onIncomingCall(String number) {
        try {
            // 播放系统来电铃声
            SHAudioManager.getInstance().startRingtone();

            String status = String.format(Locale.getDefault(),
                    "onIncomingCall------>   number: %s\n", number);

            tv_status.setText(status);
            ll_newcall.setVisibility(LinearLayout.VISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onCallRing(String number, SipCode sipCode) {
        try {
            String log = String.format(Locale.getDefault(),
                    "onCallRing------> number: %s,\n status:%s  code = %d\n",
                    number, sipCode.getValue(), sipCode.getCode());

            tv_status.setText(log);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCallOK(String number, SipCode sipCode) {
        try {
            String log = String.format(Locale.getDefault(),
                    "onCallOK------> number: %s,\n status:%s  code = %d\n",
                    number, sipCode.getValue(), sipCode.getCode());

            tv_status.setText(log);
            SHVoIPSDK.getInstance().setMute(false);
            SHVoIPSDK.getInstance().setSpeaker(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCallEnd(String number, SipCode sipCode) {
        String log = String.format(Locale.getDefault(),
                "onCallEnd------> number: %s,\n status:%s  code = %d\n",
                number, sipCode.getValue(), sipCode.getCode());

        tv_status.setText(log);
        ll_newcall.setVisibility(LinearLayout.GONE);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        showSetting();
        return true;
    }

    private void showSetting() {
        LayoutInflater li = LayoutInflater.from(this);
        final View view = li.inflate(R.layout.layout_dialog, null);
        final TextView tv_info = view.findViewById(R.id.tv_info);
        final EditText et_server = view.findViewById(R.id.et_server);
        final EditText et_username = view.findViewById(R.id.et_username);
        final EditText et_password = view.findViewById(R.id.et_password);


        et_server.setText(server);
        et_username.setText(username);
        et_password.setText(password);

        if (shVoIPSDK.getAccount() != null) {
            tv_info.setText(shVoIPSDK.getAccount().getState());
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        builder.setTitle("Account Settings");
        builder.setCancelable(false);
        builder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }
        );

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                server = et_server.getText().toString().trim();
                username = et_username.getText().toString().trim();
                password = et_password.getText().toString().trim();


            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


}
