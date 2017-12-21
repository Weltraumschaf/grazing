package de.weltraumschaf.grazing;

import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class FileReaderTest {
    private final FileReader sut = new FileReader();

    @Test
    public void readFile() throws URISyntaxException, IOException {
        final URI uri = getClass().getResource(Constants.BASE_PACKAGE_DIR + "/test.txt").toURI();

        assertThat(
            sut.readFile(Paths.get(uri)),
            is("foo\n" +
                "bar\n" +
                "baz"));
    }

    @Test
    public void readLines() throws URISyntaxException, IOException {
        final URI uri = getClass().getResource(Constants.BASE_PACKAGE_DIR + "/test.txt").toURI();

        assertThat(
            sut.readLines(Paths.get(uri)),
            containsInAnyOrder("foo", "bar", "baz"));
    }
}