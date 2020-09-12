package com.mihodihasan.marvelsuperheroes.main

import com.mihodihasan.marvelsuperheroes.common.UseCase
import com.mihodihasan.marvelsuperheroes.common.UseCaseHandler
import com.mihodihasan.marvelsuperheroes.main.usecase.GetComicsList
import com.mihodihasan.marvelsuperheroes.main.usecase.GetHeroList
import com.mihodihasan.marvelsuperheroes.utils.Constants
import com.mihodihasan.marvelsuperheroes.utils.SharedPreferenceManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val mUseCaseHandler: UseCaseHandler,
    private val getHeroList: GetHeroList,
    private val getComicsList: GetComicsList,
    private val sharedPreferenceManager: SharedPreferenceManager
): MainContract.Presenter {

    var mMainView : MainContract.View? = null

    override fun loadMarvelHeroes(pageNumber: Int) {
            val requestValues = GetHeroList.RequestValues(pageNumber)
            mUseCaseHandler.execute(getHeroList, requestValues, object : UseCase.UseCaseCallback<GetHeroList.ResponseValue> {
                override fun onSuccess(response: GetHeroList.ResponseValue) {
                    mMainView?.displayHeroesAvatar(response.getHeroesList())
                    loadComics(response.getHeroesList()[0].id, 0)
                    if (pageNumber==0) mMainView?.stopTopShimmering()
                }

                override fun onError(errMsg:String) {
                    Timber.d("PROBLEM_LSN %s", errMsg)
                    mMainView?.displayErrorMessage("Error Loading Avatars")
                }

            })
    }

    override fun loadComics(marvelHeroId: Int, pageNumber: Int) {
        var heroId=marvelHeroId
        if (heroId == -1){
            heroId = sharedPreferenceManager.getInt(Constants.CURRENT_HERO_ID)
        }
        sharedPreferenceManager.saveInt(Constants.CURRENT_HERO_ID, heroId)
        val requestValues = GetComicsList.RequestValues(heroId, pageNumber)
        mUseCaseHandler.execute(getComicsList, requestValues, object : UseCase.UseCaseCallback<GetComicsList.ResponseValue> {
            override fun onSuccess(response: GetComicsList.ResponseValue) {
                mMainView?.displayComicsList(response.getComicsList())
                if (pageNumber==0) mMainView?.stopContentShimmering()
            }

            override fun onError(errString:String) {
                Timber.d("ProblemLoadingLSN %s", errString)
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