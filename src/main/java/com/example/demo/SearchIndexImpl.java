package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchIndexImpl implements SearchIndex{

    @Override
    public List<Integer> search(String str, String stringA){
        List<Integer> idxList = new ArrayList<>();
        int idx = stringA.indexOf(str);
        while (idx != -1) {
            idxList.add(idx);
            idx = stringA.indexOf(str, idx + 1);
        }
        return idxList;
    }


}
