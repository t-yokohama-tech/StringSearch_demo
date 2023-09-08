package com.example.demo;

import org.springframework.stereotype.Component;

import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SearchIndexImpl implements SearchIndex {
    @Override
    public List<Integer> search(Reader reader, String ptn) {
        List<Integer> idxList = new ArrayList<>(); //文字数リスト
        int ptnLength = ptn.length();
        char[] ptnArray = ptn.toCharArray();
        SlidingWindow slidingWindow = new SlidingWindow(reader, ptnLength);
        AdvanceAmountCalculator advanceAmountCalculator = new AdvanceAmountCalculator(ptnArray);
        PatternMatcher patternMatcher = new PatternMatcher(ptnArray);

        var visitor = new PatternMatcher.Result.Visitor<Integer>() {
            public Integer mismatch(PatternMatcher.Mismatch mismatch){
                return advanceAmountCalculator.getSlidingAmount(mismatch.position(), slidingWindow.charAt(mismatch.position()));
            }
            public Integer match(){
                idxList.add(slidingWindow.position());
                return 1;
            }
        };

        while (!slidingWindow.eof()) {
            char[] sw = slidingWindow.toCharArray();
            System.out.println("検索対象："+ Arrays.toString(sw));
            PatternMatcher.Result result = patternMatcher.reverseMatch(sw);
            slidingWindow.advance( result.accept( visitor ) );
        }
        return idxList;
    }
}

