package org.testdatagenerator;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;
import java.util.Locale;

public class TestdataGenerator {
    private static final Faker instance = Faker.instance(Locale.GERMAN);
    public static final int NUMBER_OF_PERSONS = 1_000;

    public static void main(String[] args) throws IOException {
        final PrintStream out = new PrintStream(new FileOutputStream("employees" + NUMBER_OF_PERSONS + ".csv"));

        for (int i = 0; i < NUMBER_OF_PERSONS; i++) {
            Name name = instance.name();
            printValue(out, name.title(), 30);
            printValue(out, name.firstName(), 20);
            printValue(out, name.lastName(), 20);
            printValue(out, name.username(), 30);
            Date birthday = instance.date().birthday();
            String birthdayString = String.format("%1$td.%1$tm.%1$tY", birthday);
            printValue(out, birthdayString, 10);
            printValue(out, name.bloodGroup(), 3);
            out.println();

            if (i % 50_000 == 0) {
                // format with decmial point, show percentage (no decimal places)
                System.out.printf("%,dk (%.0f%%)%n", i / 1000, (double) i / NUMBER_OF_PERSONS * 100);
            }
        }
    }

    private static void printValue(PrintStream out, String value, int numberOfCharacters) {
        if (value.length() > numberOfCharacters) {
            out.print(value.substring(0, numberOfCharacters));
        } else {
            out.print(value);
            for (int i = value.length(); i < numberOfCharacters; i++) {
                out.print(' ');
            }
        }
        out.print(';');
    }
}
