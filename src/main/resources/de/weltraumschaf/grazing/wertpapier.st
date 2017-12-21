# <w.name>

Fondsoberkategorie: <w.fondsoberkategorie><\ >
Unterkategorie:     <w.unterkategorie><\ >
Fondsgesellschaft:  <w.fondsgesellschaft><\ >
url:                <w.url><\ >

Gesamtkosten:      <w.gesamtkosten> %<\ >
Fondswaehrung:     <w.fondswaehrung.displayValue><\ >
Fondsvermoegen:    <w.fondsvermoegen><\ >
Auflagedatum:      <w.auflagedatum><\ >
Sparplan:          <if(w.sparplan)>ja<else>nein<endif><\ >
Nachbildung:       <w.nachbildung><\ >
Ertragsverwendung: <w.ertragsverwendung><\ >

## Verteilung nach Branchen

<if(w.verteilungNachBranchen)>
 <w.verteilungNachBranchen :{ item | - <item.name> <item.prozent> % <\n>}>
<else>
 Keine Informationen vorhanden

<endif>
## Verteilung nach Ländern/Regionen

<if(w.verteilungNachLaenderRegionen)>
 <w.verteilungNachLaenderRegionen :{ item | - <item.name> <item.prozent> % <\n>}>
<else>
 Keine Informationen vorhanden

<endif>
## Größte Positionen

<if(w.greosstePositionen)>
 <w.greosstePositionen :{ item | - <item.name> <item.prozent> % <\n>}>
<else>
 Keine Informationen vorhanden

<endif>