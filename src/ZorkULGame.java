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

public class ZorkULGame implements Serializable {
    private static final long serialVersionUID = 1L;
    private Detective player;
    private AmnesiaWitness witness1;
    private HideNSeekWitness witness2;
    private QuestWitness witness3;
    private QuestWitness witness4;

    public ZorkULGame() {
        createRooms();

    }

    public Detective getPlayer(){
        return player;
    }



    private void createRooms() {
        Room mainHall, childBedroom, diningRoom, study, office, livingRoom;

        //creating main hall
        mainHall = new Room(false,"mainHall","in the main hall of the mansion\nWhere in which 5 suspects stand before you",ImageAdresses.murderScene);
        Suspect suspect1,suspect2,suspect3,suspect4,suspect5;
        suspect1 = new Suspect("Ella","Was due a priceless vase in the victims will" ,"Daughter ","Blonde teenager", "At shcool", false);
        suspect2 = new Suspect("Eoghan","The victim had not payed eoghan for several weeks","Employee" ,"Old small gardener", "Out in tha garden ", true);
        suspect3 = new Suspect("Sean", "Unknown but discovered the body on way home from work" ,"Husband","Tall businessman", "Working", false);
        suspect4 = new Suspect("Serena", "Life long resentment","Cleaner","Brunette pensioner", "not working", false);
        suspect5 = new Suspect("Tim","Was having an affair with the victim","Postman","", "Delivering post across the road ", false);




        mainHall.addSuspects(suspect1,suspect2,suspect3,suspect4,suspect5);
        player = new Detective("player","small short and stocky ", mainHall);







        //Create the Study
        AmnesiaItem memory1,memory2,memory3,memory4,memory5;

         memory1 = new AmnesiaItem(Text.memoryItem1Name, Text.memoryItem1Description, Text.memoryItem1Story,1);
         memory2 = new AmnesiaItem(Text.memoryItem2Name, Text.memoryItem2Description, Text.memoryItem2Story,2);
         memory3 = new AmnesiaItem(Text.memoryItem3Name, Text.memoryItem3Description, Text.memoryItem3Story,3);
         memory4 = new AmnesiaItem(Text.memoryItem4Name, Text.memoryItem4Description, Text.memoryItem4Story,4);
         memory5 = new AmnesiaItem(Text.memoryItem5Name, Text.memoryItem5Description, Text.memoryItem5Story,5);


        witness1 = new AmnesiaWitness("Bobby", "grandfather" , "Oh I cant quite remember, if you find a few items that could jog my memory", 60, "Small old ragged man",player);

        study = new Room(false,"study",Text.studyDescription,ImageAdresses.study, witness1,memory1,memory2,memory3,memory4,memory5);

        // create the child bedroom
        HidingSpot spot1,spot2,spot3,spot4,spot5;

        spot1 = new HidingSpot(Text.hidingSpot1Name,Text.hidingSpot1Description, 1);
        spot2 = new HidingSpot(Text.hidingSpot2Name,Text.hidingSpot2Description, 2);
        spot3 = new HidingSpot(Text.hidingSpot3Name,Text.hidingSpot3Description, 3);
        spot4 = new HidingSpot(Text.hidingSpot4Name,Text.hidingSpot4Description, 4);
        spot5 = new HidingSpot(Text.hidingSpot5Name,Text.hidingSpot5Description, 5);

        witness2 = new HideNSeekWitness("Holly", "child", 15, "Small blonde lively girl", "I saw Eoghan coming down the back staircase moments after the scream, he was clutching his leg in pain",player, spot1,spot2,spot3,spot4,spot5);


        childBedroom = new Room(true,"childBedroom",Text.childBedroomDescription,ImageAdresses.childBedroom,witness2);





        // create the Dining Room
        Item dog = new Item("Benny", "Big black and white dalmation");
        ItemQuest dogQuest = new ItemQuest(Text.dogQuestDetails,dog );
        witness3 = new QuestWitness("Ben", "child", "A man came in and grabbed one of those kitchen knives",30, "small ginger quiet boy", dogQuest, player);




        //create Living Room
        Item hammer = new Item("Hammer", "Small black and yellow");
        Item vase = new Item("Vase", "pretty vase");
        BreakQuest vaseQuest = new BreakQuest("Due to this murder my sister that I hate is gonna get mums priceless vase, could you please break it for me,just to spite her","I nedd to break my mothers vase",vase,hammer);
        witness4 = new QuestWitness("Tom", "Son" , "A person came in and robbed my shoes", 30,"Tall man", vaseQuest,player);

        livingRoom = new Room(true, "livingRoom","Small cosy living room with a boy waiting at the door",ImageAdresses.livingRoom,witness4,hammer);
        diningRoom = new Room(true, "diningRoom",Text.diningRoomDescription,ImageAdresses.diningRoom,witness3,vase);





        // create rooms
        office = new Room(false,"office","in the computing admin office",ImageAdresses.office, dog);


        // initialise room exits
        mainHall.setExit("east", childBedroom);
        mainHall.setExit("south", study);
        mainHall.setExit("west", diningRoom);
        mainHall.setExit("north", livingRoom);


        childBedroom.setExit("west", mainHall);

        diningRoom.setExit("east", mainHall);

        study.setExit("north", mainHall);
        study.setExit("east", office);

        office.setExit("west", study);

        livingRoom.setExit("south", mainHall);















    }














    public static void main(String[] args) {
        GameController controller = new GameController();
        GUI gui = new GUI();

        gui.showGUI();


    }
}
