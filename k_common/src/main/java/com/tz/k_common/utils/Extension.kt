package com.tz.k_common.utils

import android.content.res.Resources

/**
 * created by zm on 2022/12/27

 * @Description:

 */
object Extension {
    fun Number.px2dp(): Float {
        val f = toFloat()
        val scale: Float = Resources.getSystem().displayMetrics.density
        return (f / scale + 0.5f)
    }

    fun Number.dp2px(): Int {
        val f = toFloat()
        val scale: Float = Resources.getSystem().displayMetrics.density
        return (f * scale + 0.5f).toInt()
    }
}