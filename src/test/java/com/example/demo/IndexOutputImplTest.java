package com.example.demo;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class IndexOutputImplTest {

    @SuppressWarnings("FieldCanBeLocal")
    private final List<Integer> idxList = Arrays.asList(4,29);
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
            target.output(idxList);
            System.out.flush();
            var actual = byteArrayOutputStream.toString();
            var expected = "4文字目\n29文字目\n";
            assertThat(actual, is(expected));
            System.setOut(defaultPrintStream);
        }
    }
}

