import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Detective extends Character implements Serializable {
    Scanner scan = new Scanner(System.in);

    private ArrayList<Item> inventory;// creates the detectives inventory
    private Room currentRoom;



    public Detective(String name, String description,
                     Room currentRoom,
                     Item ... items) {

        super(name,description);
        this.currentRoom = currentRoom;

        inventory = new ArrayList<>();
        for (Item item : items) {
            inventory.add(item);
        }
    }



    //getters


    public String getDescription() {
        return "Detective";
    }


    public Room getCurrentRoom() {
        return currentRoom;
    }


    public void setCurrentRoom(Room room){
        this.currentRoom = room;
    }



    public void interact(){};


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


    public String getInventoryNames(){

            StringBuilder sb = new StringBuilder();
            for (Item item : inventory) {
                sb.append(item.getName()).append("|");
            }
            return sb.toString().trim();
    }


    // function for movement
    public void move(String direction) {
        Room nextRoom = currentRoom.getExit(direction);// will make sense later
        if (nextRoom != null) {
            currentRoom = nextRoom; // sets the current room to the one your in right now
            System.out.println("You moved to: " + currentRoom.getDescription());
        } else {
            System.out.println("You can't go that way!");
        }
    }


    //pick up an item in a room
    public void pickUpItem(){
        System.out.println("Items: " + currentRoom.getItemsNames());
        System.out.println("What item: ");
        String choice = scan.nextLine().toLowerCase().trim();
        boolean found = false;
        for (Item item : currentRoom.getItems()){ //cycles through items and checks if the choice matches
            if (item.getName().toLowerCase().equals(choice)){
                addToInventory(item);
                System.out.println( item.getName() + " Added to inventory");
                currentRoom.checkItems(this);
                found = true;
                break;
            }


        }
        if (!found)
            System.out.println("That is not a valid item"); // if it is not in the room then it will say this


    }


    private String printInventoryDetails(){

        System.out.println("Inventory: " + getInventoryNames());
        System.out.println("What item: ");
        return scan.nextLine().toLowerCase().trim();

    }


    // function to place an item
    public void placeItem(){

       String choice = printInventoryDetails();
        boolean found = false;
        for (Item item : inventory) { //cycles through inventory and checks if the choice matches
            if (item.getName().toLowerCase().equals(choice)) {
                currentRoom.addItem(item);
                System.out.println(item.getName() + " Placed in " + currentRoom.getDescription());
                inventory.remove(item);
                found = true;
                break;
            }
        }
        if (!found)
            System.out.println("That is not a valid item"); // if it is not in the inventory then it will say this
    }


    // to give an item
    public void giveItem(){

        String choice = printInventoryDetails();
        boolean found = false;
        for (Item item : inventory) { //cycles through inventory and checks if the choice matches

            if (item.getName().toLowerCase().equals(choice)) {
                System.out.println(item.getName() + " given to " + currentRoom.getWitness().getName());
                removeFromInventory(item);
                found = true;

                // add to witness inventory
                currentRoom.getWitness().addToInventory(item);
                if (item instanceof AmnesiaItem){
                    System.out.println("Oh I remember,");
                    System.out.println(((AmnesiaItem)item).getAttachedMemory());
                }
                break;

            }

        }
        if (!found)
            System.out.println("That is not a valid item"); // if it is not in the inventory then it will say this
    }








    }









