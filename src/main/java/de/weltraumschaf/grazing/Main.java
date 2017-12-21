package de.weltraumschaf.grazing;

import de.weltraumschaf.commons.application.InvokableAdapter;
import de.weltraumschaf.commons.application.Version;
import de.weltraumschaf.commons.jcommander.JCommanderImproved;
import de.weltraumschaf.grazing.formatter.CliTextFormatter;
import de.weltraumschaf.grazing.formatter.Formatter;
import de.weltraumschaf.grazing.model.Wertpapier;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 */
public final class Main extends InvokableAdapter {
    /**
     * The base package name of the whole application.
     */
    private static final String BASE_PACKAGE = "de.weltraumschaf.grazing";
    private static final String BASE_PACKAGE_DIR = "/" + BASE_PACKAGE.replaceAll("\\.", "/");

    /**
     * Version information.
     */
    private final Version version = new Version(BASE_PACKAGE_DIR + "/version.properties");
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

        final Wertpapier extracted = new Extractor(getIoStreams()).extract(opts.getIsin());

        final Formatter formatter = new CliTextFormatter();
        getIoStreams().print(formatter.format(extracted));

        exit(0);
    }


}
