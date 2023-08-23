package com.example.demo;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.BufferedReader;

@Component
public class ReadFileImpl implements ReadFile{
    @Override
    public String read(BufferedReader bufferedReader) throws IOException {
        char[] readArray = new char[100];
        int idxCount = bufferedReader.read(readArray,0,100);
        if(idxCount == -1){
            bufferedReader.close();
            return null;
        }
        return String.valueOf(readArray);
    }
}
