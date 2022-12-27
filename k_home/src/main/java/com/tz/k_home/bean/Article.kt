package com.tz.k_home.bean

data class Article(
    val curPage: Int,
    val over: Boolean,
    val datas: List<ArticleDetail>
)