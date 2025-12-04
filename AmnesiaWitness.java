import java.io.Serializable;
import java.util.*;


public class AmnesiaWitness extends Witness<ArrayList<AmnesiaItem>> implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<AmnesiaItem> memoryItems;


    public AmnesiaWitness(String name,
                          String victimRelationship, String information,
                          int trustLevel,
                           String description, Detective player
                          ) {

        super(name,victimRelationship,information, trustLevel,description, player);
        memoryItems = new ArrayList();

    }


    //getters an setters
    public ArrayList<AmnesiaItem> getMemoryItems(){

        return memoryItems;
    }

    public boolean isCorrectOrder(List<AmnesiaItem> currentOrder) {
        // Create a sorted copy in the correct order
        List<AmnesiaItem> correctOrder = new ArrayList<>(memoryItems);
        correctOrder.sort(Comparator.comparingInt(AmnesiaItem::getOrderNum));

        // Compare the player's current order to the correct one
        return currentOrder.equals(correctOrder);
    }

    public void setMemoryItems(ArrayList<AmnesiaItem> memoryItems){
        this.memoryItems = memoryItems;
    }








    public String getInventoryNames(){
        StringBuilder sb = new StringBuilder();
        for (Item item : memoryItems) {
            sb.append(item.getName()).append(" ");
        }
        return sb.toString().trim();

    }






    public void addToInventory(Item item ){
        if (item instanceof AmnesiaItem) {
            memoryItems.add((AmnesiaItem)item);
        }
    }

    @Override
    public String play() {
        return "===Give the items to " + getName() + " to reveal memories===\n" + "===Sort the items so that the story fits===";
    }


    public List<AmnesiaItem> getCurrentItems() {
        return memoryItems;
    }

    @Override
    public ArrayList<AmnesiaItem> getRequired(){
        return getSortedItems();
    }

    public ArrayList<AmnesiaItem> getSortedItems() {
        ArrayList<AmnesiaItem> sorted = new ArrayList<>(memoryItems);
        sorted.sort(Comparator.comparingInt(AmnesiaItem::getOrderNum));
        return sorted;
    }

    public boolean swapItems(int i, int j) {
        if (i < 0 || j < 0 || i >= memoryItems.size() || j >= memoryItems.size()) {
            return false;
        }

        Collections.swap(memoryItems, i, j);
        return true;
    }

    public boolean isSolved() {
        return memoryItems.equals(getSortedItems());
    }

    public String getFullStory() {
        StringBuilder sb = new StringBuilder();
        for (AmnesiaItem item : getSortedItems()) {
            sb.append(item.getAttachedMemory()).append("\n");
        }
        return sb.toString();
    }






    @Override
    public String interact() {
        return  "My name is " + getName() + "\n" +
                "I can't remember anything, I am no help!\n" +
                "Maybe you could find some items to spark my memory.\n\n" +
                "Would you like to help?\n";


    }
}
