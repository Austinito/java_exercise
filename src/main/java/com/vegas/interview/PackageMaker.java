package com.vegas.interview;

import java.io.IOException;
import java.util.List;

import com.vegas.interview.core.PackageBuilder;
import com.vegas.interview.core.PackageSorter;
import com.vegas.interview.io.CommandLineParser;
import com.vegas.interview.io.PackageExporter;
import com.vegas.interview.model.ItemPackage;

public class PackageMaker {

    public static void main(String[] args) {
        CommandLineParser parser = new CommandLineParser(args);
        if (!parser.isValid()) {
            displayUsageAndErrors(parser);
            return;
        }

        processPackages(parser);
    }

    private static void displayUsageAndErrors(CommandLineParser parser) {
        System.out.println("Usage: java com.some.interview.PackageMaker <inputFile> <minPrice>"
                + " <maxPrice> <exportFormat> <outputFile>");
        for (String errMsg : parser.getValidationErrors()) {
            System.out.println("\t" + errMsg);
        }
    }

    private static void processPackages(CommandLineParser parser) {
        try {
            List<ItemPackage> validPackages = buildAndSortPackage(parser);
            PackageExporter.exportToFile(parser.getOutputFileName(), validPackages);
            System.out.println("Exported packages to " + parser.getOutputFileName());
        } catch (IOException e) {
            System.err.println("Invalid input file path given: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Bad data found in given data file:\n\t" + e.getMessage());
        }
    }

    private static List<ItemPackage> buildAndSortPackage(CommandLineParser parser) throws IOException {
        PackageBuilder builder = new PackageBuilder(parser.getInputFileName(), parser.getExportFormat());
        List<ItemPackage> validPackages = builder.buildPackages(parser.getMinPrice(), parser.getMaxPrice());

        System.out.println(validPackages.size());

        PackageSorter sorter = new PackageSorter(parser.getExportFormat());
        sorter.sortItemPackages(validPackages);
        return validPackages;
    }

}
