package com.tz.testkotlin.ui

import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.alibaba.android.arouter.facade.annotation.Route
import com.tz.k_common.base.BaseActivity
import com.tz.k_common.utils.ConstConfig
import com.tz.testkotlin.R
import com.tz.testkotlin.databinding.ActivityTabBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.tz.testkotlin.viewmodel.LoginViewModel as LoginViewModel1

@Route(path = ConstConfig.ROUTE_PATH_TAB)
class TabActivity : BaseActivity<ActivityTabBinding>() {
    private val loginViewModel: LoginViewModel1 by viewModel()

    override fun getLayoutID(): Int {
        return R.layout.activity_tab
    }

    override fun init() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        mBind.botNav.setupWithNavController(navController)
    }
}