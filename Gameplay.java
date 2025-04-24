import java.util.Scanner;
import java.util.ArrayList;
import com.google.common.graph.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;


public class Gameplay {

    // Attributes:
    Hashtable<String, Character> characters;
    Hashtable<String, Room> rooms;
    Player player;
    ImmutableGraph<Room> map;


    public Gameplay(){
        
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
        .build();
        
        // Make characters
        player = new Player("Detective Poe", "murderer", "This character has no alibi.", new Hashtable<String, String>(), new ArrayList<String>(), this.rooms.get("study"));
    }

    public void gameLoop(){
        int guesses = 3;
        boolean won = false;
        Scanner userInput = new Scanner(System.in);
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
        while(guesses != 0 && !won){
            // Establish current location
            Room currentRoom = player.getLocation();
            System.out.println("You are currently in the [" + currentRoom.getName() + "].");
            currentRoom.checkDescription();

            // Offer choice to move (move and reprint location info if user chooses)
            System.out.println("Would you like to move locations?");
            System.out.println("Respond Y/N: ");
            String willMove = userInput.nextLine();
            if(willMove == "Y" || willMove == "y"){
                boolean hasMoved = false;
                while(!hasMoved){
                    System.out.println("What room would you like to move to? Type 'back' if you no longer want to move.\n");
                    String currentMove = userInput.nextLine().toLowerCase().trim();
                    if(currentMove != "back"){
                        if(this.rooms.containsKey(currentMove)){
                            player.move(this.rooms.get(currentMove));
                            hasMoved = true;
                            currentRoom = player.getLocation();
                            System.out.println("You have moved to the [" + currentRoom.getName() + "].");
                            currentRoom.checkDescription();
                        } else {
                            System.out.println("Please enter a valid room name.");
                        }
                    } else {
                        hasMoved = true;
                    }
                }
            }

            // Offer choice to investigate characters (enter character investigation if user chooses)
            if(currentRoom.hasCharacters()){
                System.out.println("Would you like to investigate a character?");
                System.out.println("Respond Y/N: ");
            }

            // Offer choice to investigate items (enter item investigation if user chooses)

            // Offer choice to guess murderer (make guess if user chooses)
            System.out.println("Would you like to guess the murderer? You have " + guesses + " guesses remaining.");
            System.out.println("Respond Y/N: ");
            String willGuess = userInput.nextLine();
            if(willGuess == "Y" || willGuess == "y"){
                boolean hasGuessed = false;
                while(!hasGuessed){
                    System.out.println("What character would you like to guess? Type 'back' if you no longer want to make a guess.\n");
                    String currentGuess = userInput.nextLine().toLowerCase().trim();
                    if(currentGuess != "back"){
                        if(characters.containsKey(currentGuess)){
                            won = guessMurderer(characters.get(currentGuess));
                            hasGuessed = true;
                        } else {
                            System.out.println("Please enter a valid character name.");
                        }
                    } else {
                        hasGuessed = true;
                    }
                }
            }
        }
        userInput.close();
        System.out.println("Game over! Thank you for playing!");
    }

    public void investigateCharacter(Character c){

    }
    
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
    }

}
