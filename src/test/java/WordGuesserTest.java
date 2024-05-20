import fk.wordleprojekt.data.WordGenerator;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class WordGuesserTest {



    @Test
    public void correctGuessTest() {
        WordGenerator.generateRandomWord();




        //System.out.println(guess);

    }

    @Test
    public void incorrectGuessTest() {
        WordGenerator.generateRandomWord();
        String guess = "fffff";

        assertNotEquals(guess, WordGenerator.getGeneratedWord());

    }

    @Test
    public void doesGuessExistInListTest() {
        WordGenerator.readWordsFromFile(Paths.get(WordGenerator.getFilePath()));
        List<String> wordList = WordGenerator.getWords();

        String guess = "albin";

        for(String word : wordList) {
            assertNotEquals(guess, word);
        }

    }




}
