package cz.diagnostic.parser;

import cz.diagnostic.model.MetricEntry;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class WingateParser {
    public static List<MetricEntry> parse(File excelFile, String defaultDate) throws Exception {
        List<MetricEntry> entries = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(new FileInputStream(excelFile));
        Sheet sheet = workbook.getSheet("Výsledky wingate");

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue;

            String id = String.valueOf((int) safeGetDouble(row.getCell(0))); // ID může být i jako text

            double peakPower = safeGetDouble(row.getCell(5));
            double fatigueIndex = safeGetDouble(row.getCell(11));
            double totalWork = safeGetDouble(row.getCell(13));

            entries.add(new MetricEntry(id, defaultDate, "Wingate", "PeakPower", peakPower, "W"));
            entries.add(new MetricEntry(id, defaultDate, "Wingate", "FatigueIndex", fatigueIndex, "%"));
            entries.add(new MetricEntry(id, defaultDate, "Wingate", "TotalWork", totalWork, "kJ"));
        }

        workbook.close();
        return entries;
    }

    private static double safeGetDouble(Cell cell) {
        if (cell == null) return 0.0;

        return switch (cell.getCellType()) {
            case NUMERIC -> cell.getNumericCellValue();
            case STRING -> {
                try {
                    yield Double.parseDouble(cell.getStringCellValue().replace(",", ".").replace("%", ""));
                } catch (NumberFormatException e) {
                    System.err.println("⚠️ Nelze převést buňku na číslo: " + cell.getStringCellValue());
                    yield 0.0;
                }
            }
            default -> 0.0;
        };
    }
}
