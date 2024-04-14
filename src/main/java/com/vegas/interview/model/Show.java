package com.vegas.interview.model;

import java.math.BigDecimal;

public class Show implements Item {
    private final int id;
    private final BigDecimal price;

    public Show(int id, BigDecimal price) {
        this.id = id;
        this.price = price;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public ItemType getType() {
        return ItemType.SHOW;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

}
