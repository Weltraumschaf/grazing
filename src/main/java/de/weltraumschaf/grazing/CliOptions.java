package de.weltraumschaf.grazing;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import java.util.ArrayList;
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
    @Parameter(names = {"-i","--isin"}, description = "Scrape the ISIN URL.", required = true)
    private List<String> isin = new ArrayList<>();

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
        return isin;
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
            Objects.equals(isin, that.isin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(version, help, debug, isin);
    }

    @Override
    public String toString() {
        return "CliOptions{" +
            "version=" + version +
            ", help=" + help +
            ", debug=" + debug +
            ", isin='" + isin + '\'' +
            '}';
    }
}
