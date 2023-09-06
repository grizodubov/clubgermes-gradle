package org.b2bConnect;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RawConsoleInputTestTest {
    public static void main(String[] args) {
        RawConsoleInput GC = new RawConsoleInput();
        int CharRead;
        try {
            CharRead = GC.read(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.printf(String.valueOf(CharRead));
    }
}