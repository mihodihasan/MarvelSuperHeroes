package com.mihodihasan.marvelsuperheroes.common

import android.os.Handler
import android.os.Looper
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class UseCaseThreadPoolScheduler @Inject constructor(): UseCaseScheduler {

    private val mHandler: Handler = Handler(Looper.getMainLooper())


    private val POOL_SIZE = 2

    private val MAX_POOL_SIZE = 4

    private val TIMEOUT = 30

    var mThreadPoolExecutor: ThreadPoolExecutor? = null

    fun UseCaseThreadPoolScheduler() {
        mThreadPoolExecutor = ThreadPoolExecutor(
            POOL_SIZE, MAX_POOL_SIZE,
            TIMEOUT.toLong(),
            TimeUnit.SECONDS, ArrayBlockingQueue(POOL_SIZE)
        )
    }

    override fun execute(runnable: Runnable) {
        mThreadPoolExecutor?.execute(runnable)
    }

    override fun <V : UseCase.ResponseValue> notifyResponse(
        response: V,
        useCaseCallback: UseCase.UseCaseCallback<V>
    ) {
        mHandler.post { useCaseCallback.onSuccess(response) }
    }

    override fun <V : UseCase.ResponseValue> onError(
        useCaseCallback: UseCase.UseCaseCallback<V>
    ) {
        mHandler.post { useCaseCallback.onError() }
    }
}