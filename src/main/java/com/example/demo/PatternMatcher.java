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

        int accept(Result.Visitor<Integer> visitor);

        interface Visitor<T>{
            T mismatch( PatternMatcher.Mismatch mismatch );
            T match();
        }
    }

    /**
     * 照合結果 パターンと不一致.
     *
     * @param position 不一致箇所のインデックス
     */
    record Mismatch(int position) implements Result {

        public int accept(Visitor<Integer> visitor) {
            return position;
        }
    }

    /**
     * 照合結果 パターンと一致.
     */
    @lombok.EqualsAndHashCode
    static class Match implements Result {

        public int accept(Visitor<Integer> visitor) {
            return 1;
        }
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
