# Wingate and InBody DocImporter 

This project serves as a simple prototype tool for importing data from exercise and body composition tests (Wingate, InBody) and converting this data into a unified CSV format.

## Features

- Loading data from Excel files (.xlsx)
- Parsing basic metrics from Wingate and InBody
- Saving combined results to data/input.csv
- Handling of incomplete or non-numeric values

## Technology

- Java 17+
- Gradle (build system)
- Apache POI (for reading Excel files)
- Structured according to OOP principles (model / parser / export)

## Usage

1. Place the Wingate.xlsx and InBody.xlsx files in the data/ folder
2. Run the program using ./gradlew run or directly from the IDE
3. The output can be found in the data/input.csv file

## Status

- Development prototype
- Serves as a basis for further data processing by the C++ computing core
- Ready for expansion with additional tests, metrics, and exports
