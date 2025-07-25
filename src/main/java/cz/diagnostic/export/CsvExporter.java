package main.java.export;

import main.java.model.MetricEntry;

import java.io.FileWriter;
import java.util.List;

public class CsvExporter {
    public static void exportToCsv(List<MetricEntry> entries, String outputPath) throws Exception {
        FileWriter writer = new FileWriter(outputPath);
        writer.write("athlete_id,date,test_type,metric_name,value,unit\n");
        for (MetricEntry e : entries) {
            writer.write(String.format("%s,%s,%s,%s,%.2f,%s\n",
                    e.athleteId, e.date, e.testType, e.metricName, e.value, e.unit));
        }
        writer.close();
    }
}
