package com.example.demo;

import lombok.NonNull;

/**
 * パターン照合器
 */
@lombok.AllArgsConstructor
public class PatternMatcher {

    /**
     * 照合結果
     */
    interface Result {
    }

    /**
     * 照合結果 パターンと不一致.
     *
     * @param position 不一致箇所のインデックス
     */
    record Mismatch(int position) implements Result {
    }

    /**
     * 照合結果 パターンと一致.
     */
    static class Match implements Result {
    }

    @NonNull
    private final char[] pattern;

    /**
     * chars とパターンを後方から照合して照合結果を返す.
     */
    @NonNull
    Result reverseMatch(@NonNull char[] chars) {
        // パターンと chars が一致する場合 : Match のインスタンス
        // パターンと chars が一致しない場合 : Mismatch のインスタンス

        int position = chars.length - 1;
        while (position >= 0) {
            if (pattern[position] != chars[position]) {
                return new Mismatch(position);
            }
            position--;
        }
        return new Match();
    }
}
