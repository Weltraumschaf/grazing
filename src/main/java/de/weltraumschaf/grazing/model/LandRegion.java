package de.weltraumschaf.grazing.model;

import de.weltraumschaf.commons.validate.Validate;

import java.util.Objects;

public final class LandRegion {
    private final String name;
    private final float prozent;

    public LandRegion(final String name, final float prozent) {
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
        if (!(o instanceof LandRegion)) {
            return false;
        }

        final LandRegion that = (LandRegion) o;
        return prozent == that.prozent &&
            Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, prozent);
    }

    @Override
    public String toString() {
        return "LandRegion{" +
            "name='" + name + '\'' +
            ", prozent=" + prozent +
            '}';
    }
}
