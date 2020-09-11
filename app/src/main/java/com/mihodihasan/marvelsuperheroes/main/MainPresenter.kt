package com.mihodihasan.marvelsuperheroes.main

import com.mihodihasan.marvelsuperheroes.common.UseCase
import com.mihodihasan.marvelsuperheroes.common.UseCaseHandler
import com.mihodihasan.marvelsuperheroes.main.usecase.GetComicsList
import com.mihodihasan.marvelsuperheroes.main.usecase.GetHeroList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainPresenter constructor(private val mUseCaseHandler: UseCaseHandler, private val getHeroList: GetHeroList, private val getComicsList: GetComicsList,): MainContract.Presenter {

    var mMainView : MainContract.View? = null

    override fun loadMarvelHeroes(pageNumber: Int) {
            mMainView?.showTopBarLoading()
            val requestValues = GetHeroList.RequestValues(pageNumber)
            mUseCaseHandler.execute(getHeroList, requestValues, object : UseCase.UseCaseCallback<GetHeroList.ResponseValue> {
                override fun onSuccess(response: GetHeroList.ResponseValue) {
                    mMainView?.hideTopBarLoading()
                    mMainView?.displayHeroesAvatar(response.getHeroesList())
                    loadComics(response.getHeroesList()[0].id, 0)
                    if (pageNumber==0) mMainView?.stopTopShimmering()
                }

                override fun onError() {
                    mMainView?.displayErrorMessage("Error Loading Avatars")
                }

            })
    }

    override fun loadComics(heroId: Int, pageNumber: Int) {
        mMainView?.showMainContentViewLoading()
        val requestValues = GetComicsList.RequestValues(heroId, pageNumber)
        mUseCaseHandler.execute(getComicsList, requestValues, object : UseCase.UseCaseCallback<GetComicsList.ResponseValue> {
            override fun onSuccess(response: GetComicsList.ResponseValue) {
                mMainView?.hideMainContentViewLoading()
                mMainView?.displayComicsList(response.getComicsList())
                if (pageNumber==0) mMainView?.stopContentShimmering()
            }

            override fun onError() {
                mMainView?.displayErrorMessage("Error Loading Content")
            }

        })

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