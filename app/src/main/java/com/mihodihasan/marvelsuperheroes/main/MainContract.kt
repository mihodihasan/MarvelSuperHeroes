package com.mihodihasan.marvelsuperheroes.main

import com.mihodihasan.marvelsuperheroes.common.BasePresenter
import com.mihodihasan.marvelsuperheroes.common.BaseView
import com.mihodihasan.marvelsuperheroes.main.model.Hero

interface MainContract {

    interface View : BaseView<Presenter> {
        fun showTopBarLoading()
        fun showMainContentViewLoading()
        fun hideTopBarLoading()
        fun hideMainContentViewLoading()
        fun displayHeroesAvatar(heroList: MutableList<Hero>)
        fun displayComicsList(heroList: MutableList<Hero>)
        fun displayErrorMessage(errMsg:String)
    }

    interface Presenter : BasePresenter {
        fun loadMarvelHeroes(pageNumber: Int)
        fun loadComics(heroId: String, pageNumber: Int)
        fun initView(view:View?)
        fun destroyView()
    }
}
