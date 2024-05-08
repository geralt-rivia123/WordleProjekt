import fk.wordleprojekt.WordGenerator;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class WordGeneratorTest {
    WordGenerator wordGenerator = new WordGenerator();

    @Test
    public void readWordListTest() {
        Path path = Paths.get(wordGenerator.getFilePath());
        assertDoesNotThrow(() -> Files.readAllLines(path));
    }


    @Test
    public void readWorldListWrongFileTest() {

        Path path = Paths.get("src/main/resources/fk/wordleprojekt/fel.txt");
         assertThrows(
                IOException.class,
                () -> Files.readAllLines(path),
                "Förväntade exception att kastas");
    }

    @Test
    public void wordListFilePathTest() {
        assertEquals("src/main/resources/fk/wordleprojekt/ordlista.txt", wordGenerator.getFilePath());
    }


    @Test
    public void allWordsAreExactlyFiveCharactersLongTest(){
        Path path = Paths.get(wordGenerator.getFilePath());
        List<String> words;
        wordGenerator.readWordsFromFile(path);
        words = wordGenerator.getWords();

        for (String word : words) {
            assertEquals(5, word.length(), "Det här ordet är inte 5 tecken: " + word);
        }
    }

    @Test
    public void generateRandomWordNotEmptyTest(){
        WordGenerator.generateRandomWord();
        assertNotEquals("", WordGenerator.getGeneratedWord());
    }

    @Test
    public void generateRandomWordNotSameTest() {
        WordGenerator.generateRandomWord();
        String word1 = WordGenerator.getGeneratedWord();

        WordGenerator.generateRandomWord();
        String word2 = WordGenerator.getGeneratedWord();

        assertNotEquals(word1, word2);
    }



}
