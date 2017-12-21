package de.weltraumschaf.grazing;

import de.weltraumschaf.commons.application.IO;
import de.weltraumschaf.commons.validate.Validate;
import de.weltraumschaf.grazing.model.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Random;
import java.util.stream.Collectors;

final class Extractor {
    private static final String QUERY_URL = "https://wertpapiere.ing-diba.de/DE/Showpage.aspx?pageID=31&ISIN=%s";
    private final Random rnd = new Random();
    private final IO io;
    private final CliOptions opts;
    private long sleep;

    Extractor(final IO io, final CliOptions opts) {
        super();
        this.io = Validate.notNull(io, "io");
        this.opts = Validate.notNull(opts, "opts");
    }

    Collection<Wertpapier> extract(final Collection<String> isins) {
        return isins.stream().map(isin -> extract(isin)).collect(Collectors.toList());
    }

    Wertpapier extract(final String isin) {
        try {
            debug("Waiting %d ms", sleep);
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        final String url = makeUrl(isin);
        final Document doc;

        try {
            debug("Fetching URL %s", url);
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException(String.format("Can't open URL '%s'!", url), e);
        }

        final Wertpapier.Builder builder = Wertpapier.Builder.create();
        builder.setIsin(isin).setUrl(url);
        extractName(doc, builder);
        extractData(doc, builder);
        extractShareHolders(doc, builder);

        sleep = newRandomSleep();
        return builder.build();
    }


    private void extractName(final Document doc, final Wertpapier.Builder builder) {
        builder.setName(doc.select("div.sh-title").text());
    }

    private long newRandomSleep() {
        return rnd.nextInt(500 - 50 + 1) + 50;
    }

    private String makeUrl(final String isin) {
        return String.format(QUERY_URL, isin);
    }

    private void extractData(final Document doc, final Wertpapier.Builder builder) {
        doc.select("table.product-overview tr")
            .stream()
            .map(row -> row.select("td"))
            .filter(cells -> cells.size() == 2)
            .map(cells -> new KeyValuePair(cells.get(0).text().trim(), cells.get(1).text().trim()))
            .forEach(pair -> {
                switch (pair.key) {
                    case "Sparplan möglich":
                        builder.setSparplan(pair.value);
                        break;
                    case "Fondsoberkategorie":
                        builder.setFondsoberkategorie(pair.value);
                        break;
                    case "Unterkategorie":
                        builder.setUnterkategorie(pair.value);
                        break;
                    case "Auflagedatum":
                        builder.setAuflagedatum(pair.value);
                        break;
                    case "Fondsvermögen":
                        builder.setFondsvermoegen(pair.value);
                        break;
                    case "Fondswährung":
                        builder.setFondswaehrung(Waehrung.forName(pair.value));
                        break;
                    case "Nachbildung":
                        builder.setNachbildung(pair.value);
                        break;
                    case "Ertragsverwendung":
                        builder.setErtragsverwendung(pair.value);
                        break;
                    case "Fondsgesellschaft":
                        builder.setFondsgesellschaft(pair.value);
                        break;
                    case "Gesamtkosten (TER) p.a.":
                    case "Verwaltungsvergütung p.a.":
                        if (!"-".equals(pair.value)) {
                            builder.setGesamtkosten(parsePercent(pair.value));
                        }
                        break;
                }
            });
    }

    private void extractShareHolders(final Document doc, final Wertpapier.Builder builder) {
        final Elements tables = doc.select("table.share-holders");

        if (tables.size() != 6) {
            debug("We expect exactly six share holders tables in the HTML! Given was %s.", tables.size());
            return;
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

        final String beforePercentSign = trimmed.substring(0, trimmed.indexOf('%'));
        return new BigDecimal(beforePercentSign.replace(',', '.'));
    }

    private void debug(final String msg, final Object... args) {
        if (opts.isDebug()) {
            io.println(String.format(msg, args));
        }
    }

    private static class KeyValuePair {
        private final String key;
        private final String value;

        private KeyValuePair(final String key, final String value) {
            super();
            this.key = Validate.notEmpty(key, "key");
            this.value = Validate.notNull(value, "value");
        }
    }
}
