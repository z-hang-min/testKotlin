package com.tz.testkotlin.ui

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.alibaba.android.arouter.facade.annotation.Route
import com.tz.k_common.base.BaseActivity
import com.tz.k_common.base.BaseStateObserver
import com.tz.k_common.utils.ToastUtil
import com.tz.testkotlin.ConstConfig
import com.tz.testkotlin.R
import com.tz.testkotlin.bean.LoginBean
import com.tz.testkotlin.databinding.ActivityLoginBinding
import com.tz.testkotlin.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

@Route(path = ConstConfig.ROUTE_PATH_LOGIN)
class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    private val loginViewModel: LoginViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun getLayoutID(): Int {
        return R.layout.activity_login

    }

    override fun init() {
        loginViewModel.loginData.observe(this, object : BaseStateObserver<LoginBean>(null){
            override fun getRespDataSuccess(it: LoginBean) {
                ToastUtil.showMsg("登陆成功！")
                finish()
            }



        })

        mBind.btnGo.setOnClickListener {
            if (mBind.etAccount.text.toString().isNotEmpty() && mBind.etPwd.text.toString().isNotEmpty()) {
                loginViewModel.login(mBind.etAccount.text.toString(), mBind.etPwd.text.toString())
            } else {
                ToastUtil.showMsg("输入有误！")
            }
        }
    }
}