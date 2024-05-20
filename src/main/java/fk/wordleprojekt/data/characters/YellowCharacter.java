package fk.wordleprojekt.data.characters;

public class YellowCharacter extends CharacterStatus{

    public YellowCharacter(int position, char character){
        super(position, character);
    }

    @Override
    public String getColor() {
        return "yellow";
    }
}
