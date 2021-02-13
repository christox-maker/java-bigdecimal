package com.project;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculateRates {

  private static final int ONE_HUNDRED = 100;
  private static final int ONE_YEAR = 365;
  private static final int ONE = 1;
  private static final int AFTER_POINT = 4;

  public double calculateRate(double endPrice, double price) {
    return round(((endPrice * ONE_HUNDRED) / price) - ONE_HUNDRED, AFTER_POINT);
  }

  public double calculateAnnualRate(double rate, int restDays) {
    return round((Math.pow((ONE + rate / ONE_HUNDRED), ((double) ONE_YEAR / (double) restDays)) - ONE) * ONE_HUNDRED, AFTER_POINT);
  }

  private double round(double value, int places) {
    if (places < 0) throw new IllegalArgumentException();

    BigDecimal bd = new BigDecimal(Double.toString(value));
    bd = bd.setScale(places, RoundingMode.HALF_UP);
    return bd.doubleValue();
  }
}
