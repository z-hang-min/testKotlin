package com.tz.k_home.api

import com.tz.k_common.network.BaseResp
import com.tz.k_home.bean.Article
import retrofit2.http.*

/**
 * Created by stew on 8/21/22.
 * mail: stewforani@gmail.com
 */
interface HomeApi {


    //首页文章列表
    @GET("article/list/{page}/json")
    suspend fun getProject(
        @Path("page") page: Int,
        @Query("page_size") page_size: Int
    ): BaseResp<Article>

    //收藏站内文章
    @POST("lg/collect/{id}/json")
    suspend fun collect(@Path("id") id: Int): BaseResp<String>

    //取消收藏（文章列表）
    @POST("lg/uncollect_originId/{id}/json")
    suspend fun unCollect(@Path("id") id: Int): BaseResp<String>
}