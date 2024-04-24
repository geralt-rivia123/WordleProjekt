Användningsfall
--------------------------
Huvudscenario:

1.Användaren startar Wordle-applikationen.

2.Applikationen visar ett fönster med spelgränssnittet, inklusive ett rutnät för att visa att man ska gissa på ett ord med fem bokstäver och att man har sex försök på sig, samt ett antal bokstavsknappar för att göra gissningar.

3.Applikationen väljer ett slumpmässigt ord från en ordlista

4.Användaren väljer en bokstav genom att klicka på motsvarande knapp

5.Applikationen jämför användarens gissning med det slumpmässigt valda ordet och ger feedback till användaren om vilka bokstäver som är korrekta och på vilken plats de befinner sig.

6.Användaren fortsätter att göra gissningar tills antingen det korrekta ordet gissas eller antalet tillåtna gissningar är uppnått.

7.Om användaren gissar det korrekta ordet, visas en vinstmeddelande och användaren blir tillfrågad om den vill spela en ny runda eller avsluta spelet.

8.Om användaren använder upp alla tillåtna gissningar utan att gissa det korrekta ordet, visas en förlustmeddelande och användaren blir tillfrågad om den vill spela en ny runda eller avsluta spelet.

--------------------------
Alternativt scenario:

Om användaren väljer att avbryta spelet innan det är slut, kan de stänga ner applikationen eller välja att starta en ny omgång.


Förslag på enhetstester

WordGeneratorTest: Testa att WordGenerator-klassen kan läsa in en ordlista och generera ett slumpmässigt ord korrekt.

WordGuesserTest: Testa att klassen kan jämföra en användares gissning med det slumpmässigt valda ordet och ge korrekt feedback.

GameManagerTest: Testa att spelets status hanteras korrekt, såsom att kontrollera om spelet är över när användaren vinner eller förlorar.
