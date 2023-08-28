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
    private final String keyword = "紳士";
    private final List<Integer> idxList = Arrays.asList(5, 30, 52, 77, 99, 124, 146, 171, 194, 219, 242, 267);

    private final String path = "data/chumonno_oi_ryoriten.utf8.txt";

    private final File file = new File(path);

    private final FileReader fileReader = mock(FileReader.class);
    private final ReadFile readFile = mock(ReadFile.class);
    {
        try {
            doReturn(fileReader).when(readFile).read(any());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final BufferedReader reader = mock(BufferedReader.class);
     private final BufferedReaderFactory bufferedReaderFactory = mock(BufferedReaderFactory.class);
    {
        doReturn(reader).when(bufferedReaderFactory).create(any());
    }


    private final SearchIndex searchIndex = mock(SearchIndex.class);
    {
        try {
            doReturn(idxList).when(searchIndex).search(any(), any());
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
            new StringSearch(readFile, indexOutput, searchIndex,bufferedReaderFactory);


    public void setUpStreams() {
        defaultPrintStream = System.out;
        byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(new BufferedOutputStream(byteArrayOutputStream)));
    }

    @Test
    void returnIndex() throws Exception {
        setUpStreams();
        target.run(keyword);
        System.out.flush();
        var actual = byteArrayOutputStream.toString();
        var expected = "5文字目\n30文字目\n52文字目\n77文字目\n99文字目\n124文字目\n146文字目\n171文字目\n194文字目\n219文字目\n242文字目\n267文字目\n293文字目\n";
        assertThat(actual, is(expected));
        System.setOut(defaultPrintStream);

        verify(readFile).read(file);
        verify(indexOutput).output(idxList);
        verify(searchIndex).search(reader, keyword);

    }
}
