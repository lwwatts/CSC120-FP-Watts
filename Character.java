import java.util.ArrayList;
import java.util.Hashtable;

public class Character {

    // Attributes:
    protected String name;
    private String role;
    private String description;
    private String alibi;
    private Hashtable<String, String> characterInfo;
    private ArrayList<String> inventory;
    protected Room location;

    /**
     * Constructor for the character class
     * @param name the character's name
     * @param role the character's narrative role (murderer/innocent/red herring)
     * @param description the character's identity
     * @param alibi the character's account of what happened the night of the murder
     * @param characterInfo the information the character has about other characters
     * @param inventory the items in the character's possession
     * @param location the room where the character is located
     */
    public Character(String name, String role, String description, String alibi, Hashtable<String, String> characterInfo, ArrayList<String> inventory, Room location){
        this.name = name;
        this.role = role;
        this.description = description;
        this.alibi = alibi;
        this.characterInfo = characterInfo;
        this.inventory = inventory;
        this.location = location;
    }

    /**
     * Accessor for the name attribute
     * @return the character's name
     */
    public String getName(){
        return this.name;
    }

    /**
     * Displays the description of the character
     */
    public void checkDescription(){
        System.out.println("[" + this.name + "] is the " + this.description + ".");
    }
    
    /**
     * Accessor for the location attribute
     * @return the room the character is located in
     */
    public Room getLocation(){
        return this.location;
    }

    /**
     * Checks if this character has information about another character
     * @param name the other character of interest
     * @return T/F: whether this character has information on the other character
     */
    public boolean checkHasCharacterInfo(String name){
        return this.characterInfo.containsKey(name);
    }
    
    /**
     * Displays the names of the other characters that this character has information about
     */
    public void checkCharacterInfo(){
        System.out.println("[" + this.name + "] has information about the following characters: ");
        for(String name: characterInfo.keySet()){
            System.out.println("\t* [" + name + "]");
        }
    }
    
    /**
     * Displays the information the character has on another character
     * @param name the other character of interest
     */
    public void getCharacterInfo(String name){
        System.out.println("[" + this.name + "] has the following information about [" + name + "]:");
        System.out.println(this.characterInfo.get(name));
    }

    /**
     * Displays the character's alibi
     */
    public void checkAlibi(){
       System.out.println(this.alibi);
    }

    /**
     * Displays the character's inventory
     */
    public void checkInventory(){
        System.out.println("[" + this.name + "] is carrying the following items:");
        for(String item: this.inventory){
            System.out.println("\t* " + item);
        }
    }

    /**
     * Checks if the character is the murderer
     * @return T/F: whether or not the character is the murderer
     */
    public boolean isMurderer(){
        return this.role.equals("murderer");
    }


}
