import java.util.*;


public class AmnesiaWitness extends PuzzleWItness {
    Scanner scan = new  Scanner(System.in);
    private ArrayList<AmnesiaItem> memoryItems;

    public AmnesiaWitness(String name,
                          String victimRelationship,
                          int trustLevel, String puzzleName,
                          AmnesiaItem... items
                          ) {

        super(name, victimRelationship, trustLevel, puzzleName); // witness constructor


        memoryItems = new ArrayList();
//        for (AmnesiaItem item : items) {
//            this.memoryItems.add(item); // takes in an item and allows you to add it to the hashmaps cleanly, allows you to make multiple differnt Amnesia wintesses
//        }
    }


    public void printStory(){
        System.out.println("My name is " + this.getName());
        System.out.println("I can't remember anything I am no help!");
        System.out.println("Maybe you could find something to spark my memory");
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
        else {
            System.out.println("Item does not relate to a memory");
        }
    }

    public void sortMemories(){
        boolean puzzleSolved = false;

        ArrayList<AmnesiaItem> sortedItems = new ArrayList<>(memoryItems);
        sortedItems.sort(Comparator.comparingInt(AmnesiaItem::getOrderNum));// creates a separate array list with the memories in the correct order NEED TO LOOK OVER THIS
        boolean memoriesSorted = false;


        System.out.println("Sort the items so that the story fits");
        System.out.println();

       while(true) {
           int order = 1;
           System.out.println("===Current order===");
           for (AmnesiaItem item : memoryItems) {
               System.out.println(order + ": " + item.getAttachedMemory());
               order++;
           }

           // checks if the order is correct
           if (memoryItems.equals(sortedItems)){
               System.out.println("Congratulations! You have solved the puzzle");
               break;
           }

           System.out.print("Enter the number you want to move: ");
           int choice1 = scan.nextInt() - 1; // to avoid indes out of boinds
           System.out.print("Enter the number you want to swap it with: ");
           int choice2 = scan.nextInt() - 1;
           System.out.println();

           AmnesiaItem holder = memoryItems.get(choice2); // creates a holder so that the choice2 variable remains

           memoryItems.set(choice2, memoryItems.get(choice1));// sets choice1 to the index of choice 2
           memoryItems.set(choice1, holder); //completes the swap




       }










    }




    @Override
    public void interact() {
        printStory();
    }
}
