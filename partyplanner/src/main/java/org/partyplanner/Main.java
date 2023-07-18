package org.partyplanner;

import java.io.IOException;
import java.time.Instant;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.time.temporal.ChronoUnit.MILLIS;

public class Main {
    public static void main(String[] args) throws IOException {
        Instant startOfThisApplication = Instant.now();
        String inputFile = "employees1000.csv";
        List<Person> people = new PersonReader().readAllPersons(inputFile);
        printPersons(people);
        MonthDay biggestParty = findBiggestParty(people);
        String formattedBiggestParty = biggestParty.format(DateTimeFormatter.ofPattern("dd.MM"));
        System.out.println("Biggest party: " + formattedBiggestParty);
        System.out.println("Time spent: " + MILLIS.between(startOfThisApplication, Instant.now()) + " ms for input file " + inputFile);
    }

    private static MonthDay findBiggestParty(List<Person> people) {
        MonthDay biggestParty = null;
        int biggestPartySize = 0;
        for (Person person : people) {
            int partySize = 0;
            for (Person otherPerson : people) {
                if (haveSameBirthday(person, otherPerson)) {
                    partySize++;
                }
            }
            if (partySize > biggestPartySize) {
                biggestPartySize = partySize;
                biggestParty = MonthDay.from(person.birthday());
            }
        }
        return biggestParty;
    }

    private static boolean haveSameBirthday(Person person, Person otherPerson) {
        return person.birthday().isEqual(otherPerson.birthday());
    }

    private static void printPersons(List<Person> people) {
        System.out.println(people);
    }
}