package com.tz.k_home.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tz.k_common.utils.ConstConfig
import com.tz.k_home.R
import com.tz.k_home.bean.a

/**
 * created by zm on 2022/11/10

 * @Description:

 */
class HomeRVAdapter(val listener: HomeItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), View.OnClickListener {
    var isLastPage = false

    //RecyclerView局部刷新，用于计算两个列表之间的差异并输出将第一个列表转换为第二个列表的更新操作列表,它可用于计算 RecyclerView 适配器的更新.
    private var diff: AsyncListDiffer<a> = AsyncListDiffer(this, MyCallback())
    private val NORMAL: Int = 0
    private val FOOT: Int = 1
    private val LAST: Int = 2


    fun setData(list: List<a>?) {
        //AsyncListDiffer需要一个新数据，不然添加无效
        diff.submitList(if (list != null) ArrayList(list) else null)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            NORMAL -> {
                MyViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_home, parent, false)
                )
            }
            FOOT -> {
                MyFootHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(com.tz.k_common.R.layout.foot_rv, parent, false)
                )
            }
            else -> {
                MyLastHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(com.tz.k_common.R.layout.last_rv, parent, false)
                )
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (getItemViewType(position) == NORMAL) {
            val data = diff.currentList[position]
            (holder as MyViewHolder).title.text = data.title
            holder.time.text = data.niceDate
            holder.type.text = data.superChapterName
            holder.tag1.visibility = if (data.fresh) View.VISIBLE else View.GONE
            holder.tag2.visibility = if (data.superChapterId == 408) View.VISIBLE else View.GONE
            holder.name.text = if (data.author.isEmpty()) data.shareUser else data.author

            if (data.collect) {
                holder.collect.setImageResource(com.tz.k_common.R.drawable.icon_collect_2)
            } else {
                holder.collect.setImageResource(com.tz.k_common.R.drawable.icon_collect_1)
            }


            holder.itemView.tag = position
            holder.itemView.setOnClickListener(this)

            holder.collect.tag = position
            holder.collect.setOnClickListener(this)
        }
    }

    override fun getItemCount(): Int {
        return if (diff.currentList.size == 0) 1 else diff.currentList.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount - 1) {
            if (isLastPage) {
                LAST
            } else {
                FOOT
            }
        } else {
            NORMAL
        }
    }

    class MyViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        var title: TextView = item.findViewById(R.id.title)
        var name: TextView = item.findViewById(R.id.name)
        var time: TextView = item.findViewById(R.id.time)
        var type: TextView = item.findViewById(R.id.type)
        var tag1: TextView = item.findViewById(R.id.tag1)
        var tag2: TextView = item.findViewById(R.id.tag2)
        var collect: ImageView = item.findViewById(R.id.img_collect)


    }

    class MyFootHolder(item: View) : RecyclerView.ViewHolder(item)

    class MyLastHolder(item: View) : RecyclerView.ViewHolder(item)

    private var lastClickTime: Long = 0
    override fun onClick(v: View?) {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime > ConstConfig.MIN_CLICK_DELAY_TIME && v != null) {
            lastClickTime = currentTime

            if (v.id == R.id.img_collect) {
                listener.onCollectClick(v.tag as Int)
            } else {
                listener.onItemClick(v.tag as Int)
            }

        }
    }
}

class MyCallback : DiffUtil.ItemCallback<a>() {
    override fun areItemsTheSame(
        oldItem: a,
        newItem: a
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: a,
        newItem: a
    ): Boolean {
        return oldItem.title == newItem.title && oldItem.niceDate == newItem.niceDate
    }
}

interface HomeItemClickListener {
    fun onItemClick(position: Int)
    fun onCollectClick(position: Int)
}