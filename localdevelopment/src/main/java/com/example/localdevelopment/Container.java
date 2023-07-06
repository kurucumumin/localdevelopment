package com.example.localdevelopment;

public class Container {
    private String uuid;
    private String dateTime;
    private double decimal1;
    private double decimal2;
    private String calculationResult;
    private String md5Hash;

    // Getter and Setter methods

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public double getDecimal1() {
        return decimal1;
    }

    public void setDecimal1(double decimal1) {
        this.decimal1 = decimal1;
    }

    public double getDecimal2() {
        return decimal2;
    }

    public void setDecimal2(double decimal2) {
        this.decimal2 = decimal2;
    }

    public String getCalculationResult() {
        return calculationResult;
    }

    public void setCalculationResult(String calculationResult) {
        this.calculationResult = calculationResult;
    }

    public String getMd5Hash() {
        return md5Hash;
    }

    public void setMd5Hash(String md5Hash) {
        this.md5Hash = md5Hash;
    }
}
