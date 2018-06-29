package com.ptrbrynt.firestorelivedata

import android.arch.lifecycle.Observer

interface ResourceObserver<T> : Observer<FirestoreResource<T>> {

    fun onSuccess(data: T?)

    fun onError(throwable: Throwable?, errorMessage: String?)

    fun onLoading()

    override fun onChanged(t: FirestoreResource<T>?) {
        when (t?.status) {
            Status.SUCCESS -> onSuccess(t.data)
            Status.ERROR -> onError(t.throwable, t.errorMessage)
            Status.LOADING -> onLoading()
        }
    }

}