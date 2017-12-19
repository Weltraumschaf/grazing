package de.weltraumschaf.caythe.grazing;

import de.weltraumschaf.commons.application.InvokableAdapter;
import de.weltraumschaf.commons.application.Version;
import de.weltraumschaf.commons.jcommander.JCommanderImproved;
import de.weltraumschaf.commons.validate.Validate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

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

        final Document doc = Jsoup.connect(opts.getUrl()).get();
        final String title = doc.title();
        getIoStreams().println(title);

        exit(0);
    }
}
