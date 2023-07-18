package org.partyplanner;


import java.time.LocalDate;

public record Person(
        JobTitle title,
        Name name,
        String username,
        LocalDate birthday,
        BloodGroup bloodGroup
) {
}
