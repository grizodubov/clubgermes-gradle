package org.b2bConnect

import java.util.concurrent.*

class ConsoleInput {
  private final int tries
  private final int timeout
  private final TimeUnit unit

  ConsoleInput(int tries, int timeout, TimeUnit unit) {
    this.tries = tries
    this.timeout = timeout
    this.unit = unit
  }

  String readLine() throws InterruptedException {
    ExecutorService ex = Executors.newSingleThreadExecutor()
    String input = null
    try {
      // start working
      for (int i = 0; i < tries; i++) {
        System.out.println(String.valueOf(i + 1) + ". loop")
        Future<String> result = ex.submit(
            new ConsoleInputReadTask())
        try {
          input = result.get(timeout, unit)
          break
        } catch (ExecutionException e) {
          e.getCause().printStackTrace()
        } catch (TimeoutException e) {
          System.out.println("Cancelling reading task")
          result.cancel(true)
          System.out.println("\nThread cancelled. input is null")
        }
      }
    } finally {
      ex.shutdownNow()
    }
    return input
  }
}
//https://www.javaspecialists.eu/archive/Issue153-Timeout-on-Console-Input.html