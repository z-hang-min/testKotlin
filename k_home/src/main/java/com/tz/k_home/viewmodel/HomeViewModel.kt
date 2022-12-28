package com.tz.k_home.viewmodel

import com.tz.k_common.base.BaseViewModel
import com.tz.k_common.network.RespStateData
import com.tz.k_home.bean.Article
import com.tz.k_home.bean.Banner
import com.tz.k_home.repo.HomeRepo

/**
 * created by zm on 2022/11/10

 * @Description:

 */
class HomeViewModel(private val repo: HomeRepo):BaseViewModel() {
    var article = RespStateData<Article>()
    var collectData = RespStateData<String>()
    var bannerList = RespStateData<List<Banner>>()
    fun getBanner() = launch { repo.getBanner(bannerList) }
    fun getProject(currentPage: Int) = launch { repo.getProject(currentPage, article) }
    fun collect(id: Int) = launch { repo.collect(id, collectData) }
    fun unCollect(id: Int) = launch { repo.unCollect(id, collectData) }
}