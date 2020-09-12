package com.mihodihasan.marvelsuperheroes.common

sealed class ResultData<out T : Any> {
    data class Success<out T : Any>(val data: T) : ResultData<T>()
    data class Error(val exception: String) : ResultData<Nothing>()
}