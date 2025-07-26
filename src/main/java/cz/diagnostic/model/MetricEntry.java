package cz.diagnostic.model;

import java.time.LocalDate;

public class MetricEntry {
    public String athleteId;
    public String date;
    public String testType;
    public String metricName;
    public double value;
    public String unit;

    public MetricEntry(String athleteId, String date, String testType, String metricName, double value, String unit) {
        this.athleteId = athleteId;
        this.date = date;
        this.testType = testType;
        this.metricName = metricName;
        this.value = value;
        this.unit = unit;
    }
}
