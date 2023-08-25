package com.example.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;

public interface ReadFile {
    Reader read(File file) throws IOException;
}
