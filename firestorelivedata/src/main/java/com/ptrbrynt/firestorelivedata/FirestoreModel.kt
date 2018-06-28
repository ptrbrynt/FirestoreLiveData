package com.ptrbrynt.firestorelivedata

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
abstract class FirestoreModel{
    @Exclude
    var id: String? = null
}