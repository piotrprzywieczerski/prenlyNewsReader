package com.prenly.newsreader.domain

sealed class RemoteResource<out R> {

    data class Success<out T>(val data: T) : RemoteResource<T>()
    data class Error(val exception: Throwable) : RemoteResource<Nothing>()
    object Loading : RemoteResource<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }
    }
}

/**
 * `true` if [RemoteResource] is of type [Success] & holds non-null [Success.data].
 */
val RemoteResource<*>.succeeded
    get() = this is RemoteResource.Success && data != null