package de.weltraumschaf.grazing;

import de.weltraumschaf.commons.application.ApplicationException;
import de.weltraumschaf.commons.application.IO;
import de.weltraumschaf.commons.validate.Validate;
import de.weltraumschaf.grazing.model.Wertpapier;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

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
        builder.setName(doc.title());
        final Wertpapier product = builder.build();

        io.println(product.getName());

        final Elements rows = doc.select("table.share-holders tr");
        for (Element row : rows) {
            io.println(row.toString());
        }

        return null;
    }

    private String makeUrl(final String isin) {
        return String.format(QUERY_URL, isin);
    }
}
