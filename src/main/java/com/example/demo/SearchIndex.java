package com.example.demo;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public interface SearchIndex {

    List<Integer> search(Reader reader, String keyword) throws IOException;
}
