import java.util.Scanner;
import java.util.ArrayList;
import com.google.common.graph.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Set;


public class Gameplay {

    // Attributes:
    Hashtable<String, Character> characters;
    Hashtable<String, Room> rooms;
    Player player;
    ImmutableGraph<Room> map;

    /**
     * Constructor for gameplay class
     */
    public Gameplay(){
        // Initialize
        this.rooms = new Hashtable<String, Room>();
        this.characters = new Hashtable<String, Character>();

        // Make rooms
        ArrayList<Room> rooms = new ArrayList<>();
        try{
            File myFile = new File("rooms.txt");
            Scanner fileReader = new Scanner(myFile);
            while(fileReader.hasNextLine()){
                String[] roomData = fileReader.nextLine().split(":");
                for(int i = 0; i < roomData.length; i++){
                    roomData[i] = roomData[i].trim();
                }
                Room tempRoom = new Room(roomData[0], roomData[1]);
                rooms.add(tempRoom);
                this.rooms.put(roomData[0].toLowerCase(), tempRoom);
            }
            fileReader.close();
        } catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        
        // Make map
        map = GraphBuilder.undirected()
        .<Room>immutable()
        .putEdge(this.rooms.get("study"), this.rooms.get("library"))
        .putEdge(this.rooms.get("library"), this.rooms.get("foyer"))
        .putEdge(this.rooms.get("foyer"), this.rooms.get("dining room"))
        .putEdge(this.rooms.get("dining room"), this.rooms.get("kitchen"))
        .putEdge(this.rooms.get("kitchen"), this.rooms.get("office"))
        .putEdge(this.rooms.get("foyer"), this.rooms.get("upstairs hallway"))
        .putEdge(this.rooms.get("upstairs hallway"), this.rooms.get("master bedroom"))
        .putEdge(this.rooms.get("upstairs hallway"), this.rooms.get("guest bedroom"))
        .putEdge(this.rooms.get("upstairs hallway"), this.rooms.get("edward's bedroom"))
        .putEdge(this.rooms.get("upstairs hallway"), this.rooms.get("edith's bedroom"))
        .putEdge(this.rooms.get("upstairs hallway"), this.rooms.get("edric's bedroom"))
        .build();
        
        // Character alibis
        String countessAlibi = "I'm offended that you feel the need to ask me, [Detective Poe]. You should know I would never hurt my husband. But if this will help with your investigation, then I'll tell you all I know. Alongside my husband, I greeted you when you arrived, [Detective]. Then I went upstairs to read in the [Master Bedroom]. I read until around 9:00 PM, then I went to bed. I didn't wake up until I heard [Ella] screaming this morning. I'm afraid I don't know anything else.";
        String edwardAlibi = "Sure, [Detective]. I'll tell you what I can. As you know, my father summoned me and [Edric] to the [Library] around 8:00 PM last night. We talked for a while about Father's mining business. Then [Edric] went to bed around 9:00, and you, Father, and I moved into the [Study]. We talked about how you got to know Father when you investigated disruptions to mining operations in Eboracum. Then you went to bed around 10:00. Father and I went over estate affairs for another hour and half. [Thomas] came in around 11:00 to check on us, and everything seemed completely normal. When I went to bed around 11:30, I left my father still going over the estate's taxes. Please, find out what happened to my father.";
        String edithAlibi = "I don't know anything, [Detective], I swear! When I went to bed, you were still talking with my father and [Edward] downstairs! I did wake up at some time during the night, I think around 2:00 AM. I went downstairs to get some water from the [Kitchen], since I didn't want to wake anyone up at that hour just for water. When I was in the [Foyer] I could see light coming from the [Study], so I assumed my father was still working and went back to bed. Do you think he was already dead then? What if I could have helped him? Oh please, [Detective], you have to find out what happened!";
        String edricAlibi = "You probably know more than I do, [Detective], since you and [Edward] stayed with my father when I went to bed. I doubt I can tell you anything you don't already know. Or… wait a second. I did wake up at one point during the night. I'm usually a pretty light sleeper, so something must have woken me up, but I didn't hear or see anything once I was awake. I'm not sure when exactly it was, but I heard the clock in the [Foyer] strike 4:00 AM while I was trying to fall back asleep. I'm praying you sort this out soon, [Detective]. ";
        String thomasAlibi = "Of course, [Detective], I'm happy to answer any questions you have if it will help resolve this matter sooner. I went about my routine as usual last night. I first locked all the doors to the manor around 10:30 PM. Then I made a sweep of the first floor just to check that everything was tidied up. I didn't notice anything out of the ordinary. The last stop of my sweep was the [Study]. When I got there, the [Count] and [Lord Edward] were still going over papers. I made sure they didn't need anything, then I put my keys in the [Office] and went to bed. I got up at 5:00 AM along with [Ella] and [Maria]. I then picked up my keys from the [Office] where I left them and unlocked all the doors to the manor. Then I went back to the [Office] to review tasks for today. I didn't know what had happened until one of the servants came in to inform me of your investigation. I hope my information helps.";
        String ellaAlibi = "I'm not sure how much help I'll be, but I'm happy to tell you what I know, [Detective]. I helped the [Countess] change into her nightgown and get settled in the [Master Bedroom] around 8:30 PM last night. Then I went to get [Lady Edith] ready for bed around 9:00 PM. I passed [Lord Edric] in the [Upstairs Hallway] as I was leaving [Edith's Bedroom]. [Lord Edric] looked like he was also going to bed. After that, I went downstairs to the [Kitchen] to go over the day's work and talk with [Maria] while she cleaned up and prepared for breakfast today. [Thomas] dropped by around 11:00 PM to tell us he was going to bed. [Maria] and I talked for a while longer, then both went to bed shortly after the clock struck midnight. I didn't get up until 5:00 AM, which is when I usually get up. I first woke all the other servants, and all of us ate breakfast in the [Kitchen]. I finished earlier than most of the others and went to do a sweep of the downstairs to see what work needed to be done. That's when I entered the [Study] and found the [Count]. I'm afraid I can't remember anything more.";
        String mariaAlibi = "I'm not sure what you're hoping to learn from me, [Detective]. I spend most of my time in the [Kitchen], so I don't interact much with the rest of the household, especially not the [Count]. I was in the [Kitchen] all of last night, and [Ella] joined me for a while. We both went to bed around midnight. When I got up in the morning, I went straight to the [Kitchen], and I haven't left it since. I only know what's going on because one of the other servants told me. The only unusual thing that happened to me in the past day was that I had to cook more food because of your unexpected visit. Good luck with the case, [Detective], but I can't help you.";
        String andrewAlibi = "Look, [Detective], there's not much to tell. I didn't do it, and I couldn't have done it. I went to my cabin on the estate grounds around 10:00 PM, and I was there all night. I couldn't have gotten back in the manor if I tried -- [Thomas] locks the doors at night, you can ask him. Although, now that I think about it, when I got up and did my morning sweep of the grounds around 4:00 AM, I thought I saw someone in a long coat moving in the [Upstairs Hallway]. It was still dark outside so I couldn't see well, but I could've sworn there was someone there. I hope that helps.";

        // Character intel
        Hashtable<String, String> countessIntel = new Hashtable<String, String>();
        Hashtable<String, String> edwardIntel = new Hashtable<String, String>();
        Hashtable<String, String> edithIntel = new Hashtable<String, String>();
        Hashtable<String, String> edricIntel = new Hashtable<String, String>();
        Hashtable<String, String> thomasIntel = new Hashtable<String, String>();
        Hashtable<String, String> ellaIntel = new Hashtable<String, String>();
        Hashtable<String, String> mariaIntel = new Hashtable<String, String>();
        Hashtable<String, String> andrewIntel = new Hashtable<String, String>();

        // Character inventories
        ArrayList<String> playerInventory = new ArrayList<String>();
        ArrayList<String> countInventory = new ArrayList<String>();
        ArrayList<String> countessInventory = new ArrayList<String>();
        ArrayList<String> edwardInventory = new ArrayList<String>();
        ArrayList<String> edithInventory = new ArrayList<String>();
        ArrayList<String> edricInventory = new ArrayList<String>();
        ArrayList<String> thomasInventory = new ArrayList<String>();
        ArrayList<String> ellaInventory = new ArrayList<String>();
        ArrayList<String> mariaInventory = new ArrayList<String>();
        ArrayList<String> andrewInventory = new ArrayList<String>();

        // Make characters + add characters to rooms
        player = new Player("Detective Poe", "murderer", "detective", "This character's alibi has been explained in the pre-game information.", null, playerInventory, this.rooms.get("study"));
        this.rooms.get("study").addCharacter(player);
        this.characters.put("detective poe", player);
        
        Character countFitzgerald = new Character("Count Fitzgerald", "victim", "lord of the house", "Even if this character had an alibi, he could not tell it to you, because he's dead.", new Hashtable<String, String>(), countInventory, this.rooms.get("study"));
        this.rooms.get("study").addCharacter(countFitzgerald);
        this.characters.put("count fitzgerald", countFitzgerald);    
        
        Character countessFitzgerald = new Character("Countess Fitzgerald", "innocent", "lady of the house", countessAlibi, countessIntel, countessInventory, this.rooms.get("master bedroom"));
        this.rooms.get("master bedroom").addCharacter(countessFitzgerald);
        this.characters.put("countess fitzgerald", countessFitzgerald);
        
        Character edwardFitzgerald = new Character("Edward Fitzgerald", "red herring", "count's older son", edwardAlibi, edwardIntel, edwardInventory, this.rooms.get("library"));
        this.rooms.get("library").addCharacter(edwardFitzgerald);
        this.characters.put("edward fitzgerald", edwardFitzgerald);
        
        Character edithFitzgerald = new Character("Edith Fitzgerald", "innocent", "count's only daughter", edithAlibi, edithIntel, edithInventory, this.rooms.get("edith's bedroom"));
        this.rooms.get("edith's bedroom").addCharacter(edithFitzgerald);
        this.characters.put("edith fitzgerald", edithFitzgerald);
        
        Character edricFitzgerald = new Character("Edric Fitzgerald", "innocent", "count's younger son", edricAlibi, edricIntel, edricInventory, this.rooms.get("library"));
        this.rooms.get("library").addCharacter(edricFitzgerald);
        this.characters.put("edric fitzgerald", edricFitzgerald);
        
        Character thomas = new Character("Thomas", "innocent", "butler", thomasAlibi, thomasIntel, thomasInventory, this.rooms.get("office"));
        this.rooms.get("office").addCharacter(thomas);
        this.characters.put("thomas", thomas);
        
        Character ella = new Character("Ella", "innocent", "housekeeper", ellaAlibi, ellaIntel, ellaInventory, this.rooms.get("dining room"));
        this.rooms.get("dining room").addCharacter(ella);
        this.characters.put("ella", ella);
        
        Character maria = new Character("Maria", "innocent", "head cook", mariaAlibi, mariaIntel, mariaInventory, this.rooms.get("kitchen"));
        this.rooms.get("kitchen").addCharacter(maria);
        this.characters.put("maria", maria);
        
        Character andrew = new Character("Andrew", "innocent", "groundskeeper", andrewAlibi, andrewIntel, andrewInventory, this.rooms.get("foyer"));
        this.rooms.get("foyer").addCharacter(andrew);
        this.characters.put("andrew", andrew);
    }

    
    /**
     * Runs all the game operations, including the user interface
     */
    public void gameLoop(){
        int guesses = 3;
        boolean won = false;
        Scanner userInput = new Scanner(System.in);

        // Print introduction
        System.out.println("Welcome to MURDER MYSTERY!");
        System.out.println("Your name is [Detective Poe]. Last night, you arrived around 7:00 PM at the estate of your old friend, [Count Fitzgerald].");
        System.out.println("Around 8:00 PM, you sat down in the [Library] with [Count Fitzgerald] and his two sons, [Edward Fitzgerald] and [Edric Fitzgerald].");
        System.out.println("Around 9:00 PM, [Edric Fitzgerald] went to bed, and you, [Count Fitzgerald], and [Edward Fitzgerald] moved into the [Study].");
        System.out.println("Around 10:30 PM, you went to bed in the [Guest Bedroom].");
        System.out.println("This morning, around 6:00 AM, you were woken by the sound of screaming. When you follow the screaming to its source, you find [Ella], the housekeeper, in the [Study].");
        System.out.println("Next to [Ella], [Count Fitzgerald] is still sitting at his desk. Given the large amount of blood on the desk, [Count Fitzgerald] is very obviously dead.");
        System.out.println("Moments later, [Countess Fitzgerald] also enters the [Study]. She promptly faints. When [Ella] manages to awake her, [Countess Fitzgerald] asks you to investigate the case of her husband's apparent murder.");
        System.out.println("=========================================================================");
        System.out.println("During your investigation, you will be able to move freely throughout the house. As you move, you can find different members of the household in different rooms.");
        System.out.println("Your task is to question the members of the household, investigate the rooms, and determine who the murderer is. You have three guesses.");
        System.out.println("The game begins now.");
        System.out.println("=========================================================================");
       
        // run game loop
        while(guesses != 0 && !won){
            // Establish current location
            Room currentRoom = player.getLocation();
            System.out.println("You are currently in the [" + currentRoom.getName() + "].");
            currentRoom.checkDescription();

            // Offer choice to move (move and reprint location info if user chooses)
            System.out.println("From the [" + currentRoom.getName() + "] you can move to the following places: ");
            Set<Room> adjacentRooms = map.adjacentNodes(currentRoom);
            for(Room r : adjacentRooms){
                System.out.println("\t* " + r.getName());
            }
            System.out.println("Would you like to move locations?");
            System.out.println("Respond Y/N: ");
            String willMove = userInput.nextLine().trim();
            System.out.println("willMove contents: " + willMove);
            if(willMove.equals("Y") || willMove.equals("y")){
                boolean hasMoved = false;
                while(!hasMoved){
                    System.out.println("What room would you like to move to? Type 'back' if you no longer want to move.\n");
                    String currentMove = userInput.nextLine().toLowerCase().trim();
                    if(!currentMove.equals("back")){
                        if(this.rooms.containsKey(currentMove)){
                            Room nextRoom = this.rooms.get(currentMove);
                            if(adjacentRooms.contains(nextRoom)){
                                player.move(nextRoom);
                                hasMoved = true;
                                currentRoom = nextRoom;
                                System.out.println("You have moved to the [" + currentRoom.getName() + "].");
                                currentRoom.checkDescription();
                            } else {
                                System.out.println("Please enter a room that is connected to your current room.");
                            }
                        } else {
                            System.out.println("Please enter a valid room name.");
                        }
                    } else {
                        hasMoved = true;
                    }
                }
            }
            System.out.println();

            // Offer choice to investigate characters (enter character investigation if user chooses)
            currentRoom.showCharacters();
            System.out.println("Would you like to investigate a character in this room?");
            System.out.println("Respond Y/N: ");
            String willInvestigateCharacter = userInput.nextLine().trim();
            if(willInvestigateCharacter.equals("Y") || willInvestigateCharacter.equals("y")){
                boolean hasInvestigated = false;
                while(!hasInvestigated){
                    System.out.println("What character would you like to investigate? Type 'back' if you no longer want to investigate a character.\n");
                    String characterName = userInput.nextLine().toLowerCase().trim();
                    if(!characterName.equals("back")){
                        if(this.characters.containsKey(characterName)){
                            Character currentCharacter = this.characters.get(characterName);
                            if(currentRoom.hasCharacter(currentCharacter)){
                                this.investigateCharacter(currentCharacter, userInput);
                            } else {
                                System.out.println("Please enter a character that is in your current room.");
                            }
                        } else{
                            System.out.println("Please enter a valid character name.");
                        }
                    } else {
                        hasInvestigated = true;
                    }
                }
            }
            System.out.println();

            // Offer choice to guess murderer (make guess if user chooses)
            System.out.println("Would you like to guess the murderer? You have " + guesses + " guesses remaining.");
            System.out.println("Respond Y/N: ");
            String willGuess = userInput.nextLine().trim();
            if(willGuess.equals("Y") || willGuess.equals("y")){
                boolean hasGuessed = false;
                while(!hasGuessed){
                    System.out.println("What character would you like to guess? Type 'back' if you no longer want to make a guess.\n");
                    String currentGuess = userInput.nextLine().toLowerCase().trim();
                    if(!currentGuess.equals("back")){
                        if(characters.containsKey(currentGuess)){
                            won = guessMurderer(characters.get(currentGuess));
                            if(!won){
                                guesses -= 1;
                            }
                            hasGuessed = true;
                        } else {
                            System.out.println("Please enter a valid character name.");
                        }
                    } else {
                        hasGuessed = true;
                    }
                }
            }
            System.out.println();
        }
        userInput.close();
        System.out.println("Game over! Thank you for playing!");
    }

