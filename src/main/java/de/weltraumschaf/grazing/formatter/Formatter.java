package de.weltraumschaf.grazing.formatter;

import de.weltraumschaf.grazing.model.Wertpapier;

import java.util.Collection;

public interface Formatter {
    String format(Wertpapier w);
    String format(Collection<Wertpapier> w);
}
