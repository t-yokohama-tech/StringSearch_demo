package com.example.demo;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class StringSearchTest {

    @SuppressWarnings("FieldCanBeLocal")
    private final String SearchStr = "紳士";
    private final List<Integer> idxList = Arrays.asList(5, 30, 52, 77, 99, 124, 146, 171, 194, 219, 242, 267);
    private final List<Integer> idxList1 = Arrays.asList(5, 30, 52, 77, 99);
    private final List<Integer> idxList2 = Arrays.asList(5, 30, 52, 77, 99, 124, 146, 171, 194);
    private final List<Integer> idxList3 = Arrays.asList(5, 30, 52, 77, 99, 124, 146, 171, 194, 219, 242, 267);

    private final SearchIndex searchIndex = mock(SearchIndex.class);
    {
        doReturn(idxList1).doReturn(idxList2).doReturn(idxList3).when(searchIndex).search(any(), any(), anyInt(), any());
    }

    String stringA_1 = "1はじめの紳士は、すこし顔いろを悪くして、じっと、もひとりの紳士の、顔つきを見ながら云いました。はじめの紳士は、すこし顔いろを悪くして、じっと、もひとりの紳士の、顔つきを見ながら云いました。はじめの紳";
    String stringA_2 = "士は、すこし顔いろを悪くして、じっと、もひとりの紳士の、顔つきを見ながら云いました。はじめの紳士は、すこし顔いろを悪くして、じっと、もひとりの紳士の、顔つきを見ながら云いました。\n2はじめの紳士は、す";
    String stringA_3 = "こし顔いろを悪くして、じっと、もひとりの紳士の、顔つきを見ながら云いました。はじめの紳士は、すこし顔いろを悪くして、じっと、もひとりの紳士の、顔つきを見ながら云いました。\n3はじめの紳士は、すこし顔い";

    private final ReadFile readFile = mock(ReadFile.class);
    {
        try {
            doReturn(stringA_1)
                    .doReturn(stringA_2)
                    .doReturn(stringA_3)
                    .doReturn(null)
                    .when(readFile).read(any());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final IndexOutput indexOutput = mock(IndexOutput.class);
    {
        doAnswer((i) -> {
            System.out.print("5文字目\n30文字目\n52文字目\n77文字目\n99文字目\n124文字目\n146文字目\n171文字目\n194文字目\n219文字目\n242文字目\n267文字目\n293文字目\n");
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
    void returnIndex() throws Exception {
        setUpStreams();
        target.run(SearchStr);
        System.out.flush();
        var actual = byteArrayOutputStream.toString();
        var expected = "5文字目\n30文字目\n52文字目\n77文字目\n99文字目\n124文字目\n146文字目\n171文字目\n194文字目\n219文字目\n242文字目\n267文字目\n293文字目\n";
        assertThat(actual, is(expected));
        System.setOut(defaultPrintStream);
        
       // verify(readFile).read(bufferedReader);
        verify(indexOutput).output(idxList);
        verify(searchIndex).search(SearchStr,stringA_1,0,Arrays.asList());
        verify(searchIndex).search(SearchStr,stringA_2,100,Arrays.asList(5,30,52,77,99));
        verify(searchIndex).search(SearchStr,stringA_3,200,Arrays.asList(5,30,52,77,99,124,146,171,194));

    }
}
