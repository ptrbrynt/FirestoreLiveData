package com.ptrbrynt.firestorelivedatasample

import com.google.firebase.Timestamp
import com.ptrbrynt.firestorelivedata.FirestoreModel

data class Book(
        var title: String? = null,
        var author: String? = null,
        var published: Timestamp? = null
) : FirestoreModel()