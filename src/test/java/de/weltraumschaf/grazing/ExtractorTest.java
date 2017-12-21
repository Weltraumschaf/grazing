package de.weltraumschaf.grazing;

import de.weltraumschaf.commons.application.IO;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;

public class ExtractorTest {

    private final Extractor sut = new Extractor(mock(IO.class), new CliOptions());

    @Test
    public void parsePercent() {
        assertThat(sut.parsePercent(""), is(new BigDecimal(0)));
        assertThat(sut.parsePercent("10,18%"), is(new BigDecimal("10.18")));
        assertThat(sut.parsePercent("1,97%"), is(new BigDecimal("1.97")));
        assertThat(sut.parsePercent("0,40% (zum 31.01.2015)"), is(new BigDecimal("0.40")));
    }
}