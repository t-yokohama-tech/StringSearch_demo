package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class SearchIndexImpl implements SearchIndex{

    @Override
    public String search(String str,String stringA){
        StringBuilder searchResult = new StringBuilder();
        int idx = stringA.indexOf(str);
        while (idx != -1) {
            searchResult.append(idx).append("文字目\n");
            idx = stringA.indexOf(str, idx + 1);
        }
        return searchResult.toString();
    }


}
