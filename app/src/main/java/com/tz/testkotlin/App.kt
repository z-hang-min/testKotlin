package com.tz.testkotlin

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.tencent.mmkv.MMKV
import com.tz.k_common.utils.ToastUtil
import com.tz.k_home.di.homeModule
import com.tz.k_project.di.projectModule
import com.tz.testkotlin.di.userModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * created by zm on 2022/11/7

 * @Description:

 */
class App : Application() {
    private val modules = mutableListOf(userModule,homeModule, projectModule)
    override fun onCreate() {
        super.onCreate()
        initARouter()
        initKoin()
        initMMKV()
        ToastUtil.init(this)
    }

    private fun initARouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()    // 打印日志
            ARouter.openDebug()  // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this) // 尽可能早，推荐在Application中初始化

    }
    private fun initMMKV() {
        MMKV.initialize(this)
    }
    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(modules)
        }
    }
}