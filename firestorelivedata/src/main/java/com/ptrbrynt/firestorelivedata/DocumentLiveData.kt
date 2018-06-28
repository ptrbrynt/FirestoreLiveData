package com.ptrbrynt.firestorelivedata

import android.arch.lifecycle.LiveData
import com.google.firebase.firestore.DocumentReference

class DocumentLiveData<T: FirestoreModel>(private val modelClass: Class<T>, private val reference: DocumentReference): LiveData<FirestoreResource<T>>() {

    override fun onActive() {
        super.onActive()
        postValue(FirestoreResource.loading())
        reference.addSnapshotListener { documentSnapshot, firebaseFirestoreException ->
            if (firebaseFirestoreException != null) {
                postValue(FirestoreResource.error(firebaseFirestoreException))
            } else {
                postValue(FirestoreResource.success(documentSnapshot?.toObject(modelClass)?.apply { id = documentSnapshot.id }))
            }
        }
    }

}