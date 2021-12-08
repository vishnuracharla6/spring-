package com.springproject.springfinaldemo.aspect;

import com.springproject.springfinaldemo.entity.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.persistence.Column;
import java.sql.Driver;
import java.util.logging.Logger;

@Aspect
@Component
public class Submission {

    Logger logger =Logger.getLogger(Driver.class.getName());


    //@Before("execution(void add*())")
    //@Before("execution(* add*())")
    @Before("execution(public String com.springproject.springfinaldemo.controller.LoginController.processRegister(..))")
    public void beforeSubmissionAdvice(JoinPoint joinPoint) {
        // display the method signature
        MethodSignature methodSig = (MethodSignature) joinPoint.getSignature();

        Object[] args = joinPoint.getArgs();
        for (Object tempArg : args) {

            if (tempArg instanceof User) {

                // downcast and print Account specific stuff
                User theUser = (User) tempArg;

                String name= "ROLE_"+theUser.getRole().toUpperCase();
                theUser.setRole(name);
            }
        }
        logger.info("\n=>>> Executing @Before advice on method");

    }

}
