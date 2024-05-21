package fk.wordleprojekt.controllers;

import fk.wordleprojekt.Difficulty;
import fk.wordleprojekt.GameManager;
import fk.wordleprojekt.data.WordGenerator;
import fk.wordleprojekt.data.WordGuesser;
import fk.wordleprojekt.data.characters.GreenCharacter;
import fk.wordleprojekt.data.characters.RedCharacter;
import fk.wordleprojekt.data.characters.YellowCharacter;
import fk.wordleprojekt.exceptions.GuessTooShortException;
import fk.wordleprojekt.exceptions.InvalidGuessException;
import fk.wordleprojekt.exceptions.WordNotInListException;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.*;

public class WordleController {
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
    private RadioButton rBtnEasy;
    @FXML
    private RadioButton rBtnMedium;
    @FXML
    private RadioButton rBtnHard;
    @FXML
    private Label infoLabel;
    @FXML
    private Button buttonReset;

    private final List<Button> buttons = new LinkedList<>();
    private String defaultLabelStyle;
    private String defaultButtonStyle;

    @FXML
    private void initialize() {
        addOnClickToButtons();
        setRadioButtons();
        setButtonReset();
        GameManager.setDifficulty(Difficulty.EASY);
        infoLabel.setText("Startade ny runda med svårighetsgrad " + GameManager.getDifficulty() + getDifficultyHint());
        buttonErase.setOnAction(actionEvent ->
        {
            eraseLabel();
        });

        buttonEnter.setOnAction(actionEvent ->
        {
            guess();
        });
        System.out.println(GameManager.getDifficulty());
    }

    private void setRadioButtons() {
        final ToggleGroup group = new ToggleGroup();
        rBtnEasy.setToggleGroup(group);
        rBtnMedium.setToggleGroup(group);
        rBtnHard.setToggleGroup(group);

        rBtnEasy.setOnAction(actionEvent -> {
            GameManager.setDifficulty(Difficulty.EASY);
        });
        rBtnMedium.setOnAction(actionEvent -> {
            GameManager.setDifficulty(Difficulty.MEDIUM);
        });
        rBtnHard.setOnAction(actionEvent -> {
            GameManager.setDifficulty(Difficulty.HARD);
        });

        rBtnEasy.setSelected(true);

    }


    private void setAllCharacterButtons() {

        for(Node node : buttonContainer.getChildren()){
            if(node instanceof HBox hBox) {
                for (Node buttonNode : hBox.getChildren()){
                    if(buttonNode instanceof Button button) {
                        //Hämta inte enter knappen och radera knappen här
                        if(!buttonNode.equals(buttonEnter) && !buttonNode.equals(buttonErase)){
                            // Här har vi referensen till varje knapp i HBoxen
                            buttons.add(button);
                            defaultButtonStyle = button.getStyle();
                            System.out.println(defaultButtonStyle);
                        }
                    }
                }
            }
        }
    }

    private void addOnClickToButtons() {

        setAllCharacterButtons();

        for (Button button : buttons) {
            //Tilldelar knappen ett actionevent som skriver ut knappens bokstav när man klickar på den
            button.setOnAction(actionEvent -> {
                writeToLabel(button.getText());

            });
        }
    }

    private void setButtonReset() {
        buttonReset.setOnAction(actionEvent ->
                reset());
    }

    private String getDifficultyHint() {
        String difficultyHint = "";
        switch (GameManager.getDifficulty()) {
            case EASY -> {
                difficultyHint = " ordet börjar på " + WordGenerator.getGeneratedWord().toUpperCase().toCharArray()[0] + " och slutar på " + WordGenerator.getGeneratedWord().toUpperCase().toCharArray()[4];
            }
            case MEDIUM -> difficultyHint = " ordet börjar på " + WordGenerator.getGeneratedWord().toUpperCase().toCharArray()[0];

            case HARD -> difficultyHint = " här får du ingen ledtråd";
        }
        return difficultyHint;
    }


