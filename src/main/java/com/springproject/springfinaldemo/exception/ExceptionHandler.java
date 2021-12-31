package com.springproject.springfinaldemo.exception;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
    public class ExceptionHandler{
            ModelAttribute message;
        @org.springframework.web.bind.annotation.ExceptionHandler
        public String exceptionHandlerMethod(IdNotFoundException exc, Model model){

            model.addAttribute("message1",exc.getMessage());

            return "error/custom-error1";

        }
        @org.springframework.web.bind.annotation.ExceptionHandler
        public String exceptionHandlerMethod(CollegesnotAvailable exc, Model model){

            model.addAttribute("message2",exc.getMessage());

            return "error/custom-error2";

        }
        @org.springframework.web.bind.annotation.ExceptionHandler
        public String exceptionHandlerMethod(Exception exc, Model model){

            model.addAttribute("message",exc.getMessage());

            return "error/custom-error";
        }

    }


