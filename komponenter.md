Koden bör delas upp i klasser för att separare koden som ansvarar för användargränsnittet och koden som hanterar själva logiken.

WordleGUI: En klass som ansvarar för att skapa och hantera GUI-komponenterna. 
Detta inkluderar fönstret, knappar för bokstäverna, textfält för att visa ordet och antal gissningar kvar, etc.

WordleController: En klass som fungerar som en mellanhand mellan spellogiken och GUI:et. 
Den hanterar interaktioner från användaren (till exempel när en bokstav gissas) och uppdaterar GUI:et enligt spelets status.

Själva spellogiken tänker jag kan delas upp i flera mindre klasser

Några förslag på klasser är

WordGenerator: En klass som ansvarar för att generera ett slumpmässigt ord från en lista av tillgängliga ord.

WordGuesser: En klass som jämför användarens gissning med det slumpmässigt valda ordet och returnerar feedback om vilka bokstäver som är korrekta och på vilken plats de befinner sig.

GameManager: En klass som håller reda på spelets status, till exempel om användaren har vunnit, förlorat eller om spelet fortsätter.

