package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class ReadFileImplTest {
    private final ReadFile target = new ReadFileImpl();
    private final String path = "data/chumonno_oi_ryoriten.utf8.txt";
    private final File file = new File(path);
    @Nested
    class readFile {
        @Test
        void returnReadFile() throws IOException {


            try (MockedStatic<FileReader> mockedFileReader = mockStatic(FileReader.class)) {

                var fileReader = new FileReader(file);
                mockedFileReader.when(() ->target.read(file)).thenReturn(fileReader);
                //var expected = new FileReader(file);
               // doReturn(mockedFileReader).when(fileReader);
                var expected = fileReader;
                var result = target.read(file);
                assertEquals(expected, result);
            }
        }
    }
}