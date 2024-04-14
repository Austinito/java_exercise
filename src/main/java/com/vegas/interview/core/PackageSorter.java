package com.vegas.interview.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.vegas.interview.model.ItemPackage;
import com.vegas.interview.model.ItemType;

public class PackageSorter {
    ArrayList<ItemType> sortPriority;

    public PackageSorter(String sortString) {
        sortPriority = new ArrayList<>();
        for (char c : sortString.toLowerCase().toCharArray()) {
            switch (c) {
                case 'h':
                    sortPriority.add(ItemType.HOTEL);
                    break;
                case 's':
                    sortPriority.add(ItemType.SHOW);
                    break;
                case 't':
                    sortPriority.add(ItemType.TOUR);
                    break;
                case 'p':
                    sortPriority.add(ItemType.PACKAGE);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid sort key: " + c);
            }
        }
    }

    public List<ItemPackage> sortItemPackages(List<ItemPackage> packages) {
        Collections.sort(packages, new Comparator<ItemPackage>() {
            @Override
            public int compare(ItemPackage p1, ItemPackage p2) {
                for (ItemType type : sortPriority) {
                    int comparison = p1.getPriceOf(type).compareTo(p2.getPriceOf(type));
                    if (comparison != 0)
                        return comparison;
                }
                return 0;
            }
        });

        return packages;
    }

}
