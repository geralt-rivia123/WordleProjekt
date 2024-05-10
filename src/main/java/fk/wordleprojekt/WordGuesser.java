package fk.wordleprojekt;

import fk.wordleprojekt.exceptions.WordNotInListException;
import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WordGuesser {

    static List<String> matchingCharacters;

    static List<String> greenCharacters = new ArrayList<>();
    static List<String> yellowCharacters = new ArrayList<>();
    static List<String> redCharacters = new ArrayList<>();

    public static Set<String> greenCharacters2 = new HashSet<>();
    public static Set<String> yellowCharacters2 = new HashSet<>();
    public static Set<String> redCharacters2 = new HashSet<>();

    public static List<String> guess(String guess) {
        // En lista av strängar som består av strängens tecken, fast i stringformat
        List<String> guessList = guess.chars().mapToObj(c -> String.valueOf((char) c)).toList();
        List<String> wordList = WordGenerator.getGeneratedWord().chars().mapToObj(c -> String.valueOf((char) c)).toList();

        matchingCharacters = guessList.stream()
                // Filtrera ut de tecken som finns i både gissningen och det utvalda ordet
                .filter(wordList::contains)
                .toList();

        return matchingCharacters;
    }

    public static List<Pair<String, Integer>> guess1(String guess) {
        // En lista av par (tecken, index) för gissningen
        List<Pair<String, Integer>> guessList = IntStream.range(0, guess.length())
                .mapToObj(i -> new Pair<>(String.valueOf(guess.charAt(i)), i))
                .toList();

        // En lista av par (tecken, index) för det genererade ordet
        List<Pair<String, Integer>> wordList = IntStream.range(0, WordGenerator.getGeneratedWord().length())
                .mapToObj(i -> new Pair<>(String.valueOf(WordGenerator.getGeneratedWord().charAt(i)), i))
                .toList();

        // Filtrera ut de par som matchar på tecken och skapa en lista av matchande par
        return guessList.stream()
                .filter(guessPair -> wordList.stream().anyMatch(wordPair -> Objects.equals(wordPair.getKey(), guessPair.getKey())))
                .collect(Collectors.toList());
    }

    public static List<Pair<String, Integer>> guess2(String guess) {
        // En lista av par (tecken, index) för gissningen
        List<Pair<String, Integer>> guessList = IntStream.range(0, guess.length())
                .mapToObj(i -> new Pair<>(String.valueOf(guess.charAt(i)), i))
                .toList();

        // En lista av par (tecken, index) för det genererade ordet
        List<Pair<String, Integer>> wordList = IntStream.range(0, WordGenerator.getGeneratedWord().length())
                .mapToObj(i -> new Pair<>(String.valueOf(WordGenerator.getGeneratedWord().charAt(i)), i))
                .toList();

        // Filtrera ut de par som matchar på tecken och skapa en lista av matchande par
        return guessList.stream()
                .filter(guessPair -> wordList.stream().anyMatch(wordPair -> Objects.equals(wordPair.getKey(), guessPair.getKey())))
                .collect(Collectors.toList());
    }


    public static void guess3(String guess) throws WordNotInListException {

        boolean validGuess = doesWordExistInList(guess);

        if(validGuess)
        {
            greenCharacters.clear();
            yellowCharacters.clear();
            redCharacters.clear();

        /*
        char[] word = WordGenerator.getGeneratedWord().toCharArray();

        for (int i =0; i < word.length; i++) {
            if(word[i] == guess.toCharArray()[i]) {
                greenCharacters.add(String.valueOf(word[i]));
            }
            else if (guess.indexOf(word[i]) != -1 &&  !(greenCharacters.contains(String.valueOf(word[i]))))
                {
                yellowCharacters.add(String.valueOf(word[i]));
            }
            else if (!(greenCharacters.contains(String.valueOf(guess.toCharArray()[i]))) && !(yellowCharacters.contains(String.valueOf(guess.toCharArray()[i])))){
                redCharacters.add(String.valueOf(guess.toCharArray()[i]));
            }

        }

         */

            String word = WordGenerator.getGeneratedWord();
            for (int i = 0; i < guess.length(); i++) {
                char c = guess.charAt(i);

                if (word.indexOf(c) != -1) { // Tecknet finns i ordet
                    if (word.charAt(i) == c) { // Tecknet matchar på rätt plats
                        greenCharacters.add(String.valueOf(c));
                    } else { // Tecknet matchar inte på rätt plats
                        yellowCharacters.add(String.valueOf(c));
                    }
                } else { // Tecknet matchar inte alls
                    redCharacters.add(String.valueOf(c));
                }
            }
        }
        else throw new WordNotInListException("Ordet finns inte i ordlistan");



    }


    private static boolean doesWordExistInList(String guess) {
        return WordGenerator.getWords().contains(guess);
    }

    /*
    public static void guess4(String guess) {
        greenCharacters2.clear();
        yellowCharacters2.clear();
        redCharacters2.clear();

        char[] word = WordGenerator.getGeneratedWord().toCharArray();
        char[] guessChars = guess.toCharArray();

        // Set to keep track of indices already matched
        Set<Integer> matchedIndices = new HashSet<>();

        for (int i = 0; i < word.length; i++) {
            if (word[i] == guessChars[i]) {
                greenCharacters2.add(String.valueOf(word[i]));
                matchedIndices.add(i);
            }
        }

        for (int i = 0; i < word.length; i++) {
            // Skip indices already matched
            if (matchedIndices.contains(i)) {
                continue;
            }

            char currentChar = word[i];

            if (guess.indexOf(currentChar) != -1) {
                if (yellowCharacters2.contains(String.valueOf(currentChar)) && !greenCharacters2.contains(String.valueOf(currentChar))) {
                    // If already yellow, make it green
                    yellowCharacters2.remove(String.valueOf(currentChar));
                    greenCharacters2.add(String.valueOf(currentChar));
                } else {
                    yellowCharacters2.add(String.valueOf(currentChar));
                }
            } else {
                redCharacters2.add(String.valueOf(guessChars[i]));
            }
        }
    }

     */

    public static List<String> getGreenCharacters() {
        return greenCharacters;
    }

    public static List<String> getYellowCharacters() {
        return yellowCharacters;
    }

    public static List<String> getRedCharacters() {
        return redCharacters;
    }


        public static void main(String[] args) {






    }







}
