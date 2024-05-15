package fk.wordleprojekt;

public class GameManager {

    private static int currentRound;
    private static Difficulty difficulty;

    public static int getCurrentRound() {
        return currentRound;
    }

    public static void setCurrentRound(int currentRound) {
        GameManager.currentRound = currentRound;
    }

    public static void setDifficulty(Difficulty difficulty) {
        GameManager.difficulty = difficulty;
    }

    public static Difficulty getDifficulty() {
        return GameManager.difficulty;
    }

    public static void startNewGame() {
        setCurrentRound(1);
        WordGenerator.generateRandomWord();
    }
}
