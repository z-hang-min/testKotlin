package com.tz.k_project

import com.stew.kb_project.bean.Project
import com.stew.kb_project.bean.ProjectType
import com.tz.k_common.base.BaseRepository
import com.tz.k_common.network.RespStateData
import com.tz.k_project.api.ProjectApi

/**
 * created by zm on 2022/12/28

 * @Description:

 */
class ProjectRepo(private val api: ProjectApi) : BaseRepository() {
    suspend fun getProTypeList(data: RespStateData<List<ProjectType>>) =
        dealResp({ api.getProType() }, data)

    suspend fun getProList(
        currentPage: Int,
        cid: Int,
        data: RespStateData<Project>
    ) = dealResp({ api.getProList(currentPage, 10, cid) }, data)

    suspend fun collect(id: Int, data: RespStateData<String>) = dealResp(
        { api.collect(id) }, data
    )

    suspend fun unCollect(id: Int, data: RespStateData<String>) = dealResp(
        { api.unCollect(id) }, data
    )
}