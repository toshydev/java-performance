package org.partyplanner;

import java.io.IOException;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Person> people = new PersonReader().readAllPersons("employees1000.csv");
        printPersons(people);
        MonthDay biggestParty = findBiggestParty(people);
        String formattedBiggestParty = biggestParty.format(DateTimeFormatter.ofPattern("dd.MM"));
        System.out.println("Biggest party: " + formattedBiggestParty);
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