package cz.diagnostic;

import cz.diagnostic.export.CsvExporter;
import cz.diagnostic.model.MetricEntry;
import cz.diagnostic.parser.InBodyParser;
import cz.diagnostic.parser.WingateParser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            String date = "2025-07-01";

            File wingateFile = new File("data/Wingate.xlsx");
            File inbodyFile = new File("data/InBody.xlsx");

            System.out.println("✅ Načítám data...");
            System.out.println(" - Wingate existuje? " + wingateFile.exists());
            System.out.println(" - InBody existuje? " + inbodyFile.exists());

            List<MetricEntry> all = new ArrayList<>();
            all.addAll(WingateParser.parse(wingateFile, date));
            all.addAll(InBodyParser.parse(inbodyFile, date));

            System.out.println("📦 Celkem metrik: " + all.size());

            File outputFile = new File("data/input.csv");
            CsvExporter.exportToCsv(all, outputFile.getPath());

            System.out.println("✅ Výstup zapsán do: " + outputFile.getPath());

        } catch (Exception e) {
            System.err.println("❌ Chyba při zpracování:");
            e.printStackTrace();
        }
    }
}