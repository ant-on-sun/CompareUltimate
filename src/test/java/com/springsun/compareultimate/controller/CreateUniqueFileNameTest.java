package com.springsun.compareultimate.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreateUniqueFileNameTest {
    String pathToSaveFile = System.getProperty("user.home");

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getNameTest1() {
        String result;
        result = CreateUniqueFileName.getName("initialFileName", pathToSaveFile);
        assertNotEquals("initialFileName", result);
    }
}