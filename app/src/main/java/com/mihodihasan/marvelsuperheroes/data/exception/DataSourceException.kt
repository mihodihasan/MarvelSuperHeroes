package com.mihodihasan.marvelsuperheroes.data.exception

open class DataSourceException(message: String? = null) : Exception(message)

class RemoteDataNotFoundException : DataSourceException("Data not found in remote data source")