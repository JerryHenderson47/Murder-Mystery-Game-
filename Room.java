import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class Room {
    private String description;
    private Map<String, Room> exits;// Map direction to neighboring Room
    private ArrayList<Item>  itemsList;
    private ArrayList<String> itemsNames;



        //constructor that allows you to add a description and any number of items to a room object
    public Room(String description,
                Item... items) {
        this.description = description;
        exits = new HashMap<>();

        itemsList = new ArrayList<>();
        for (Item item : items) {
            itemsList.add(item);
        }
    }

    public void checkItems(Detective currentPlayer){
        itemsList.removeIf(item -> currentPlayer.getInventory().contains(item)); // need to research this
    }

    public void addItem(Item item){
        itemsList.add(item);
    }







  public ArrayList<Item> getItems(){
        return itemsList;
  }

    public String getItemsNames() {
        StringBuilder sb = new StringBuilder();
        for (Item item : itemsList) {
            sb.append(item.getName()).append(" ");
        }
        return sb.toString().trim();
  }

    // just gets the instances description
    public String getDescription() {
        return description;
    }


    // Sets the exits for the room, mapping a direction to a room
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    // just lists all the exits
    public Room getExit(String direction) {
        return exits.get(direction);
    }


    // builds a string listing all the avalible exits
    public String getExitString() {
        StringBuilder sb = new StringBuilder();
        for (String direction : exits.keySet()) {
            sb.append(direction).append(" ");
        }
        return sb.toString().trim();
    }

    // gives the player a sentence
    public String getLongDescription() {
        return "You are " + description + ".\nExits: " + getExitString() + "\nItems: " + getItemsNames();

    }
}
