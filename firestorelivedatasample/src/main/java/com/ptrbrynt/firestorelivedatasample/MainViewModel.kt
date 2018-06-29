package com.ptrbrynt.firestorelivedatasample

import android.arch.lifecycle.ViewModel
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.ptrbrynt.firestorelivedata.FirestoreLiveData
import com.ptrbrynt.firestorelivedata.asLiveData

class MainViewModel : ViewModel() {

    private val firestore = FirebaseFirestore.getInstance()
    private val booksCollection = firestore.collection("books")

    val books: FirestoreLiveData<List<Book>> = booksCollection.asLiveData()

    val booksQuery = booksCollection.orderBy("published", Query.Direction.ASCENDING).asLiveData<Book>()

    fun add() {
        booksCollection.add(Book(
                "Harry Potter and the Order of the Phoenix",
                "J.K. Rowling",
                Timestamp.now()
        ))
    }
}