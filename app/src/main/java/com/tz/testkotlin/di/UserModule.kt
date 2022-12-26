package com.tz.testkotlin.di

import com.tz.k_common.network.RetrofitManager
import com.tz.testkotlin.api.UserApi
import com.tz.testkotlin.repo.IndexRepo
import com.tz.testkotlin.repo.LoginRepo
import com.tz.testkotlin.viewmodel.IndexViewModel
import com.tz.testkotlin.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by stew on 8/21/22.
 * mail: stewforani@gmail.com
 */
val userModule = module {
    single { RetrofitManager.getService(UserApi::class.java) }
    single { LoginRepo(get()) }
    single { IndexRepo(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { IndexViewModel(get()) }
}