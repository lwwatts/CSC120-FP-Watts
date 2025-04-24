import java.util.ArrayList;
import java.util.Hashtable;

public class Player extends Character{

    // Attributes:

    public Player(String name, String role, String alibi, Hashtable<String, String> characterInfo, ArrayList<String> inventory, Room location){
        super(name, role, alibi, characterInfo, inventory, location);
    }

    public void move(Room r){
        this.location = r;
        System.out.println("[" + this.getName() + "] has moved to [" + r.getName() + "]");
    }
}
