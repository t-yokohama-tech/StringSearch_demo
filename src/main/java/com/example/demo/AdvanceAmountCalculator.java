package com.example.demo;

import org.springframework.lang.NonNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 * ウィンドウスライド量計算器
 */
class AdvanceAmountCalculator {

    @NonNull
    private final Map<Character, Integer> advanceAmountMap;
    private final int patternLength;

    AdvanceAmountCalculator(@NonNull char[] pattern) {
        // advanceAmountMap の初期化
        this.patternLength = pattern.length;
        this.advanceAmountMap = Collections.unmodifiableMap(makeAdvanceAmountMap(pattern));
    }

    private Map<Character, Integer> makeAdvanceAmountMap(char[] pattern){
        Map<Character,Integer> charAmountMap = new HashMap<>();
        for( int i = 0; i < pattern.length - 1; i++){
            if(!charAmountMap.containsKey(pattern[i])) {
                charAmountMap.put(
                        pattern[i],
                        patternLength - i - 1
                );
            }
        }
        return charAmountMap;
    }

    /**
     * 不一致位置 position と 不一致文字 c に応じたウィンドウスライド量を返す.
     * @param position 不一致文字の位置
     * @param c 不一致文字
     * @return ウィンドウスライド量
     */
    int getSlidingAmount(int position, char c) {
        // advanceAmountMap が c を含む場合:
        //   advanceAmountMap から得られるスライド量
        //     または
        //   patternLength - position
        //   のいずれか大きい方の値を返す
        // advanceAmountMap が c を含まない場合:
        //   position + 1 を返す
        if (advanceAmountMap.containsKey(c))
            return Math.max(advanceAmountMap.get(c), patternLength - position);
        else
            return position+1;
    }
}