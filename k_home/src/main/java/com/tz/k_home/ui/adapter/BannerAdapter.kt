package com.tz.k_home.ui.adapter

import android.widget.ImageView
import coil.load
import com.tz.k_home.R
import com.tz.k_home.bean.Banner
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder

/**
 * created by zm on 2022/12/28

 * @Description:

 */

class BannerAdapter : BaseBannerAdapter<Banner>() {

    override fun bindData(holder: BaseViewHolder<Banner>, data: Banner?, position: Int, pageSize: Int) {
        holder?.findViewById<ImageView>(R.id.image_banner)?.load(data?.imagePath)

    }

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.item_banner;
    }
}
