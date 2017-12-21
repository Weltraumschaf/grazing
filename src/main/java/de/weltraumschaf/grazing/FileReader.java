package de.weltraumschaf.grazing;

import de.weltraumschaf.commons.validate.Validate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.stream.Collectors;

public final class FileReader {
    public String readFile(final Path path) throws IOException {
        try (final InputStream input = Files.newInputStream(Validate.notNull(path, "path"))) {
            return readFile(input);
        }
    }

    public String readFile(final InputStream input) throws IOException {
            return readLines(input).stream().collect(Collectors.joining("\n"));
    }

    public Collection<String> readLines(final Path path) throws IOException {
        try (final InputStream input = Files.newInputStream(Validate.notNull(path, "path"))) {
            return readLines(input);
        }
    }
    public Collection<String> readLines(final InputStream input) throws IOException {
        try (final BufferedReader reader = createReader(input)) {
            return reader.lines().collect(Collectors.toList());
        }
    }

    private BufferedReader createReader(final InputStream input) {
        return new BufferedReader(new InputStreamReader(input, Charset.forName(StandardCharsets.UTF_8.name())));
    }
}
