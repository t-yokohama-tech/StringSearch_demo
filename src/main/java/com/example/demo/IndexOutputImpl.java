package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IndexOutputImpl implements IndexOutput {
    @Override
    public void output(List<Integer> idxList) {
        int count = 0;
        while (count < idxList.size()) {
            System.out.println(idxList.get(count) + "文字目");
            count++;
        }
    }
}