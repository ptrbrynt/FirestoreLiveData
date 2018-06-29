package com.ptrbrynt.firestorelivedata;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FirestoreResourceTest {

    @Test
    public void success() {
        FirestoreResource<String> result = FirestoreResource.Companion.success("hello");
        assertEquals(result.getStatus(), Status.SUCCESS);
        assertEquals(result.getData(), "hello");
        assertEquals(result.getThrowable(), null);
        assertEquals(result.getErrorMessage(), null);
    }

    @Test
    public void error() {
        FirestoreResource<String> result = FirestoreResource.Companion.error(new IllegalStateException("You've been played"));
        assertEquals(result.getStatus(), Status.ERROR);
        assertEquals(result.getData(), null);
        assertTrue(result.getThrowable() instanceof IllegalStateException);
        assertEquals(result.getErrorMessage(), "You've been played");
    }

    @Test
    public void loading() {
        FirestoreResource<String> result = FirestoreResource.Companion.loading();
        assertEquals(result.getStatus(), Status.LOADING);
        assertEquals(result.getData(), null);
        assertEquals(result.getThrowable(), null);
        assertEquals(result.getErrorMessage(), null);
    }

}
