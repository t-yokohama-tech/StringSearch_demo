package com.example.demo;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class IndexOutputTest {

    @SuppressWarnings("FieldCanBeLocal")
    private final String searchResult = "4文字目\n29文字目\n";
    private PrintStream defaultPrintStream;
    private ByteArrayOutputStream byteArrayOutputStream;

    public void setUpStreams() {
        defaultPrintStream = System.out;
        byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(new BufferedOutputStream(byteArrayOutputStream)));
    }

    private final IndexOutput target = new IndexOutputImpl();

    @Nested
    class output {
        @Test
        void returnOutputResult() {
            setUpStreams();
            target.output(searchResult);
            System.out.flush();
            var actual = byteArrayOutputStream.toString();
            var expected = "4文字目\n29文字目\n";
            assertThat(actual, is(expected));
            System.setOut(defaultPrintStream);
        }
    }
}

