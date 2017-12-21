package de.weltraumschaf.grazing;

import de.weltraumschaf.commons.application.ApplicationException;
import de.weltraumschaf.commons.application.IO;
import de.weltraumschaf.commons.validate.Validate;
import de.weltraumschaf.grazing.model.Branche;
import de.weltraumschaf.grazing.model.LandRegion;
import de.weltraumschaf.grazing.model.Position;
import de.weltraumschaf.grazing.model.Wertpapier;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.math.BigDecimal;

final class Extractor {
    private static final String QUERY_URL = "https://wertpapiere.ing-diba.de/DE/Showpage.aspx?pageID=31&ISIN=%s";
    private final IO io;

    Extractor(final IO io) {
        super();
        this.io = Validate.notNull(io, "io");
    }

    Wertpapier extract(final String isin) throws ApplicationException {
        final String url = makeUrl(isin);
        final Document doc;

        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new ApplicationException(ExitCodes.FATAL, String.format("Can't open URL '%s'!", url), e);
        }

        final Wertpapier.Builder builder = Wertpapier.Builder.create();
        builder.setUrl(url);
        extractName(doc, builder);
        extractShareHolders(doc, builder);

        return builder.build();
    }

    private String makeUrl(final String isin) {
        return String.format(QUERY_URL, isin);
    }

    private void extractName(final Document doc, final Wertpapier.Builder builder) {
        builder.setName(doc.select("div.sh-title").text());
    }

    private void extractShareHolders(final Document doc, final Wertpapier.Builder builder) {
        final Elements tables = doc.select("table.share-holders");

        if (tables.size() != 6) {
            throw new IllegalStateException(
                String.format("We expect exactly six share holders tables in the HTML! Given was %s.", tables.size()));
        }

        extractBranchen(tables, builder);
        extractLaenderRegionen(tables, builder);
        extractGroesstePositionen(tables, builder);
    }

    private void extractBranchen(final Elements tables, final Wertpapier.Builder builder) {
        // First two tables are "Verteilung nach Branchen".
        selectRows(tables, 0, 1)
            .stream()
            .map(row -> {
                final Elements cells = row.select("td");
                final Element name = cells.get(1);
                final Element percent = cells.get(2);
                return new Branche(name.text(), parsePercent(percent.text()));
            }).forEach(builder::addBranche);
    }

    private void extractLaenderRegionen(final Elements tables, final Wertpapier.Builder builder) {
        // Third nd fourth tables are "Verteilung nach Ländern/Regionen".
        selectRows(tables, 2, 3)
            .stream()
            .map(row -> {
                final Elements cells = row.select("td");
                final Element name = cells.get(1);
                final Element percent = cells.get(2);
                return new LandRegion(name.text(), parsePercent(percent.text()));
            }).forEach(builder::addLaenderRegionen);
    }

    private void extractGroesstePositionen(final Elements tables, final Wertpapier.Builder builder) {
        // The last two tables are "Größte Positionen ".
        selectRows(tables, 4, 5)
            .stream()
            .map(row -> {
                final Elements cells = row.select("td");
                final Element name = cells.get(1);
                final Element percent = cells.get(2);
                return new Position(name.text(), parsePercent(percent.text()));
            }).forEach(builder::addPositionen);
    }

    private Elements selectRows(final Elements tables, final int firstTableIndex, final int secondTableIndex) {
        final Elements rows = tables.get(firstTableIndex).select("tr");
        rows.addAll(tables.get(secondTableIndex).select("tr"));
        return rows;
    }

    BigDecimal parsePercent(final String percent) {
        final String trimmed = percent.trim();

        if (trimmed.isEmpty()) {
            return new BigDecimal(0);
        }

        return new BigDecimal(trimmed.replace(',', '.').replace("%", ""));
    }

}
