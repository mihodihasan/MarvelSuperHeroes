package com.mihodihasan.marvelsuperheroes.main

import com.mihodihasan.marvelsuperheroes.common.UseCase
import com.mihodihasan.marvelsuperheroes.common.UseCaseHandler
import com.mihodihasan.marvelsuperheroes.main.usecase.GetHeroList

class MainPresenter(private val mMainView: MainContract.View, private val mUseCaseHandler: UseCaseHandler, private val getHeroList: GetHeroList): MainContract.Presenter {

    init {
        mMainView.setPresenter(this)
    }
    override fun loadMarvelHeroes(pageNumber: Int) {
        mMainView.showMainContentViewLoading()
        val requestValues = GetHeroList.RequestValues(pageNumber)
        mUseCaseHandler.execute(getHeroList, requestValues, object : UseCase.UseCaseCallback<GetHeroList.ResponseValue> {
            override fun onSuccess(response: GetHeroList.ResponseValue) {
                mMainView.hideTopBarLoading()
                mMainView.displayHeroesAvatar(response.getHeroesList())
            }

            override fun onError() {
                mMainView.displayErrorMessage("Error Loading Avatars")
            }

        })
    }

    override fun loadComics(heroId: String, pageNumber: Int) {

    }

    override fun start() {
        loadMarvelHeroes(0)
    }
}