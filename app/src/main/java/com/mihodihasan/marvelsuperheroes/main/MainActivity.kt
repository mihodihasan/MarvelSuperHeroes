package com.mihodihasan.marvelsuperheroes.main

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.mihodihasan.marvelsuperheroes.HeroApp
import com.mihodihasan.marvelsuperheroes.R
import com.mihodihasan.marvelsuperheroes.common.BaseActivity
import com.mihodihasan.marvelsuperheroes.main.adapter.CharacterAdapter
import com.mihodihasan.marvelsuperheroes.main.adapter.ContentAdapter
import com.mihodihasan.marvelsuperheroes.main.model.ComicsResult
import com.mihodihasan.marvelsuperheroes.main.model.HeroResult
import com.mihodihasan.marvelsuperheroes.utils.*
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View {
    @Inject
    lateinit var presenter: MainContract.Presenter
    private lateinit var characterAdapter: CharacterAdapter
    private lateinit var characterList: MutableList<HeroResult>
    private lateinit var contentAdapter: ContentAdapter
    private lateinit var contentList: MutableList<ComicsResult>
    private var comicsPageNumber: Int = 0
    private lateinit var topLinearManager: LinearLayoutManager
    private lateinit var contentLinearManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as HeroApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        characterList = mutableListOf()
        contentList = mutableListOf()
        characterAdapter = CharacterAdapter(this, characterList)
        contentAdapter = ContentAdapter(this, contentList)
        top_list_recycler.adapter = characterAdapter
        topLinearManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        top_list_recycler.layoutManager = topLinearManager

        top_list_recycler.isNestedScrollingEnabled = true
        top_list_recycler.setHasFixedSize(true)

        content_recycler.adapter = contentAdapter
        contentLinearManager = LinearLayoutManager(this)
        content_recycler.layoutManager = contentLinearManager
        contentAdapter.notifyDataSetChanged()

        top_list_recycler.addOnItemTouchListener(
            RecyclerClick(this, top_list_recycler, object : ClickListener {
                override fun onClick(view: View?, position: Int) {
                    comicsPageNumber = 0
                    startContentShimmering()
                    contentList.clear()
                    presenter.loadComics(characterList[position].id, comicsPageNumber)
                }

                override fun onLongClick(view: View?, position: Int) {
                }
            })
        )

        top_list_recycler.addOnScrollListener(object :
            EndlessRecyclerViewScrollListener(topLinearManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                presenter.loadMarvelHeroes(page)
            }
        })

        content_recycler.addOnScrollListener(object :
            EndlessRecyclerViewScrollListener(contentLinearManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                presenter.loadComics(-1, page)
            }
        })
    }

    override fun stopTopShimmering() {
        top_shimmer.stopShimmer()
        top_shimmer.hide()
        top_list_recycler.show()
    }

    override fun stopContentShimmering() {
        content_shimmer.stopShimmer()
        content_shimmer.hide()
        content_recycler.show()
    }

    override fun startContentShimmering() {
        content_shimmer.startShimmer()
        content_shimmer.show()
        content_recycler.hide()
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

    override fun displayHeroesAvatar(heroList: MutableList<HeroResult>) {
        Timber.d("displayHeroesAvatar: ".plus(heroList.toString()))
        characterList.addAll(heroList)
        characterAdapter.notifyDataSetChanged()
        if (heroList.size == 0) characterAdapter.stopContinueLoading()
    }

    override fun displayComicsList(comicsList: MutableList<ComicsResult>) {
        Timber.d("displayComicsList: ".plus(comicsList.toString()))
        contentList.addAll(comicsList)
        contentAdapter.notifyDataSetChanged()
        if (comicsList.size == 0) contentAdapter.stopLoading()
    }

    override fun displayErrorMessage(errMsg: String) {
        Snackbar.make(snackbar_tv, errMsg, Snackbar.LENGTH_LONG).show()
//        toast(errMsg)
    }

}
