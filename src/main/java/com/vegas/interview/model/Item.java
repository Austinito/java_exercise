package com.vegas.interview.model;

import java.math.BigDecimal;

public interface Item {
    public int getId();
    public ItemType getType();
    public BigDecimal getPrice();

    public static Item createItem(int itemId, ItemType itemType, BigDecimal price) {
        switch (itemType) {
			case HOTEL:
                return new Hotel(itemId, price);
			case SHOW:
                return new Show(itemId, price);
			case TOUR:
                return new Tour(itemId, price);
			default:
                throw new UnsupportedOperationException("Could not create item for type: " + itemType.getDisplayName());
        }


    }
}
