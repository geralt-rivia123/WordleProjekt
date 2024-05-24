import fk.wordleprojekt.data.WordGenerator;
import fk.wordleprojekt.data.WordGuesser;
import fk.wordleprojekt.exceptions.GuessTooShortException;
import fk.wordleprojekt.exceptions.InvalidGuessException;
import fk.wordleprojekt.exceptions.WordNotInListException;
import fk.wordleprojekt.exceptions.WordNotInListException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class WordGuesserTest {


    @Test
    public void incorrectGuessTest() {
        WordGenerator.generateRandomWord();
        String guess = "fffff";

        assertNotEquals(guess, WordGenerator.getGeneratedWord());

    }

    @Test
    public void correctGuessTest() {
        WordGenerator.generateRandomWord();
        String generatedWord = WordGenerator.getGeneratedWord();

        assertDoesNotThrow(() -> WordGuesser.guess(generatedWord),
                "Ingen exception borde kastas för en korrekt gissning");
    }

    @Test
    public void doesGuessNotExistInListTest() {
        WordGenerator.generateRandomWord();

        String guess = "albin";

        assertThrows(WordNotInListException.class,
                () -> WordGuesser.guess(guess),
                "Förväntade exception att kastas");

    }

    @Test
    public void isGuessShorterThanFiveCharactersTest() {
        WordGenerator.generateRandomWord();

        String guess = "har";

        assertThrows(GuessTooShortException.class,
                () -> WordGuesser.guess(guess),
                "Förväntade exception att kastas");

    }

}
