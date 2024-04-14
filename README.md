# Package Maker

Package Maker is a Java application designed to create packages based on specified item types. It uses command-line arguments to receive input parameters and outputs the results to a specified file.

## Prerequisites

Before you begin, ensure you have met the following requirements:
- Java JDK 11 or later
- Maven (for dependency management and running the project)

## Usage

navigate to project directory:

```bash
cd package-maker
```

Build the project using Maven:

```bash
mvn clean install
```

Use package Maker: run when at the root of the project:
```bash
java -cp target/package-maker-1.0.jar com.some.interview.PackageMaker <inputFile> <minPrice> <maxPrice> <exportFormat> <outputFile>
```

Replace the placeholders with actual values:

`<inputFile>`: Path to the input file containing item data.
`<minPrice>`: Minimum price filter for packages.
`<maxPrice>`: Maximum price filter for packages.
`<exportFormat>`: Format string specifying which item types to include and their order.
`<outputFile>`: Path where the output packages should be saved.
