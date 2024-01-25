package model.enums;

public enum CardType {
    SILVER(5.0),
    GOLD(10.0),
    PLATINUM(15.0);

    private final Double discount;

    public Double getDiscount() {
        return discount;
    }

    CardType(Double discount) {
        this.discount = discount;
    }
}
