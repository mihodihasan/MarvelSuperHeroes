package com.mihodihasan.marvelsuperheroes.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mihodihasan.marvelsuperheroes.R
import com.mihodihasan.marvelsuperheroes.main.model.Comics
import com.mihodihasan.marvelsuperheroes.main.model.ComicsResult
import kotlinx.android.synthetic.main.item_view_main_list.view.*

class ContentAdapter(val context: Context, private val list: MutableList<ComicsResult>) : RecyclerView.Adapter<ContentAdapter.ContentVH>() {

    class ContentVH(itemView:View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentVH = ContentVH(LayoutInflater.from(context).inflate(
        R.layout.item_view_main_list, parent, false))

    override fun onBindViewHolder(holder: ContentVH, position: Int) {
        holder.itemView.textView.text = list[position].description
    }

    override fun getItemCount(): Int = list.size
}