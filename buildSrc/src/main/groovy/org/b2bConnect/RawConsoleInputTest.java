package org.b2bConnect;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RawConsoleInputTest {
    public static void main(String[] args) {
        RawConsoleInput GC = new RawConsoleInput();
        int CharRead = 0;
        for (; ; ) {
            try {
                CharRead = GC.read(true);
            } catch (IOException ex) {
                Logger.getLogger(RawConsoleInput.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (CharRead == -1 || CharRead == 27 || CharRead == 3 || CharRead == 4)    // ^c, ^d, or Esc)
                break;
            switch (CharRead) {
                case 1:
                    System.out.printf(String.valueOf(CharRead));
                    break;
            }
        }
    }
}