package de.weltraumschaf.grazing.model;

import de.weltraumschaf.commons.validate.Validate;

import java.math.BigDecimal;
import java.util.Objects;

public final class LandRegion implements ShareHolder {
    private final String name;
    private final BigDecimal prozent;

    public LandRegion(final String name, final BigDecimal prozent) {
        super();
        this.name = Validate.notEmpty(name, "name");
        this.prozent = prozent;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public BigDecimal getProzent() {
        return prozent;
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof LandRegion)) {
            return false;
        }

        final LandRegion that = (LandRegion) o;
        return Objects.equals(prozent, that.prozent) &&
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
