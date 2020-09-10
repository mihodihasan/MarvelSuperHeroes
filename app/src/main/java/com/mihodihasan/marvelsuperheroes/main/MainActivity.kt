package com.mihodihasan.marvelsuperheroes.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mihodihasan.marvelsuperheroes.HeroApp
import com.mihodihasan.marvelsuperheroes.R
import com.mihodihasan.marvelsuperheroes.main.adapter.CharacterAdapter
import com.mihodihasan.marvelsuperheroes.main.adapter.ContentAdapter
import com.mihodihasan.marvelsuperheroes.main.model.Comics
import com.mihodihasan.marvelsuperheroes.main.model.Hero
import com.mihodihasan.marvelsuperheroes.main.model.HeroResult
import com.mihodihasan.marvelsuperheroes.utils.hide
import com.mihodihasan.marvelsuperheroes.utils.show
import com.mihodihasan.marvelsuperheroes.utils.toast
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {
    @Inject
    lateinit var presenter: MainContract.Presenter
    private lateinit var characterAdapter: CharacterAdapter
    private lateinit var characterList: MutableList<HeroResult>
    lateinit var contentAdapter: ContentAdapter
    private lateinit var contentList: MutableList<Comics>

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as HeroApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        characterList = mutableListOf()
        contentList = mutableListOf()
        characterAdapter = CharacterAdapter(this, characterList)
        contentAdapter = ContentAdapter(this, contentList)
        top_list_recycler.adapter = characterAdapter
        top_list_recycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        top_list_recycler.isNestedScrollingEnabled = true
        top_list_recycler.setHasFixedSize(true)

        content_recycler.adapter = contentAdapter
        content_recycler.layoutManager =
            LinearLayoutManager(this)

        contentList.add(Comics("A Quick Brown Fox Jumps Over The Lazy Dog!"))
        contentList.add(Comics("A Quick Brown Fox Jumps Over The Lazy Dog!"))
        contentList.add(Comics("A Quick Brown Fox Jumps Over The Lazy Dog!"))
        contentList.add(Comics("A Quick Brown Fox Jumps Over The Lazy Dog!"))
        contentList.add(Comics("A Quick Brown Fox Jumps Over The Lazy Dog!"))
        contentList.add(Comics("A Quick Brown Fox Jumps Over The Lazy Dog!"))
        contentList.add(Comics("A Quick Brown Fox Jumps Over The Lazy Dog!"))
        contentList.add(Comics("A Quick Brown Fox Jumps Over The Lazy Dog!"))
        contentList.add(Comics("A Quick Brown Fox Jumps Over The Lazy Dog!"))
        contentList.add(Comics("A Quick Brown Fox Jumps Over The Lazy Dog!"))
        contentList.add(Comics("A Quick Brown Fox Jumps Over The Lazy Dog!"))
        contentList.add(Comics("A Quick Brown Fox Jumps Over The Lazy Dog!"))
        contentList.add(Comics("A Quick Brown Fox Jumps Over The Lazy Dog!"))
        contentAdapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        presenter.initView(this)
        presenter.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroyView()
    }

    override fun showTopBarLoading() {
        progressBar.show()
    }

    override fun showMainContentViewLoading() {
    }

    override fun hideTopBarLoading() {
        progressBar.hide()
    }

    override fun hideMainContentViewLoading() {
    }

    override fun displayHeroesAvatar(heroList: MutableList<HeroResult>) {
        Log.d("RESULT_FROM_ACTIVITY", "displayHeroesAvatar: ".plus(heroList.toString()))
        characterList.addAll(heroList)
        characterAdapter.notifyDataSetChanged()
    }

    override fun displayComicsList(heroList: MutableList<Hero>) {
    }

    override fun displayErrorMessage(errMsg: String) {
        toast(errMsg)
    }
}