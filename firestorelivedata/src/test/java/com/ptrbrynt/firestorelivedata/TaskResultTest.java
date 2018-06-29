package com.ptrbrynt.firestorelivedata;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TaskResultTest {

    @Test
    public void success() {
        TaskResult<String> result = TaskResult.Companion.success("hello");
        assertEquals(result.getStatus(), TaskStatus.SUCCESS);
        assertEquals(result.getData(), "hello");
        assertEquals(result.getException(), null);
    }

    @Test
    public void failure() {
        TaskResult<String> result = TaskResult.Companion.failure(new IllegalStateException());
        assertEquals(result.getStatus(), TaskStatus.FAILED);
        assertEquals(result.getData(), null);
        assertTrue(result.getException() instanceof IllegalStateException);
    }

    @Test
    public void cancelled() {
        TaskResult<String> result = TaskResult.Companion.cancelled();
        assertEquals(result.getStatus(), TaskStatus.CANCELLED);
        assertEquals(result.getData(), null);
        assertEquals(result.getException(), null);
    }

    @Test
    public void running() {
        TaskResult<String> result = TaskResult.Companion.running();
        assertEquals(result.getStatus(), TaskStatus.RUNNING);
        assertEquals(result.getData(), null);
        assertEquals(result.getException(), null);
    }

}
