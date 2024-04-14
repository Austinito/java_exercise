package com.vegas.interview.core;

import com.vegas.interview.model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

class PackageBuilderTest {
    private PackageBuilder packageBuilder;
    private List<Item> items;

    @BeforeEach
    private void simpleSetUp() {
        items = new ArrayList<>();
        items.add(new Hotel(1, new BigDecimal("100")));
        items.add(new Show(2, new BigDecimal("50")));
        items.add(new Tour(3, new BigDecimal("30")));

        packageBuilder = new PackageBuilder(items, "hst");
    }

    @Test
    void allTypesIncluded() {
        List<ItemPackage> result = packageBuilder.buildPackages(new BigDecimal("80"), new BigDecimal("200"));
        assertTrue(result.size() == 1);
        assertTrue(result.get(0).getTotalPrice().compareTo(new BigDecimal("180")) == 0);
    }

    @Test
    void noValidCombination() {
        List<ItemPackage> result = packageBuilder.buildPackages(new BigDecimal("200"), new BigDecimal("300"));
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void minMaxPriceEdgeCase() {
        List<ItemPackage> result = packageBuilder.buildPackages(new BigDecimal("180"), new BigDecimal("180"));
        assertTrue(result.size() == 1);
        assertTrue(result.get(0).getTotalPrice().compareTo(new BigDecimal("180")) == 0);
    }

}
