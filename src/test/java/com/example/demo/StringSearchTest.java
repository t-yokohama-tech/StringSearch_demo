package com.example.demo;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class StringSearchTest {

    @SuppressWarnings("FieldCanBeLocal")
    private final String SearchStr = "紳士";
    private final String searchResult = "4文字目\n29文字目";
    private final SearchIndex searchIndex = mock(SearchIndex.class);
    {
        doReturn(searchResult).when(searchIndex).search(any(),any());
    }

    String stringA = "はじめの紳士は、すこし顔いろを悪くして、じっと、もひとりの紳士の、顔つきを見ながら云いました。";
    private final ReadFile readFile = mock(ReadFile.class);
    {
        try {
            doReturn(stringA).when(readFile).read(any());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final IndexOutput indexOutput = mock(IndexOutput.class);
    {
        doAnswer((i) -> {
            System.out.println(searchResult);
            return null;
        }).when(indexOutput).output(any());
    }

    private PrintStream defaultPrintStream;
    private ByteArrayOutputStream byteArrayOutputStream;
    private final StringSearch target =
            new StringSearch(readFile,indexOutput,searchIndex);

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
