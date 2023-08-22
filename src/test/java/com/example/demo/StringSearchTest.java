package com.example.demo;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StringSearchTest {


    private final StringSearch target = new StringSearch();
    @SuppressWarnings("FieldCanBeLocal")
    private final String SearchStr = "紳士";
    private PrintStream defaultPrintStream;
    private ByteArrayOutputStream byteArrayOutputStream;

    public void setUpStreams() {
        defaultPrintStream = System.out;
        byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(new BufferedOutputStream(byteArrayOutputStream)));
    }

    @Test
    void returnIndex_useAssert() throws Exception {
        setUpStreams();
        target.run(SearchStr);
        System.out.flush();
        var actual = byteArrayOutputStream.toString();
        var expected = "4文字目\n29文字目\n";
        assertThat(actual, is(expected));
        System.setOut(defaultPrintStream);
    }
}
