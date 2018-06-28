package com.ptrbrynt.firestorelivedata

class FirestoreResource<T> private constructor(val status: Status, val data: T?, val throwable: Throwable?) {

    val errorMessage: String?
        get() = throwable?.localizedMessage

    companion object {
        fun <T> loading() = FirestoreResource<T>(Status.LOADING, null, null)
        fun <T> success(data: T?) = FirestoreResource(Status.SUCCESS, data, null)
        fun <T> error(throwable: Throwable?) = FirestoreResource<T>(Status.ERROR, null, throwable)
    }

}