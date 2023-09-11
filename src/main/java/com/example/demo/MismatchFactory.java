package com.example.demo;

import java.util.HashMap;
import java.util.Map;

public class MismatchFactory {


    private static final MismatchFactory singleton = new MismatchFactory();

    private final Map<Integer, PatternMatcher.Result> pool = new HashMap<>();

    private MismatchFactory() {
    }

    public static MismatchFactory getInstance() {
        return singleton;
    }


    public PatternMatcher.Result getMismatch(int position) {

        PatternMatcher.Result result = pool.get(position);
        if(result == null){
            result = new PatternMatcher.Mismatch(position);
            synchronized (pool) {
                pool.put(position, result);
            }
        }
        return result;
    }
}