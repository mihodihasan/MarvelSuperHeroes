package com.mihodihasan.marvelsuperheroes.common

abstract class UseCase<Q : UseCase.RequestValues, P : UseCase.ResponseValue> {
    lateinit var mRequestValues: Q

    lateinit var mUseCaseCallback: UseCaseCallback<P>

    fun run() {
        executeUseCase(mRequestValues)
    }

    protected abstract fun executeUseCase(requestValues: Q)

    interface RequestValues

    interface ResponseValue

    interface UseCaseCallback<R> {
        fun onSuccess(response: R)
        fun onError(errMsg:String)
    }
}