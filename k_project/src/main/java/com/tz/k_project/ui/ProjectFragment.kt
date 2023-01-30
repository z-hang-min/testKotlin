package com.tz.k_project.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayoutMediator
import com.stew.kb_project.bean.ProjectType
import com.tz.k_common.base.BaseStateObserver
import com.tz.k_common.base.BaseVMFragment
import com.tz.k_project.R
import com.tz.k_project.databinding.FragmentProjectBinding
import com.tz.k_project.ui.adapter.ProVPAdapter
import com.tz.k_project.viewmodel.ProjectViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProjectFragment : BaseVMFragment<FragmentProjectBinding>() {
    private val projectViewModel: ProjectViewModel by viewModel()
    private var l: MutableList<String> = arrayListOf()
    private var f: MutableList<Fragment> = arrayListOf()
    override fun getLayoutID(): Int {
        return R.layout.fragment_project
    }

    override fun observe() {
        projectViewModel.proTypeList.observe(this,
            object : BaseStateObserver<List<ProjectType>>(null) {
                override fun getRespDataSuccess(it: List<ProjectType>) {
                    initTab(it)
                }
            })
    }

    override fun init() {
        projectViewModel.getProTypeList()
    }

    private fun initTab(list: List<ProjectType>) {
        for (i in 0..4) {
            l.add((i + 1).toString() + "." + list[i].name)
            f.add(ProjectChildFragment.newInstance(list[i].id, i))
        }
        mBind.viewPager.adapter = ProVPAdapter(this, f)
        mBind.viewPager.offscreenPageLimit = 5
        TabLayoutMediator(mBind.tabLayout, mBind.viewPager) { tab, position ->
            tab.text = l[position]
        }.attach()
    }
}