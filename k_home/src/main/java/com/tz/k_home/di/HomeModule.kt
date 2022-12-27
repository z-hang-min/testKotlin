package com.tz.k_home.di

import com.tz.k_common.network.RetrofitManager
import com.tz.k_home.api.HomeApi
import com.tz.k_home.repo.HomeRepo
import com.tz.k_home.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val homeModule = module {
    single { RetrofitManager.getService(HomeApi::class.java) }
    single { HomeRepo(get()) }
    viewModel { HomeViewModel(get()) }
}