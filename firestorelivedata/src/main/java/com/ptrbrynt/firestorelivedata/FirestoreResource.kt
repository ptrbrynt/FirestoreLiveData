package com.ptrbrynt.firestorelivedata

/**
 * Represents a piece of data retrieved from Cloud Firestore, and includes its current [Status].
 *
 * Also includes information about errors if one exists.
 *
 * @property status The current [Status] of the data retrieval operation
 * @property data If [status] is [Status.SUCCESS], [data] represents the result of the retrieval operation.
 * @property throwable If [status] is [Status.ERROR], [throwable] is the error returned by the Firebase SDK, which can be used to display an error message to the user
 * @property errorMessage If [status] is [Status.ERROR] and [throwable] is not null, [errorMessage] will be a human-readable error message for display to the user
 */
class FirestoreResource<T> private constructor(val status: Status, val data: T?, val throwable: Throwable?) {

    val errorMessage: String?
        get() = throwable?.localizedMessage

    companion object {
        /**
         * Returns a [FirestoreResource] with [Status.LOADING]
         */
        fun <T> loading() = FirestoreResource<T>(Status.LOADING, null, null)

        /**
         * Returns a [FirestoreResource] with [Status.SUCCESS], and optional data
         */
        fun <T> success(data: T?) = FirestoreResource(Status.SUCCESS, data, null)

        /**
         * Returns a [FirestoreResource] with [Status.ERROR], and an optional throwable representing the error details
         */
        fun <T> error(throwable: Throwable?) = FirestoreResource<T>(Status.ERROR, null, throwable)
    }

}