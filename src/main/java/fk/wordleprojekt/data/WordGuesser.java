package fk.wordleprojekt.data;

import fk.wordleprojekt.data.characters.GreenCharacter;
import fk.wordleprojekt.data.characters.RedCharacter;
import fk.wordleprojekt.data.characters.YellowCharacter;
import fk.wordleprojekt.exceptions.GuessTooShortException;
import fk.wordleprojekt.exceptions.InvalidGuessException;
import fk.wordleprojekt.exceptions.WordNotInListException;

import java.util.*;

public class WordGuesser {

    //static List<Pair<Integer, String>> greenCharacters = new ArrayList<>();
    //static List<Pair<Integer, String>> yellowCharacters = new ArrayList<>();
    //static List<Pair<Integer, String>> redCharacters = new ArrayList<>();
    //static HashMap<Integer,String> redCharacters = new HashMap<>();
    //static HashMap<Integer,String> yellowCharacters = new HashMap<>();
    //static HashMap<Integer,String> greenCharacters = new HashMap<>();

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

        greenCharacters.clear();
        yellowCharacters.clear();
        redCharacters.clear();

        String word = WordGenerator.getGeneratedWord();
        boolean[] matched = new boolean[word.length()]; // Array för att spåra matchade positioner

        // Kontrollera gröna tecken
        for (int i = 0; i < guess.length(); i++) {
            char c = guess.charAt(i);
            if (word.charAt(i) == c) {
                //greenCharacters.add(new Pair<>(i, String.valueOf(c)));
                //greenCharacters.put(i, String.valueOf(c));
                greenCharacters.add(new GreenCharacter(i, c));
                matched[i] = true;
            }
        }

        // Kontrollera gula och röda tecken
        //går igenom varje bokstav igen, men denna gång hoppar vi över bokstäver som redan är markerade som gröna.
        for (int i = 0; i < guess.length(); i++) {
            char c = guess.charAt(i);
            if (word.charAt(i) != c) { // Kolla om bokstaven inte redan är grön
                boolean foundYellow = false;
                //Om vi hittar en gul bokstav, markerar vi den positionen som matchad.
                //Loopar igenom alla positioner
                for (int j = 0; j < word.length(); j++) {
                    if (!matched[j] && word.charAt(j) == c) { //Inte rätt matchat, inte rätt position men rätt bokstav
                        //yellowCharacters.add(new Pair<>(i, String.valueOf(c)));
                        //yellowCharacters.put(i, String.valueOf(c));
                        yellowCharacters.add(new YellowCharacter(i,c));
                        matched[j] = true;
                        foundYellow = true;
                        break;
                    }
                }
                if (!foundYellow) {
                    //redCharacters.add(new Pair<>(i, String.valueOf(c)));
                    //redCharacters.put(i, String.valueOf(c));
                    redCharacters.add(new RedCharacter(i,c));
                }
            }
        }
    }

    private static boolean doesWordExistInList(String guess) {
        return WordGenerator.getWords().contains(guess);
    }

    private static boolean isGuessFiveCharactersLong(String guess) {
        return guess.length() == 5;
    }

    //public static List<Pair<Integer, String>> getGreenCharacters() {
      //  return greenCharacters;
    //}

    //public static HashMap<Integer, String> getGreenCharacters() {
      //  return greenCharacters;
    //}

    public static List<GreenCharacter> getGreenCharacters() {
        return greenCharacters;
    }

    //public static List<Pair<Integer, String>> getYellowCharacters() {
      //  return yellowCharacters;
    //}

    //public static HashMap<Integer, String> getYellowCharacters() {
      //  return yellowCharacters;
    //}

    public static List<YellowCharacter> getYellowCharacters() {
        return yellowCharacters;
    }

   // public static List<Pair<Integer, String>> getRedCharacters() {
     //   return redCharacters;
    //}

    //public static HashMap<Integer,String> getRedCharacters() {
      //  return redCharacters;
    //}

    public static List<RedCharacter> getRedCharacters(){
        return redCharacters;
    }


}
