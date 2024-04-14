package com.vegas.interview.model;

import java.math.BigDecimal;

public class Item {
    private int id;
    private ItemType type;
    private BigDecimal price;

    public Item(int id, ItemType type, BigDecimal price) {
        this.id = id;
        this.type = type;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s (%s):\t$%s", this.type, this.id, this.price);
    }

}
