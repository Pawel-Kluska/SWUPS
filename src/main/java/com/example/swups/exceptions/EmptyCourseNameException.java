package com.example.swups.exceptions;

public class EmptyCourseNameException extends Exception{

    public EmptyCourseNameException() {
        super();
    }

    public EmptyCourseNameException(String message) {
        super(message);
    }
}
