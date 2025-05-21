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
        countessIntel.put("edward fitzgerald", "Oh, [Edward] wouldn't hurt his father. The two of them have always been close, since my husband has been preparing [Edward] to receive the title of count one day.");
        countessIntel.put("edith fitzgerald", "[Edith] could never hurt a fly! She's such a shy girl, always hiding in her room. My husband was working hard to find a husband for her though.");
        countessIntel.put("edric fitzgerald", "[Edric] fought with his father a lot, since [Edric] wants to join the priesthood while his father would rather he practice law. But I don't believe [Edric] would ever truly hurt his father, he's such a sweet boy.");
        countessIntel.put("thomas", "Ah, I don't actually know much about [Thomas]. He's a more recent hire. He was highly recommended by the last household he worked for though. They emphasized just how thorough he is when it comes to paperwork.");
        countessIntel.put("ella", "[Ella] has worked for our family for years. My husband took her in and gave her a job when she needed one. She would never betray him.");
        countessIntel.put("maria", "[Maria] has been a great blessing to our family. When we were struggling to make ends meet before my husband opened his mines, [Maria] was always able to make the best meals with only a few ingredients. She's a kind soul. I can't imagine she'd ever murder anyone.");
        countessIntel.put("andrew", "I've never interacted with Andrew very much, nor do I know much about him. My husband took a trip to Eboracum several years ago to check on the mines he owned there. I believe that's actually when he met you, [Detective]. But anyway, he came back from that trip with [Andrew] and told me that [Andrew] was going to be our new groundskeeper.");
        Hashtable<String, String> edwardIntel = new Hashtable<String, String>();
        edwardIntel.put("countess fitzgerald", "My mother and my father may have had an arranged marriage, but they were always on good terms. My father gave my mother everything he could. She would never hurt him.");
        edwardIntel.put("edith fitzgerald", "My sister is a shut-in, always hiding away in her room with her latest book. Even if she did have some issue with my father, [Edith] isn't the type for violence.");
        edwardIntel.put("edric fitzgerald", "I hate to speak ill of my brother, [Detective], but [Edric] and Father never got along. They might have seemed peaceful last night, but they've been arguing even more than usual lately. It's all about [Edric]'s career choice. He wants to waste the opportunities our father has given us by going off and joining the priesthood.");
        edwardIntel.put("thomas", "[Thomas] has been a large help around here lately, helping my father and I review our family's account books. He really is brilliant with numbers. [Thomas] is loyal to my father. He's definitely not the culprit here.");
        edwardIntel.put("ella", "My mother adores [Ella] since she's always been a reliable housekeeper, even when we were going through financial problems and couldn't pay her properly. Personally, I've always thought [Ella] was a bit too nosy for her own good, but after all she's done for us, I can't really hold it against her.");
        Hashtable<String, String> edithIntel = new Hashtable<String, String>();
        edithIntel.put("countess fitzgerald", "My mother is an angel. I know she didn't love my father in a romantic way, but she certainly loved him like family. I refuse to believe that she'd hurt him.");
        edithIntel.put("edward fitzgerald", "[Edward] is such a snob. He's been kissing up to our father since we were young. He's just been waiting for the day he finally gets to inherit the title. Well, I guess he finally got what he wanted, though I doubt he wanted it like this.");
        edithIntel.put("edric fitzgerald", "[Edric] is much nicer than [Edward]. And don't tell [Edward], but I think [Edric] would make a great priest. He's so kind, though he's also as stubborn as they come. I know [Edric] fought with our father, but he would certainly never murder him.");
        edithIntel.put("ella", "[Ella] is wonderful. She took care of me when I was younger, and she brings me new books to read. She definitely didn't kill my father.");
        edithIntel.put("maria", "[Maria] has supported our family for a long time, and she's always been kind to me. She was never resentful when we couldn't afford to pay much. [Maria] may seem fierce, but she's a very caring person. She's no murderer.");
        Hashtable<String, String> edricIntel = new Hashtable<String, String>();
        edricIntel.put("countess fitzgerald", " You can't actually believe my mother murdered my father. She's stood by him through thick and thin. Even when our family hit hard times, she supported my father. Besides, she's just not a violent person.");
        edricIntel.put("edward fitzgerald", "Please forgive me for what I'm about to say, but my brother is as obnoxious and greedy as they come. [Edward] has never been content with what our family had. He was actually the one who pushed my father to look at mining in Eboracum. He's always looking for ways to grow his own power. I can't believe my own brother would commit patricide, but… he certainly won't mind his new position.");
        edricIntel.put("edith fitzgerald", "My sister may be far sharper than she lets on to most people, but she's still as gentle as she looks. [Edith] didn't like our father's plans to marry her off, but despite that, she still loved our father. She would never hurt him, even if she could.");
        Hashtable<String, String> thomasIntel = new Hashtable<String, String>();
        thomasIntel.put("edward fitzgerald", "[Lord Edward] is a talented young man, though he lacks his father's kindness. However, he's hardly cruel enough to murder his own father in cold blood.");
        thomasIntel.put("ella", "[Ella] has been working here far longer than I have, and she's earned the trust the family has in her.");
        thomasIntel.put("maria", "[Maria] is a talented cook and a loyal woman. She's been with the family for a long time, and I don't believe she'd ever harm her employer.");
        thomasIntel.put("andrew", "When it comes to [Andrew], I must confess I harbor some suspicion. He wasn't hired through any of the normal processes, and when I was going over the estate's accounts, I noticed he has an unusually high wage for a groundskeeper. I suspect he may have been blackmailing the [Count], although I never got the chance to speak to the [Count] about it.");
        Hashtable<String, String> ellaIntel = new Hashtable<String, String>();
        ellaIntel.put("edith fitzgerald", "Oh, [Lady Edith] is such a smart girl. She's always reading some new mystery novel. I've told her that such gruesome stories aren't appropriate for a girl her age, but she insists that there's nothing she can't handle. She's still a gentle soul though.");
        ellaIntel.put("thomas", "[Thomas] is a more recent hire, so I don't know him as well as I'd like to, but he's been a great help to me since he arrived. He always knows what's going on everywhere.");
        ellaIntel.put("maria", "[Maria] has been a dear friend of mine for a long time. We've both been with the Fitzgerald family for ages, even when times were hard for them. She acts all tough sometimes, but she's a sweetheart.");
        Hashtable<String, String> mariaIntel = new Hashtable<String, String>();
        mariaIntel.put("thomas", "[Thomas] was always off with the [Count] and [Lord Edward] going over some paperwork, so I can't tell you much about him. He's certainly efficient though. He never lets a mistake slip by him.");
        mariaIntel.put("ella", "[Ella] is the best sort of person you'll ever meet, and I'm damn lucky to have a friend like her. She cares for the Fitzgeralds like their her own family. It's why she stayed, even when the family hit hard times.");
        mariaIntel.put("andrew", "I've never liked [Andrew] much, always skulking around, avoiding the rest of us. Besides, he's never bothered to hide his disdain for the [Count] either. Rotten man, if you ask me.");
        Hashtable<String, String> andrewIntel = new Hashtable<String, String>();
        andrewIntel.put("count fitzgerald", "Look, [Count Fitzgerald] didn't deserve his good name, that's all I'm going to say. But hey, [Detective], I'm sure you know that well enough by now.");

        // Character inventories
        ArrayList<String> playerInventory = new ArrayList<String>();
        playerInventory.add("A newspaper clipping with the headline 'Young girl dies in mining accident.'");
        ArrayList<String> countInventory = new ArrayList<String>();
        countInventory.add("An unopened letter of no importance.");
        countInventory.add("A worn wedding ring.");
        ArrayList<String> countessInventory = new ArrayList<String>();
        countessInventory.add("Several used handkerchiefs.");
        countessInventory.add("A tube of smelling salts.");
        countessInventory.add("A worn wedding ring.");
        ArrayList<String> edwardInventory = new ArrayList<String>();
        edwardInventory.add("The count's seal.");
        edwardInventory.add("A heavy purse.");
        ArrayList<String> edithInventory = new ArrayList<String>();
        edithInventory.add("A locket with a picture of an unfamiliar young man.");
        edithInventory.add("A mystery novel.");
        ArrayList<String> edricInventory = new ArrayList<String>();
        edricInventory.add("A string of prayer beads.");
        edricInventory.add("A cross necklace.");
        ArrayList<String> thomasInventory = new ArrayList<String>();
        thomasInventory.add("The keys to the house's exterior doors.");
        ArrayList<String> ellaInventory = new ArrayList<String>();
        ellaInventory.add("The keys to the house's interior doors.");
        ArrayList<String> mariaInventory = new ArrayList<String>();
        mariaInventory.add("A paring knife.");
        ArrayList<String> andrewInventory = new ArrayList<String>();
        andrewInventory.add("A shovel.");
        andrewInventory.add("An expensive-looking pocket watch.");

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
        System.out.println("Next to [Ella], [Count Fitzgerald] is lying on the ground behind his desk. Given the large amount of blood under him, [Count Fitzgerald] is very obviously dead.");
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
