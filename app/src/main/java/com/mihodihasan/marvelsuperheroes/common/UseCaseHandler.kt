package com.mihodihasan.marvelsuperheroes.common

import javax.inject.Inject

class UseCaseHandler @Inject constructor(private val mUseCaseScheduler: UseCaseScheduler) {
    companion object {
        private var INSTANCE : UseCaseHandler?=null
        get() {
            if (field ==null) INSTANCE = UseCaseHandler(UseCaseThreadPoolScheduler())
            return field
        }
    }

    fun <T : UseCase.RequestValues, R : UseCase.ResponseValue> execute(
        useCase: UseCase<T, R>, values: T, callback: UseCase.UseCaseCallback<R>
    ) {
        useCase.mRequestValues=values
        useCase.mUseCaseCallback = UiCallbackWrapper(callback, this)

        // The network request might be handled in a different thread so make sure
        // Espresso knows
        // that the app is busy until the response is handled.
//        EspressoIdlingResource.increment() // App is busy until further notice
        mUseCaseScheduler.execute(Runnable {
            useCase.run()
            // This callback may be called twice, once for the cache and once for loading
            // the data from the server API, so we check before decrementing, otherwise
            // it throws "Counter has been corrupted!" exception.
            /*if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                EspressoIdlingResource.decrement() // Set app as idle.
            }*/
        })
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

        override fun onError() {
            mUseCaseHandler.notifyError(mCallback)
        }

    }


}