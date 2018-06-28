package com.ptrbrynt.firestorelivedata

import android.arch.lifecycle.LiveData

abstract class FirestoreLiveData<T>: LiveData<FirestoreResource<T>>()