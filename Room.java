import java.util.ArrayList;

public class Room {

    // Attributes:
    String name;
    ArrayList<String> evidence;
    String description;
    ArrayList<Character> characters;

    public Room(String name, ArrayList<String> evidence, String description){
        this.name = name;
        this.evidence = evidence;
        this.description = description;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void checkDescription(){
        System.out.println(this.description);
    }

    public ArrayList<Character> getCharacters(){
        return this.characters;
    }

    public void addCharacter(Character c){
        characters.add(c);
    }


}
