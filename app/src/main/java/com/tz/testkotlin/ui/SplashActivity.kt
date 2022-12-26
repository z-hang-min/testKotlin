package com.tz.testkotlin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter
import com.tz.k_common.base.BaseActivity
import com.tz.k_common.utils.ConstConfig
import com.tz.k_common.utils.KVUtil
import com.tz.testkotlin.R
import com.tz.testkotlin.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity<ActivitySplashBinding>() {


    override fun getLayoutID(): Int {
        return R.layout.activity_splash
    }

    override fun init() {
        mBind.btnStart.setOnClickListener {
            KVUtil.getString(ConstConfig.USER_NAME, "")?.let { it1 -> Log.d("zm", it1) }
            if (KVUtil.getString(ConstConfig.USER_NAME, "") == null)
                ARouter.getInstance().build(ConstConfig.ROUTE_PATH_LOGIN).navigation()
            else
                ARouter.getInstance().build(ConstConfig.ROUTE_PATH_TAB).navigation()
        }
    }
}