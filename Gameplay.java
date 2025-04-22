import java.util.Scanner;
import java.util.ArrayList;
import java.util.Hashtable;

public class Gameplay {

    // Attributes:
    ArrayList<Character> characters;
    ArrayList<Room> rooms;
    Player player;


    public Gameplay(){
               
    }

    public void gameLoop(){
        int guesses = 3;
        boolean won = false;
        Scanner userInput = new Scanner(System.in);
        System.out.println("Welcome to MURDER MYSTERY!");
        System.out.println("Your name is [Detective Poe]. Last night, you arrived around 6:00 PM at the estate of your old friend, [Count Fitzgerald].");
        System.out.println("");
        while(guesses != 0 && !won){

        }
        System.out.println("Game over! Thank you for playing!");
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

}
