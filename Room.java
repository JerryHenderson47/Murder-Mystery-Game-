import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.*;

public class Room implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String description;
    private Map<String, Room> exits;// Map direction to neighboring Room
    private ArrayList<Item>  itemsList;
    private Witness witness;
    private boolean hasWitness;
    private boolean hasItems;
    private ArrayList<Suspect> suspects;
    private String imagePath;
    private boolean firstPersonEnabled;





    //constructor that allows you to add a description and any number of items to a room object
    public Room(boolean firstPersonEnabled,String name, String description,
                String imagePath,Item... items
                ) {
       this(firstPersonEnabled,name,description,imagePath);

        hasItems = true;

        itemsList = new ArrayList<>();
        for (Item item : items) {
            itemsList.add(item);
        }
    }

    public Room(boolean firstPersonEnabled,String name,String description,String imagePath, Witness witness, Item... items) {

        this(firstPersonEnabled,name,description,imagePath,items);

        this.witness = witness;

        hasWitness = true;
        hasItems = true;

    }



    public Room(boolean firstPersonEnabled,String name, String description, String imagePath) {
        this.imagePath = imagePath;
        this.description = description;
        exits = new HashMap<>();

        hasWitness = false;
        hasItems = false;
        suspects = new ArrayList<>();
        this.name = name;
        this.firstPersonEnabled = firstPersonEnabled;

    }





    public boolean hasSuspects(){
        return(!suspects.isEmpty());
    }

    public ArrayList<Suspect> getSuspectList() {
        return suspects;
    }

    public ArrayList<String> getSuspectNames(){
        ArrayList<String> names = new ArrayList<>();
        for(Suspect suspect : suspects){
            names.add(suspect.getName());
        }
        return names;
    }





    public void addSuspects(Suspect ... suspects){
        Collections.addAll(this.suspects,suspects);
    }

    public String getImagePath(){
        return imagePath;
    }



    public void checkItems(Detective currentPlayer){
        itemsList.removeIf(item -> currentPlayer.getInventory().contains(item)); // need to research this
    }

    public void addItem(Item item){
        itemsList.add(item);
    }
    public void removeItem(Item item){
        itemsList.remove(item);
    }

    public boolean hasWitness(){
        return hasWitness;
    }

    public String getName(){
        return name;
    }

    public boolean isFirstPersonEnabled(){
        return firstPersonEnabled;
    }




    public Witness getWitness() {
        return witness;
    }

    public ArrayList<Item> getItems(){
        return itemsList;
  }



    public String getItemsNames() {
        StringBuilder sb = new StringBuilder();
        for (Item item : itemsList) {
            sb.append(item.getName()).append("|");
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
        if (hasWitness && hasItems)
            return "You are " + description + ".\nExits: " + getExitString() + "\nItems: " + getItemsNames() +"\nA witness called " + witness.getName();
        else if (hasItems)
            return "You are " + description + ".\nExits: " + getExitString() + "\nItems: " + getItemsNames();
        else
            return "You are " + description + ".\nExits: " + getExitString();
    }
}
