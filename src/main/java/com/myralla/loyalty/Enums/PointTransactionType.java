package com.myralla.loyalty.Enums;

public enum PointTransactionType {
    EARN("Earned Points"),
    SPEND("Spend Points"),
    REDEEM("Redeemed Points"),
    EXPIRE("Expired Points"),
    ADJUSTMENT("Points Adjustment");

    private final String description;

    PointTransactionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
