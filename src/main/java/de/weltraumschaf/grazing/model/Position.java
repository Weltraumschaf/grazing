package de.weltraumschaf.grazing.model;

import de.weltraumschaf.commons.validate.Validate;

import java.util.Objects;

public final class Position {
    private final String name;
    private final float prozent;

    public Position(final String name, final float prozent) {
        super();
        this.name = Validate.notEmpty(name, "name");
        this.prozent = prozent;
    }

    public String getName() {
        return name;
    }

    public float getProzent() {
        return prozent;
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof Position)) {
            return false;
        }

        final Position position = (Position) o;
        return prozent == position.prozent &&
            Objects.equals(name, position.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, prozent);
    }

    @Override
    public String toString() {
        return "Position{" +
            "name='" + name + '\'' +
            ", prozent=" + prozent +
            '}';
    }
}