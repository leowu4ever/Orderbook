package com.cfbenchmarks.interview;

public class InvalidIdException extends RuntimeException{

    public InvalidIdException(String errorMessage) {
        super(errorMessage);
    }
}