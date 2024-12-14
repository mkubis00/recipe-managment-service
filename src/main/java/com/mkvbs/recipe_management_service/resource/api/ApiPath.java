package com.mkvbs.recipe_management_service.resource.api;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiPath {

    private static final String ID = "/{id}";

    public static final String API_BASE = "/api";
    public static final String API_VERSION_V1 = API_BASE + "/v1";

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class RECIPE {

        public static final String BASE_V1 = API_VERSION_V1 + "/recipes";

        public static final String CREATE_V1 = BASE_V1;
        public static final String CREATE_LIST_V1 = BASE_V1 + "/list";

        public static final String DELETE_V1 = BASE_V1 + ID;
        public static final String DELETE_ALL_V1 = BASE_V1 + "/all";

        public static final String GET_V1 = BASE_V1 + ID;
        public static final String GET_ALL_V1 = BASE_V1 + "/all";

        public static final String TEST_V1 = BASE_V1 + "/";
    }
}
