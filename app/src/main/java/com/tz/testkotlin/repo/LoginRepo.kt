package com.tz.testkotlin.repo

import com.tz.k_common.base.BaseRepository
import com.tz.k_common.network.RespStateData
import com.tz.testkotlin.api.UserApi
import com.tz.testkotlin.bean.LoginBean

/**
 * created by zm on 2022/11/8

 * @Description:

 */
class LoginRepo(private val api: UserApi) : BaseRepository() {

    suspend fun login(name: String, pwd: String, data: RespStateData<LoginBean>) =
        dealResp(block = { api.login(name, pwd) },data)
}