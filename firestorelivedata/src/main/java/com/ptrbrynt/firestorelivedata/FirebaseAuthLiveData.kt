package com.ptrbrynt.firestorelivedata

import android.arch.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

/**
 * An observable [LiveData] representing the currently logged in user. The value will be `null` if the user is signed out.
 *
 * @param firebaseAuth The instance of [FirebaseAuth] to use to check logged in status
 * @return A [LiveData] representing the currently logged in user, as a [FirebaseUser]
 */
class FirebaseAuthLiveData(private val firebaseAuth: FirebaseAuth): LiveData<FirebaseUser>() {

    override fun onActive() {
        super.onActive()
        firebaseAuth.addAuthStateListener { postValue(it.currentUser) }
    }

}