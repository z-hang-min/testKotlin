package com.tz.k_common.widget

import android.content.AttributionSource
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.tz.k_common.utils.Extension.dp2px
import kotlin.math.abs

/**
 * created by zm on 2022/12/27

 * @Description:下拉刷新控件

 */
class MySwipeRefreshLayout(context: Context, attr: AttributeSet) :
    SwipeRefreshLayout(context, attr) {

    var vpIsDragging: Boolean = false
    var x1 = 0f
    var y1 = 0f
    var x2 = 0f
    var y2 = 0f
    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        when (ev?.action) {
            MotionEvent.ACTION_DOWN -> {
                x1 = ev.x
                y1 = ev.y

            }
            MotionEvent.ACTION_MOVE -> {
                if (vpIsDragging) {
                    return false
                }
                x2 = ev.x
                y2 = ev.y
                if (abs(x1 - x2) > abs(y1 - y2) && y1 <= 180.dp2px()) {
                    vpIsDragging=true
                    return false
                }
            }
            MotionEvent.ACTION_UP,MotionEvent.ACTION_CANCEL->{
                vpIsDragging=false
            }

        }
        return super.onInterceptTouchEvent(ev)
    }

}