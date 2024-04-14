package com.vegas.interview.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class ItemPackage {
    private final List<Item> items;
    private final BigDecimal totalPrice;

    public ItemPackage(Item hotel, Item show, Item tour, BigDecimal totalPrice) {
        items = List.of(hotel, show, tour);
        this.totalPrice = totalPrice;
    }

    public ItemPackage(List<Item> items, BigDecimal totalPrice) {
        this.items = items;
        this.totalPrice = totalPrice;
    }

    public Optional<Item> getHotel() {
        for (Item it : items) {
            if (it.getType() == ItemType.HOTEL)
                return Optional.of(it);
        }

        return Optional.empty();
    }

    public Optional<Item> getShow() {
        for (Item it : items) {
            if (it.getType() == ItemType.SHOW)
                return Optional.of(it);
        }

        return Optional.empty();
    }

    public Optional<Item> getTour() {
        for (Item it : items) {
            if (it.getType() == ItemType.TOUR)
                return Optional.of(it);
        }

        return Optional.empty();
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Optional<Item> getItem(ItemType type) {
        switch (type) {
            case HOTEL:
                return getHotel();
            case SHOW:
                return getShow();
            case TOUR:
                return getTour();
            default:
                // Should never happen
                return Optional.empty();
        }
    }

    public BigDecimal getPriceOf(ItemType type) {
        switch (type) {
            case HOTEL:
                return getHotel().map(Item::getPrice).orElseGet(() -> BigDecimal.ZERO);
            case SHOW:
                return getShow().map(Item::getPrice).orElseGet(() -> BigDecimal.ZERO);
            case TOUR:
                return getTour().map(Item::getPrice).orElseGet(() -> BigDecimal.ZERO);
            case PACKAGE:
                return getTotalPrice();
            default:
                // Should never happen
                throw new IllegalArgumentException("Type not implemented: " + type);
        }
    }

}
