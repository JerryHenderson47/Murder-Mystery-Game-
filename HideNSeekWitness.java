import java.util.ArrayList;
import java.security.SecureRandom;


public class HideNSeekWitness extends PuzzleWItness{
    private HidingSpot position;
    private String information;
    private ArrayList<HidingSpot> hidingSpots;

    public HideNSeekWitness(String name, String victimRelationship,
                         int trustLevel, String puzzleName,
                             String information,HidingSpot ... spots) {
        super(name,  victimRelationship,  trustLevel,puzzleName);

        this.information = information;

        //create the random hiding spot
        hidingSpots = new ArrayList<>();
        for (HidingSpot spot : spots) {
            hidingSpots.add(spot);
        }

       assignRandomPosition();
    }


   public HideNSeekWitness getHideNSeekWitness(){
        return this;
   }

    private void assignRandomPosition() {
        position = hidingSpots.get(getRandomHidingSpot()); // just to clean up the constructor
        position.setIsOccupied(true);
    }



    public int getRandomHidingSpot(){
        SecureRandom randNum = new SecureRandom();
        int randSpot = randNum.nextInt(hidingSpots.size());

        return randSpot;

    }

    public HidingSpot getPosition() {
        return position;
    }

    public  void interact(){
        System.out.println("HideNSeekWitness interact"); // fix gthisis
    };

    public  void addToInventory(Item item){
        System.out.println("HideNSeekWitness addToInventory"); // FIX THISSSSSSS
    };
    public ArrayList getInventory(){
        System.out.println("HideNSeekWitness getInventory"); //fixthis
        return hidingSpots;
    }

    public String getInventoryNames(){
        System.out.println("HideNSeekWitness gerInventoryNames"); // fiz thiss s
        return "Hello";
    }



}
