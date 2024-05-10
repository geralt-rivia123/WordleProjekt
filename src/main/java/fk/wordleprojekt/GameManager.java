package fk.wordleprojekt;

public class GameManager {

    private int currentRound;

    // Skapa en privat statisk instansvariabel av samma typ som klassen själv
    private static GameManager instance;

    // Gör konstruktorn privat för att förhindra att den kan kallas externt
    private GameManager() {
        // Konstruktorn är privat, så den kan inte kallas utifrån klassen
    }

    // Skapa en statisk metod för att få åtkomst till instansen av Singleton-klassen
    public static GameManager getInstance() {
        // Om instansen inte har skapats ännu, skapa den
        if (instance == null) {
            instance = new GameManager();
        }
        // Returnera instansen
        return instance;
    }


    public int getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }

    public void incrementCurrentRound() {
        this.currentRound++;
    }

    public void startNewGame() {
        setCurrentRound(1);
        WordGenerator wordGenerator = new WordGenerator();
        wordGenerator.generateRandomWord();

    }
}
