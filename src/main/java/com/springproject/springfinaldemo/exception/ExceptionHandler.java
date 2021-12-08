package com.springproject.springfinaldemo.exception;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;

    @ControllerAdvice
    public class ExceptionHandler{

        @org.springframework.web.bind.annotation.ExceptionHandler
        public String exceptionHandlerMethod(IdNotFoundException exc, Model model){

            model.addAttribute("message",exc.getMessage());

            return "error/custom-error";

        }
        @org.springframework.web.bind.annotation.ExceptionHandler
        public String exceptionHandlerMethod(CollegesnotAvailable exc, Model model){

            model.addAttribute("message",exc.getMessage());

            return "error/custom-error";

        }

        @org.springframework.web.bind.annotation.ExceptionHandler
        public String exceptionHandlerMethod(Exception exc, Model model){

            model.addAttribute("message",exc.getMessage());

            return "error/custom-error";

        }

    }


