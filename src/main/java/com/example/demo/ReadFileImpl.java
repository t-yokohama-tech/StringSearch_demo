package com.example.demo;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class ReadFileImpl implements ReadFile{

    @Override
    public String read(Path path) throws IOException {
        return Files.readString(path);

    }
}
