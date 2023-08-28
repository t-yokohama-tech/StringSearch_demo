package com.example.demo;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Component
public class SearchIndexImpl implements SearchIndex{
    private int count = 0;
    private char[] readArray = new char[100];
    private final List<Integer> idxList = new ArrayList<>(); //文字数リスト

    @Override
    public List<Integer> search(Reader reader, String str) throws IOException {
        int strLength = str.length();
        getFileRead(reader, strLength, str);
        return idxList;
    }

    private void getFileRead(Reader reader, int strLength, String str) throws IOException {
        while (reader.read(readArray) != -1) {//100文字ごとの処理
            String stringA = String.valueOf(readArray);
            readArray = new char[100 + strLength];
            int idx = stringA.indexOf(str);
            getSearchResult(idx, stringA, str);
            count = count + stringA.length() - strLength;
            reader.skip(-strLength); //検索文字の数だけ、検索対象文字列を後退
        }
    }

    private void getSearchResult(int idx, String stringA, String str) {
        while (idx != -1) {//取得した文字列の中での検索処理
            idxList.add(idx + count);//読み込んだ1行の中での検索該当文字数＋全文カウント をリストに追加
            idx = stringA.indexOf(str, idx + 1);
        }
    }
}

