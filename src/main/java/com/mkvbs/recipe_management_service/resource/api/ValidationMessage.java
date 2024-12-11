package com.mkvbs.recipe_management_service.resource.api;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidationMessage {

    public static final String INCORRECT_NAME_LEN = "name must be between 3 and 50 characters";
    public static final String NAME_NOT_NULL = "name cannot be null";
    public static final String DESCRIPTION_NOT_NULL = "description cannot be null";
    public static final String INCORRECT_DESCRIPTION_LEN = "name must be between 20 and 200 characters";
    public static final String HTTP_MESSAGE_NOT_READABLE = "Some of the values aren't assigned correctly. Check structure of the json.";
}