package org.partyplanner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PersonReader {
    public List<MonthDay> readAllPersons(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(fileName));
        return convertLinesToDates(lines);
    }

    private List<MonthDay> convertLinesToDates(List<String> lines) {
        return lines.stream()
                .map(line -> MonthDay.from(parseDate(line.split(";")[4].trim())))
                .toList();
    }

    private static LocalDate parseDate(String dateText) {
        return LocalDate.parse(dateText, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}
