package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchIndexImpl implements SearchIndex{

    @Override
    public List<Integer> search(String str, String stringA, int count, List<Integer> idxList) {
        int idx = stringA.indexOf(str);//読み込んだ1行の中での検索該当文字数
        while (idx != -1) {
            idxList.add(idx+count);//読み込んだ1行の中での検索該当文字数＋全文カウント をリストに追加
            idx = stringA.indexOf(str, idx + 1);
        }
        return idxList;
    }
}
