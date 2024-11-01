package fk.wordleprojekt.data;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordGenerator {

    // Sökväg till ordlista
    private static final String filePath = "/fk/wordleprojekt/ordlista.txt";
    private static String randomWord = null;
    private static List<String> words;

    public static void readWordsFromFile() {
        words = new ArrayList<>();
        //Hämta ordlista från resources
        try (InputStream inputStream = WordGenerator.class.getResourceAsStream(filePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line);
            }

        } catch (IOException | NullPointerException e) {
            System.out.println("Något gick fel när filen skulle läsas: " + e.getMessage());
        }
    }

    public static void generateRandomWord(){
        readWordsFromFile();

        Random random = new Random();
        int randomIndex;
        do {
                randomIndex = random.nextInt(words.size());
            // Fortsätt tills ett annat ord än det förra har valts
        } while (words.get(randomIndex).equals(randomWord));
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

