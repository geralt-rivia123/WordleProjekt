package fk.wordleprojekt.data.characters;

public class RedCharacter extends CharacterStatus{

    public RedCharacter(int position, char character) {
        super(position, character);
    }

    @Override
    public String getColor() {
        return "red";
    }
}
