import fk.wordleprojekt.controllers.WordleController;
import javafx.scene.layout.VBox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ButtonTest {

    @Mock
    private VBox buttonContainer;


    @InjectMocks
    private WordleController controller;


    @BeforeEach
    public void setUp() throws Exception {
        // Skapa en ny VBox för testet
        VBox mockButtonContainer = new VBox();
        // Mocka beteendet för buttonContainer
        when(buttonContainer.getChildren()).thenReturn(mockButtonContainer.getChildren());
        // Injicera den mockade buttonContainer i kontrollern
        controller.buttonContainer = buttonContainer;
    }




    @Test
    public void testSetRowVisibility() {
        // Testa setRowVisibility med olika visibilities




        // Testa setRowVisibility med olika visibilities
        controller.setRowVisibility(true);
        verify(buttonContainer, times(1)).setVisible(true);

        controller.setRowVisibility(false);
        verify(buttonContainer, times(1)).setVisible(false);


    }

    @Test
    public void testButtonListType() {
        assertNull(controller.getAllCharacterButtons());
    }
}

