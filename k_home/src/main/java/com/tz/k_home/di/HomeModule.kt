package com.tz.k_home.di

import com.tz.k_common.network.RetrofitManager
import com.tz.k_home.api.HomeApi
import com.tz.k_home.repo.HomeRepo
import com.tz.k_home.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Koin 是为 Kotlin 开发者提供的一个实用型轻量级依赖注入框架
 * di==Dependency Injection 依赖注入
 * get()： 解析 Koin 模块中的实例，调用 get() 函数解析所请求组件需要的实例，这个 get() 函数通常用于构造函数中，注入构造函数值
factory：声明这是一个工厂组件，每次请求都为您提供一个新实例
single：采用单例设计模式
name：用于命名定义，当您希望具有不同类型的同一个类的多个实例时，需要使用它
typealias 是用来为已经存在的类型重新定义名字的
 */
val homeModule = module {
    single { RetrofitManager.getService(HomeApi::class.java) }
    single { HomeRepo(get()) }
    viewModel { HomeViewModel(get()) }
}