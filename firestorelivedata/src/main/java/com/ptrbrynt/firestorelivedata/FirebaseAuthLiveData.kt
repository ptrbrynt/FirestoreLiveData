package com.ptrbrynt.firestorelivedata

import android.arch.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

internal class FirebaseAuthLiveData(private val firebaseAuth: FirebaseAuth): LiveData<FirebaseUser>() {

    override fun onActive() {
        super.onActive()
        firebaseAuth.addAuthStateListener { postValue(it.currentUser) }
    }

}