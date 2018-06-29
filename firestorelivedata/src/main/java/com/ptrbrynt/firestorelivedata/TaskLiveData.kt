package com.ptrbrynt.firestorelivedata

import android.arch.lifecycle.LiveData
import com.google.android.gms.tasks.Task

class TaskLiveData<T>(private val task: Task<T>) : LiveData<TaskResult<T>>() {

    override fun onActive() {
        super.onActive()
        postValue(TaskResult.running())
        task.addOnCompleteListener {
            when {
                it.exception != null -> postValue(TaskResult.failure(it.exception))
                it.isCanceled -> postValue(TaskResult.cancelled())
                it.isSuccessful -> postValue(TaskResult.success(it.result))
            }
        }
    }

}