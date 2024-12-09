package com.mkvbs.recipe_management_service.resource.api;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidationMessage {

    public static final String HTTP_MESSAGE_NOT_READABLE = "Some of the values aren't assigned correctly. Check structure of the json.";
}