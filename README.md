# Package Maker

Package Maker is a Java application designed to create packages based on specified item types. It uses command-line arguments to receive input parameters and outputs the results to a specified file.

## Prerequisites

Before you begin, ensure you have met the following requirements:
- Java JDK 11 or later
- Maven (for dependency management and running the project)

## Usage

navigate to project directory (where src folder lives):

```bash
cd java_exercise
```

Build the project using Maven:

```bash
mvn clean install
```

Use package Maker: run when at the root of the project:
```bash
java -cp target/classes com.vegas.interview.PackageMaker <inputFile> <minPrice> <maxPrice> <exportFormat> <outputFile>
```

Replace the placeholders with actual values:

`<inputFile>`: Path to the input file containing item data.
`<minPrice>`: Minimum price filter for packages.
`<maxPrice>`: Maximum price filter for packages.
`<exportFormat>`: Format string specifying which item types to include and their order.
`<outputFile>`: Path where the output packages should be saved.

## Future Improvements

This section outlines potential enhancements to improve the functionality, performance, and maintainability of the Package Maker application:

### 1. Enhanced Data Structures
Currently, the application extensively utilizes lists and sets to manage data. In future cases, we may  want to optimize collections for search operations and improve efficiency, we could consider the following changes:
- **Use of HashSets**: Implementing `HashSet` for operations that require frequent search operations to reduce time complexity from O(n) to O(1).
- **Use of Maps (Dictionaries)**: Utilizing maps to quickly lookup items by key attributes (like ID-type tuple) could dramatically decrease the time required for locating specific items within large datasets.

### 2. Multithreading
The application could see significant performance improvements by implementing multithreading in the following areas:
- **Parallel Data Processing**: For the construction and sorting of packages, where independent computations allow for concurrent execution.
- **Asynchronous I/O Operations**: Reading from and writing to files can be done asynchronously, especially beneficial when dealing with large input and output files.

### 3. Monetary Value Representation
Currently, monetary values are handled with `BigDecimal`, which ensures accuracy but might impact performance:
- **Integer Representation**: Consider using integer types for prices (e.g., representing currency in the smallest units like cents) to enhance performance in environments where absolute precision of floating-point operations is not critical.

### 4. Logging with SLF4J
Implement a logging framework to replace current logging practices:
- **SLF4J with Logback or Log4j2**: Adopt a SLF4J-compatible library to provide a flexible logging framework that supports different levels of logs (DEBUG, INFO, WARN, ERROR) and can be configured for various outputs (console, files, external systems).

### 5. Centralized Error Handling
Develop a robust error management strategy:
- **Unified Error Handling Method**: Create a method or class dedicated to handling exceptions and errors uniformly across the application, enhancing maintainability and ensuring consistent responses to failures.

These improvements will not only enhance the current capabilities of the Package Maker but also ensure its scalability and maintainability as it evolves to handle more complex scenarios and larger datasets.
