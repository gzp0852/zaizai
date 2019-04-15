package com.shuaibi.demo.AopDemo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author WH1707069
 * @date 2019/1/22 19:33
 */
@Aspect
@Component
public class DemoAspect {

	@Pointcut("execution(public * com.shuaibi.demo.controller.*.*(..)) && @annotation(com.shuaibi.demo.AopDemo.DemoAnnotation)")
	public void log() {
	}

	@Before("log()")
	public void before(JoinPoint joinPoint) {
		System.out.println("beforebeforebeforebeforebeforebeforebeforebefore");
	}

	@After("log()")
	public void after(JoinPoint joinPoint) {
		System.out.println(joinPoint.getArgs()[0].toString());
		System.out.println("afterafterafterafterafterafterafterafterafterafter");
	}

	@AfterReturning(value = "log()", returning = "user")
	public void after(JoinPoint joinPoint, Object user) {
		System.out.println(joinPoint.getArgs()[0].toString());
		System.out.println("afterafterafterafterafterafterafterafterafterafter");
		System.out.println("-----------------------"+user.toString());
	}

	@Around("@annotation(demoAnnotation)")
	public void around(ProceedingJoinPoint pjp, DemoAnnotation demoAnnotation) throws Throwable {
		pjp.proceed();
		System.out.println("aroundaroundaroundaroundaroundaroundaroundaroundaround");
		System.out.println(demoAnnotation.desc());
	}
}
