package com.tz.k_common.base

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {
    val TAG: String = this.javaClass.simpleName
    lateinit var mBind: T

    /**
     *     xml id
     */
    abstract fun getLayoutID(): Int
    abstract fun init()
    lateinit var mContext: Context
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView: ")
        mBind = DataBindingUtil.inflate(inflater, getLayoutID(), container, false)
        return mBind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }
    override fun onDestroy() {
        super.onDestroy()
        mBind.unbind()
    }
}