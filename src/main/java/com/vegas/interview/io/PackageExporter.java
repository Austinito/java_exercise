package com.vegas.interview.io;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import com.vegas.interview.model.ItemPackage;
import com.vegas.interview.model.ItemType;

public class PackageExporter {

    public static void exportToFile(String fileName, Iterable<ItemPackage> packages) throws IOException {
        OutputStream fout = Files.newOutputStream(Paths.get(fileName), StandardOpenOption.CREATE,
                StandardOpenOption.WRITE);

        for (ItemPackage itemPackage : packages) {
            fout.write((getExportFormat(itemPackage) + '\n').getBytes());
        }

        fout.flush();
        fout.close();
    }

    // Expected format:
    // PACKAGE\t[package_price]\tHOTEL\t[hotel_id]\t[price]\tSHOW\t[show_id]\t[price]\TOUR\t[tour_id]\t[price]\t
    private static String getExportFormat(ItemPackage itemPackage) {
        StringBuilder builder = new StringBuilder();

        // Note: I only use `doubleValue()` to make it exact match with given examples.
        // Otherwise, I'd prefer to keep it a BigDecimal.
        builder.append("PACKAGE\t").append(itemPackage.getTotalPrice().doubleValue());
        appendItemDetails(builder, itemPackage, ItemType.HOTEL);
        appendItemDetails(builder, itemPackage, ItemType.SHOW);
        appendItemDetails(builder, itemPackage, ItemType.TOUR);
        return builder.toString();
    }

    private static void appendItemDetails(StringBuilder builder, ItemPackage itemPackage, ItemType itemType) {
        // I only append the extra tabs when it's not present to make it exact match
        // with given examples.
        itemPackage.getItem(itemType)
                .ifPresentOrElse(p -> builder.append('\t').append(itemType.getDisplayName()).append('\t')
                        .append(p.getId()).append('\t').append(p.getPrice().doubleValue()),
                        () -> builder.append("\t\t\t"));
    }

}
