package kr.co.yapp._22nd.coffice.domain.place;

public enum FoodType {
    GLUTEN_FREE("글루텐 프리"),
    MEAL_WORTHY("식사대용"),
    ;

    private final String description;


    FoodType(String description) {
        this.description = description;
    }
}
