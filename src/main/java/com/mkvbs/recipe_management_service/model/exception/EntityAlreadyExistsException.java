package com.mkvbs.recipe_management_service.model.exception;

public class EntityAlreadyExistsException extends FymException {
    public EntityAlreadyExistsException(String message) {
        super(message);
    }
}
