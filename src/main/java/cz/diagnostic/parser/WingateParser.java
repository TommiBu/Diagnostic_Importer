package main.java.parser;

import main.java.model.MetricEntry;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class WingateParser {
    public static List<MetricEntry> parse(File excelFile, String defaultDate) throws Exception {
        List<MetricEntry> entries = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(new FileInputStream(excelFile));
        Sheet sheet = workbook.getSheet("Výsledky wingate");

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue;

            String id = String.valueOf((int) row.getCell(0).getNumericCellValue());
            entries.add(new MetricEntry(id, defaultDate, "Wingate", "PeakPower", row.getCell(5).getNumericCellValue(), "W"));
            entries.add(new MetricEntry(id, defaultDate, "Wingate", "FatigueIndex", row.getCell(11).getNumericCellValue(), "%"));
            entries.add(new MetricEntry(id, defaultDate, "Wingate", "TotalWork", row.getCell(13).getNumericCellValue(), "kJ"));
        }

        workbook.close();
        return entries;
    }
}
