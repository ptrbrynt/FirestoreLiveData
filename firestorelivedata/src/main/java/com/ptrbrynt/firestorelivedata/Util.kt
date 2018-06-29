package com.ptrbrynt.firestorelivedata

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Query

/**
 * Transforms a [Query] into a [QueryLiveData], which can be used to retrieve data and observe the result
 *
 * @receiver The [Query] to transform
 * @param T The [FirestoreModel] class to convert the result of the query into
 * @return A [QueryLiveData] representing the current state of the [Query], as a [FirestoreResource]
 * @see [DocumentReference.asLiveData]
 * @see [CollectionReference.asLiveData]
 */
inline fun <reified T : FirestoreModel> Query.asLiveData(): QueryLiveData<T> {
    return QueryLiveData(T::class.java, this)
}

/**
 * Transforms a [DocumentReference] into a [DocumentLiveData], which can be used to retrieve data and observe the result
 *
 * @receiver The [DocumentReference] to transform
 * @param T The [FirestoreModel] class to convert the result of the document reference into
 * @return A [DocumentLiveData] representing the current state of the [DocumentReference], as a [FirestoreResource]
 * @see [Query.asLiveData]
 * @see [CollectionReference.asLiveData]
 */
inline fun <reified T : FirestoreModel> DocumentReference.asLiveData(): DocumentLiveData<T> {
    return DocumentLiveData(T::class.java, this)
}

/**
 * Transforms a [CollectionReference] into a [CollectionLiveData], which can be used to retrieve data and observe the result
 *
 * @receiver The [CollectionReference] to transform
 * @param T The [FirestoreModel] class to convert the result of the collection reference into
 * @return A [CollectionLiveData] representing the current state of the [CollectionReference], as a [FirestoreResource]
 * @see [DocumentReference.asLiveData]
 * @see [Query.asLiveData]
 */
inline fun <reified T : FirestoreModel> CollectionReference.asLiveData(): CollectionLiveData<T> {
    return CollectionLiveData(T::class.java, this)
}

/**
 * Transforms a [Task] into a [TaskLiveData], which can be used to observe [Task] state changes
 *
 * @receiver The [Task] to transform
 * @param T The return type of the [Task]
 * @return A [TaskLiveData] representing the [Task] state as a [TaskResult]
 */
fun <T> Task<T>.asLiveData(): TaskLiveData<T> = TaskLiveData(this)

/**
 * An observable [LiveData] representing the currently signed-in user, as a [FirebaseUser]
 */
val FirebaseAuth.currentUserLiveData: LiveData<FirebaseUser>
    get() = FirebaseAuthLiveData(this)

/**
 * Shortcut method to transform a [CollectionReference] into a [CollectionLiveData], and observe it.
 */
inline fun <reified T : FirestoreModel> CollectionReference.observe(lifecycleOwner: LifecycleOwner, observer: Observer<FirestoreResource<List<T>>>) {
    this.asLiveData<T>().observe(lifecycleOwner, observer)
}

/**
 * Shortcut method to transform a [Query] into a [QueryLiveData], and observe it.
 */
inline fun <reified T : FirestoreModel> Query.observe(lifecycleOwner: LifecycleOwner, observer: Observer<FirestoreResource<List<T>>>) {
    this.asLiveData<T>().observe(lifecycleOwner, observer)
}

/**
 * Shortcut method to transform a [DocumentReference] into a [DocumentLiveData], and observe it.
 */
inline fun <reified T : FirestoreModel> DocumentReference.observe(lifecycleOwner: LifecycleOwner, observer: Observer<FirestoreResource<T>>) {
    this.asLiveData<T>().observe(lifecycleOwner, observer)
}