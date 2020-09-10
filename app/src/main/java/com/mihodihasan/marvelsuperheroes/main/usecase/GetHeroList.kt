package com.mihodihasan.marvelsuperheroes.main.usecase

import com.mihodihasan.marvelsuperheroes.common.UseCase
import com.mihodihasan.marvelsuperheroes.data.source.DataSource
import com.mihodihasan.marvelsuperheroes.data.source.Repository
import com.mihodihasan.marvelsuperheroes.main.model.Hero
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetHeroList @Inject constructor(private val repository: Repository) :
    UseCase<GetHeroList.RequestValues, GetHeroList.ResponseValue>() {

    override fun executeUseCase(requestValues: RequestValues) {
         val job = Job()
         val coroutineScope = CoroutineScope(
            job + Dispatchers.Main
        )
        coroutineScope.launch {
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
    }

    class RequestValues(private val pageNo: Int) : UseCase.RequestValues {
        fun getPageNumber(): Int = pageNo
    }

    class ResponseValue(private val heroesList: MutableList<Hero>) : UseCase.ResponseValue {
        fun getHeroesList(): MutableList<Hero> = heroesList
    }
}
