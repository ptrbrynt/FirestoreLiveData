package com.ptrbrynt.firestorelivedata

/**
 * Represents a Task executed on the Firestore database, and includes the current [TaskStatus], any data returned, and any errors thrown.
 *
 * @property status The current [TaskStatus] of the Task.
 * @property data If [status] is [TaskStatus.SUCCESS], [data] represents the data returned by the task.
 * @property exception If [status] is [TaskStatus.FAILED], [exception] is the error returned by Firestore.
 */
class TaskResult<T> private constructor(val status: TaskStatus, val data: T?, val exception: Exception?) {

    companion object {
        /**
         * Returns a [TaskResult] with [TaskStatus.SUCCESS], and accompanying data
         *
         * @param data The data returned by the task
         */
        fun <T> success(data: T) = TaskResult<T>(TaskStatus.SUCCESS, data, null)

        /**
         * Returns a [TaskResult] with [TaskStatus.FAILED], and accompanying exception if present
         *
         * @param exception The error returned by Firestore
         */
        fun <T> failure(exception: Exception?) = TaskResult<T>(TaskStatus.FAILED, null, exception)

        /**
         * Returns a [TaskResult] with [TaskStatus.CANCELLED]
         */
        fun <T> cancelled() = TaskResult<T>(TaskStatus.CANCELLED, null, null)

        /**
         * Returns a [TaskResult] with [TaskStatus.RUNNING]
         */
        fun <T> running() = TaskResult<T>(TaskStatus.RUNNING, null, null)
    }

}