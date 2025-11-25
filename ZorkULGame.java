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

import java.io.*;

public class ZorkULGame {
    private Parser parser;
    private Detective player;
    private AmnesiaWitness witness1;
    private HideNSeekWitness witness2;

    public ZorkULGame() {
        createRooms();
        parser = new Parser();
    }

    private void createRooms() {
        Room outside, childBedroom, diningRoom, study, office;


        //Create the Study
        AmnesiaItem memory1 = new AmnesiaItem(Text.memoryItem1Name, Text.memoryItem1Description, Text.memoryItem1Story,1);
        AmnesiaItem memory2 = new AmnesiaItem(Text.memoryItem2Name, Text.memoryItem2Description, Text.memoryItem2Story,2);
        AmnesiaItem memory3 = new AmnesiaItem(Text.memoryItem3Name, Text.memoryItem3Description, Text.memoryItem3Story,3);
        AmnesiaItem memory4 = new AmnesiaItem(Text.memoryItem4Name, Text.memoryItem4Description, Text.memoryItem4Story,4);
        AmnesiaItem memory5 = new AmnesiaItem(Text.memoryItem5Name, Text.memoryItem5Description, Text.memoryItem5Story,5);


         witness1 = new AmnesiaWitness("Bobby", "grandfather" , "Oh I cant quite remember, if you find a few items that could jog my memory", 60, "Small old ragged man");

        study = new Room(Text.studyDescription, witness1,memory1,memory2,memory3,memory4,memory5);

        // create the child bedroom
        HidingSpot spot1 = new HidingSpot(Text.hidingSpot1Name,Text.hidingSpot1Description, 1);
        HidingSpot spot2 = new HidingSpot(Text.hidingSpot2Name,Text.hidingSpot2Description, 2);
        HidingSpot spot3 = new HidingSpot(Text.hidingSpot3Name,Text.hidingSpot3Description, 3);
        HidingSpot spot4 = new HidingSpot(Text.hidingSpot4Name,Text.hidingSpot4Description, 4);
        HidingSpot spot5 = new HidingSpot(Text.hidingSpot5Name,Text.hidingSpot5Description, 5);

        witness2 = new HideNSeekWitness("Holly", "child", 15, "Small blonde lively girl", "A man walked in and grabbed a knife",spot1,spot2,spot3,spot4,spot5);


        childBedroom = new Room(Text.childBedroomDescription,witness2);



        // create the Dining Room
        Item dog = new Item("Benny", "Big black and white dalmation");
        ItemQuest dogQuest = new ItemQuest(Text.dogQuestDetails,dog );
        QuestWitness witness3 = new QuestWitness("Ben", "child", "A person came in and grabbed one of those kitchen knives",30, "small ginger quiet boy", dogQuest);


        diningRoom = new Room(Text.diningRoomDescription,witness3);




        // create rooms
        outside = new Room("outside the main entrance of the university");
        office = new Room("in the computing admin office", dog);


        // initialise room exits
        outside.setExit("east", childBedroom);
        outside.setExit("south", study);
        outside.setExit("west", diningRoom);

        childBedroom.setExit("west", outside);

        diningRoom.setExit("east", outside);

        study.setExit("north", outside);
        study.setExit("east", office);

        office.setExit("west", study);












        // create the player character and start outside
        player = new Detective("player","small short and stocky ", outside);

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
        Room currentRoom = player.getCurrentRoom();


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
                if(currentRoom.hasWitness())
                    currentRoom.getWitness().interact();
                else System.out.println("I don't understand your command...");
                break;
            case "give":
                player.giveItem();
                break;
            case "play":
                if (currentRoom.hasWitness()) {
                    currentRoom.getWitness().play();
                }
                else System.out.println("There is no witness in this room!");
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
//        GUI gui = new GUI();



       // gui.showGUI();
        game.play();

    }
}
