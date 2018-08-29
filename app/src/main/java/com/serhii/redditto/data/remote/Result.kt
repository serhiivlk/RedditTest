package com.serhii.redditto.data.remote

sealed class Result<out T> {
    fun determine(onSuccess: (T) -> Unit, onFailure: (Throwable) -> Unit) =
            when (this) {
                is Success -> onSuccess(data)
                is Failure -> onFailure(error)
            }
}

data class Success<out T>(val data: T) : Result<T>()

data class Failure(val error: Throwable) : Result<Nothing>()