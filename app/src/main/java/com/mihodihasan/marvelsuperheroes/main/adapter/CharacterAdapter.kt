package com.mihodihasan.marvelsuperheroes.main.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mihodihasan.marvelsuperheroes.R
import com.mihodihasan.marvelsuperheroes.main.model.Hero
import com.mihodihasan.marvelsuperheroes.main.model.HeroResult
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_view_top_bar.view.*

class CharacterAdapter(private val context: Context, private val charList: MutableList<HeroResult>) :
    RecyclerView.Adapter<CharacterAdapter.CharacterVH>() {

    class CharacterVH(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterVH = CharacterVH(
        LayoutInflater.from(context).inflate(
            R.layout.item_view_top_bar, parent, false
        )
    )

    override fun onBindViewHolder(holder: CharacterVH, position: Int) {
        holder.itemView.nameTv.text = charList[position].name
//        Log.d("LSNTAG", "onBindViewHolder: "+charList[position].thumbnail.path.plus(".").plus(charList[position].thumbnail.extension))
//        Picasso.get().load(charList[position].thumbnail.path.plus(".").plus(charList[position].thumbnail.extension).replace("http://","https://")).into(holder.itemView.imageView)
    }

    override fun getItemCount(): Int = charList.size
}