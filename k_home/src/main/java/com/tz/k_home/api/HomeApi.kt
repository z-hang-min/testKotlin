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
}