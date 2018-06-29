package com.ptrbrynt.firestorelivedata

import org.junit.Test

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue

class FirestoreResourceTest {

    @Test
    fun success() {
        val result = FirestoreResource.success("hello")
        assertEquals(result.status, Status.SUCCESS)
        assertEquals(result.data, "hello")
        assertEquals(result.throwable, null)
        assertEquals(result.errorMessage, null)
    }

    @Test
    fun error() {
        val result = FirestoreResource.error<String>(IllegalStateException("You've been played"))
        assertEquals(result.status, Status.ERROR)
        assertEquals(result.data, null)
        assertTrue(result.throwable is IllegalStateException)
        assertEquals(result.errorMessage, "You've been played")
    }

    @Test
    fun loading() {
        val result = FirestoreResource.loading<String>()
        assertEquals(result.status, Status.LOADING)
        assertEquals(result.data, null)
        assertEquals(result.throwable, null)
        assertEquals(result.errorMessage, null)
    }

}
