package com.tz.testkotlin.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tz.testkotlin.R
import com.tz.testkotlin.bean.Articles
import com.tz.testkotlin.bean.ProjectDetials

/**
 * created by zm on 2022/11/10

 * @Description:

 */
class ArticlesAdapter(val listener: View.OnClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), View.OnClickListener {
    private var dataList: List<ProjectDetials> = ArrayList<ProjectDetials>()
    fun setData(list: List<ProjectDetials>) {
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
            var projectDetials: ProjectDetials = dataList[position]
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