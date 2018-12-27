package dev.gabrieljbo.poc.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("within(dev.gabrieljbo.poc.processing.FileProcessor+) && execution(* processFile(..))")
    public void fileProcessorExecution() {
    }

    @Pointcut("within(dev.gabrieljbo.poc.processing.BirthEventLineProcessor) && execution(* processLine(..))")
    public void lineProcessorExecution() {
    }
}
