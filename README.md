

Projektet är en version av ordspelet Wordle. Spelet går ut på att gissa fram ett ord på fem bokstäver
där man har sex försök på sig. Programmet kommer bestå av ett enkelt GUI med knappar för varje bostav för att gissa
fram orden samt ett "rutnät" 5x6 för att visualisera bokstäverna som ingår i ordet. 

------------------------
Funktionella krav

- Som spelare ska jag kunna klicka på en bokstav för varje ruta i ordet. Om bokstaven finns i ordet och på rätt plats, ska den visas som grön. Om bokstaven finns i ordet men i fel ruta ska bokstaven visas som gul. Om bokstaven inte finns med i ordet ska bokstaven visas som grå.

- Om spelaren gissar på ett ord som inte finns med i ordlistan så ska den informeras om det och gissningen ska inte gå igenom.

- Programmet måste hantera fel på ett lämpligt sätt, samt visa hjälpsamma felmeddelande till spelaren.

- Det ska vara möjligt att starta ett spel på nytt efter man spelet klart en runda, utan att behöva starta om programmet.
 
------------------------

Icke funktionella krav

- Programmets GUI ska vara intuitiv och enkelt att förstå sig på och använda.

- GUI ska tas fram med javafx och scenebuilder.

- Koden ska efterleva clean code, den ska vara lätt att läsa och förstå för någon som inte jobbat med projektet. Kommentarer vid behov, men viktigast med bra namngivning på metoder och variabler.

- Tanken är att försöka jobba enligt TDD, enhetstester ska skapas.

------------------------
Bygga och starta projektet:

Se till att ha JDK 21 eller senare installerat https://www.oracle.com/se/java/technologies/downloads/

Kör projektet i en IDE:
Föreslår Intellij, då projektet är byggt i den IDE:n https://www.jetbrains.com/idea/download/?section=windows
Öppna projektet och leta dig fram till main-klassen src/main/java/fk/wordleprojekt/Main.java högerklicka Main "Run Main.main()"

Bygg och kör JAR i Intellij

Bygg:
Fle -> Project strucuture 
-> Artifacts + JAR 
-> From module with dependencies Main class: Main 
-> Apply
Build -> Build artifacts

Kör JAR:

out/artifacts/WordleProjekt_jar högerklicka Wordleprojekt.jar "run"
Alternativt skriv java -jar "Wordleprojekt.jar" i kommandotolken.