package com.example.demo;

public class MatchFactory {

    private static final MatchFactory singleton = new MatchFactory();

    private MatchFactory() {
    }

    public static MatchFactory getInstance() {
        return singleton;
    }


    public synchronized PatternMatcher.Result getMatch() {
        return new PatternMatcher.Match();
    }
}
