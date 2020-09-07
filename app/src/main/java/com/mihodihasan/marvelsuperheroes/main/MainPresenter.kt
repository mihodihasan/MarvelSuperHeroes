package com.mihodihasan.marvelsuperheroes.main

import com.mihodihasan.marvelsuperheroes.common.UseCase
import com.mihodihasan.marvelsuperheroes.common.UseCaseHandler
import com.mihodihasan.marvelsuperheroes.main.usecase.GetHeroList
import javax.inject.Inject

class MainPresenter constructor(private val mUseCaseHandler: UseCaseHandler, private val getHeroList: GetHeroList): MainContract.Presenter {

    var mMainView : MainContract.View? = null
    init {
//        mMainView.setPresenter(this)
    }
    override fun loadMarvelHeroes(pageNumber: Int) {
        mMainView?.showMainContentViewLoading()
        val requestValues = GetHeroList.RequestValues(pageNumber)
        mUseCaseHandler.execute(getHeroList, requestValues, object : UseCase.UseCaseCallback<GetHeroList.ResponseValue> {
            override fun onSuccess(response: GetHeroList.ResponseValue) {
                mMainView?.hideTopBarLoading()
                mMainView?.displayHeroesAvatar(response.getHeroesList())
            }

            override fun onError() {
                mMainView?.displayErrorMessage("Error Loading Avatars")
            }

        })
    }

    override fun loadComics(heroId: String, pageNumber: Int) {

    }

    override fun initView(view: MainContract.View?) {
        mMainView = view
    }

    override fun destroyView() {
        mMainView = null
    }

    override fun start() {
        loadMarvelHeroes(0)
    }
}