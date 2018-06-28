package com.ptrbrynt.firestorelivedata

import com.google.firebase.firestore.Query

class QueryLiveData<T : FirestoreModel>(private val modelClass: Class<T>, private val query: Query) : FirestoreLiveData<List<T>>() {

    override fun onActive() {
        super.onActive()
        postValue(FirestoreResource.loading())
        query.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
            if (firebaseFirestoreException != null) {
                postValue(FirestoreResource.error(firebaseFirestoreException))
            } else {
                val documents = querySnapshot?.documents.orEmpty()
                val models = documents.mapNotNull { doc -> doc.toObject(modelClass)?.apply { id = doc.id } }
                postValue(FirestoreResource.success(models))
            }
        }
    }

}