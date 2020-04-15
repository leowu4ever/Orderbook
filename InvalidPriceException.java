package com.cfbenchmarks.interview;

public class InvalidPriceException extends RuntimeException{
    public InvalidPriceException(String errorMessage) {
        super(errorMessage);
    }
}