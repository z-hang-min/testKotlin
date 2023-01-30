package com.tz.k_project.di

import com.tz.k_common.base.BaseViewModel
import com.tz.k_common.network.RetrofitManager
import com.tz.k_project.ProjectRepo
import com.tz.k_project.api.ProjectApi
import com.tz.k_project.viewmodel.ProjectViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * created by zm on 2022/12/28

 * @Description:

 */
val projectModule = module {
    single { RetrofitManager.getService(ProjectApi::class.java) }
    single { ProjectRepo(get()) }
    viewModel { ProjectViewModel(get()) }
}