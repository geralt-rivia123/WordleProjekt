package fk.wordleprojekt.exceptions;

public class WordNotInListException extends InvalidGuessException{
    public WordNotInListException(String errorMessage) {
        super(errorMessage);
    }
}
