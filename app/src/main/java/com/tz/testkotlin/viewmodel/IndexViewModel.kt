package com.tz.testkotlin.viewmodel

import com.tz.k_common.base.BaseViewModel
import com.tz.k_common.network.RespStateData
import com.tz.testkotlin.bean.Articles
import com.tz.testkotlin.repo.IndexRepo

/**
 * created by zm on 2022/11/10

 * @Description:

 */
class IndexViewModel(private val indexRepo: IndexRepo):BaseViewModel() {
    var article = RespStateData<Articles>()
    fun getProject(currentPage: Int) = launch { indexRepo.getProject(currentPage, article) }
}