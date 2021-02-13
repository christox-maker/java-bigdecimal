package com.project;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModelCsv {

  private String name;
  private double price;
  private Instant nowCol;
  private double endPrice;
  private LocalDate endDate;
  private int restDays;
}
