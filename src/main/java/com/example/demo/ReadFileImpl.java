package com.example.demo;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class ReadFileImpl implements ReadFile{
    @Override
    public Reader read(File file) throws IOException{

        return new FileReader(file);

    }
}
