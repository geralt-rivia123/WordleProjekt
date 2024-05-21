package fk.wordleprojekt;

import fk.wordleprojekt.data.WordGenerator;

public class GameManager {
    private static boolean winStatus;
    private static int currentRound;
    private static Difficulty difficulty;

    public static int getCurrentRound() {
        return currentRound;
    }

    public static void setCurrentRound(int currentRound) {
        if(currentRound >= 1 && currentRound <= 7) {
            GameManager.currentRound = currentRound;
        }
    }

    public static void setDifficulty(Difficulty difficulty) {
        GameManager.difficulty = difficulty;
    }

    public static Difficulty getDifficulty() {
        return GameManager.difficulty;
    }

    public static void startNewGame() {
        setCurrentRound(1);
        setWinStatus(false);
        WordGenerator.generateRandomWord();
    }

    public static boolean getWinStatus() {
        return winStatus;
    }

    public static void setWinStatus(boolean status) {
        GameManager.winStatus = status;
    }
}
