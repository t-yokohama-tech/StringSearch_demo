package com.example.demo;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class StringSearchTest {


    private final StringSearch target = new StringSearch();
    private final String SearchStr = "紳士";
    private final PrintStream defaultPrintStream = System.out;
    private final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    @Nested
    class main {
        @Test
        void returnIndex() throws IOException {
            Path p1 = Paths.get("");
            Path p2 = p1.toAbsolutePath();

            System.out.println(p2.toString());

            StringSearch.main(new String[]{SearchStr});

            System.out.flush();
            String result = byteArrayOutputStream.toString();

            String expected = "4文字目　29文字目";
            assertEquals(expected, result);
        }
    }
//    @Nested
//    class getFilepath {
//
//        @Test
//        void returnFilePath() throws IOException{
//            Path p1 = Paths.get("");
//            Path p2 = p1.toAbsolutePath();
//
//            System.out.println("Test  "+p2.toString());
//            StringSearch.main(new String[]{SearchStr});
////            assertEquals(,
////                    Files.readString(Paths.get("data/String_A_File.txt")));
//
//        }
//    }
}
