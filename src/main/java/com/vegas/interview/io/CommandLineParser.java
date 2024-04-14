package com.vegas.interview.io;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/* CommandLineParser
 * Interprets user input and collects all error to user if anything is invalid.
 */
public class CommandLineParser {
    private String inputFileName;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private String exportFormat;
    private String outputFileName;

    final private List<String> validationErrors = new ArrayList<>();

    public CommandLineParser(String[] args) {
        if (args.length != 5) {
            validationErrors.add("Invalid number of arguments. Expected 5 arguments.");
        } else {
            assignProperties(args);
        }
    }

    public String getInputFileName() {
        return inputFileName;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public String getExportFormat() {
        return exportFormat;
    }

    public String getOutputFileName() {
        return outputFileName;
    }

    public boolean isValid() {
        return validationErrors.isEmpty();
    }

    public List<String> getValidationErrors() {
        return validationErrors;
    }

    // Assumption: Do not worry about breaking the bounds of Double.
    private void assignProperties(String[] args) {

        inputFileName = args[0].trim();

        try {
            minPrice = new BigDecimal(args[1]);
            if (minPrice.compareTo(BigDecimal.ZERO) == -1) {
                throw new NumberFormatException("Must be non-negative");
            }
        } catch (NumberFormatException e) {
            validationErrors.add("Invalid minimum price: " + args[1] + ". It must be a non-negative number.");
        }

        try {
            maxPrice = new BigDecimal(args[2]);
            if (maxPrice.compareTo(BigDecimal.ZERO) == -1) {
                throw new NumberFormatException("Maximum price cannot be negative.");
            }
            if (maxPrice.compareTo(minPrice) == -1) {
                throw new NumberFormatException("Maximum price cannot be less than minimum price.");
            }
        } catch (NumberFormatException e) {
            validationErrors.add("Invalid maximum price: " + args[2]
                    + ". It must be a non-negative number, and not less than minimum price.");
        }

        exportFormat = args[3].trim().toLowerCase();
        if (!exportFormat.matches("[phst]*")) {
            validationErrors
                    .add("Invalid sort order: " + args[3] + ". It must only contain letters 'p', 'h', 's', 't'.");
        }

        outputFileName = args[4].trim();
    }

}
