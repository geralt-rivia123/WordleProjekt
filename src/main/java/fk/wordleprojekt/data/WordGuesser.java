package fk.wordleprojekt.data;

import fk.wordleprojekt.GameManager;
import fk.wordleprojekt.data.characters.GreenCharacter;
import fk.wordleprojekt.data.characters.RedCharacter;
import fk.wordleprojekt.data.characters.YellowCharacter;
import fk.wordleprojekt.exceptions.GuessTooShortException;
import fk.wordleprojekt.exceptions.InvalidGuessException;
import fk.wordleprojekt.exceptions.WordNotInListException;

import java.util.*;

public class WordGuesser {

    static List<GreenCharacter> greenCharacters = new ArrayList<>();
    static List<YellowCharacter> yellowCharacters = new ArrayList<>();
    static List<RedCharacter> redCharacters = new ArrayList<>();



    public static void guess(String guess) throws InvalidGuessException {
        if (!isGuessFiveCharactersLong(guess)) {
            throw new GuessTooShortException("Gissningen måste bestå av fem bokstäver");
        }
        if (!doesWordExistInList(guess)) {
            throw new WordNotInListException("Ordet finns inte i ordlistan");
        }

        //Rensa listorna inför varje ny gissning som ka behandlas
        greenCharacters.clear();
        yellowCharacters.clear();
        redCharacters.clear();

        String word = WordGenerator.getGeneratedWord();

        // Array för att spåra matchade positioner
        boolean[] matched = new boolean[5];


        // Sätter alla till grön om gissningen är samma ord som det genererade ordet
        if (guess.equalsIgnoreCase(word)) {
            for (int i = 0; i < word.length(); i++) {
                greenCharacters.add(new GreenCharacter(i, word.charAt(i)));
            }
            GameManager.setWinStatus(true);
        }
        else
        {
            //Ordet är inte korrekt, går igenom gissnigen och kategoriserar bokstäverna som grön, gul, röd

            // Kontrollera gröna tecken
            //Loopar igenom gissninens alla bokstäver
            for (int i = 0; i < guess.length(); i++) {
                char c = guess.charAt(i);
                //Om bokstav och position matchar
                if (word.charAt(i) == c) {
                    greenCharacters.add(new GreenCharacter(i, c));
                    matched[i] = true;
                }
            }

            // Kontrollera gula och röda tecken
            //går igenom varje bokstav i gissningen igen, men denna gång hoppar vi över bokstäver som redan är markerade som gröna.
            for (int i = 0; i < guess.length(); i++) {
                char c = guess.charAt(i);
                // Kolla så att bokstaven inte matchar på position
                if (word.charAt(i) != c) {
                    boolean foundYellow = false;
                    //Loopar igenom alla positioner för varje bokstav i gissningen
                    for (int j = 0; j < word.length(); j++) {
                        //Om vi hamnar på en position som inte har matchats, men bokstaven stämmer överens
                        //Lägg till bokstaven i gula listan
                        if (!matched[j] && word.charAt(j) == c) {
                            yellowCharacters.add(new YellowCharacter(i,c));
                            matched[j] = true;
                            foundYellow = true;
                            break;
                        }
                    }
                    //Bokstaven är varken gul eller grön, finns alltså inte i ordet
                    if (!foundYellow) {
                        redCharacters.add(new RedCharacter(i,c));
                    }
                }
            }
        }


    }

    public static boolean doesWordExistInList(String guess) {
        return WordGenerator.getWords().contains(guess);
    }

    public static boolean isGuessFiveCharactersLong(String guess) {
        return guess.length() == 5;
    }

    public static List<GreenCharacter> getGreenCharacters() {
        return greenCharacters;
    }

    public static List<YellowCharacter> getYellowCharacters() {
        return yellowCharacters;
    }

    public static List<RedCharacter> getRedCharacters(){
        return redCharacters;
    }


}
