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

            var visitor = new PatternMatcher.Result.Visitor<Integer>() {

                public Integer mismatch(PatternMatcher.Mismatch mismatch){
                    System.out.println("mismatch");
                    return advanceAmountCalculator.getSlidingAmount(mismatch.position(), sw[mismatch.position()]);
                }
                
                public Integer match(){
                    System.out.println("match");
                    idxList.add(slidingWindow.position());
                    return 1;
                }
            };
            slidingWindow.advance( result.accept( visitor ) );
        }
        return idxList;
    }
}

