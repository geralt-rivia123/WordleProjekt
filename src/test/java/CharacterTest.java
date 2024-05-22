import fk.wordleprojekt.data.characters.GreenCharacter;
import fk.wordleprojekt.data.characters.RedCharacter;
import fk.wordleprojekt.data.characters.YellowCharacter;
import org.junit.jupiter.api.Test;
import fk.wordleprojekt.data.WordGenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CharacterTest {

    @Test
    public void isCharacterSameTest() {
        GreenCharacter greenCharacter = new GreenCharacter(0,'A');
        GreenCharacter greenCharacter1 = new GreenCharacter(0,'A');
        assertEquals(greenCharacter,greenCharacter1);
    }

    @Test
    public void isCharacterNotSameTest() {
        GreenCharacter greenCharacter = new GreenCharacter(0,'A');
        GreenCharacter greenCharacter1 = new GreenCharacter(1,'B');
        assertNotEquals(greenCharacter,greenCharacter1);
    }


}
