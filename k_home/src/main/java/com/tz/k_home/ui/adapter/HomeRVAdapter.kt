package com.tz.k_home.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tz.k_home.R
import com.tz.k_home.bean.ArticleDetail

/**
 * created by zm on 2022/11/10

 * @Description:

 */
class HomeRVAdapter(val listener: View.OnClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), View.OnClickListener {
    private var dataList: List<ArticleDetail> = ArrayList<ArticleDetail>()
    fun setData(list: List<ArticleDetail>) {
        this.dataList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_articles, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(holder as MyViewHolder) {
            var projectDetials: ArticleDetail = dataList[position]
            holder.title.text = projectDetials.title
            holder.desc.text = projectDetials.author
            holder.fenlei.text = projectDetials.superChapterName

        }
    }

    override fun getItemCount(): Int {
        if (dataList.isEmpty())
            return 0
        return dataList.size
    }

    class MyViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        var title: TextView = item.findViewById(R.id.tv_title)
        var fenlei: TextView = item.findViewById(R.id.tv_fenlei)
        var desc: TextView = item.findViewById(R.id.tv_desc)

    }

    override fun onClick(p0: View?) {
    }
}