package com.vegas.interview.io;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CommandLineParserTest {

    @Test
    void validArguments() {
        String[] args = {"items.dat", "50", "200.50", "phst", "phst.dat"};
        CommandLineParser parser = new CommandLineParser(args);
        assertTrue(parser.isValid());
        assertEquals("items.dat", parser.getInputFileName());
        assertEquals(50.0, parser.getMinPrice());
        assertEquals(200.50, parser.getMaxPrice());
        assertEquals("phst", parser.getExportFormat());
        assertEquals("phst.dat", parser.getOutputFileName());
    }

    @Test
    void invalidNumberOfArguments() {
        String[] args = {"items.dat", "50"};
        CommandLineParser parser = new CommandLineParser(args);
        assertFalse(parser.isValid());
        assertTrue(parser.getValidationErrors().contains("Invalid number of arguments. Expected 5 arguments."));
    }

    @Test
    void invalidMinPrice() {
        String[] args = {"items.dat", "-1", "200", "phst", "phst.dat"};
        CommandLineParser parser = new CommandLineParser(args);
        assertFalse(parser.isValid());
        assertTrue(parser.getValidationErrors().contains("Invalid minimum price: -1. It must be a non-negative number."));
    }

    @Test
    void invalidMaxPriceNegative() {
        String[] args = {"items.dat", "50", "-1", "phst", "phst.dat"};
        CommandLineParser parser = new CommandLineParser(args);
        assertFalse(parser.isValid());
        assertTrue(parser.getValidationErrors().contains("Invalid maximum price: -1. It must be a non-negative number, and not less than minimum price."));
    }

    @Test
    void invalidMaxPriceLessThanMin() {
        String[] args = {"items.dat", "50", "20", "phst", "phst.dat"};
        CommandLineParser parser = new CommandLineParser(args);
        assertFalse(parser.isValid());
        assertTrue(parser.getValidationErrors().contains("Invalid maximum price: 20. It must be a non-negative number, and not less than minimum price."));
    }


}
