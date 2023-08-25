package com.example.demo;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Component
public class SearchIndexImpl implements SearchIndex{

    @Override
    public List<Integer> search(Reader reader, String str) throws IOException {
        List<Integer> idxList = new ArrayList<>(); //文字数リスト
        char[] readArray = new char[100];
        String stringA;
        int count= 0;
        while((reader.read(readArray))!= -1){
            stringA = String.valueOf(readArray);
            int idx = stringA.indexOf(str);
            while (idx != -1) {
                idxList.add(idx + count);//読み込んだ1行の中での検索該当文字数＋全文カウント をリストに追加
                idx = stringA.indexOf(str, idx + 1);
            }
            count = count+stringA.length();
        }
        return idxList;
    }
}
