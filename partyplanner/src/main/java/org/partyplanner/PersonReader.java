package org.partyplanner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PersonReader {
    public List<Person> readAllPersons(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(fileName));
        return convertLinesToPersons(lines);
    }

    private List<Person> convertLinesToPersons(List<String> lines) {
        List<Person> people = new LinkedList<>();
        for (String line : lines) {
            List<String> parts = parseLine(line);
            List<String> trimmedParts = trimAll(parts);
            people.add(new Person(
                    new JobTitle(trimmedParts.get(0)),
                    new Name(trimmedParts.get(1), trimmedParts.get(2)),
                    trimmedParts.get(3),
                    parseDate(trimmedParts.get(4)),
                    parseBloodGroup(trimmedParts.get(5))
            ));
        }
        return people;
    }

    private static LocalDate parseDate(String dateText) {
        return LocalDate.parse(dateText, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    private static BloodGroup parseBloodGroup(String bloodGroupString) {
        for (BloodGroup bloodGroup : BloodGroup.values()) {
            if (bloodGroup.shortName().equals(bloodGroupString)) {
                return bloodGroup;
            }
        }
        throw new RuntimeException("Unknown blood group: " + bloodGroupString);
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
