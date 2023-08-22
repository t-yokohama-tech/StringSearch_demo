package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IndexOutputImpl implements IndexOutput {
    @Override
    public void output(List<Integer> searchResults) {
        int count = 0;
        while (count < searchResults.size()) {
            System.out.println(searchResults.get(count) + "文字目");
            count++;
        }
    }
}
