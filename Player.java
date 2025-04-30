import java.util.ArrayList;
import java.util.Hashtable;

public class Player extends Character{

    // Attributes:

    /**
     * Constructor for the player class
     * @param name the name of the player
     * @param role the narrative role of the player (murderer/innocent/red herring)
     * @param description the player's identity
     * @param alibi the player's account of what happened the night of the murder
     * @param characterInfo the information the player has about other characters
     * @param inventory the items in the player's possession
     * @param location the room where the player is located
     */
    public Player(String name, String role, String description, String alibi, Hashtable<String, String> characterInfo, ArrayList<String> inventory, Room location){
        super(name, role, description, alibi, characterInfo, inventory, location);
    }

    /**
     * Changes the player's location to a different room
     * @param r the room for the player to move to
     */
    public void move(Room r){
        this.location = r;
        r.addCharacter(this);
        System.out.println("[" + this.name + "] has moved to [" + r.getName() + "]");
    }

    /**
     * Overrides the character class version of this function, since the player does not have starting information on any other characters
     */
    public void checkCharacterInfo(){
        System.out.println("[" + this.name + "] does not have info to share on any other characters.");
    }

    /**
     * Overrides the character class version of this function, since the player does not have starting information on any other characters
     */
    public boolean checkHasCharacterInfo(String name){
        return false;
    }

    /**
     * Overrides the character class version of this function, since the player does not have starting information on any other characters
     */
    public void getCharacterInfo(String name){
        System.out.println("[" + this.name + "] does not have info to share on any other characters.");
    }

}
