package fk.wordleprojekt;

import java.util.ArrayList;
import java.util.List;

public class WordGuesser {

    public static List<String> guess(String guess) {

        List<String> matchingCharacters = new ArrayList<>();

        char[] word = WordGenerator.getGeneratedWord().toCharArray();
        for (int i =0; i < word.length; i++) {
            if(word[i] == guess.toCharArray()[i]) {
                matchingCharacters.add(String.valueOf(word[i]));
            }
        }

        return matchingCharacters;

    }





}
