Name:               <w.name>
Fondsoberkategorie: <w.fondsoberkategorie>
Unterkategorie:     <w.unterkategorie>
Fondsgesellschaft:  <w.fondsgesellschaft>
url:                <w.url>

Gesamtkosten:      <w.gesamtkosten> %
Fondswaehrung:     <w.fondswaehrung.displayValue>
Fondsvermoegen:    <w.fondsvermoegen>
Auflagedatum:      <w.auflagedatum>
Sparplan:          <if(w.sparplan)>ja<else>nein<endif>
Nachbildung:       <w.nachbildung>
Ertragsverwendung: <w.ertragsverwendung>

Verteilung nach Branchen:
<w.verteilungNachBranchen :{ item | - <item.name> <item.prozent> % <\n>}>
Verteilung nach Ländern/Regionen:
<w.verteilungNachLaenderRegionen :{ item | - <item.name> <item.prozent> % <\n>}>
Größte Positionen:
<w.greosstePositionen :{ item | - <item.name> <item.prozent> % <\n>}>