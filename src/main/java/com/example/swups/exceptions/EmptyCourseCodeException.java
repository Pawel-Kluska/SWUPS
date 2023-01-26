package com.example.swups.exceptions;

public class EmptyCourseCodeException extends Exception
{

    public EmptyCourseCodeException() {
        super();
    }

    public EmptyCourseCodeException(String message) {
        super(message);
    }
}
