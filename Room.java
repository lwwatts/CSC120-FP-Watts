import java.util.ArrayList;

public class Room {

    // Attributes:
    String name;
    ArrayList<String> evidence;
    String description;
    ArrayList<Character> characters;

    public Room(String name, String description){
        this.name = name;
        this.evidence = new ArrayList<String>();
        this.description = description;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void checkDescription(){
        System.out.println(this.description);
    }

    public boolean hasCharacters(){
        if(this.characters.size() == 0){
            return false;
        }
        return true;
    }
    
    public ArrayList<Character> getCharacters(){
        return this.characters;
    }

    public void addEvidence(String item){
        this.evidence.add(item);
    }

    public void addCharacter(Character c){
        characters.add(c);
    }


}
