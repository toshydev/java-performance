package org.partyplanner;

import java.io.IOException;
import java.time.Instant;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.time.temporal.ChronoUnit.MILLIS;

public class Main {
    public static void main(String[] args) throws IOException {
        Instant startOfThisApplication = Instant.now();
        String inputFile = "employees10000.csv";
        List<MonthDay> dates = new PersonReader().readAllPersons(inputFile);
        MonthDay biggestParty = findBiggestParty(dates);
        String formattedBiggestParty = biggestParty.format(DateTimeFormatter.ofPattern("dd.MM"));
        System.out.println("Biggest party: " + formattedBiggestParty);
        System.out.println("Time spent: " + MILLIS.between(startOfThisApplication, Instant.now()) + " ms for input file " + inputFile);
    }

    private static MonthDay findBiggestParty(List<MonthDay> dates) {
        MonthDay biggestParty = null;
        Map<MonthDay, Integer> dateMap = new HashMap<>();
        dates.forEach(date -> {
            if (dateMap.containsKey(date)) {
                int oldCount = dateMap.get(date);
                dateMap.replace(date, oldCount + 1);
            } else {
                dateMap.put(date, 0);
            }
        });
        int maxValueInMap = (Collections.max(dateMap.values()));
        for (Map.Entry<MonthDay, Integer> entry : dateMap.entrySet()) {
            if (entry.getValue() == maxValueInMap) {
                biggestParty =  entry.getKey();
            }
        }
        return biggestParty;
    }
}
