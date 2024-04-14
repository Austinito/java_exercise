package com.vegas.interview.model;

import java.util.Arrays;
import java.util.Optional;

public enum ItemType {
    HOTEL("HOTEL"), SHOW("SHOW"), TOUR("TOUR"),
    // Package added to facilitate sort ordering (vs creating another enum)
    PACKAGE("PACKAGE");

    private final String displayName;

    ItemType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public static Optional<ItemType> fromString(String str) {
        return Arrays.stream(ItemType.values()).filter(type -> type.name().equalsIgnoreCase(str)).findFirst();
    }

}
