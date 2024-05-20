package fk.wordleprojekt.exceptions;

public class InvalidGuessException extends Exception{
    public InvalidGuessException(String errorMessage){
        super(errorMessage);
    }
}
