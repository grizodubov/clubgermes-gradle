package org.b2bConnect

import java.util.concurrent.TimeUnit

class ConsoleInputTest {
    static void main(String[] args) {

            ConsoleInput con = new ConsoleInput(
                    Integer.parseInt("1"),
                    Integer.parseInt("10"),
                    TimeUnit.SECONDS
            )

            String input = con.readLine()
            System.out.println("Done. Your input was: " + input)
        }
    }
//https://www.javaspecialists.eu/archive/Issue153-Timeout-on-Console-Input.html