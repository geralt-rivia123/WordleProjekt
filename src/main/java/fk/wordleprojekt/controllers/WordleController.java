package fk.wordleprojekt.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class WordleController {

    @FXML
    public VBox buttonContainer;

    @FXML
    private void initialize() {
        setRowVisibility(true);
    }


    @FXML
    public void setRowVisibility(Boolean visbility) {
        buttonContainer.setVisible(visbility);
        addOnClickToButtons(getAllButtons());
    }

    public List<Button> getAllButtons() {
        List<Button> buttons = new ArrayList<>();

        for(Node node : buttonContainer.getChildren()){
            if(node instanceof HBox hBox) {
                for (Node buttonNode : hBox.getChildren()){
                    if(buttonNode instanceof Button button) {
                        // Här har vi referensen till varje knapp i HBoxen
                        System.out.println("Hämtade referens till knapp: " + button.getText());
                        buttons.add(button);
                    }
                }
            }
        }
        return buttons;
    }

    private void addOnClickToButtons(List<Button> buttons) {
        for (Button button : buttons) {
            //Tilldelar knappen ett actionevent som skriver ut knappens bokstav när man klickar på den
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    System.out.println(button.getText());
                    button.setStyle("-fx-background-color: red;");
                }
            });
        }
    }


}
