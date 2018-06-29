package com.ptrbrynt.firestorelivedata

import com.google.firebase.firestore.Exclude

abstract class FirestoreModel {
    @get:Exclude
    var id: String? = null
}