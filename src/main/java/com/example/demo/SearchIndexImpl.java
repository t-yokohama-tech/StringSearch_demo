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
        int slidingAmount;

        while (!slidingWindow.eof()) {
            char[] sw = slidingWindow.toCharArray();
            PatternMatcher.Result result = patternMatcher.reverseMatch(sw);

            if (result.equals(new PatternMatcher.Match())) {
                idxList.add(slidingWindow.position());
                slidingAmount = ptnLength;
            } else {
                slidingAmount = advanceAmountCalculator.getSlidingAmount(result.hashCode(), sw[result.hashCode()]);
            }
            slidingWindow.advance(slidingAmount);
        }
        return idxList;
    }
}

