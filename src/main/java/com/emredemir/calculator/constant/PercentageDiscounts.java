package com.emredemir.calculator.constant;

public enum PercentageDiscounts {
    CUSTOMER(0.05),
    AFFILIATE(0.1),
    EMPLOYEE(0.3);

    private final double discount;

    PercentageDiscounts(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }
}
