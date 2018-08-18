package com.ptrbrynt.firestorelivedata

import androidx.lifecycle.LiveData
import android.util.Log
import com.google.firebase.firestore.DocumentReference

/**
 * An observable [LiveData] representing the current state of the data at a [DocumentReference].
 *
 * @param T The [FirestoreModel] to convert the resulting Firestore document into
 * @param modelClass The class representing [T]
 * @param reference The [DocumentReference] to provide the [DocumentLiveData] for.
 *
 * @see [DocumentReference.asLiveData]
 * @see [FirestoreResource]
 * @see [FirestoreModel]
 */
class DocumentLiveData<T: FirestoreModel>(private val modelClass: Class<T>, private val reference: DocumentReference): LiveData<FirestoreResource<T>>() {

    /**
     * When the instance becomes active, initially post a [Status.LOADING] value.
     * Then, call [DocumentReference.addSnapshotListener] to listen for data changes, and post new values to the [DocumentLiveData] instance appropriately.
     */
    override fun onActive() {
        super.onActive()
        postValue(FirestoreResource.loading())
        reference.addSnapshotListener { documentSnapshot, firebaseFirestoreException ->
            if (firebaseFirestoreException != null) {
                postValue(FirestoreResource.error(firebaseFirestoreException))
                Log.w("DocumentLiveData", firebaseFirestoreException.localizedMessage)
                firebaseFirestoreException.printStackTrace()
            } else {
                postValue(FirestoreResource.success(documentSnapshot?.toObject(modelClass)?.apply { id = documentSnapshot.id }))
            }
        }
    }

    /**
     * Replaces the current object stored at the [reference] with a new value.
     *
     * This is returned as a [TaskLiveData], so the state of the operation can be observed if desired. However, it is not required to be observed in order to execute the operation.
     *
     * @param item The object with which to replace the current data
     * @return A [TaskLiveData] representing the state of the operation, as a [TaskResult].
     */
    fun set(item: T): TaskLiveData<Void> = reference.set(item).asLiveData()

    /**
     * Sets the values of multiple fields in the document.
     *
     * This is returned as a [TaskLiveData], so the state of the operation can be observed if desired. However, it is not required to be observed in order to execute the operation.
     *
     * @param fields The key-value pairs representing the field names and values to update
     * @return A [TaskLiveData] representing the state of the operation, as a [TaskResult].
     */
    fun update(fields: Map<String, Any>): TaskLiveData<Void> = reference.update(fields).asLiveData()

    /**
     * Sets the value of a single field in the document.
     *
     * This is returned as a [TaskLiveData], so the state of the operation can be observed if desired. However, it is not required to be observed in order to execute the operation.
     *
     * @param field The name of the field in the document
     * @param value The new value of the field
     * @return A [TaskLiveData] representing the state of the operation, as a [TaskResult].
     */
    fun update(field: String, value: Any): TaskLiveData<Void> = reference.update(field, value).asLiveData()

    /**
     * Deletes the document from the database.
     *
     * This is returned as a [TaskLiveData], so the state of the operation can be observed if desired. However, it is not required to be observed in order to execute the operation.
     *
     * @return A [TaskLiveData] representing the state of the operation, as a [TaskResult].
     */
    fun delete(): TaskLiveData<Void> = reference.delete().asLiveData()

}