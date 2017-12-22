# <w.name>

ISIN: <w.isin><\ >

Fondsoberkategorie: <w.fondsoberkategorie><\ >
Unterkategorie:     <w.unterkategorie><\ >
Fondsgesellschaft:  <w.fondsgesellschaft><\ >
url:                <w.url><\ >

Gesamtkosten p.a.: <w.gesamtkosten> %<\ >
Fondswaehrung:     <w.fondswaehrung.displayValue><\ >
Fondsvermoegen:    <w.fondsvermoegen><\ >
Auflagedatum:      <w.auflagedatum><\ >
Jahre:             <w.jahre><\ >
Sparplan:          <w.sparplan><\ >
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