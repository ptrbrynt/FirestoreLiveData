package com.ptrbrynt.firestorelivedata

import android.arch.lifecycle.Observer

interface ResourceObserver<T> : Observer<FirestoreResource<T>> {

    fun onSuccess(data: T?)

    fun onError(throwable: Throwable?)

    fun onLoading()

    override fun onChanged(t: FirestoreResource<T>?) {
        when (t?.status) {
            Status.SUCCESS -> onSuccess(t.data)
            Status.ERROR -> onError(t.throwable)
            Status.LOADING -> onLoading()
        }
    }

}