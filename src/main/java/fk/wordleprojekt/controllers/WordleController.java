package fk.wordleprojekt.controllers;

import fk.wordleprojekt.GameManager;
import fk.wordleprojekt.WordGenerator;
import fk.wordleprojekt.WordGuesser;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WordleController {
    GameManager gameManager = GameManager.getInstance();
    @FXML
    private VBox buttonContainer;
    @FXML
    private HBox labelRowRoundOne;
    @FXML
    private HBox labelRowRoundTwo;
    @FXML
    private HBox labelRowRoundThree;
    @FXML
    private HBox labelRowRoundFour;
    @FXML
    private HBox labelRowRoundFive;
    @FXML
    private HBox labelRowRoundSix;

    @FXML
    private Button buttonErase;
    @FXML
    private Button buttonEnter;

    @FXML
    private void initialize() {
        addOnClickToButtons(getAllCharacterButtons());
        buttonErase.setOnAction(actionEvent ->
        {
            eraseLabel();
        });

        buttonEnter.setOnAction(actionEvent ->
        {
            guess();
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
                //getCurrentLabel().setText(button.getText());
                writeToLabel(button.getText());
            });
        }
    }


    private HBox getCurrentRow() {
        HBox currentRow;
        switch (gameManager.getCurrentRound())
        {
            case 1: currentRow = labelRowRoundOne;
            break;

            case 2: currentRow = labelRowRoundTwo;
            break;

            case 3: currentRow = labelRowRoundThree;
            break;

            case 4: currentRow = labelRowRoundFour;
            break;

            case 5: currentRow = labelRowRoundFive;
            break;

            case 6: currentRow = labelRowRoundSix;
            break;

            default: currentRow = labelRowRoundOne;
            break;
        }

        return currentRow;

    }

    private List<Label> getCurrentLabels() {
        List<Label> labels = new ArrayList<>();
        for ( Node node : getCurrentRow().getChildren()){
            if (node instanceof Label label){
                labels.add(label);
            }

        }
        return labels;
    }

    private void writeToLabel(String text) {

        //Java Stream för att filtrera och hitta den första labeln utan text
        Optional<Label> firstEmptyLabel = getCurrentLabels().stream()
                .filter(label -> label.getText().isEmpty())
        //Returnerar en Optional<Label> eftersom det inte garanteras att det finns en tom label i listan.
                .findFirst();

        // Kontrollera om en tom label hittades
        if (firstEmptyLabel.isPresent()) {
            Label emptyLabel = firstEmptyLabel.get();
            emptyLabel.setText(text);
            System.out.println("Första labeln utan text: " + emptyLabel);
        } else {
            System.out.println("Ingen label utan text hittades.");
        }
    }

    private void eraseLabel() {
        Optional <Label> lastLabelWithText = getCurrentLabels().stream()
                .filter(label -> !label.getText().isEmpty()) // Filter för label med text
                //Hämta senaste
                .reduce((first, second) -> second);

        // Kontrollera om en label med text hittades
        if (lastLabelWithText.isPresent()) {
            Label labelWithText = lastLabelWithText.get();
            labelWithText.setText("");
            System.out.println("Sista labeln med text: " + getCurrentLabels().getLast().getText());
        } else {
            System.out.println("Ingen label med text hittades.");
        }


    }

    private void guess() {
        StringBuilder word = new StringBuilder();
        for(Label label : getCurrentLabels()) {
            word.append(label.getText());
        }
        System.out.println("du gissade" + word);
        System.out.println("rätt ord är " + WordGenerator.getGeneratedWord());
        System.out.println(WordGuesser.guess(String.valueOf(word)));
        gameManager.setCurrentRound(gameManager.getCurrentRound() + 1);
    }
}
