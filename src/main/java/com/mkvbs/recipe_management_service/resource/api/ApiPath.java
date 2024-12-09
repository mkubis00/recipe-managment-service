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
        public static final String TEST_GET_FROM_ID_LIST_V1 = BASE_V1 + "/from_id_list/";
        public static final String TEST_GET_INGREDIENT_ID_BY_NAME_V1 = BASE_V1 + "/id_by_name/";
        public static final String TEST_GET_LIST_WITH_SIMILAR_NAME_V1 = BASE_V1 + "/with_similar_name/";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ALLERGEN {

        public static final String BASE_V1 = API_VERSION_V1 + "/allergens";

        public static final String GET_DESCRIPTION_V1 = BASE_V1 + "/description/{allergen}";
        public static final String GET_FROM_INGREDIENTS_IDS_V1 = BASE_V1 + "/from_ingredients_ids/{ingredientsIds}";

        public static final String TEST_GET_DESCRIPTION_V1 = BASE_V1 + "/description/";
        public static final String TEST_GET_FROM_INGREDIENTS_IDS_V1 = BASE_V1 + "/from_ingredients_ids/";
    }
}
