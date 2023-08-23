package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadFileImplTest {
    private final ReadFile target = new ReadFileImpl();
    private final String path = "data/chumonno_oi_ryoriten.utf8.txt";
    private final File file = new File(path);
    @Nested
    class readFile {
        @Test
        void returnReadFile() throws IOException {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            var result = target.read(bufferedReader);
            var expected = """
                    注文の多い料理店
                    宮沢賢治

                    -------------------------------------------------------
                    【テキスト中に現れる記号について】

                    《》：ルビ
                    （例）紳""";
            assertEquals(expected, result);

            var result2 = target.read(bufferedReader);
            var expected2 = """
                    士《しんし》
                                        
                    ｜：ルビの付く文字列の始まりを特定する記号
                    （例）二｜疋《ひき》
                                        
                    ［＃］：入力者注　主に外字の説明や、傍点の位置の指定
                    （例）［＃ここから４字下げ、横書き、中央揃え、罫囲み］
                    ---""";
            assertEquals(expected2, result2);

        }
    }
}