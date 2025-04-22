import java.util.ArrayList;
import java.util.Hashtable;

public class Character {

    // Attributes:
    private String name;
    private String role;
    private String alibi;
    private Hashtable<String, String> characterInfo;
    private ArrayList<String> inventory;
    protected Room location;

    public Character(String name, String role, String alibi, Hashtable<String, String> characterInfo, ArrayList<String> inventory, Room location){
        this.name = name;
        this.role = role;
        this.alibi = alibi;
        this.characterInfo = characterInfo;
        this.inventory = inventory;
        this.location = location;
    }

    public String getName(){
        return this.name;
    }
    
    public Room getLocation(){
        return this.location;
    }

    public void checkCharacterInfo(String name){
        System.out.println("[" + this.name + "] has the following information about [" + name + "]:");
        System.out.println(this.characterInfo.get(name));
    }

    public void checkAlibi(){
       System.out.println(this.alibi);
    }

    public void checkInventory(){
        System.out.println("[" + this.name + "] is carrying the following items:");
        for(String item: this.inventory){
            System.out.println(item);
        }
    }

    public boolean isMurderer(){
        return this.role == "murderer";
    }


}
