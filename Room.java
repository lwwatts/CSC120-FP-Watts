import java.util.ArrayList;
import java.util.Hashtable;

public class Room {

    // Attributes:
    String name;
    Hashtable<String, String> evidence;
    String description;
    ArrayList<Character> characters;

    
    /**
     * Constructor for the room class
     * @param name the name of the room
     * @param description a description of the room and what's in it
     */
    public Room(String name, String description){
        this.name = name;
        this.evidence = new Hashtable<String, String>();
        this.description = description;
        this.characters = new ArrayList<>();
    }
    
    /**
     * Accessor for the name attribute
     * @return the name of the room
     */
    public String getName(){
        return this.name;
    }
    
    /**
     * Displays the description of the room
     */
    public void checkDescription(){
        System.out.println(this.description);
    }
    
    /**
     * Displays the names of the characters located in the room
     */
    public void showCharacters(){
        System.out.println("The [" + this.name + "] has the following characters: ");
        for(Character c: this.characters){
            System.out.println("\t* [" + c.getName() + "]");
        }
    }

    /**
     * Checks if a certain character is in the room
     * @param c the character of interest
     * @return T/F: whether or not the character is in the room
     */
    public boolean hasCharacter(Character c){
        return this.characters.contains(c);
    }

    /**
     * Adds items that the user can investigate to the room
     * @param item the name of the item to be added to the room
     * @param description a description of the item to be added to the room
     */
    public void addEvidence(String item, String description){
        this.evidence.put(item, description);
    }

    /**
     * Displays the names of the evidence items located in the room
     */
    public void showEvidence(){
        System.out.println("The [" + this.name + "] has the following items: ");
        for(String item: evidence.keySet()){
            System.out.println("\t* " + item);
        }
    }

    /**
     * Checks if a certain item is in the room
     * @param item the name of the item of interest
     * @return T/F: whether or not the item is in the room
     */
    public boolean hasEvidence(String item){
        return this.evidence.containsKey(item);
    }

    /**
     * Displays the description of a certain item in the room
     * @param item the name of the item of interest
     */
    public void checkEvidence(String item){
        System.out.println(this.evidence.get(item));
    }

    /**
     * Adds a character to the room
     * @param c the character to be added to the room
     */
    public void addCharacter(Character c){
        characters.add(c);
    }


}
