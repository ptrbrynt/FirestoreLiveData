package com.ptrbrynt.firestorelivedata

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties

/**
 * An abstract class from which all models used with this library should extend.
 *
 * Simply provides an [id] property, which is set by the library and represents the document key.
 *
 * @property id The document key
 */
@IgnoreExtraProperties
interface FirestoreModel {
    @get:Exclude
    var id: String?
}