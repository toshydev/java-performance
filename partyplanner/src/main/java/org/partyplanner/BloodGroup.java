package org.partyplanner;

public enum BloodGroup {
    A_POSITIVE("A+"),
    A_NEGATIVE("A-"),
    B_POSITIVE("B+"),
    B_NEGATIVE("B-"),
    AB_POSITIVE("AB+"),
    AB_NEGATIVE("AB-"),
    O_POSITIVE("O+"),
    O_NEGATIVE("O-");

    private final String shortName;

    BloodGroup(String shortName) {
        this.shortName = shortName;
    }

    public String shortName() {
        return shortName;
    }
}
