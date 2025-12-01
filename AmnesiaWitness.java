import java.util.*;


public class AmnesiaWitness extends Witness {
    Scanner shcan = new  Scanner(System.in);
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
        else {
            System.out.println("Item does not relate to a memory");
        }
    }

    @Override
    public void play(){

        System.out.println("===Give the items to " + getName() + "to job their memory===");

        ArrayList<AmnesiaItem> sortedItems = new ArrayList<>(memoryItems);
        sortedItems.sort(Comparator.comparingInt(AmnesiaItem::getOrderNum));// creates a separate array list with the memories in the correct order NEED TO LOOK OVER THIS


        System.out.println("===Sort the items so that the story fits===");
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
               String fullStory = sortedItems.toString();
               getPlayer().setWitnessInfo(getName(),fullStory);
               break;
           }

           System.out.print("Enter the number you want to move: ");
           int choice1 = shcan.nextInt() - 1; // to avoid indes out of boinds
           System.out.print("Enter the number you want to swap it with: ");
           int choice2 = shcan.nextInt() - 1;
           System.out.println();

           AmnesiaItem holder = memoryItems.get(choice2); // creates a holder so that the choice2 variable remains
           try {
               memoryItems.set(choice2, memoryItems.get(choice1));// sets choice1 to the index of choice 2
               memoryItems.set(choice1, holder); //completes the swap
           }
           catch (ArrayIndexOutOfBoundsException e) {
               System.out.println("Invalid choice");

           }

       }


    }

    @Override
    public void interact() {
        System.out.println("My name is " + this.getName());
        System.out.println("I can't remember anything I am no help!");
        System.out.println("Maybe you could find some items to spark my memory");
        System.out.println();
        System.out.println("Would you like to help?");
        System.out.println("yes/no: ");
        String choice = shcan.nextLine().toLowerCase().trim();
        if (choice.equals("yes")){
            play();
        }
    }
}
