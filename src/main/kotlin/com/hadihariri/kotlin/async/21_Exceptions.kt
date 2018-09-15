package com.hadihariri.kotlin.async

import kotlinx.coroutines.experimental.*


fun main(args: Array<String>) = runBlocking {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("Caught $exception")
    }
    val job = launch(handler) {
        throw AssertionError()
    }
    val deferred = async(handler) {
        throw ArithmeticException()
    }
    deferred.await()
    joinAll(job, deferred)
}