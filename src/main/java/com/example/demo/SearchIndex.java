package com.example.demo;

import java.util.List;

public interface SearchIndex {

    List<Integer> search(String str, String stringA, int count, List<Integer> idxList);
}