    private HBox getCurrentRow() {
        HBox currentRow;
        switch (GameManager.getCurrentRound())
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
                defaultLabelStyle = label.getStyle();
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
            emptyLabel.setStyle("-fx-border-width: 2px; " + "-fx-border-color: black;");
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
        } else {
            System.out.println("Ingen label med text hittades.");
        }
    }

    private void guess() {
        StringBuilder word = new StringBuilder();
        for(Label label : getCurrentLabels()) {
            word.append(label.getText());
        }

        try
        {
            WordGuesser.guess(String.valueOf(word).toLowerCase());
            changeLabelColors();
            changeButtonColors();

            System.out.println("du gissade " + word);
            System.out.println("rätt svar är "+ WordGenerator.getGeneratedWord());

            GameManager.setCurrentRound(GameManager.getCurrentRound() + 1);
        }
        catch (InvalidGuessException e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
        }

        if(GameManager.getWinStatus()) {
            disableInput(true);
            infoLabel.setText("Grattis! Du vann!");
        }

        if(GameManager.getCurrentRound() == 7 && !GameManager.getWinStatus()) {
            disableInput(true);
            infoLabel.setText("Du förlorade. Försök igen med ett nytt ord!");
        }



    }

    private void changeButtonColors() {

        for (Button button : buttons) {
            String buttonText = button.getText();
            String currentColor = button.getStyle();
            boolean isGreen = false;
            boolean isYellow = false;

            // Kontrollera gröna tecken först
            for(GreenCharacter greenCharacter : WordGuesser.getGreenCharacters()) {
                if (buttonText.equalsIgnoreCase(String.valueOf(greenCharacter.getCharacter()))) {
                    button.setStyle("-fx-background-color: " + greenCharacter.getColor());
                    isGreen = true;
                    break;
                }
            }


            // Om inte grön, kontrollera gula tecken
            if (!isGreen) {
                for (YellowCharacter yellowCharacter : WordGuesser.getYellowCharacters()) {
                    if (buttonText.equalsIgnoreCase(String.valueOf(yellowCharacter.getCharacter())))  {
                        if (!currentColor.contains("yellow") && !currentColor.contains("green")) {
                            button.setStyle("-fx-background-color: " + yellowCharacter.getColor());
                        }
                        isYellow = true;
                        break;
                    }
                }
            }

            // Om varken grön eller gul
            if (!isGreen && !isYellow) {
                for (RedCharacter redCharacter : WordGuesser.getRedCharacters()) {
                    if (buttonText.equalsIgnoreCase(String.valueOf(redCharacter.getCharacter())))  {
                        button.setStyle("-fx-background-color: "+ redCharacter.getColor());
                    }
                }
            }

        }

    }

    private void changeLabelColors() {

        for (GreenCharacter greenCharacter : WordGuesser.getGreenCharacters()) {
            getCurrentLabels().get(greenCharacter.getPosition()).setStyle("-fx-background-color: " + greenCharacter.getColor());
        }

        for (YellowCharacter yellowCharacter : WordGuesser.getYellowCharacters()) {
            getCurrentLabels().get(yellowCharacter.getPosition()).setStyle("-fx-background-color: " + yellowCharacter.getColor());
        }

        for (RedCharacter redCharacter : WordGuesser.getRedCharacters()) {
            getCurrentLabels().get(redCharacter.getPosition()).setStyle("-fx-background-color: " + redCharacter.getColor());
        }
    }

    private void reset() {
        GameManager.startNewGame();
        clearUi();
        disableInput(false);
        System.out.println(WordGenerator.getGeneratedWord());

    }

    private void clearUi(){
        for (Button button: buttons) {
            button.setStyle(defaultButtonStyle);
        }
        for (Label label : getAllLabels()) {
            label.setStyle(defaultLabelStyle);
            label.setText("");
            label.setStyle("-fx-border-width: 1px; " + "-fx-border-color: black;");
        }

        infoLabel.setText("Startade ny runda med svårighetsgrad " + GameManager.getDifficulty() + getDifficultyHint());
    }

    private void disableInput(boolean status) {
        for (Button button : buttons) {
            button.setDisable(status);
        }
        buttonEnter.setDisable(status);
        buttonErase.setDisable(status);
    }

    private List<Label> getAllLabels() {

        List<HBox> rows = new ArrayList<>();
        List<Label> labels = new ArrayList<>();
        rows.add(labelRowRoundOne);
        rows.add(labelRowRoundTwo);
        rows.add(labelRowRoundThree);
        rows.add(labelRowRoundFour);
        rows.add(labelRowRoundFive);
        rows.add(labelRowRoundSix);

        for (HBox row : rows) {
            for ( Node node : row.getChildren()){
                if (node instanceof Label label){
                    labels.add(label);
                }

            }
        }
        return labels;
    }
}
