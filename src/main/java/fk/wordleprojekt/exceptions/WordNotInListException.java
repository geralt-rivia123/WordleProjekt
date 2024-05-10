package fk.wordleprojekt.exceptions;

public class WordNotInListException extends RuntimeException{
    public WordNotInListException(String errorMessage) {
        super(errorMessage);
    }
}
