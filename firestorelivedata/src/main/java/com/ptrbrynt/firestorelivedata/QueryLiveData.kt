package com.ptrbrynt.firestorelivedata

import android.arch.lifecycle.LiveData
import android.util.Log
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query

/**
 * An observable [LiveData] representing the current state of the data from a [Query].
 *
 * @param T The [FirestoreModel] to convert the resulting Firestore documents into
 * @param modelClass The class representing [T]
 * @param query The [Query] to provide the [QueryLiveData] for.
 *
 * @see [Query.asLiveData]
 * @see [FirestoreResource]
 * @see [FirestoreModel]
 */
class QueryLiveData<T : FirestoreModel>(private val modelClass: Class<T>, private val query: Query) : LiveData<FirestoreResource<List<T>>>() {

    /**
     * When the instance becomes active, initially post a [Status.LOADING] value.
     * Then, call [Query.addSnapshotListener] to listen for data changes, and post new values to the [QueryLiveData] instance appropriately.
     */
    override fun onActive() {
        super.onActive()
        postValue(FirestoreResource.loading())
        query.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
            if (firebaseFirestoreException != null) {
                postValue(FirestoreResource.error(firebaseFirestoreException))
                Log.w("QueryLiveData", firebaseFirestoreException.localizedMessage)
                firebaseFirestoreException.printStackTrace()
            } else {
                val documents = querySnapshot?.documents.orEmpty()
                val models = documents.mapNotNull { doc -> doc.toObject(modelClass)?.apply { id = doc.id } }
                postValue(FirestoreResource.success(models))
            }
        }
    }

}