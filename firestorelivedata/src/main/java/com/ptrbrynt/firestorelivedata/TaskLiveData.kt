package com.ptrbrynt.firestorelivedata

import androidx.lifecycle.LiveData
import com.google.android.gms.tasks.Task

/**
 * An observable [LiveData] representation of a [Task]'s status, as a [TaskResult].
 *
 * @param task The task to represent the status of
 */
class TaskLiveData<T>(private val task: Task<T>) : LiveData<TaskResult<T>>() {

    /**
     * When the instance becomes active, initially post a [TaskStatus.RUNNING] value.
     * Then, call [Task.addOnCompleteListener] to listen for status changes, and post new values to the [TaskLiveData] instance appropriately.
     */
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