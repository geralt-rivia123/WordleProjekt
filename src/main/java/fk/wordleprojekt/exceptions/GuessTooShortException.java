package fk.wordleprojekt.exceptions;

public class GuessTooShortException extends RuntimeException{
    public GuessTooShortException(String errorMessage) {super(errorMessage);}
}
