package com.project;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class MainAnnualRate {

  public static void main(String[] args) {

    ReadDataCsv readDataCsv = new ReadDataCsv();
    CalculateRates calculateRates = new CalculateRates();

    List<ModelCsv> modelsCsv = readDataCsv.getDataFromCsvFile("C:\\Users\\User\\Documents\\annual_rate_with_result_prep.csv");

    System.out.println("");
    log.info("Calculating rates start!");

    List<RateWithRestDays> rates = new ArrayList<>(modelsCsv.size());

    for (ModelCsv modelCsv : modelsCsv) {
      double rate = calculateRates.calculateRate(modelCsv.getEndPrice(), modelCsv.getPrice());
      rates.add(RateWithRestDays.builder().rate(rate).restDays(modelCsv.getRestDays()).build());
    }

    log.info("Calculating end! rates size: {}", rates.size());

    List<Double> annualRates = new ArrayList<>(rates.size());

    System.out.println("");
    log.info("Calculating annual rates start!");
    for (RateWithRestDays rateWithRestDays : rates) {
      double annualRate = calculateRates.calculateAnnualRate(rateWithRestDays.getRate(), rateWithRestDays.getRestDays());
      annualRates.add(annualRate);
    }

    log.info("Calculating end! annual rates size: {}", annualRates.size());

    System.out.println("");
    log.info("Final result for w5");
    log.info("rate: " + rates.get(0).getRate());
    log.info("annualRate: " + annualRates.get(0));
  }
}
