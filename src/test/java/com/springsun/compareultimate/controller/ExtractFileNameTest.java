package com.springsun.compareultimate.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import static org.junit.Assert.*;

public class ExtractFileNameTest {
    Part part0;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getNameFromPartTest1() {
        part0 = new Part() {
            @Override
            public InputStream getInputStream() throws IOException {
                return null;
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getSubmittedFileName() {
                return null;
            }

            @Override
            public long getSize() {
                return 0;
            }

            @Override
            public void write(String s) throws IOException {

            }

            @Override
            public void delete() throws IOException {

            }

            @Override
            public String getHeader(String s) {
                return "some stuff; filename = SomeNameOfTheFile.jpg ; some other stuff";
            }

            @Override
            public Collection<String> getHeaders(String s) {
                return null;
            }

            @Override
            public Collection<String> getHeaderNames() {
                return null;
            }
        };
        String result = ExtractFileName.getNameFromPart(part0);
        assertEquals("SomeNameOfTheFile.jpg", result);
    }

    @Test
    public void getNameFromPartTest2() {
        part0 = new Part() {
            @Override
            public InputStream getInputStream() throws IOException {
                return null;
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getSubmittedFileName() {
                return null;
            }

            @Override
            public long getSize() {
                return 0;
            }

            @Override
            public void write(String s) throws IOException {

            }

            @Override
            public void delete() throws IOException {

            }

            @Override
            public String getHeader(String s) {
                return "some stuff; some second stuff ; some other stuff";
            }

            @Override
            public Collection<String> getHeaders(String s) {
                return null;
            }

            @Override
            public Collection<String> getHeaderNames() {
                return null;
            }
        };
        String result = ExtractFileName.getNameFromPart(part0);
        assertEquals("", result);
    }
}