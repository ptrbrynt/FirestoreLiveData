package com.ptrbrynt.firestorelivedata

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Tests the [FirestoreResource] class
 */
class FirestoreResourceTest {

    /** Tests the success state of [FirestoreResource], ensuring that data is passed correctly and there is no error. **/
    @Test
    fun success() {
        val result = FirestoreResource.success("hello")
        assertEquals(result.status, Status.SUCCESS)
        assertEquals(result.data, "hello")
        assertEquals(result.throwable, null)
        assertEquals(result.errorMessage, null)
    }

    /** Tests the error state of [FirestoreResource], ensuring there is no data and that the error is passed successfully. **/
    @Test
    fun error() {
        val result = FirestoreResource.error<String>(IllegalStateException("You've been played"))
        assertEquals(result.status, Status.ERROR)
        assertEquals(result.data, null)
        assertTrue(result.throwable is IllegalStateException)
        assertEquals(result.errorMessage, "You've been played")
    }

    /** Tests the loading state of [FirestoreResource], ensuring there is no data or error. **/
    @Test
    fun loading() {
        val result = FirestoreResource.loading<String>()
        assertEquals(result.status, Status.LOADING)
        assertEquals(result.data, null)
        assertEquals(result.throwable, null)
        assertEquals(result.errorMessage, null)
    }

}
