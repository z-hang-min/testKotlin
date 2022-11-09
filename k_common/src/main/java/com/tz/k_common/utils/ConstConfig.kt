package com.tz.k_common.utils

/**
 * created by zm on 2022/11/7

 * @Description:

 */
class ConstConfig {
    companion object {
        const  val  ROUTE_PATH_LOGIN:String = "/ui/LoginActivity"
        const  val  ROUTE_PATH_MAIN:String = "/MainActivity"
        //kv
        const val USER_NAME: String = "user_name"
        const val USER_COOKIE: String = "user_cookie"

        //http
        const val HTTP_SUCCESS = 0
        const val HTTP_AUTH_INVALID = -1001
    }
}