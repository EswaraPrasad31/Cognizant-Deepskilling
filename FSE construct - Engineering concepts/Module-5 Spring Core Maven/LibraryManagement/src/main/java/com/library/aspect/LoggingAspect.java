package com.library.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.library.service.BookService.issueBook(..))")
    public void beforeMethod() {

        System.out.println("Logging: issueBook() method is about to execute.");

    }

}