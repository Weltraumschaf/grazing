package de.weltraumschaf.grazing;

import de.weltraumschaf.commons.application.ApplicationException;
import de.weltraumschaf.commons.application.InvokableAdapter;
import de.weltraumschaf.commons.application.Version;
import de.weltraumschaf.commons.jcommander.JCommanderImproved;
import de.weltraumschaf.grazing.formatter.CliTextFormatter;
import de.weltraumschaf.grazing.formatter.CsvFormatter;
import de.weltraumschaf.grazing.formatter.Formatter;
import de.weltraumschaf.grazing.model.Wertpapier;

import java.io.IOError;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public final class Main extends InvokableAdapter {

    /**
     * Version information.
     */
    private final Version version = new Version(Constants.BASE_PACKAGE_DIR + "/version.properties");
    private final JCommanderImproved<CliOptions> cliArgs = new JCommanderImproved<CliOptions>("grazing", CliOptions.class);

    /**
     * Dedicated constructor.
     *
     * @param args must not be {@code null}
     */
    private Main(final String[] args) {
        super(args);
    }

    /**
     * Invoked from JVM.
     *
     * @param args CLI arguments
     */
    public static void main(final String[] args) {
        final Main app = new Main(args);
        InvokableAdapter.main(app);
    }

    @Override
    public void execute() throws Exception {
        final CliOptions opts = cliArgs.gatherOptions(getArgs());
        debug = opts.isDebug();

        if (opts.isVersion()) {
            version.load();
            getIoStreams().println(String.format("Version: %s", version.getVersion()));
            exit(0);
            return;
        }

        if (opts.isHelp()) {
            getIoStreams().println("Help: TODO");
            exit(0);
            return;
        }

        final Collection<Wertpapier> extracted = extract(opts);

        if (opts.getCsv().isEmpty()) {
            getIoStreams().print(new CliTextFormatter().format(extracted));
        } else {
            final byte[] content = new CsvFormatter().format(extracted).getBytes(Constants.DEFAULT_CHARSET);
            Files.write(Paths.get(opts.getCsv()), content);
        }

        exit(0);
    }

    private Collection<Wertpapier> extract(final CliOptions opts) throws ApplicationException {
        final Collection<String> isins = new ArrayList<>(opts.getIsin());

        if (!opts.getFile().isEmpty()) {
            final FileReader reader = new FileReader();
            try {
                isins.addAll(reader.readLines(Paths.get(opts.getFile())));
            } catch (IOException e) {
                throw new ApplicationException(ExitCodes.FATAL, String.format("Can't read ISIN file %s!", opts.getFile()));
            }
        }

        if (isins.isEmpty()) {
            throw new ApplicationException(ExitCodes.FATAL, "No ISINs given to scrape!");
        }

        return new Extractor(getIoStreams(), opts).extract(isins);
    }

}
