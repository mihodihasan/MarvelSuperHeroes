package com.mihodihasan.marvelsuperheroes.main.usecase

import com.mihodihasan.marvelsuperheroes.common.UseCase
import com.mihodihasan.marvelsuperheroes.data.source.DataSource
import com.mihodihasan.marvelsuperheroes.data.source.Repository
import com.mihodihasan.marvelsuperheroes.main.model.Hero

class GetHeroList(private val repository: Repository) :
    UseCase<GetHeroList.RequestValues, GetHeroList.ResponseValue>() {

    override fun executeUseCase(requestValues: RequestValues) {
        repository.getHeroes(requestValues.getPageNumber(), object : DataSource.LoadHeroesCallback {
            override fun onHeroesLoaded(heroes: MutableList<Hero>) {
                val responseValue = ResponseValue(heroes)
                mUseCaseCallback.onSuccess(responseValue)
            }

            override fun onDataNotAvailable() {
                mUseCaseCallback.onError()
            }
        })
    }

    class RequestValues(private val pageNo: Int) : UseCase.RequestValues {
        fun getPageNumber(): Int = pageNo
    }

    class ResponseValue(private val heroesList: MutableList<Hero>) : UseCase.ResponseValue {
        fun getHeroesList(): MutableList<Hero> = heroesList
    }


}