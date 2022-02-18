package com.learning.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class UserServiceAspect 
{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Pointcut("within(@org.springframework.stereotype.Repository *) " + 
			"|| within(@org.springframework.stereotype.Service *)" +
			"|| within(@org.springframework.web.bind.annotation.RestController *)")
	public void springPointCutExp()
	{
		
	}
	
	@Pointcut("within(com.learning.controller..*) " + 
			"|| within(com.learning.service.impl..*)")
	public void springPointCutExp2()
	{
		
	}
	
	
	
	@AfterThrowing(pointcut = "springPointCutExp() && springPointCutExp2()" , throwing="e")
	public void lofAfterThrowingException(JoinPoint joinPoint, Throwable e)
	{
		// LOG GENERATION - but only applicable for the annotations mentioned in @Pointcut //
		log.error("exception {}.{}() with cause {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), e.getCause()!=null? e.getCause():"NULL");
	}
	
//	@Around(value = "execution(* com.zee.zee5app.service.impl.*.get*(..))") // it means adding code both before and after code execution 
//	public void aroundAllServiceMethods(JoinPoint joinPoint)
//	{
//		System.out.println("Have a good day!");
//		System.out.println(joinPoint);
//	}
	
	@Before(value = "execution(* com.learning.service.impl.*.get*(..))")
	public void beforeAllServiceMethods(JoinPoint joinPoint)
	{
		System.out.println("hello");
		System.out.println(joinPoint);
	}

}
