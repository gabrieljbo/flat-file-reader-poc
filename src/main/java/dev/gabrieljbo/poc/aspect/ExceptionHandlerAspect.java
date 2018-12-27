package dev.gabrieljbo.poc.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import dev.gabrieljbo.poc.exception.ExceptionHandlingErrorCode;
import dev.gabrieljbo.poc.exception.SystemException;

@Aspect
@Component
public class ExceptionHandlerAspect {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandlerAspect.class);
    
    @Around("dev.gabrieljbo.poc.aspect.Pointcuts.lineProcessorExecution()")
    public void handleException(ProceedingJoinPoint joinPoint) {
	Object[] methodArguments = joinPoint.getArgs();
	String line = (String) methodArguments[0];
	int lineNumber = (Integer) methodArguments[1];

	try {
	    joinPoint.proceed();
	} catch (Throwable ex) {
	    if (ex instanceof SystemException) {
		SystemException se = (SystemException) ex;


		logger.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> An error has ocurred in line {} [{}] with code {}", lineNumber, line, se.getErrorCode().getNumber());
	    } else {
		throw new SystemException(ExceptionHandlingErrorCode.GENERAL_ERROR);
	    }
	}
    }

}
