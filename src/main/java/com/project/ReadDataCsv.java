package com.project;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ReadDataCsv {

  public List<ModelCsv> getDataFromCsvFile(String filePath) {

    log.info("Start reading file.");

    List<ModelCsv> modelCsvList = new ArrayList<>();

    try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
      CSVParser parser = new CSVParserBuilder()
        .withSeparator(';')
        .withIgnoreQuotations(true)
        .build();

      CSVReader csvReader = new CSVReaderBuilder(reader)
        .withSkipLines(1)
        .withCSVParser(parser)
        .build();

      double bigDecimalZero = 0.0;
      modelCsvList = csvReader.readAll().stream()
        .map(dataMap -> {
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
          return ModelCsv.builder()
            .name(dataMap[0])
            .price(dataMap[1].isEmpty() ? 0 : Double.parseDouble(dataMap[1]))
            .nowCol(dataMap[2].isEmpty() ? null : Instant.ofEpochMilli(Long.parseLong(dataMap[2])))
            .endPrice(dataMap[3].isEmpty() ? 0 : Double.parseDouble(dataMap[3]))
            .endDate(dataMap[4].isEmpty() ? null : LocalDate.parse(dataMap[4], formatter))
            .restDays(dataMap[5].isEmpty() ? 0 : Integer.parseInt(dataMap[5]))
            .build();
        })
        .filter(data -> data.getPrice() != bigDecimalZero)
        .collect(Collectors.toList());

      reader.close();
      csvReader.close();

      log.info("End reading file. size: {}", modelCsvList.size());
    } catch (IOException e) {
      e.printStackTrace();
    }

    return modelCsvList;
  }
}
