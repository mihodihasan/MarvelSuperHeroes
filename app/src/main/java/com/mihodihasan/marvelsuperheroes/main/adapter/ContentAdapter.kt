package com.mihodihasan.marvelsuperheroes.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mihodihasan.marvelsuperheroes.R
import com.mihodihasan.marvelsuperheroes.main.model.ComicsResult
import com.mihodihasan.marvelsuperheroes.utils.Constants
import com.mihodihasan.marvelsuperheroes.utils.hide
import com.mihodihasan.marvelsuperheroes.utils.show
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.content_item_loading.view.*
import kotlinx.android.synthetic.main.item_view_main_list.view.*
import kotlinx.android.synthetic.main.item_view_main_list.view.comic_title_tv
import kotlinx.android.synthetic.main.item_view_main_list.view.content_description
import kotlinx.android.synthetic.main.item_view_main_list.view.content_img_view
import kotlinx.android.synthetic.main.item_view_main_list.view.date_modified_tv
import java.text.SimpleDateFormat
import java.util.*

class ContentAdapter(val context: Context, private val list: MutableList<ComicsResult>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var continueLoading: Boolean = true

    class ContentVH(itemView: View) : RecyclerView.ViewHolder(itemView)
    class ContentVHLoading(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0) ContentVH(
            LayoutInflater.from(context).inflate(
                R.layout.item_view_main_list, parent, false
            )
        ) else ContentVHLoading(
            LayoutInflater.from(context).inflate(
                R.layout.content_item_loading, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position == list.size){
            if (continueLoading) holder.itemView.content_loading_root.show()
            else holder.itemView.content_loading_root.hide()
        }else{
            holder.itemView.content_description.text =
                if (list[position].description == null) "No Details Available" else list[position].description
            holder.itemView.comic_title_tv.text = list[position].title
            holder.itemView.date_modified_tv.text =
                if (beautifyDate(list[position].modified) == null) list[position].modified else beautifyDate(
                    list[position].modified
                )
            Picasso.get().load(
                list[position].thumbnail.path.plus(".").plus(list[position].thumbnail.extension)
                    .replace("http://", "https://")
            ).into(holder.itemView.content_img_view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == list.size) 1 else 0
    }

    override fun getItemCount(): Int = list.size + 1

    private fun beautifyDate(serverDate: String): String? {
        val simpleDateFormatParser = SimpleDateFormat(Constants.SERVER_DATE_FORMAT, Locale.ENGLISH)
        val simpleDateFormatFormatter =
            SimpleDateFormat(Constants.BEAUTIFY_DATE_FORMAT, Locale.ENGLISH)
        val date = simpleDateFormatParser.parse(serverDate)
        return if (date == null) null else simpleDateFormatFormatter.format(date)
    }

    fun stopLoading() {
        continueLoading = false
        notifyDataSetChanged()
    }
}