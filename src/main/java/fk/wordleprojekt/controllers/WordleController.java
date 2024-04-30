package fk.wordleprojekt.controllers;

import fk.wordleprojekt.GameManager;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class WordleController {

    private int curentLabelIndex;
    GameManager gameManager = GameManager.getInstance();
    @FXML
    public VBox buttonContainer;
    @FXML
    public HBox labelRowRoundOne;
    @FXML
    Button buttonErase;
    @FXML
    Button buttonEnter;

    @FXML
    private void initialize() {
        addOnClickToButtons(getAllCharacterButtons());
        buttonErase.setOnAction(actionEvent ->
        {
            erase();
        });
    }


    @FXML
    public void setRowVisibility(Boolean visbility) {
        buttonContainer.setVisible(visbility);
        addOnClickToButtons(getAllCharacterButtons());
    }

    public List<Button> getAllCharacterButtons() {
        List<Button> buttons = new ArrayList<>();

        for(Node node : buttonContainer.getChildren()){
            if(node instanceof HBox hBox) {
                for (Node buttonNode : hBox.getChildren()){
                    if(buttonNode instanceof Button button) {
                        //Hämta inte enter knappen och radera knappen här
                        if(!buttonNode.equals(buttonEnter) && !buttonNode.equals(buttonErase)){
                            // Här har vi referensen till varje knapp i HBoxen
                            System.out.println("Hämtade referens till knapp: " + button.getText());
                            buttons.add(button);
                        }
                    }
                }
            }
        }
        return buttons;
    }

    private void addOnClickToButtons(List<Button> buttons) {
        for (Button button : buttons) {
            //Tilldelar knappen ett actionevent som skriver ut knappens bokstav när man klickar på den
            button.setOnAction(actionEvent -> {
                System.out.println(button.getText());
                button.setStyle("-fx-background-color: red;");
                getCurrentLabel().setText(button.getText());
            });
        }
    }


    private HBox getCurrentRow() {
        HBox currentRow;
        switch (gameManager.getCurrentRound())
        {
            case 1:  currentRow = labelRowRoundOne;
            break;
            default: currentRow = labelRowRoundOne;
            break;
        }

        return currentRow;

    }

    private Label getCurrentLabel() {
        Label currentLabel = (Label) getCurrentRow().getChildren().get(getCurrentLabelIndex());
        currentLabel.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-border-style: solid;");
        return currentLabel;
    }

    private int getCurrentLabelIndex() {
        int currentLabelIndex = 0;
        for (int i = 0; i < getCurrentRow().getChildren().size(); i++){
            if(getCurrentRow().getChildren().get(i) instanceof Label label)
                //Om rutan är tom eller det är den sista rutan
                if(label.getText().isEmpty() || label.equals(getCurrentRow().getChildren().getLast())) {
                    currentLabelIndex = i;
                    System.out.println(currentLabelIndex);
                    //Stoppa loopen om vi hittat rätt ruta
                    break;
                }
        }
        return currentLabelIndex;
    }



    private void erase() {

        Label labelToErase = null;
        if(getCurrentLabelIndex() != 0)
        {
            labelToErase = (Label) getCurrentRow().getChildren().get(getCurrentLabelIndex() -1);
        }
        else if (getCurrentLabelIndex() == 4)
        {
            labelToErase = getCurrentLabel();
        }

        labelToErase.setText("");
        labelToErase.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-border-style: solid;");


    }



}
