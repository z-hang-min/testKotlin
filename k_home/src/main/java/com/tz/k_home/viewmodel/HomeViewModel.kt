package com.tz.k_home.viewmodel

import com.tz.k_common.base.BaseViewModel
import com.tz.k_common.network.RespStateData
import com.tz.k_home.bean.Article
import com.tz.k_home.repo.HomeRepo

/**
 * created by zm on 2022/11/10

 * @Description:

 */
class HomeViewModel(private val indexRepo: HomeRepo):BaseViewModel() {
    var article = RespStateData<Article>()
    fun getProject(currentPage: Int) = launch { indexRepo.getProject(currentPage, article) }
}