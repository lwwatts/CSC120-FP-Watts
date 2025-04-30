import java.util.ArrayList;

public class Room {

    // Attributes:
    String name;
    String description;
    ArrayList<Character> characters;

    
    /**
     * Constructor for the room class
     * @param name the name of the room
     * @param description a description of the room and what's in it
     */
    public Room(String name, String description){
        this.name = name;
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
     * Adds a character to the room
     * @param c the character to be added to the room
     */
    public void addCharacter(Character c){
        characters.add(c);
    }


}
