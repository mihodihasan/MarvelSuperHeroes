package com.mihodihasan.marvelsuperheroes.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mihodihasan.marvelsuperheroes.R
import com.mihodihasan.marvelsuperheroes.main.model.Hero
import kotlinx.android.synthetic.main.item_view_top_bar.view.*

class CharacterAdapter(private val context: Context, private val charList: MutableList<Hero>) :
    RecyclerView.Adapter<CharacterAdapter.CharacterVH>() {

    class CharacterVH(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterVH = CharacterVH(
        LayoutInflater.from(context).inflate(
            R.layout.item_view_top_bar, parent, false
        )
    )

    override fun onBindViewHolder(holder: CharacterVH, position: Int) {
        holder.itemView.nameTv.text = charList[position].name
    }

    override fun getItemCount(): Int = charList.size
}