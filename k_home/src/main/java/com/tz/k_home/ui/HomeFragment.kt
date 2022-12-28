package com.tz.k_home.ui

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.launcher.ARouter
import com.tz.k_common.base.BaseFragment
import com.tz.k_common.base.BaseStateObserver
import com.tz.k_common.base.BaseVMFragment
import com.tz.k_common.utils.ConstConfig
import com.tz.k_common.utils.ToastUtil
import com.tz.k_home.R
import com.tz.k_home.bean.Article
import com.tz.k_home.bean.ArticleDetail
import com.tz.k_home.bean.Banner
import com.tz.k_home.databinding.FragmentHomeBinding
import com.tz.k_home.ui.adapter.BannerAdapter
import com.tz.k_home.ui.adapter.HomeItemClickListener
import com.tz.k_home.ui.adapter.HomeRVAdapter
import com.tz.k_home.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * 首页
 */
class HomeFragment : BaseVMFragment<FragmentHomeBinding>() {
    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var homeRVAdapter: HomeRVAdapter
    var list: MutableList<ArticleDetail> = arrayListOf()
    var isLoadMore = false
    var collectPosition: Int = 0
    lateinit var lm: LinearLayoutManager
    override fun getLayoutID(): Int {
        return R.layout.fragment_home
    }

    override fun init() {

        mBind.banner.apply {
            setAdapter(BannerAdapter())
            setLifecycleRegistry(lifecycle)
            setScrollDuration(600)
            setInterval(5000)
            setAutoPlay(false)
        }.create()
        lm = LinearLayoutManager(this.mContext)
        lm.orientation = RecyclerView.VERTICAL
        mBind.indexRecv.layoutManager = lm

        homeRVAdapter = HomeRVAdapter(object : HomeItemClickListener {
            override fun onItemClick(position: Int) {
                val data = list[position]
                ARouter.getInstance()
                    .build(ConstConfig.PATH_WEB)
                    .withString(ConstConfig.WEB_LINK, data.link)
                    .withString(ConstConfig.WEB_TITLE, data.title)
                    .navigation()
            }

            override fun onCollectClick(position: Int) {
                collectPosition = position
                if (list[collectPosition].collect) {
                    homeViewModel.unCollect(list[collectPosition].id)
                } else {
                    homeViewModel.collect(list[collectPosition].id)
                }
            }

        })

        mBind.indexRecv.adapter = homeRVAdapter
        mBind.indexRecv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE && homeRVAdapter.itemCount != 0 && (lm.findLastCompletelyVisibleItemPosition() + 1) == homeRVAdapter.itemCount && !isLoadMore && !homeRVAdapter.isLastPage) {
                    Log.d(TAG, "onScrollStateChanged: last-----")
                    isLoadMore = true
                    homeViewModel.getProject(currentPage + 1)
                }
            }
        })
        mBind.srlHome.setColorSchemeResources(R.color.purple_700)
        mBind.srlHome.setOnRefreshListener {
            homeRVAdapter.isLastPage = false
            getData()

        }

        getData()
    }

    private fun getData() {
        homeViewModel.getBanner()
        homeViewModel.getProject(0)
    }

    override fun observe() {
        homeViewModel.bannerList.observe(this,object :BaseStateObserver<List<Banner>>(null){
            override fun getRespDataSuccess(it: List<Banner>) {
                Log.d(TAG, "observe bannerList: " + it.size)
                if (it.isEmpty()) {
                    return
                }
                mBind.banner.refreshData(it)
            }

            override fun getRespDataEnd() {
                resetUI()
            }
        })

        homeViewModel.article.observe(this, object : BaseStateObserver<Article>(null) {
            override fun getRespDataSuccess(it: Article) {
                resetUI()
                currentPage = it.curPage - 1
                //下拉刷新
                if (currentPage == 0) {
                    list.clear()
                }
                //最后一页
                if (it.over) {
                    homeRVAdapter.isLastPage = true
                }
                //list添加数据
                list.addAll(it.datas)
                //处理数据
                if (currentPage == 0) {
                    Log.d("zm", list.toString())
                    homeRVAdapter.setData(null)
                    homeRVAdapter.setData(list)
                    lm.scrollToPosition(0)
                } else {
                    homeRVAdapter.setData(list)
                }
            }
            override fun getRespDataEnd() {
                resetUI()
            }

        })
        homeViewModel.collectData.observe(this, object : BaseStateObserver<String>(null) {
            override fun getRespSuccess() {
                if (list[collectPosition].collect) {
                    ToastUtil.showMsg("取消收藏！")
                    list[collectPosition].collect = false
                } else {
                    ToastUtil.showMsg("收藏成功！")
                    list[collectPosition].collect = true
                }
                homeRVAdapter.notifyItemChanged(collectPosition)
            }

        })
    }

    private fun resetUI() {
        isLoadMore = false//加载更多完成，重置false
        if (mBind.srlHome.isRefreshing) {
            mBind.srlHome.isRefreshing = false
        }
    }
}