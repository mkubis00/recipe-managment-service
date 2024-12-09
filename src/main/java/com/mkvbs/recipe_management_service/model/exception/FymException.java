package com.mkvbs.recipe_management_service.model.exception;

public abstract class FymException extends RuntimeException {
    protected FymException(String message) {
        super(message);
    }
}
