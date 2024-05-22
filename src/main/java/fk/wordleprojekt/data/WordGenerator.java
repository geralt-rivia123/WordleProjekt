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
    //final private static String filePath = "src/main/resources/fk/wordleprojekt/ordlista.txt";
    private static final String resourcePath = "/fk/wordleprojekt/ordlista.txt";
    private static String randomWord = null;
    //private final static Path path = Paths.get(filePath);
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




    public static void readWordsFromResource() {
        words = new ArrayList<>();
        //Hämta ordlista från resources
        try (InputStream inputStream = WordGenerator.class.getResourceAsStream(resourcePath);
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
        //readWordsFromFile(path);
        readWordsFromResource();
        //randomWord = "tvätt";

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
        //return filePath;
        return resourcePath;
    }

    public static String getGeneratedWord() {
        return randomWord;
    }


}

