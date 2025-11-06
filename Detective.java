import java.util.ArrayList;
import java.util.Scanner;

public class Detective extends Character{

    ArrayList<Item> inventory; // creates the detectives inventory

    public Detective(String name,
                     Room startingRoom,
                     Item ... items) {

        super(name, startingRoom);

        inventory = new ArrayList<>();
        for (Item item : items) {
            inventory.add(item);
        }
    }

    public ArrayList<Item> getInventory(){
        return inventory;
    }

    public void inventoryAdd(Item item ){
        inventory.add(item);
    }

    public String getInvetoryNames(){

            StringBuilder sb = new StringBuilder();
            for (Item item : inventory) {
                sb.append(item.getName()).append(" ");
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

    public void pickUpItem(){
        Scanner scan = new Scanner(System.in);

        System.out.println("What item: ");
        String choice = scan.nextLine().toLowerCase().trim();
        boolean found = false;
        for (Item item : currentRoom.getItems()){ //cycles through items and checks if the choice matches
            if (item.getName().toLowerCase().equals(choice)){
                inventory.add(item);
                System.out.println( item.getName() + " Added to inventory");
                currentRoom.checkItems(this);
                found = true;
                break;
            }


        }
        if (!found)
            System.out.println("That is not a valid item"); // if it is not in the room then it will say this


    }

    public void placeItem(){
        Scanner scan = new Scanner(System.in);

        System.out.println("Inventory: " + getInvetoryNames());
        System.out.println("What item: ");
        String choice = scan.nextLine().toLowerCase().trim();
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

    @Override
    public void getDescription() {
        System.out.println("Brief Description: ");
    }

}



