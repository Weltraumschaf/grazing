package de.weltraumschaf.grazing;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public final class Constants {
    /**
     * The base package name of the whole application.
     */
    public static final String BASE_PACKAGE = "de.weltraumschaf.grazing";
    public static final String BASE_PACKAGE_DIR = "/" + BASE_PACKAGE.replaceAll("\\.", "/");
    public static final Charset DEFAULT_CHARSET = Charset.forName(StandardCharsets.UTF_8.name());
}
