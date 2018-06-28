package com.ptrbrynt.firestorelivedata

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Query

inline fun <reified T: FirestoreModel> Query.asLiveData(): LiveData<FirestoreResource<List<T>>> {
    return QueryLiveData(T::class.java, this)
}

inline fun <reified T: FirestoreModel> DocumentReference.asLiveData(): LiveData<FirestoreResource<T>> {
    return DocumentLiveData(T::class.java, this)
}

inline fun <reified T: FirestoreModel> CollectionReference.asLiveData(): LiveData<FirestoreResource<List<T>>> {
    return CollectionLiveData(T::class.java, this)
}

fun <T: FirestoreModel> LiveData<FirestoreResource<T>>.observeResource(lifecycleOwner: LifecycleOwner, observer: ResourceObserver<T>) {
    this.observe(lifecycleOwner, observer)
}