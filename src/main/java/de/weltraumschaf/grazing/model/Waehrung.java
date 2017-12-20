package de.weltraumschaf.grazing.model;

import de.weltraumschaf.commons.validate.Validate;

public enum Waehrung {
    NA("n/a"), EUR("EUR"), USD("USD");

    private final String displayValue;

    Waehrung(final String displayValue) {
        this.displayValue = Validate.notEmpty(displayValue, "displayValue");
    }

    public String getDisplayValue() {
        return displayValue;
    }

    @Override
    public String toString() {
        return "Waehrung{" +
            "displayValue='" + displayValue + '\'' +
            '}';
    }
}
