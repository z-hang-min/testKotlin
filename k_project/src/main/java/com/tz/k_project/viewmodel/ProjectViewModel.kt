package com.tz.k_project.viewmodel

import com.stew.kb_project.bean.Project
import com.stew.kb_project.bean.ProjectType
import com.tz.k_common.base.BaseViewModel
import com.tz.k_common.network.RespStateData
import com.tz.k_project.ProjectRepo

/**
 * created by zm on 2022/12/28

 * @Description:

 */
class ProjectViewModel(private var repo: ProjectRepo) : BaseViewModel() {
    val proTypeList = RespStateData<List<ProjectType>>()
    var proList = RespStateData<Project>()
    var collectData = RespStateData<String>()

    fun getProTypeList() = launch(
        block = { repo.getProTypeList(proTypeList) }
    )

    fun getProList(currentPage: Int, cid: Int) = launch(
        block = { repo.getProList(currentPage, cid, proList) }
    )

    fun collect(id: Int) {
        launch(
            block = { repo.collect(id, collectData) }
        )
    }

    fun unCollect(id: Int) {
        launch(
            block = { repo.unCollect(id, collectData) }
        )
    }
}