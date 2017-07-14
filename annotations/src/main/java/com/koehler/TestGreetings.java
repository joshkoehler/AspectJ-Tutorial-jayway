package com.koehler;

/**
 * Created by joshkoehler on 7/9/17.
 */
public class TestGreetings {

  @Log
  @GreetGlobe
  @Farewell
  public static void main(String[] args) {
    TestGreetings tg = new TestGreetings();
    try {
      tg.anotherTime();
      tg.butNotHere();
    } catch (InterruptedException e) {}
  }

  @Log
  @GreetGlobe
  private void anotherTime() throws InterruptedException {
    Thread.sleep(250);
  }

  @Log
  private void butNotHere() throws InterruptedException {
    System.out.println("But Not Here! I take a while");
    Thread.sleep(500);
  }
}
