package com.shopping.hee.domain.Enum;

public enum ItemColor {
    Silver("실버"),
    RoseGold("로즈골드"),
    Gold("골드"),
    Mixed("혼합");

    private final String name;

    ItemColor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
