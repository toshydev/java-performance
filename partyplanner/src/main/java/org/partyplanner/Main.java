package org.partyplanner;

import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    public static void main(String[] args) {
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
            int partySize = countPartySize(people, person);
            if (partySize > biggestPartySize) {
                biggestPartySize = partySize;
                biggestParty = MonthDay.from(person.birthday());
            }
        }
        return biggestParty;
    }

    private static int countPartySize(List<Person> people, Person person) {
        int partySize = 0;
        for (Person otherPerson : people) {
            if (person.birthday().isEqual(otherPerson.birthday())) {
                partySize++;
            }
        }
        return partySize;
    }

    private static void printPersons(List<Person> people) {
        System.out.println(people);
    }
}