package de.weltraumschaf.grazing.model;

import de.weltraumschaf.commons.validate.Validate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public final class Wertpapier {

    private String name;
    private long gesamtkosten;
    private Waehrung fondswaehrung;
    private String fondsvermoegen;
    private String auflagedatum;
    private boolean sparplan;
    private String url;
    private String nachbildung;
    private String ertragsverwendung;
    private final Collection<Branche> verteilungNachBranchen = new ArrayList<>();
    private final Collection<LandRegion> verteilungNachLaenderRegionen = new ArrayList<>();
    private final Collection<Position> greosstePositionen = new ArrayList<>();

    private Wertpapier() {
        super();
    }

    public String getName() {
        return name;
    }

    public long getGesamtkosten() {
        return gesamtkosten;
    }

    public Waehrung getFondswaehrung() {
        return fondswaehrung;
    }

    public String getFondsvermoegen() {
        return fondsvermoegen;
    }

    public String getAuflagedatum() {
        return auflagedatum;
    }

    public boolean isSparplan() {
        return sparplan;
    }

    public String getUrl() {
        return url;
    }

    public String getNachbildung() {
        return nachbildung;
    }

    public String getErtragsverwendung() {
        return ertragsverwendung;
    }

    public Collection<Branche> getVerteilungNachBranchen() {
        return Collections.unmodifiableCollection(verteilungNachBranchen);
    }

    public Collection<LandRegion> getVerteilungNachLaenderRegionen() {
        return Collections.unmodifiableCollection(verteilungNachLaenderRegionen);
    }

    public Collection<Position> getGreosstePositionen() {
        return Collections.unmodifiableCollection(greosstePositionen);
    }

    public static final class Builder {
        private static final String NA = "n/a";
        private String name = NA;
        private long gesamtkosten;
        private Waehrung fondswaehrung = Waehrung.NA;
        private String fondsvermoegen = NA;
        private String auflagedatum = NA;
        private boolean sparplan;
        private String url = NA;
        private String nachbildung = NA;
        private String ertragsverwendung = NA;
        private final Collection<Branche> verteilungNachBranchen = new ArrayList<>();
        private final Collection<LandRegion> verteilungNachLaenderRegionen = new ArrayList<>();
        private final Collection<Position> greosstePositionen = new ArrayList<>();

        private Builder() {
            super();
        }

        public static Builder create() {
            return new Builder();
        }

        public Wertpapier build() {
            final Wertpapier product = new Wertpapier();
            product.name = name;
            product.gesamtkosten = gesamtkosten;
            product.fondswaehrung = fondswaehrung;
            product.fondsvermoegen = fondsvermoegen;
            product.auflagedatum = auflagedatum;
            product.sparplan = sparplan;
            product.url = url;
            product.nachbildung = nachbildung;
            product.ertragsverwendung = ertragsverwendung;
            product.verteilungNachBranchen.addAll(verteilungNachBranchen);
            product.verteilungNachLaenderRegionen.addAll(verteilungNachLaenderRegionen);
            product.greosstePositionen.addAll(greosstePositionen);
            return product;
        }

        public Builder setName(final String name) {
            this.name = name;
            return this;
        }

        public Builder setGesamtkosten(final long gesamtkosten) {
            this.gesamtkosten = Validate.notNull(gesamtkosten);
            return this;
        }

        public Builder setFondswaehrung(final Waehrung fondswaehrung) {
            this.fondswaehrung = Validate.notNull(fondswaehrung, "fondswaehrung");
            return this;
        }

        public Builder setFondsvermoegen(final String fondsvermoegen) {
            this.fondsvermoegen = Validate.notEmpty(fondsvermoegen, "fondsvermoegen");
            return this;
        }

        public Builder setAuflagedatum(final String auflagedatum) {
            this.auflagedatum = Validate.notEmpty(auflagedatum, "auflagedatum");
            return this;
        }

        public Builder setSparplan(final boolean sparplan) {
            this.sparplan = sparplan;
            return this;
        }

        public Builder setUrl(final String url) {
            this.url = Validate.notEmpty(url, "url");
            return this;
        }

        public Builder setNachbildung(final String nachbildung) {
            this.nachbildung = Validate.notEmpty(nachbildung, "nachbildung");
            return this;
        }

        public Builder setErtragsverwendung(final String ertragsverwendung) {
            this.ertragsverwendung = Validate.notEmpty(ertragsverwendung, "ertragsverwendung");
            return this;
        }

        public Builder addBranche(final Branche branchen) {
            this.verteilungNachBranchen.add(Validate.notNull(branchen, "branchen"));
            return this;
        }

        public Builder addLaenderRegionen(final LandRegion landRegion) {
            this.verteilungNachLaenderRegionen.add(Validate.notNull(landRegion, "landRegion"));
            return this;
        }

        public Builder addPositionen(final Position position) {
            this.greosstePositionen.add(Validate.notNull(position, "position"));
            return this;
        }
    }
}
