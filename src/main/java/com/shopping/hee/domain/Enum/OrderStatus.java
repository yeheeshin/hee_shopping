package com.shopping.hee.domain.Enum;

public enum OrderStatus {
    PAYMENT_COMPLETED("결제 완료"),
    DELIVERY("배송중"),
    DELIVERY_COMPLETED("배송 완료");

    private final String name;

    OrderStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

