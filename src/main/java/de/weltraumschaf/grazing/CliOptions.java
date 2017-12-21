package de.weltraumschaf.grazing;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 *
 */
@Parameters(commandDescription = "Creates a module scaffold.")
@SuppressWarnings( {"unused", "FieldCanBeLocal"})
public final class CliOptions {

    @SuppressWarnings( {"CanBeFinal", "unused"})
    @Parameter(names = {"-v", "--version"}, description = "Version.", help = true)
    private boolean version;

    @SuppressWarnings( {"CanBeFinal", "unused"})
    @Parameter(names = {"-h", "--help"}, description = "Show help.", help = true)
    private boolean help;

    @SuppressWarnings( {"CanBeFinal", "unused"})
    @Parameter(names = {"--debug"}, description = "Print debug output such as stack traces to STDOUT.")
    private boolean debug;

    @SuppressWarnings( {"CanBeFinal", "unused"})
    @Parameter(names = {"-i","--isin"}, description = "Scrape the ISIN URL.")
    private List<String> isin = new ArrayList<>();

    @SuppressWarnings( {"CanBeFinal", "unused"})
    @Parameter(names = {"-f","--file"}, description = "Give file with ISINs.")
    private String file = "";

    boolean isVersion() {
        return version;
    }

    final boolean isHelp() {
        return help;
    }

    boolean isDebug() {
        return debug;
    }

    List<String> getIsin() {
        return Collections.unmodifiableList(isin);
    }

    String getFile() {
        return file;
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof CliOptions)) {
            return false;
        }

        final CliOptions that = (CliOptions) o;
        return version == that.version &&
            help == that.help &&
            debug == that.debug &&
            Objects.equals(isin, that.isin) &&
            Objects.equals(file, that.file);
    }

    @Override
    public int hashCode() {
        return Objects.hash(version, help, debug, isin, file);
    }

    @Override
    public String toString() {
        return "CliOptions{" +
            "version=" + version +
            ", help=" + help +
            ", debug=" + debug +
            ", isin=" + isin +
            ", file='" + file + '\'' +
            '}';
    }
}
