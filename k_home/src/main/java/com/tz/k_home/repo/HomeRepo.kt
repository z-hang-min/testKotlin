package com.tz.k_home.repo

import com.tz.k_common.base.BaseRepository
import com.tz.k_common.network.RespStateData
import com.tz.k_home.api.HomeApi
import com.tz.k_home.bean.Article

/**
 * created by zm on 2022/11/10

 * @Description:

 */
class HomeRepo(var api: HomeApi) : BaseRepository() {
    suspend fun getProject(currentPage: Int, data: RespStateData<Article>) = dealResp(
        { api.getProject(currentPage, 10) }, data
    )
}