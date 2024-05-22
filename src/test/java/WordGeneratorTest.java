import fk.wordleprojekt.data.WordGenerator;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WordGeneratorTest {

    @Test
    public void readWordListTest() {
        //Path path = Paths.get(WordGenerator.getFilePath());
        assertDoesNotThrow(() -> WordGenerator.readWordsFromResource());
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
        assertEquals("/fk/wordleprojekt/ordlista.txt", WordGenerator.getFilePath());
    }

    @Test
    public void allWordsAreExactlyFiveCharactersLongTest(){
        WordGenerator.readWordsFromResource();
        //Path path = Paths.get(WordGenerator.getFilePath());
        List<String> words;
        //WordGenerator.readWordsFromFile(path);
        words = WordGenerator.getWords();

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
