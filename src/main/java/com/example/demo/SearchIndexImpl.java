package com.example.demo;

import org.springframework.stereotype.Component;

import java.io.Reader;
import java.util.ArrayList;
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


        while (!slidingWindow.eof()) {
            char[] sw = slidingWindow.toCharArray();
            PatternMatcher.Result result = patternMatcher.reverseMatch(sw);
            int slidingAmount;
            if (result instanceof PatternMatcher.Match) {
                idxList.add(slidingWindow.position());
                slidingAmount = 1;
            } else {
                var misMatchResult = (PatternMatcher.Mismatch) result;
                slidingAmount = advanceAmountCalculator.getSlidingAmount(misMatchResult.position(), sw[misMatchResult.position()]);
            }
            slidingWindow.advance(slidingAmount);
        }
        return idxList;
    }
}

