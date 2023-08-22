package com.example.demo;

import java.io.IOException;
import java.nio.file.Path;

public interface ReadFile {
    String read(Path path)throws IOException;
}
