package com.cfbenchmarks.interview;

public class InvalidQuantityException extends RuntimeException{
    public InvalidQuantityException(String errorMessage) {
        super(errorMessage);
    }
}