package com.springsun.compareultimate.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ComparePixelsTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void areTheyDifferentTest1() {
        boolean result;
        result = ComparePixels.areTheyDifferent(new int[]{10, 10, 10, 0}, new int[]{10, 30, 10, 0});
        assertEquals(false, result);
    }

    @Test
    public void areTheyDifferentTest2() {
        boolean result;
        result = ComparePixels.areTheyDifferent(new int[]{10, 10, 10, 0}, new int[]{10, 40, 10, 0});
        assertEquals(true, result);
    }
}