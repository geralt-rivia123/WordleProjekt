package fk.wordleprojekt.data.characters;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharacterStatus that = (CharacterStatus) o;
        return position == that.position && character == that.character;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, character);
    }
}
