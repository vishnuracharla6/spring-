package com.springproject.springfinaldemo.exception;

public class CollegesnotAvailable extends RuntimeException{
    public CollegesnotAvailable(String message)
    {
        super(message);
    }
}
