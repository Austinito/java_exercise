package com.vegas.interview.core;

import com.vegas.interview.io.ItemReader;
import com.vegas.interview.model.Item;
import com.vegas.interview.model.ItemPackage;
import com.vegas.interview.model.ItemType;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class PackageBuilder {
    private List<Set<Item>> productSets;

    public PackageBuilder(String inputFileName, String exportFormat) throws IOException, IllegalArgumentException {
        Map<ItemType, Boolean> productInclusionsMap = initItemInclusionMap(exportFormat);
        List<Item> items = ItemReader.readItems(inputFileName, productInclusionsMap);
        initProductSets(items, productInclusionsMap);
    }

    // package-private constructor for tests
    PackageBuilder(List<Item> items, String exportFormat) {
        Map<ItemType, Boolean> productInclusionsMap = initItemInclusionMap(exportFormat);
        initProductSets(items, productInclusionsMap);
    }

    public List<ItemPackage> buildPackages(BigDecimal minPrice, BigDecimal maxPrice) {
        List<ItemPackage> validItemPackages = new ArrayList<>();
        buildPackageCombinations(new ArrayList<>(), BigDecimal.ZERO, 0, validItemPackages, minPrice, maxPrice);
        return validItemPackages;
    }

    // Recursive function used to construct combination
    private void buildPackageCombinations(List<Item> currentCombo, BigDecimal currentPrice, int index,
            List<ItemPackage> validItemPackages, BigDecimal minPrice, BigDecimal maxPrice) {
        // Base case: If within price range and contains all desired products -- add to
        // valid packages
        if (currentPrice.compareTo(minPrice) >= 0 && currentPrice.compareTo(maxPrice) <= 0) {
            if (!currentCombo.isEmpty() && currentCombo.size() == productSets.size()) {
                validItemPackages.add(new ItemPackage(new ArrayList<>(currentCombo), currentPrice));
            }
        }

        // Base case: If over price or we already added all possible -- ignore combo.
        if (currentPrice.compareTo(maxPrice) >= 0 || index >= productSets.size()) {
            return;
        }

        // Recurse: Add new item from next set, then check again.
        for (Item item : productSets.get(index)) {
            List<Item> newCombo = new ArrayList<>(currentCombo);
            newCombo.add(item);
            BigDecimal newPrice = currentPrice.add(item.getPrice());
            buildPackageCombinations(newCombo, newPrice, index + 1, validItemPackages, minPrice, maxPrice);
        }
    }

    private Map<ItemType, Boolean> initItemInclusionMap(String exportFormat) {
        Map<ItemType, Boolean> productInclusionsMap = new EnumMap<>(ItemType.class);
        for (ItemType type : ItemType.values()) {
            // TODO: fix smell from PACKAGE edge case
            if (type != ItemType.PACKAGE) {
                boolean isIncluded = exportFormat.toLowerCase()
                        .contains(type.getDisplayName().substring(0, 1).toLowerCase());
                productInclusionsMap.put(type, isIncluded);
            }
        }

        return productInclusionsMap;
    }

    private void initProductSets(List<Item> items, Map<ItemType, Boolean> productInclusionsMap) {
        productSets = new ArrayList<>();
        for (ItemType type : ItemType.values()) {
            if (productInclusionsMap.getOrDefault(type, false)) {
                productSets.add(items.stream().filter(item -> item.getType() == type).collect(Collectors.toSet()));
            }
        }
    }

}
