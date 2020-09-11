package com.mihodihasan.marvelsuperheroes.main.usecase

import com.mihodihasan.marvelsuperheroes.common.UseCase
import com.mihodihasan.marvelsuperheroes.data.source.DataSource
import com.mihodihasan.marvelsuperheroes.data.source.Repository
import com.mihodihasan.marvelsuperheroes.main.model.ComicsResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetComicsList @Inject constructor(private val repository: Repository) :
    UseCase<GetComicsList.RequestValues, GetComicsList.ResponseValue>() {

    override fun executeUseCase(requestValues: RequestValues) {
        val job = Job()
        val coroutineScope = CoroutineScope(
            job + Dispatchers.Main
        )
        coroutineScope.launch {
            repository.getComics(
                requestValues.getHeroId(),
                requestValues.getPageNumber(),
                object : DataSource.LoadComicsCallback {
                    override fun onComicsLoaded(comics: MutableList<ComicsResult>) {
                        val responseValue = ResponseValue(comics)
                        mUseCaseCallback.onSuccess(responseValue)
                    }

                    override fun onDataNotAvailable() {
                        mUseCaseCallback.onError()
                    }
                })
        }
    }

    class RequestValues(private val heroId: Int, private val pageNo: Int) : UseCase.RequestValues {
        fun getPageNumber(): Int = pageNo
        fun getHeroId(): String = heroId.toString()
    }

    class ResponseValue(private val comicsList: MutableList<ComicsResult>) : UseCase.ResponseValue {
        fun getComicsList(): MutableList<ComicsResult> = comicsList
    }

}