package com.gildedtros.gildedtros_spring_app.internal.exception;

public class InvalidItemNameException extends RuntimeException {
    public InvalidItemNameException(String message) {
        super(message);
    }

    public InvalidItemNameException() { this("Invalid Item Name Provided"); }
}
