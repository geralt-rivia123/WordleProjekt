package fk.wordleprojekt;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class WordGenerator {

    // Sökväg till ordlista
    final private static String filePath = "src/main/resources/fk/wordleprojekt/ordlista.txt";
    private static String randomWord = null;
    private final static Path path = Paths.get(filePath);
    private static List<String> words;

    public static void readWordsFromFile(Path path) {
        {
            try {
                words = Files.readAllLines(path);
            } catch (IOException e) {
                System.out.println("Något gick fel när filen skulle läsas");
            }
        }
    }

    public static void generateRandomWord(){
        readWordsFromFile(path);

        Random random = new Random();
        int randomIndex;
        do {
                randomIndex = random.nextInt(words.size());
        } while (words.get(randomIndex).equals(randomWord)); // Fortsätt tills ett annat ord än det förra har valts
        randomWord = words.get(randomIndex);
    }

    public static List<String> getWords() {
        return words;
    }

    public static String getFilePath() {
        return filePath;
    }

    public static String getGeneratedWord() {
        return randomWord;
    }


}

