import java.util.ArrayList;
import java.util.Hashtable;

public class Player extends Character{

    // Attributes:

    public Player(String name, String role, String description, String alibi, Hashtable<String, String> characterInfo, ArrayList<String> inventory, Room location){
        super(name, role, description, alibi, characterInfo, inventory, location);
    }

    public void move(Room r){
        this.location = r;
        r.addCharacter(this);
        System.out.println("[" + this.name + "] has moved to [" + r.getName() + "]");
    }

    public void checkHasCharacterInfo(){
        System.out.println("[" + this.name + "] does not have info to share on any other characters.");
    }

    public void checkCharacterInfo(String name){
        System.out.println("[" + this.name + "] does not have info to share on any other characters.");
    }
}
