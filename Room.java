import java.util.ArrayList;
import java.util.Hashtable;

public class Room {

    // Attributes:
    String name;
    Hashtable<String, String> evidence;
    String description;
    ArrayList<Character> characters;

    public Room(String name, String description){
        this.name = name;
        this.evidence = new Hashtable<String, String>();
        this.description = description;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void checkDescription(){
        System.out.println(this.description);
    }
    
    public void showCharacters(){
        System.out.println("The [" + this.name + "] has the following characters: ");
        for(Character c: this.characters){
            System.out.println("\t* [" + c.getName() + "]");
        }
    }

    public boolean hasCharacter(Character c){
        return this.characters.contains(c);
    }

    public void addEvidence(String item, String description){
        this.evidence.put(item, description);
    }

    public void showEvidence(){
        System.out.println("The [" + this.name + "] has the following items: ");
        for(String item: evidence.keySet()){
            System.out.println("\t* " + item);
        }
    }

    public boolean hasEvidence(String item){
        return this.evidence.containsKey(item);
    }

    public void checkEvidence(String item){
        System.out.println(this.evidence.get(item));
    }

    public void addCharacter(Character c){
        characters.add(c);
    }


}
