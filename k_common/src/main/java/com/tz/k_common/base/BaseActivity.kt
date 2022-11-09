package com.tz.k_common.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {
    val TAG: String = this.javaClass.simpleName
    lateinit var mBind: T

    /**
     *     xml id
     */
    abstract fun getLayoutID(): Int
    abstract fun init()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        mBind = DataBindingUtil.setContentView(this, getLayoutID())
        init()
    }

    override fun onDestroy() {
        super.onDestroy()
        mBind.unbind()
    }
}