    /**
     * Runs the user interface for character investigation
     * @param c the character under investigation
     * @param userInput the scanner listening for user input
     */
    public void investigateCharacter(Character c, Scanner userInput){        
        // Introduce character underinvestigation
        System.out.println();
        System.out.println("You are currently investigating [" + c.getName() + "].");
        c.checkDescription();

        // Offer choice to check alibi
        System.out.println("Would you like to ask for [" + c.getName() + "]'s alibi?");
        System.out.println("Respond Y/N: ");
        String askAlibi = userInput.nextLine().trim();
        if(askAlibi.equals("Y") || askAlibi.equals("y")){
            System.out.println(c.getName() + ": ");
            c.checkAlibi();
        }
        System.out.println();

        // Offer choice to ask about other characters
        c.checkCharacterInfo();
        System.out.println("Would you like to ask [" + c.getName() + "] about other characters?");
        System.out.println("Respond Y/N: ");
        String askAboutOthers = userInput.nextLine().trim();
        if(askAboutOthers.equals("Y") || askAboutOthers.equals("y")){
            boolean hasAsked = false;
            while(!hasAsked){
                System.out.println("What character would you like to ask about? Type 'back' if you are done asking about charatcers.\n");
                String characterName = userInput.nextLine().toLowerCase().trim();
                if(!characterName.equals("back")){
                    if(this.characters.containsKey(characterName)){
                        if(c.checkHasCharacterInfo(characterName)){
                            c.getCharacterInfo(characterName);
                            System.out.println();
                        } else {
                            System.out.println("Please enter a character that [" + c.getName() + "] knows about.");
                        }
                    } else{
                        System.out.println("Please enter a valid character name.");
                    }
                } else {
                    hasAsked = true;
                }
            }
        }
        System.out.println();

        // Offer choice to look at character's inventory
        System.out.println("Would you like to look at [" + c.getName() + "]'s inventory?");
        System.out.println("Respond Y/N: ");
        String lookAtInventory = userInput.nextLine().trim();
        if(lookAtInventory.equals("Y") || lookAtInventory.equals("y")){
            c.checkInventory();
        }
        System.out.println();

        System.out.println("You have finished investigating [" + c.getName() + "]");
    }
    
    /**
     * Checks if a certain character is the murderer
     * @param c the character whose role is in question
     * @return T/F: whether or not the character is the murderer
     */
    public boolean guessMurderer(Character c){
        boolean guess = c.isMurderer();
        if(guess){
            System.out.println("Your guess is correct! You have won!");
        } else {
            System.out.println("Sorry, that is incorrect.");
        }
        return guess;
    }

    public static void main(String[] args) {
        Gameplay gameplay = new Gameplay();
        gameplay.gameLoop();
    }

}
