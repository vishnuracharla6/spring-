package com.springproject.springfinaldemo.aspect;

import com.springproject.springfinaldemo.dto.UserDto;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import org.springframework.stereotype.Component;


@Aspect
@Component
public class Submission {

    @Before("execution(public String com.springproject.springfinaldemo.controller.LoginController.processRegister(..))")
    public void beforeSubmissionAdvice(JoinPoint joinPoint) {

        Object[] args = joinPoint.getArgs();
        for (Object tempArg : args) {

            if (tempArg instanceof UserDto) {

                // downcast and print Account specific stuff
                UserDto user = (UserDto) tempArg;

                String name= "ROLE_"+user.getRole().toUpperCase();
                user.setRole(name);
            }
        }


    }

}
