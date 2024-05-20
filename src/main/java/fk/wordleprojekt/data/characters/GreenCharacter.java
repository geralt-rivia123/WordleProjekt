package fk.wordleprojekt.data.characters;

import fk.wordleprojekt.data.characters.CharacterStatus;

public class GreenCharacter extends CharacterStatus {

    public GreenCharacter(int position, char character) {
        super(position,character);
    }

    @Override
    public String getColor() {
        return "green";
    }
}
