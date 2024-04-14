package com.vegas.interview.io;

import com.vegas.interview.model.Item;
import com.vegas.interview.model.ItemType;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ItemReader {

    // Expected format: [product_type]\t[item_id]\t[price]
    private static final int TYPE_INDEX = 0;
    private static final int ID_INDEX = 1;
    private static final int PRICE_INDEX = 2;

    public static List<Item> readItems(String filePath, Map<ItemType, Boolean> packageInclusionsMap)
            throws IOException, IllegalArgumentException {
        return Files.lines(Paths.get(filePath)).map(dataRow -> getItemFromDataString(dataRow, packageInclusionsMap))
                .flatMap(Optional::stream).collect(Collectors.toList());
    }

    private static Optional<Item> getItemFromDataString(String dataRow, Map<ItemType, Boolean> packageInclusionsMap) {
        String[] datums = dataRow.split("\t");
        if (datums.length != 3) {
            throw new IllegalArgumentException(
                    "Invalid data format, expected format: [product_type]\t[item_id]\t[price]. Received: " + dataRow);
        }

        ItemType itemType = ItemType.fromString(datums[TYPE_INDEX])
                .orElseThrow(() -> new IllegalArgumentException("Unknown item type for row: " + dataRow));

        if (packageInclusionsMap.get(itemType)) {
            int itemId;
            try {
                itemId = Integer.parseInt(datums[ID_INDEX]);
                if (itemId < 0) {
                    throw new NumberFormatException("Should be non-negative");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid item ID (expects non-negative integer) for row: " + dataRow,
                        e);
            }
            BigDecimal price;
            try {
                price = new BigDecimal(datums[PRICE_INDEX]);
                if (price.compareTo(BigDecimal.ZERO) == -1) {
                    throw new NumberFormatException("Should be non-negative");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid price (expects non-negative number) for row: " + dataRow,
                        e);
            }

            return Optional.of(Item.createItem(itemId, itemType, price));
        } else {
            return Optional.empty();
        }
    }
}
