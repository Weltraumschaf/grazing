package de.weltraumschaf.caythe.grazing;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.util.Objects;

/**
 *
 */
@Parameters(commandDescription = "Creates a module scaffold.")
@SuppressWarnings( {"unused", "FieldCanBeLocal"})
public final class CliOptions {

    @SuppressWarnings( {"CanBeFinal", "unused"})
    @Parameter(names = {"-h", "--help"}, description = "Show help.", help = true)
    private boolean help;

    @SuppressWarnings( {"CanBeFinal", "unused"})
    @Parameter(names = {"--debug"}, description = "Print debug output such as stack traces to STDOUT.")
    private boolean debug;

    @SuppressWarnings( {"CanBeFinal", "unused"})
    @Parameter(names = {"-u","--url"}, description = "Scrape URL.", required = true)
    private String url;

    final boolean isHelp() {
        return help;
    }

    boolean isDebug() {
        return debug;
    }

    String getUrl() {
        return url;
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof CliOptions)) {
            return false;
        }

        final CliOptions that = (CliOptions) o;
        return help == that.help &&
            debug == that.debug &&
            Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(help, debug, url);
    }

    @Override
    public String toString() {
        return "CliOptions{" +
            "help=" + help +
            ", debug=" + debug +
            ", url='" + url + '\'' +
            '}';
    }
}
