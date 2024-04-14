package com.vegas.interview.model;

import java.math.BigDecimal;

public class Tour implements Item {
    private final int id;
    private final BigDecimal price;

    public Tour(int id, BigDecimal price) {
        this.id = id;
        this.price = price;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public ItemType getType() {
        return ItemType.TOUR;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

}
