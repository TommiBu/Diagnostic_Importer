package main.java.parser;

import main.java.model.MetricEntry;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class InBodyParser {
    public static List<MetricEntry> parse(File excelFile, String defaultDate) throws Exception {
        List<MetricEntry> entries = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(new FileInputStream(excelFile));
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue;

            String id = row.getCell(1).getStringCellValue().trim(); // 2. ID
            id = id.replaceAll("[^0-9]", ""); // očistit čísla

            double weight = parseCommaNumber(row.getCell(6));   // váha
            double smm = parseCommaNumber(row.getCell(234));    // SMM
            double ffm = parseCommaNumber(row.getCell(65));     // FFM
            double fat = parseCommaNumber(row.getCell(63));     // % tuku

            entries.add(new MetricEntry(id, defaultDate, "InBody", "Weight", weight, "kg"));
            entries.add(new MetricEntry(id, defaultDate, "InBody", "SMM", smm, "kg"));
            entries.add(new MetricEntry(id, defaultDate, "InBody", "FFM", ffm, "kg"));
            entries.add(new MetricEntry(id, defaultDate, "InBody", "FatPercent", fat, "%"));
        }

        workbook.close();
        return entries;
    }

    private static double parseCommaNumber(Cell cell) {
        String raw = cell.getStringCellValue().replace(",", ".").replace("%", "").trim();
        return Double.parseDouble(raw);
    }
}
