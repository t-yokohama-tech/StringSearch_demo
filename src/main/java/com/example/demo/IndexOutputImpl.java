package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class IndexOutputImpl implements IndexOutput {
    @Override
    public void output(String searchResult) {
        System.out.print(searchResult);
    }
}
