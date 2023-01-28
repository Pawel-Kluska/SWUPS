package com.example.swups.exceptions;

public class NoUniqueCourseCodeException extends Exception {

    public NoUniqueCourseCodeException() {super();}

    public NoUniqueCourseCodeException(String message) {
        super(message);
    }
}
