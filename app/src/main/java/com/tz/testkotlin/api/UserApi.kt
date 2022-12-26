package com.tz.testkotlin.api

import com.tz.k_common.network.BaseResp
import com.tz.testkotlin.bean.LoginBean
import com.tz.testkotlin.bean.Articles
import retrofit2.http.*

/**
 * Created by stew on 8/21/22.
 * mail: stewforani@gmail.com
 */
interface UserApi {
    //登录
    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): BaseResp<LoginBean>


    //首页文章列表
    @GET("article/list/{page}/json")
    suspend fun getProject(
        @Path("page") page: Int,
        @Query("page_size") page_size: Int
    ): BaseResp<Articles>
}