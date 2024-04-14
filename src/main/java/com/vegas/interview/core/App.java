package com.vegas.interview.core;

import java.io.IOException;
import java.util.List;

import com.vegas.interview.io.CommandLineParser;
import com.vegas.interview.io.PackageExporter;
import com.vegas.interview.model.ItemPackage;

public class App {
    private CommandLineParser parser;
    private PackageBuilder builder;
    private PackageSorter sorter;

    public App(String[] args) throws IOException {
        parser = new CommandLineParser(args);
        if (!parser.isValid()) {
            showUsageAndErrors(parser);
            return;
        }
        this.builder = new PackageBuilder(parser.getInputFileName(), parser.getExportFormat());
        this.sorter = new PackageSorter(parser.getExportFormat());
    }

    public void run() throws IOException {
        List<ItemPackage> packages = builder.buildPackages(parser.getMinPrice(), parser.getMaxPrice());
        sorter.sortItemPackages(packages);
        PackageExporter.exportToFile(parser.getOutputFileName(), packages);
        //System.out.println("Exported packages to " + parser.getOutputFileName());
    }

    private void showUsageAndErrors(CommandLineParser parser) {
        System.out.println("Usage: java com.some.interview.PackageMaker <inputFile> <minPrice>"
                + " <maxPrice> <exportFormat> <outputFile>");
        for (String errMsg : parser.getValidationErrors()) {
            System.out.println("\t" + errMsg);
        }
    }

}
