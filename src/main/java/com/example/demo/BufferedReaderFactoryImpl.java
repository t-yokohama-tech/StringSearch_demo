package com.example.demo;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.Reader;

@Component
public class BufferedReaderFactoryImpl implements BufferedReaderFactory {
    @Override
    public BufferedReader create(Reader reader){
        return new BufferedReader(reader);
    }
}
