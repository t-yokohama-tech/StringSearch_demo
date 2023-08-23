package com.example.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Path;

public interface ReadFile {
    String read(BufferedReader bufferedReader)throws IOException;
}
