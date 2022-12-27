package com.tz.k_home.ui

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tz.k_common.base.BaseFragment
import com.tz.k_common.base.BaseStateObserver
import com.tz.k_common.utils.ToastUtil
import com.tz.k_home.R
import com.tz.k_home.bean.Article
import com.tz.k_home.databinding.FragmentHomeBinding
import com.tz.k_home.ui.adapter.HomeRVAdapter
import com.tz.k_home.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * 首页
 */
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var adapter: HomeRVAdapter

    override fun getLayoutID(): Int {
        return R.layout.fragment_home
    }

    override fun init() {
        adapter = HomeRVAdapter() {
            ToastUtil.showMsg("ssss")
        }
        var manager = LinearLayoutManager(this.mContext)
        manager.orientation = RecyclerView.VERTICAL
        mBind.indexRecv.layoutManager = manager
        mBind.indexRecv.adapter = adapter
        homeViewModel.getProject(0)

        homeViewModel.article.observe(this, object : BaseStateObserver<Article>(null) {
            override fun getRespDataSuccess(it: Article) {
                Log.d("zm", it.datas.size.toString())
                adapter.setData(it.datas)
            }

        })
    }

}