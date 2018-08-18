package com.ptrbrynt.firestorelivedata

import androidx.lifecycle.Observer

/**
 * Utility interface providing easy callbacks for each possible state of a [FirestoreResource].
 *
 * Can be passed as an [Observer] in LiveData observations.
 */
interface ResourceObserver<T> : Observer<FirestoreResource<T>> {

    /**
     * Callback triggered when the [FirestoreResource]'s [Status] is [Status.SUCCESS].
     *
     * @param data The data retrieved from Firestore
     */
    fun onSuccess(data: T?)

    /**
     * Callback triggered when the [FirestoreResource]'s [Status] is [Status.ERROR].
     *
     * @param throwable A [Throwable] representing the error
     * @param [errorMessage] A human-readable error message to display to the user
     */
    fun onError(throwable: Throwable?, errorMessage: String?)

    /**
     * Callback triggered when the [FirestoreResource]'s [Status] is [Status.LOADING].
     */
    fun onLoading()

    /**
     * When the observable's value changes, look at the [Status] of the value and call the relevant callback method.
     */
    override fun onChanged(t: FirestoreResource<T>?) {
        when (t?.status) {
            Status.SUCCESS -> onSuccess(t.data)
            Status.ERROR -> onError(t.throwable, t.errorMessage)
            Status.LOADING -> onLoading()
        }
    }

}