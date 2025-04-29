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

    public Character(String name, String role, String description, String alibi, Hashtable<String, String> characterInfo, ArrayList<String> inventory, Room location){
        this.name = name;
        this.role = role;
        this.description = description;
        this.alibi = alibi;
        this.characterInfo = characterInfo;
        this.inventory = inventory;
        this.location = location;
    }

    public String getName(){
        return this.name;
    }

    public void checkDescription(){
        System.out.println("[" + this.name + "] is the " + this.description + ".");
    }
    
    public Room getLocation(){
        return this.location;
    }

    public boolean checkHasCharacterInfo(String name){
        return this.characterInfo.containsKey(name);
    }
    
    public void checkCharacterInfo(){
        System.out.println("[" + this.name + "] has information about the following characters: ");
        for(String name: characterInfo.keySet()){
            System.out.println("\t* [" + name + "]");
        }
    }
    
    public void getCharacterInfo(String name){
        System.out.println("[" + this.name + "] has the following information about [" + name + "]:");
        System.out.println(this.characterInfo.get(name));
    }

    public void checkAlibi(){
       System.out.println(this.alibi);
    }

    public void checkInventory(){
        System.out.println("[" + this.name + "] is carrying the following items:");
        for(String item: this.inventory){
            System.out.println("\t* " + item);
        }
    }

    public boolean isMurderer(){
        return this.role == "murderer";
    }


}
