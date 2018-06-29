package com.ptrbrynt.firestorelivedata

import android.arch.lifecycle.LiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.Query

inline fun <reified T : FirestoreModel> Query.asLiveData(): QueryLiveData<T> {
    return QueryLiveData(T::class.java, this)
}

inline fun <reified T : FirestoreModel> DocumentReference.asLiveData(): DocumentLiveData<T> {
    return DocumentLiveData(T::class.java, this)
}

inline fun <reified T : FirestoreModel> CollectionReference.asLiveData(): CollectionLiveData<T> {
    return CollectionLiveData(T::class.java, this)
}

fun <T> Task<T>.asLiveData(): TaskLiveData<T> = TaskLiveData(this)

val FirebaseAuth.currentUserLiveData: LiveData<FirebaseUser>
    get() {
        return object : LiveData<FirebaseUser>() {
            override fun onActive() {
                this@currentUserLiveData.addAuthStateListener { postValue(it.currentUser) }
            }
        }
    }