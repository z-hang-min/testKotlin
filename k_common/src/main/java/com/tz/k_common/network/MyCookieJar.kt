package com.tz.k_common.network

import android.util.Log
import com.google.gson.Gson
import com.tz.k_common.utils.ConstConfig
import com.tz.k_common.utils.KVUtil
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

/**
 * Created by stew on 8/21/22.
 * mail: stewforani@gmail.com
 */
class MyCookieJar : CookieJar {

    private val gson = Gson()

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        val s = KVUtil.getString(ConstConfig.USER_COOKIE)
        if (s != null) {
            val l = gson.fromJson(s, CookieBean::class.java).list
            Log.d("OkHttp", "loadForRequest: $l")
            return l
        }
        return arrayListOf()
    }

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        Log.d("OkHttp", "saveFromResponse:1")

        if (KVUtil.getString(ConstConfig.USER_COOKIE) != null) {
            Log.d("OkHttp", "saveFromResponse:2")
            return
        }

        for (item in cookies) {
            if (item.toString().contains("loginUserName")) {
                Log.d("OkHttp", "saveFromResponse:3")
                KVUtil.put(ConstConfig.USER_COOKIE, gson.toJson(CookieBean(list = cookies)))
                break
            }
        }


    }

}