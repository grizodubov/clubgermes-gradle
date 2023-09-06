package org.b2bConnect

import java.util.concurrent.Callable

class ConsoleInputReadTask implements Callable<String> {
  String call() throws IOException {
    BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in))
    System.out.println("ConsoleInputReadTask run() called.")
    String input
    do {
      System.out.println("Please type something: ")
      try {
        // wait until we have data to complete a readLine()
        while (!br.ready()) {
          Thread.sleep(200)
        }
        input = br.readLine()
      } catch (InterruptedException e) {
        System.out.println("ConsoleInputReadTask() cancelled")
        return null
      }
    } while ("".equals(input))
    System.out.println("Thank You for providing input!")
    return input
  }
}
//https://www.javaspecialists.eu/archive/Issue153-Timeout-on-Console-Input.html

//Variants of Console Input:
//char tmp = (char) System.in.read();
//char tmp = (char) new InputStreamReader(System.in).read ();
//char tmp = (char) System.console().reader().read();           // Java 6