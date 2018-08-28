package com.serhii.redditto.core.interactor

import com.serhii.redditto.data.remote.Result
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import kotlin.coroutines.experimental.CoroutineContext

abstract class UseCase<in P, out T> where T : Any {
    private val background: CoroutineContext = CommonPool
    private val ui: CoroutineContext = UI

    abstract suspend fun executeOnBackground(params: P): Result<T>

    fun execute(params: P, onResult: (Result<T>) -> Unit) {
        val job = async(background) { executeOnBackground(params) }
        launch(ui) { onResult(job.await()) }
    }
}