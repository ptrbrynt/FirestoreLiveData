package com.ptrbrynt.firestorelivedata

import org.junit.Test

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue

/**
 * Tests the [TaskResult] class
 */
class TaskResultTest {

    /** Tests the success state **/
    @Test
    fun success() {
        val result = TaskResult.success("hello")
        assertEquals(result.status, TaskStatus.SUCCESS)
        assertEquals(result.data, "hello")
        assertEquals(result.exception, null)
    }

    /** Tests the failure state **/
    @Test
    fun failure() {
        val result = TaskResult.failure<String>(IllegalStateException())
        assertEquals(result.status, TaskStatus.FAILED)
        assertEquals(result.data, null)
        assertTrue(result.exception is IllegalStateException)
    }

    /** Tests the cancelled state **/
    @Test
    fun cancelled() {
        val result = TaskResult.cancelled<String>()
        assertEquals(result.status, TaskStatus.CANCELLED)
        assertEquals(result.data, null)
        assertEquals(result.exception, null)
    }

    /** Tests the running state **/
    @Test
    fun running() {
        val result = TaskResult.running<String>()
        assertEquals(result.status, TaskStatus.RUNNING)
        assertEquals(result.data, null)
        assertEquals(result.exception, null)
    }

}
