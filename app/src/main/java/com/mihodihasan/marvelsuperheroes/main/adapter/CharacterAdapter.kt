package com.mihodihasan.marvelsuperheroes.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mihodihasan.marvelsuperheroes.R
import com.mihodihasan.marvelsuperheroes.main.model.HeroResult
import com.mihodihasan.marvelsuperheroes.utils.hide
import com.mihodihasan.marvelsuperheroes.utils.show
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_view_top_bar.view.*
import kotlinx.android.synthetic.main.item_view_top_bar.view.imageView
import kotlinx.android.synthetic.main.item_view_top_bar.view.nameTv
import kotlinx.android.synthetic.main.top_bar_item_loading.view.*

class CharacterAdapter(
    private val context: Context,
    private val charList: MutableList<HeroResult>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var continueLoading: Boolean = true

    class CharacterVH(itemView: View) : RecyclerView.ViewHolder(itemView)
    class CharacterVHLoading(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0) CharacterVH(
            LayoutInflater.from(context).inflate(
                R.layout.item_view_top_bar, parent, false
            )
        ) else
            CharacterVHLoading(
                LayoutInflater.from(context).inflate(
                    R.layout.top_bar_item_loading, parent, false
                )
            )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position == charList.size){
            if (continueLoading) holder.itemView.loading_shimmer.show()
            else holder.itemView.loading_shimmer.hide()
        }else {
            holder.itemView.nameTv.text = charList[position].name
            Picasso.get().load(
                charList[position].thumbnail.path.plus(".")
                    .plus(charList[position].thumbnail.extension)
                    .replace("http://", "https://")
            ).into(holder.itemView.imageView)
        }
    }

    public fun stopContinueLoading() {
        continueLoading = false
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == charList.size) 1 else 0
    }

    override fun getItemCount(): Int = charList.size+1
}