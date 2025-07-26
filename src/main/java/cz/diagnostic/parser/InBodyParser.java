package cz.diagnostic.parser;

import cz.diagnostic.model.MetricEntry;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class InBodyParser {
    public static List<MetricEntry> parse(File excelFile, String defaultDate) throws Exception {
        List<MetricEntry> entries = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(new FileInputStream(excelFile));
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue;

            // Čti ID
            Cell idCell = row.getCell(1); // sloupec 2. ID
            if (idCell == null) continue;
            String id = idCell.getStringCellValue().replaceAll("[^0-9]", "");

            double weight = parseCommaNumber(row.getCell(6));     // váha
            double smm    = parseCommaNumber(row.getCell(234));   // Skeletal Muscle Mass
            double ffm    = parseCommaNumber(row.getCell(65));    // Fat Free Mass
            double fatPct = parseCommaNumber(row.getCell(63));    // % tuku

            entries.add(new MetricEntry(id, defaultDate, "InBody", "Weight", weight, "kg"));
            entries.add(new MetricEntry(id, defaultDate, "InBody", "SMM", smm, "kg"));
            entries.add(new MetricEntry(id, defaultDate, "InBody", "FFM", ffm, "kg"));
            entries.add(new MetricEntry(id, defaultDate, "InBody", "FatPercent", fatPct, "%"));
        }

        workbook.close();
        return entries;
    }

    private static double parseCommaNumber(Cell cell) {
        if (cell == null) return 0.0;

        try {
            String raw = cell.getStringCellValue()
                    .replace(",", ".")
                    .replace("%", "")
                    .replace("-", "")
                    .replace(" ", "")
                    .trim();

            if (raw.isEmpty()) return 0.0;

            return Double.parseDouble(raw);
        } catch (Exception e) {
            System.err.println("⚠️ Chybná hodnota v buňce: " + cell + " (" + e.getMessage() + ")");
            return 0.0;
        }
    }
}
