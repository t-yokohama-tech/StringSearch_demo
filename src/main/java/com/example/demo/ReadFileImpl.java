package com.example.demo;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.BufferedReader;

@Component
public class ReadFileImpl implements ReadFile{

    @Override
    public String readLine(BufferedReader bufferedReader) throws IOException {
        return bufferedReader.readLine();
    }
}
