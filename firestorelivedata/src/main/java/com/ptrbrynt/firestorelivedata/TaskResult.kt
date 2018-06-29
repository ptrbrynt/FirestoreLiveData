package com.ptrbrynt.firestorelivedata

class TaskResult<T> private constructor(val status: TaskStatus, val data: T?, val exception: Exception?) {

    companion object {
        fun <T> success(data: T) = TaskResult<T>(TaskStatus.SUCCESS, data, null)
        fun <T> failure(exception: Exception?) = TaskResult<T>(TaskStatus.FAILED, null, exception)
        fun <T> cancelled() = TaskResult<T>(TaskStatus.CANCELLED, null, null)
        fun <T> running() = TaskResult<T>(TaskStatus.RUNNING, null, null)
    }

}