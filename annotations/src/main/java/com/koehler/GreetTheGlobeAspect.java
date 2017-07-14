package com.koehler;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class GreetTheGlobeAspect {

  @Pointcut("@annotation(GreetGlobe)")
  public void greetGlobePointCut(){
  }

  @Pointcut("@annotation(Farewell)")
  public void farewellPointCut() {
  }

  @Pointcut("@annotation(Log)")
  public void logPointCut() {
  }

  //Defines a pointcut that we can use in the @Before,@After, @AfterThrowing, @AfterReturning,@Around specifications
  //The pointcut is a catch-all pointcut with the scope of execution
  @Pointcut("execution(* *(..))")
  public void atExecution(){}


  /* This should be the order of definition: after, before, around. Otherwise, ajc complains of circular advice
  * parameters. Change the order of before and after to experience this. Look at http://dev.eclipse.org/mhonarc/lists/aspectj-users/msg02138.html
  * for when this was reported */

  @After("farewellPointCut() && atExecution()")
  public void farewell(JoinPoint jp) {
    System.out.println("Farewell Cruel World, From " + jp.getSignature().getName());
  }

  @Before("greetGlobePointCut() && atExecution()")
  public void greet(JoinPoint jp) {
    System.out.println("Greetings Globe, From " + jp.getSignature().getName());
  }

  @Around("logPointCut() && atExecution()")
  public void logTime(ProceedingJoinPoint jp) throws Throwable {
    long currTime = System.currentTimeMillis();
    jp.proceed();
    System.out.println(jp.getSignature().getName() + " took " + (System.currentTimeMillis() - currTime) + "ms");
  }
}
