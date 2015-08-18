package com.demo.buggyapp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainActivityTest {
    @Test
    public void testCalculateUsernameLength() {
        String username = "patrick";
        int usernameLength = new MainActivity().calculateUsernameLength(username);
        assertEquals(7, usernameLength);
    }
}
