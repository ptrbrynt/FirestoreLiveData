package com.ptrbrynt.firestorelivedata

import android.arch.lifecycle.LiveData
import com.google.firebase.firestore.CollectionReference

class CollectionLiveData<T : FirestoreModel>(private val modelClass: Class<T>, private val collectionReference: CollectionReference) : LiveData<FirestoreResource<List<T>>>() {

    override fun onActive() {
        super.onActive()
        postValue(FirestoreResource.loading())
        collectionReference.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
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