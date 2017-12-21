package de.weltraumschaf.grazing.formatter;

import de.weltraumschaf.grazing.Constants;
import de.weltraumschaf.grazing.model.Wertpapier;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.StringRenderer;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.stream.Collectors;

public final class CliTextFormatter implements Formatter {
    @Override
    public String format(final Wertpapier w) {
        final ST template = new ST(readTempate());
        template.add("w", w);
        return template.render();
    }

    private String readTempate() {
        try (
            final InputStream input = getClass().getResourceAsStream(Constants.BASE_PACKAGE_DIR + "/wertpapier.st");
            final BufferedReader reader = new BufferedReader(new InputStreamReader(input, Charset.forName(StandardCharsets.UTF_8.name())))) {
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException("Cant read template!");
        }
    }

    @Override
    public String format(final Collection<Wertpapier> ws) {
        return ws.stream()
            .map(Wertpapier::toString)
            .collect(Collectors.joining("\n"));
    }
}
