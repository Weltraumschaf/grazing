package de.weltraumschaf.grazing.formatter;

import de.weltraumschaf.grazing.Constants;
import de.weltraumschaf.grazing.FileReader;
import de.weltraumschaf.grazing.model.Wertpapier;
import org.stringtemplate.v4.ST;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.stream.Collectors;

public final class CliTextFormatter implements Formatter {
    @Override
    public String format(final Wertpapier w) {
        final ST template = new ST(readTemplate());
        template.add("w", w);
        return template.render();
    }

    private String readTemplate() {
        final FileReader reader = new FileReader();

        try (
            final InputStream input = getClass().getResourceAsStream(Constants.BASE_PACKAGE_DIR + "/wertpapier.st")) {
            return reader.readFile(input);
        } catch (IOException e) {
            throw new RuntimeException("Cant read template!");
        }
    }

    @Override
    public String format(final Collection<Wertpapier> ws) {
        return ws.stream()
            .map(this::format)
            .collect(Collectors.joining("----\n\n"));
    }
}
