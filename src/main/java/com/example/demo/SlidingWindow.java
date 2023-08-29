package com.example.demo;

import org.springframework.lang.NonNull;

import java.io.IOException;
import java.io.Reader;
import java.util.Objects;

public class SlidingWindow {
    @NonNull
    private final Reader reader;
    private final int windowSize;
    @NonNull
    private char[] window;
    private boolean eof = false;
    private int position = 0;

    public SlidingWindow( @NonNull Reader reader, int windowSize ) {
        this.reader = Objects.requireNonNull(reader);
        this.windowSize = windowSize;
        this.window = readChars(windowSize);
    }

    public boolean eof(){
        return eof;
    }

    public int position(){
        return position;
    }

    public @NonNull char[] toCharArray(){
        if( eof )
            throw new RuntimeException("EOF");

        return window.clone();
    }

    public void advance() {
        if( eof )
            throw new RuntimeException("EOF");

        var buffer = readChars(1);
        if( eof )
            return;

        var newWindow = new char[windowSize];
        System.arraycopy( window, 1, newWindow, 0, windowSize - 1 );
        System.arraycopy( buffer, 0, newWindow, windowSize -1, 1 );
        this.window = newWindow;

        position++;
    }




    private @NonNull char[] readChars(int length){
        var buffer = new char[length];
        var read = read(buffer);
        this.eof = read != length;
        return buffer;
    }

    private int read(char[] buffer) {
        try {
            return reader.read(buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
