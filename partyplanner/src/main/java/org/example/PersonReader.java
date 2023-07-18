package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PersonReader {
    public List<Person> readAllPersons(String fileName) {
        try {
            List<String> lines = Files.readAllLines(Path.of(fileName));
            return lines.stream()
                    .map(line -> {
                        List<String> parts = parseLine(line);
                        List<String> trimmedParts = trimAll(parts);
                        return new Person(
                                new JobTitle(trimmedParts.get(0)),
                                new Name(trimmedParts.get(1), trimmedParts.get(2)),
                                trimmedParts.get(3),
                                parseDate(trimmedParts.get(4)),
                                parseBloodGroup(trimmedParts.get(5))
                        );
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Cannot read input", e);
        }
    }

    private static LocalDate parseDate(String dateText) {
        return LocalDate.parse(dateText, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    private static BloodGroup parseBloodGroup(String bloodGroupString) {
        return Arrays.stream(BloodGroup.values()).filter(bg -> bg.shortName().equals(bloodGroupString)).findFirst().orElseThrow();
    }

    private List<String> trimAll(List<String> parts) {
        List<String> trimmedParts = new LinkedList<>();
        for (String part : parts) {
            trimmedParts.add(part.trim());
        }
        return trimmedParts;
    }

    private static List<String> parseLine(String line) {
        return Arrays.asList(line.split(";"));
    }
}
