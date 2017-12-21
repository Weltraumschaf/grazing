package de.weltraumschaf.grazing.formatter;

import de.weltraumschaf.grazing.model.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public final class CsvFormatter implements Formatter {

    private static final Collection<String> HEADER_NAMES = Arrays.asList("Name",
        "ISIN",
        "Fondsoberkategorie",
        "Unterkategorie",
        "Fondsgesellschaft",
        "Gesamtkosten (% p.a.)",
        "Fondswaehrung",
        "Fondsvermoegen",
        "Auflagedatum",
        "Sparplan",
        "Nachbildung",
        "Ertragsverwendung",
        "Verteilung nach Branchen",
        "Verteilung nach Länder/Regionen",
        "größte Positionen",
        "Bemerkungen",
        "url");
    private static final String FIELD_SEPARATOR = ",";
    private static final char FIELD_DELIMITER = '"';
    private static final String ROW_SEPARATOR = "\n";

    @Override
    public String format(final Wertpapier w) {
        return new StringBuilder().append(wrapWithWuotes(w.getName()))
            .append(FIELD_SEPARATOR)
            .append(wrapWithWuotes(w.getIsin()))
            .append(FIELD_SEPARATOR)
            .append(wrapWithWuotes(w.getFondsoberkategorie()))
            .append(FIELD_SEPARATOR)
            .append(wrapWithWuotes(w.getUnterkategorie()))
            .append(FIELD_SEPARATOR)
            .append(wrapWithWuotes(w.getFondsgesellschaft()))
            .append(FIELD_SEPARATOR)
            .append(wrapWithWuotes(w.getGesamtkosten().toString()))
            .append(FIELD_SEPARATOR)
            .append(wrapWithWuotes(w.getFondswaehrung().getDisplayValue()))
            .append(FIELD_SEPARATOR)
            .append(wrapWithWuotes(w.getFondsvermoegen()))
            .append(FIELD_SEPARATOR)
            .append(wrapWithWuotes(w.getAuflagedatum()))
            .append(FIELD_SEPARATOR)
            .append(wrapWithWuotes(w.getSparplan()))
            .append(FIELD_SEPARATOR)
            .append(wrapWithWuotes(w.getNachbildung()))
            .append(FIELD_SEPARATOR)
            .append(wrapWithWuotes(w.getErtragsverwendung()))
            .append(FIELD_SEPARATOR)
            .append(wrapWithWuotes(
                w.getVerteilungNachBranchen()
                    .stream()
                    .map(this::format).collect(Collectors.joining(ROW_SEPARATOR))))
            .append(FIELD_SEPARATOR)
            .append(wrapWithWuotes(
                w.getVerteilungNachLaenderRegionen()
                    .stream()
                    .map(this::format).collect(Collectors.joining(ROW_SEPARATOR))))
            .append(FIELD_SEPARATOR)
            .append(wrapWithWuotes(
                w.getGreosstePositionen()
                    .stream()
                    .map(this::format).collect(Collectors.joining(ROW_SEPARATOR))))
            .append(FIELD_SEPARATOR)
            // Bemerkungen.
            .append(FIELD_SEPARATOR)
            .append(wrapWithWuotes(w.getUrl()))
            .append(ROW_SEPARATOR)
            .toString();
    }

    private String format(final ShareHolder s) {
        return "- " + s.getName() + ' ' + s.getProzent() + " %";
    }

    @Override
    public String format(final Collection<Wertpapier> w) {
        final StringBuilder buffer = new StringBuilder();
        createHeader(buffer);
        w.stream().map(this::format).forEach(buffer::append);
        return buffer.toString();
    }

    private void createHeader(final StringBuilder buffer) {
        buffer.append(HEADER_NAMES.stream()
            .map(this::wrapWithWuotes)
            .collect(Collectors.joining(FIELD_SEPARATOR)))
            .append(ROW_SEPARATOR);
    }

    private String wrapWithWuotes(final String in) {
        return FIELD_DELIMITER + in + FIELD_DELIMITER;
    }
}
