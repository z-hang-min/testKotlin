package com.tz.testkotlin.viewmodel

import com.tz.k_common.base.BaseViewModel
import com.tz.k_common.network.RespStateData
import com.tz.testkotlin.bean.LoginBean
import com.tz.testkotlin.repo.LoginRepo

/**
 * created by zm on 2022/11/8

 * @Description:

 */
class LoginViewModel(private val repo: LoginRepo) : BaseViewModel() {
    var loginData = RespStateData<LoginBean>()
    fun login(name: String, pwd: String) {
        launch(block = {
            repo.login(name, pwd, loginData)

        })

    }
}