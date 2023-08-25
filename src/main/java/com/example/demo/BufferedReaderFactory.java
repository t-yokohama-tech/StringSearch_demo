package com.example.demo;

import java.io.BufferedReader;
import java.io.Reader;

public interface BufferedReaderFactory {
    BufferedReader create(Reader reader);
}
