package com.shopping.hee.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class ItemOption {

    private int memory;
    private int save;

    @Enumerated(EnumType.STRING)  // Enumerated 어노테이션을 사용하여 매핑
    private Color color;

    @Enumerated(EnumType.STRING)  // Enumerated 어노테이션을 사용하여 매핑
    private Wifi wifi;

    public enum Color {
        BLACK, WHITE
    }

    public enum Wifi {
        WIFI, WIFI_CELLULAR
    }
}
