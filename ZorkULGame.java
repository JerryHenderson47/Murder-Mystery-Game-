/* This game is a classic text-based adventure set in a university environment.
   The player starts outside the main entrance and can navigate through different rooms like a 
   lecture theatre, campus pub, computing lab, and admin office using simple text commands (e.g., "go east", "go west").
    The game provides descriptions of each location and lists possible exits.

Key features include:
Room navigation: Moving among interconnected rooms with named exits.
Simple command parser: Recognizes a limited set of commands like "go", "help", and "quit".
Player character: Tracks current location and handles moving between rooms.
Text descriptions: Provides immersive text output describing the player's surroundings and available options.
Help system: Lists valid commands to guide the player.
Overall, it recreates the classic Zork interactive fiction experience with a university-themed setting, 
emphasizing exploration and simple command-driven gameplay
*/

import java.util.Scanner;
import java.io.*;

public class ZorkULGame {
    private Parser parser;
    private Detective player;
    private AmnesiaWitness witness1;

    public ZorkULGame() {
        createRooms();
        parser = new Parser();
    }

    private void createRooms() {
        Room outside, childBedroom, pub, study, office;


        //Create the Study
        AmnesiaItem memory1 = new AmnesiaItem(Text.memoryItem1Name, Text.memoryItem1Description, Text.memoryItem1Story,1);
        AmnesiaItem memory2 = new AmnesiaItem(Text.memoryItem2Name, Text.memoryItem2Description, Text.memoryItem2Story,2);
        AmnesiaItem memory3 = new AmnesiaItem(Text.memoryItem3Name, Text.memoryItem3Description, Text.memoryItem3Story,3);
        AmnesiaItem memory4 = new AmnesiaItem(Text.memoryItem4Name, Text.memoryItem4Description, Text.memoryItem4Story,4);
        AmnesiaItem memory5 = new AmnesiaItem(Text.memoryItem5Name, Text.memoryItem5Description, Text.memoryItem5Story,5);


         witness1 = new AmnesiaWitness("Bobby", "grandfather" , 25 , "Amnesia");

        study = new Room(Text.studyDescription, witness1,memory1,memory2,memory3,memory4,memory5);

        // create the child bedroom
        HidingSpot spot1 = new HidingSpot(Text.hidingSpot1Name,Text.hidingSpot1Description, 1);

        childBedroom = new Room(Text.childBedroomDescription);


        // create rooms
        outside = new Room("outside the main entrance of the university");
        pub = new Room("in the campus pub");
        office = new Room("in the computing admin office");


        // initialise room exits
        outside.setExit("east", childBedroom);
        outside.setExit("south", study);
        outside.setExit("west", pub);

        childBedroom.setExit("west", outside);

        pub.setExit("east", outside);

        study.setExit("north", outside);
        study.setExit("east", office);

        office.setExit("west", study);




        // create the player character and start outside
        player = new Detective("player", outside);

    }

    private void serialize(){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("player.ser"))) {
            out.writeObject(player);
            System.out.println("Object has been serialized to player.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deserialize(){
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("player.ser"))) {
            Detective deserializedPerson = (Detective) in.readObject();
            System.out.println("Object has been deserialized:");
            deserializedPerson.getDescription();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }





    public void play() {
        deserialize();
        printWelcome();

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing. Goodbye.");
        serialize();
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the University adventure!");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(player.getCurrentRoom().getLongDescription());

    }

    private boolean processCommand(Command command) {
        String commandWord = command.getCommandWord();

        if (commandWord == null) {
            System.out.println("I don't understand your command...");
            return false;
        }

        switch (commandWord) {
            case "help":
                printHelp();
                break;
            case "go":
                goRoom(command);
                break;
            case "quit":
                if (command.hasSecondWord()) {
                    System.out.println("Quit what?");
                    return false;
                } else {
                    return true; // signal to quit
                }
            case "pick":
                player.pickUpItem();
                break;
            case "place":
                player.placeItem();
                break;
            case "talk":
                if(player.getCurrentRoom().hasWitness())
                    player.getCurrentRoom().getWitness().interact();
                else System.out.println("I don't understand your command...");
                break;
            case "give":
                player.giveItem();
                break;
            case "play":
                witness1.sortMemories();
                break;
            default:
                System.out.println("I don't know what you mean...");
                break;
        }
        return false;
    }

    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander around the university.");
        System.out.print("Your command words are: ");
        parser.showCommands();
    }

    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            player.setCurrentRoom(nextRoom);

            System.out.println(player.getCurrentRoom().getLongDescription());
        }
    }

    public static void main(String[] args) {
        ZorkULGame game = new ZorkULGame();
        GUI gui = new GUI();
       // gui.showGUI();
        game.play();

    }
}
