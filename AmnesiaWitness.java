import java.util.HashMap;


public class AmnesiaWitness extends PuzzleWItness {

    private HashMap<String, Item> memoryItems;

    public AmnesiaWitness(String name,
                          String victimRelationship,
                          int trustLevel, String puzzleName,
                          AmnesiaItem... items
                          ) {

        super(name, victimRelationship, trustLevel, puzzleName); // witness constructor


        memoryItems = new HashMap<>();
        for (AmnesiaItem item : items) {
            this.memoryItems.put(item.getName(), item); // takes in an item and allows you to add it to the hashmaps cleanly, allows you to make multiple differnt Amnesia wintesses
        }
    }


    private void printStory(){
        System.out.print("My name is" + this.getName());
        System.out.println("I can't remember anything I am no help!");
        System.out.println("Maybe you could find something to spark my memory");
    }

    private boolean checkItems(){

        return true;
    }


    public HashMap<String, Item> getMemoryItems() {
        return memoryItems;
    }
    public void setMemoryItems(HashMap<String, Item> memoryItems) {
        this.memoryItems = memoryItems;
    }









    @Override
    public void playPuzzle() {

    }
}
