import com.project.CalculateRates;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculateRatesTest {

  @Test
  public void calculateRateTest() {

    CalculateRates calculateRates = new CalculateRates();

    double endPrice = 1832.284816;
    double price = 1494;

    double expectedResult = 22.6429;

    double rate = calculateRates.calculateRate(endPrice, price);
    System.out.println("rate: " + rate);

    assertEquals(expectedResult, rate, 0.0001);
  }

  @Test
  public void calculateAnnualRateTest() {

    CalculateRates calculateRates = new CalculateRates();

    double rate = 22.6429;
    int restDays = 337;

    double expectedResult = 24.7405;

    double annualRate = calculateRates.calculateAnnualRate(rate, restDays);
    System.out.println("annualRate: " + annualRate);

    assertEquals(expectedResult, annualRate, 0.0001);
  }
}
