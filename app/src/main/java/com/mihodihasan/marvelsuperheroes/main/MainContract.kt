package com.mihodihasan.marvelsuperheroes.main

import com.mihodihasan.marvelsuperheroes.common.BasePresenter
import com.mihodihasan.marvelsuperheroes.common.BaseView
import com.mihodihasan.marvelsuperheroes.main.model.ComicsResult
import com.mihodihasan.marvelsuperheroes.main.model.Hero
import com.mihodihasan.marvelsuperheroes.main.model.HeroResult

interface MainContract {

    interface View : BaseView<Presenter> {
        fun showTopBarLoading()
        fun stopTopShimmering()
        fun stopContentShimmering()
        fun showMainContentViewLoading()
        fun hideTopBarLoading()
        fun hideMainContentViewLoading()
        fun displayHeroesAvatar(heroList: MutableList<HeroResult>)
        fun displayComicsList(comicsList: MutableList<ComicsResult>)
        fun displayErrorMessage(errMsg:String)
    }

    interface Presenter : BasePresenter {
        fun loadMarvelHeroes(pageNumber: Int)
        fun loadComics(heroId: Int, pageNumber: Int)
        fun initView(view:View?)
        fun destroyView()
    }
}
