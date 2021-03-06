package de.weltraumschaf.grazing.model;

import de.weltraumschaf.commons.validate.Validate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public final class Wertpapier {

    private String name;
    private String isin;
    private BigDecimal gesamtkosten;
    private Waehrung fondswaehrung;
    private String fondsvermoegen;
    private String auflagedatum;
    private int jahre;
    private String sparplan;
    private String url;
    private String nachbildung;
    private String ertragsverwendung;
    private String fondsoberkategorie;
    private String unterkategorie;
    private String fondsgesellschaft;
    private final Collection<Branche> verteilungNachBranchen = new ArrayList<>();
    private final Collection<LandRegion> verteilungNachLaenderRegionen = new ArrayList<>();
    private final Collection<Position> greosstePositionen = new ArrayList<>();

    private Wertpapier() {
        super();
    }

    public String getName() {
        return name;
    }

    public String getIsin() {
        return isin;
    }

    public BigDecimal getGesamtkosten() {
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

    public int getJahre() {
        return jahre;
    }

    public String getSparplan() {
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

    public String getFondsoberkategorie() {
        return fondsoberkategorie;
    }

    public String getUnterkategorie() {
        return unterkategorie;
    }

    public String getFondsgesellschaft() {
        return fondsgesellschaft;
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

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof Wertpapier)) {
            return false;
        }

        final Wertpapier that = (Wertpapier) o;
        return jahre == that.jahre &&
            Objects.equals(name, that.name) &&
            Objects.equals(isin, that.isin) &&
            Objects.equals(gesamtkosten, that.gesamtkosten) &&
            fondswaehrung == that.fondswaehrung &&
            Objects.equals(fondsvermoegen, that.fondsvermoegen) &&
            Objects.equals(auflagedatum, that.auflagedatum) &&
            Objects.equals(sparplan, that.sparplan) &&
            Objects.equals(url, that.url) &&
            Objects.equals(nachbildung, that.nachbildung) &&
            Objects.equals(ertragsverwendung, that.ertragsverwendung) &&
            Objects.equals(fondsoberkategorie, that.fondsoberkategorie) &&
            Objects.equals(unterkategorie, that.unterkategorie) &&
            Objects.equals(fondsgesellschaft, that.fondsgesellschaft) &&
            Objects.equals(verteilungNachBranchen, that.verteilungNachBranchen) &&
            Objects.equals(verteilungNachLaenderRegionen, that.verteilungNachLaenderRegionen) &&
            Objects.equals(greosstePositionen, that.greosstePositionen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, isin, gesamtkosten, fondswaehrung, fondsvermoegen, auflagedatum, jahre, sparplan, url, nachbildung, ertragsverwendung, fondsoberkategorie, unterkategorie, fondsgesellschaft, verteilungNachBranchen, verteilungNachLaenderRegionen, greosstePositionen);
    }

    @Override
    public String toString() {
        return "Wertpapier{" +
            "name='" + name + '\'' +
            ", isin='" + isin + '\'' +
            ", gesamtkosten=" + gesamtkosten +
            ", fondswaehrung=" + fondswaehrung +
            ", fondsvermoegen='" + fondsvermoegen + '\'' +
            ", auflagedatum='" + auflagedatum + '\'' +
            ", jahre=" + jahre +
            ", sparplan='" + sparplan + '\'' +
            ", url='" + url + '\'' +
            ", nachbildung='" + nachbildung + '\'' +
            ", ertragsverwendung='" + ertragsverwendung + '\'' +
            ", fondsoberkategorie='" + fondsoberkategorie + '\'' +
            ", unterkategorie='" + unterkategorie + '\'' +
            ", fondsgesellschaft='" + fondsgesellschaft + '\'' +
            ", verteilungNachBranchen=" + verteilungNachBranchen +
            ", verteilungNachLaenderRegionen=" + verteilungNachLaenderRegionen +
            ", greosstePositionen=" + greosstePositionen +
            '}';
    }

    public static final class Builder {
        private static final String NA = "n/a";
        private String name = NA;
        private String isin = NA;
        private BigDecimal gesamtkosten = new BigDecimal(0);
        private Waehrung fondswaehrung = Waehrung.NA;
        private String fondsvermoegen = NA;
        private String auflagedatum = NA;
        private int jahre;
        private String sparplan = NA;
        private String url = NA;
        private String nachbildung = NA;
        private String ertragsverwendung = NA;
        private final Collection<Branche> verteilungNachBranchen = new ArrayList<>();
        private final Collection<LandRegion> verteilungNachLaenderRegionen = new ArrayList<>();
        private final Collection<Position> greosstePositionen = new ArrayList<>();
        private String fondsoberkategorie;
        private String unterkategorie;
        private String fondsgesellschaft;

        private Builder() {
            super();
        }

        public static Builder create() {
            return new Builder();
        }

        public Wertpapier build() {
            final Wertpapier product = new Wertpapier();
            product.name = name;
            product.isin = isin;
            product.gesamtkosten = gesamtkosten;
            product.fondswaehrung = fondswaehrung;
            product.fondsvermoegen = fondsvermoegen;
            product.auflagedatum = auflagedatum;
            product.jahre = jahre;
            product.sparplan = sparplan;
            product.url = url;
            product.nachbildung = nachbildung;
            product.ertragsverwendung = ertragsverwendung;
            product.fondsoberkategorie = fondsoberkategorie;
            product.unterkategorie = unterkategorie;
            product.fondsgesellschaft = fondsgesellschaft;
            product.verteilungNachBranchen.addAll(verteilungNachBranchen);
            product.verteilungNachLaenderRegionen.addAll(verteilungNachLaenderRegionen);
            product.greosstePositionen.addAll(greosstePositionen);
            return product;
        }

        public Builder setName(final String name) {
            this.name = name;
            return this;
        }

        public Builder setIsin(final String isin) {
            this.isin = Validate.notNull(isin, "isin");
            return this;
        }

        public Builder setGesamtkosten(final BigDecimal gesamtkosten) {
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

        public Builder setJahre(final int jahre) {
            this.jahre = Validate.greaterThanOrEqual(jahre, 0, "jahre");
            return this;
        }

        public Builder setSparplan(final String sparplan) {
            this.sparplan = Validate.notEmpty(sparplan, "sparplan");
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

        public Builder setFondsoberkategorie(final String fondsoberkategorie) {
            this.fondsoberkategorie = Validate.notEmpty(fondsoberkategorie, "fondsoberkategorie");
            return this;
        }

        public Builder setUnterkategorie(final String unterkategorie) {
            this.unterkategorie = Validate.notEmpty(unterkategorie, "unterkategorie");
            return this;
        }

        public Builder setFondsgesellschaft(final String fondsgesellschaft) {
            this.fondsgesellschaft = Validate.notEmpty(fondsgesellschaft, "fondsgesellschaft");
            return this;
        }
    }
}
