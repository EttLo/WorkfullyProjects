package it.accenture.stream;

import java.util.HashMap;

public class DeveloperSummaryData {
    private double salarySum;
    private int ageProduct = 1;

    public static DeveloperSummaryData addDeveloper(DeveloperSummaryData dsd, Developer d) {
        dsd.salarySum += d.getSalary();
        dsd.ageProduct *= d.getAge();
        return dsd;
    }

    public static DeveloperSummaryData combine(DeveloperSummaryData dsd1, DeveloperSummaryData dsd2) {
        DeveloperSummaryData dsd = new DeveloperSummaryData();
        dsd.salarySum = dsd1.salarySum + dsd2.salarySum;
        dsd.ageProduct = dsd1.ageProduct * dsd2.ageProduct;
        return dsd;
    }

    public double getSalarySum() {
        return salarySum;
    }

    public int getAgeProduct() {
        return ageProduct;
    }
}
