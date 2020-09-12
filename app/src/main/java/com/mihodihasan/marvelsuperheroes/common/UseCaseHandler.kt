package com.mihodihasan.marvelsuperheroes.common

import android.util.Log
import kotlinx.coroutines.*
import java.lang.Runnable
import javax.inject.Inject

class UseCaseHandler @Inject constructor(private val mUseCaseScheduler: UseCaseScheduler) {


    fun <T : UseCase.RequestValues, R : UseCase.ResponseValue> execute(
        useCase: UseCase<T, R>, values: T, callback: UseCase.UseCaseCallback<R>
    ) {
        useCase.mRequestValues=values
        useCase.mUseCaseCallback = UiCallbackWrapper(callback, this)

        // The network request might be handled in a different thread so make sure
        // Espresso knows
        // that the app is busy until the response is handled.
//        EspressoIdlingResource.increment() // App is busy until further notice

        mUseCaseScheduler.execute {
                useCase.run()
        }
    }

    fun <V : UseCase.ResponseValue> notifyResponse(
        response: V,
        useCaseCallback: UseCase.UseCaseCallback<V>
    ) {
        mUseCaseScheduler.notifyResponse(response, useCaseCallback)
    }

    private fun <V : UseCase.ResponseValue> notifyError(
        useCaseCallback: UseCase.UseCaseCallback<V>
    ) {
        mUseCaseScheduler.onError(useCaseCallback)
    }

    private class UiCallbackWrapper<V : UseCase.ResponseValue>(
        callback: UseCase.UseCaseCallback<V>,
        useCaseHandler: UseCaseHandler
    ) : UseCase.UseCaseCallback<V> {
        private val mCallback: UseCase.UseCaseCallback<V> = callback
        private val mUseCaseHandler: UseCaseHandler = useCaseHandler
        override fun onSuccess(response: V) {
            mUseCaseHandler.notifyResponse(response, mCallback)
        }

        override fun onError(errMsg:String) {
            mUseCaseHandler.notifyError(mCallback)
        }

    }


}