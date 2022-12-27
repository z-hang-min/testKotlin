package com.tz.testkotlin.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tz.k_common.base.BaseFragment
import com.tz.k_common.base.BaseStateObserver
import com.tz.k_common.utils.ToastUtil
import com.tz.testkotlin.R
import com.tz.testkotlin.bean.Articles
import com.tz.testkotlin.databinding.FragmentIndexBinding
import com.tz.testkotlin.ui.adapter.ArticlesAdapter
import com.tz.testkotlin.viewmodel.IndexViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class IndexFragment : BaseFragment<FragmentIndexBinding>() {
    private val indexModel: IndexViewModel by viewModel()
    private lateinit var adapter: ArticlesAdapter
    override fun getLayoutID(): Int {
        return R.layout.fragment_index
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()

    }

    fun init() {
        adapter = ArticlesAdapter() {
            ToastUtil.showMsg("ssss")
        }
        var manager = LinearLayoutManager(this.mContext)
        manager.orientation = RecyclerView.VERTICAL
        mBind.indexRecv.layoutManager = manager
        mBind.indexRecv.adapter = adapter
        indexModel.getProject(0)

        indexModel.article.observe(this, object : BaseStateObserver<Articles>(null) {
            override fun getRespDataSuccess(it: Articles) {
                Log.d("zm", it.datas.size.toString())
                adapter.setData(it.datas)
            }

        })
    }
}