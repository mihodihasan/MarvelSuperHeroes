package com.mihodihasan.marvelsuperheroes.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mihodihasan.marvelsuperheroes.R
import com.mihodihasan.marvelsuperheroes.main.model.Comics
import com.mihodihasan.marvelsuperheroes.main.model.ComicsResult
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_view_main_list.view.*

class ContentAdapter(val context: Context, private val list: MutableList<ComicsResult>) : RecyclerView.Adapter<ContentAdapter.ContentVH>() {

    class ContentVH(itemView:View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentVH = ContentVH(LayoutInflater.from(context).inflate(
        R.layout.item_view_main_list, parent, false))

    override fun onBindViewHolder(holder: ContentVH, position: Int) {
        holder.itemView.content_description.text = list[position].description
        holder.itemView.comic_title_tv.text = list[position].title
        holder.itemView.date_modified_tv.text = list[position].modified
//        Picasso.get().load(list[position].thumbnail?.path.plus(".").plus(list[position].thumbnail?.extension).replace("http://","https://")).into(holder.itemView.content_img_view)
    }

    override fun getItemCount(): Int = list.size
}