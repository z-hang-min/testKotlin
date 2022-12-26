package com.tz.testkotlin.repo

import com.tz.k_common.base.BaseRepository
import com.tz.k_common.network.RespStateData
import com.tz.testkotlin.api.UserApi
import com.tz.testkotlin.bean.Articles

/**
 * created by zm on 2022/11/10

 * @Description:

 */
class IndexRepo(var api: UserApi) : BaseRepository() {
    suspend fun getProject(currentPage: Int, data: RespStateData<Articles>) = dealResp(
        { api.getProject(currentPage, 10) }, data
    )
}