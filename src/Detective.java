import java.util.ArrayList;
import java.util.HashMap;

import java.io.*;

public class Detective extends Character implements Serializable {
    private static final long serialVersionUID = 1L; //Ai generated in every class
    private ArrayList<Item> inventory;// creates the detectives inventory
    private Room currentRoom;
    private AbstractQuest currentQuest;
    private HashMap<String,String> witnessInfo;
    private HashMap<String,String> suspectInfo;
    private boolean gameOver;



    public Detective(String name, String description,
                     Room currentRoom,
                     Item ... items) {

        super(name,description);
        this.currentRoom = currentRoom;
        this.currentQuest = null;
        witnessInfo = new HashMap<>();
        suspectInfo = new HashMap<>();

        inventory = new ArrayList<>();
        for (Item item : items) {
            inventory.add(item);
        }
    }



    //getters
    public AbstractQuest getCurrentQuest() {
        return currentQuest;
    }

    public void setCurrentQuest(AbstractQuest quest){
        this.currentQuest = quest;
    }

    public String getDescription() {
        return "Detective";
    }

    public boolean isGameOver(){
        return gameOver;
    }


    public Room getCurrentRoom() {
        return currentRoom;
    }

    public HashMap<String, String> getWitnessInfo() {
        return witnessInfo;
    }

    public HashMap<String,String> getSuspectInfo(){
        return suspectInfo;
    }

    public void setSuspectInfo(String suspectName,String info){
        suspectInfo.put(suspectName,info);
    }

    public void setWitnessInfo(String witnessName, String info){
        witnessInfo.put(witnessName,info);
    }





    public void setGameOver(){

    }

    public ArrayList<Item> getInventory(){
        return inventory;
    }

    // adding and subtracting from arrayList
    public void addToInventory(Item item){
        inventory.add(item);
    }

    public void removeFromInventory(Item item){
        inventory.remove(item);
    }

    public ArrayList<String> getInventoryItemNames(){
        ArrayList<String> names = new ArrayList<>();
        for (Item item : inventory){
            names.add(item.getName());
        }
        return names;
    }


    public String getInventoryNames(){

            StringBuilder sb = new StringBuilder();
            for (Item item : inventory) {
                if (inventory.size() >1) {
                    sb.append(item.getName()).append("|");
                }
                else sb.append(item.getName());

            }
            return sb.toString().trim();
    }

    public String accuse(String accusation){
        ArrayList<Suspect> suspects = currentRoom.getSuspectList();
        for (Suspect suspect : suspects){
            if(suspect.getName().equals(accusation) && suspect.isGuilty()){
                gameOver = true;
                return "Congratulations you hav solved the mystery " + accusation + " was the murderer all along!";
            }

        }
        gameOver = true;
        return accusation + " was not the suspect!!!!\nYou have failed\nThe killer walks away scot free\n===GAME OVER===";

    }



    // function for movement
    public boolean move(String direction) {
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom != null) {
            currentRoom = nextRoom;
            return true;
        }
        return false;
    }


    //pick up an item in a room
    public String pickUpItem(String choice){
        for (Item item : currentRoom.getItems()){ //cycles through items and checks if the choice matches
            if (item.getName().toLowerCase().equals(choice)){
                addToInventory(item);
                currentRoom.checkItems(this);
                return item.getName() + " Added to inventory\n\n" + currentRoom.getLongDescription();
            }


        }
        return "That is not a valid item"; // if it is not in the room then it will say this


    }




    // function to place an item
    public String placeItem(String choice){


        for (Item item : inventory) { //cycles through inventory and checks if the choice matches
            if (item.getName().toLowerCase().equals(choice)) {
                currentRoom.addItem(item);
                inventory.remove(item);
                return item.getName() + " Placed in " + currentRoom.getName() + "\n\n" + currentRoom.getLongDescription();

            }
        }

        return "That is not a valid item"; // if it is not in the inventory then it will say this
    }


    // to give an item
    public String giveItem(String choice){
        for (Item item : inventory) { //cycles through inventory and checks if the choice matches
            if (item.getName().toLowerCase().equals(choice)) {
                removeFromInventory(item);
                // add to witness inventory
                currentRoom.getWitness().addToInventory(item);

                if (item instanceof AmnesiaItem && currentRoom.getWitness() instanceof AmnesiaWitness){
                    return  "Oh I remember," + ((AmnesiaItem)item).getAttachedMemory();
                }
                else {
                    return item.getName() + " given to " + currentRoom.getWitness().getName();
                }
            }

        }

         return "That is not a valid item"; // if it is not in the inventory then it will say this
    }
    public String breakItem(String choice){
        // Must be in a BreakQuest
        if (!(currentQuest instanceof BreakQuest currentQuest)) {
            return "You are not on a breaking quest!";
        }

        // Check player has the required tool
        boolean toolFound = false;
        for (Item item : inventory) {
            if (item == currentQuest.getRequiredTool()) {
                toolFound = true;
                break;
            }
        }

        if (!toolFound) {
            return "You don't have the required tool to break the item!";
        }


        choice = choice.toLowerCase().trim();

        // Check the room for the target item
        for (Item item : currentRoom.getItems()) {
            if (item.getName().toLowerCase().equals(choice)) {

                // If it is the *correct* break quest item
                if (item == currentQuest.getTargetItem()) {

                    // Complete quest
                    currentQuest.setBroken(true);

                    // Remove item from room
                    currentRoom.removeItem(item);

                    return "You have broken " + item.getName() + "!";
                }

                // Wrong item chosen
                return "You can’t break that item for this quest!";
            }
        }

        return "That item is not in this room.";
    }
    }






















