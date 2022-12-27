package com.tz.k_common.utils

/**
 * created by zm on 2022/11/7

 * @Description:

 */
class ConstConfig {
    companion object {
        const  val  ROUTE_PATH_LOGIN:String = "/ui/LoginActivity"
        const  val  ROUTE_PATH_TAB:String = "/ui/TabActivity"
        const val WEB_TITLE: String = "web_title"
        const val WEB_LINK: String = "web_link"
        const val MIN_CLICK_DELAY_TIME: Int = 1000
        const val PATH_WEB: String = "/kb_common/ui/WebActivity"
        const val PATH_LOGIN: String = "/kb_user/ui/loginActivity"
        //kv
        const val USER_NAME: String = "user_name"
        const val USER_COOKIE: String = "user_cookie"

        //http
        const val HTTP_SUCCESS = 0
        const val HTTP_AUTH_INVALID = -1001
    }
}