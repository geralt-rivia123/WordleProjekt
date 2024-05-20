package fk.wordleprojekt.data.characters;

public abstract class CharacterStatus {
    private int position;
    private char character;

    public CharacterStatus(int position, char character) {
        this.position = position;
        this.character = character;
    }

    public int getPosition() {
        return position;
    }

    public char getCharacter() {
        return character;
    }

    public abstract String getColor();
}
