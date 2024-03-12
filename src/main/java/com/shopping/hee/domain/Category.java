package com.shopping.hee.domain;

public enum Category {
    Bracelet("팔찌"),
    Ring("반지"),
    Necklace("목걸이"),
    Earring("귀걸이");

    private final String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
