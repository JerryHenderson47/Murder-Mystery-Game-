import java.util.ArrayList;
import java.security.SecureRandom;
import java.util.Scanner;


public class HideNSeekWitness extends Witness{

    private HidingSpot position;

    private ArrayList<HidingSpot> hidingSpots;

    public HideNSeekWitness(String name, String victimRelationship,
                         int trustLevel,
                            String description,
                            String information,HidingSpot ... spots) {

        super(name,victimRelationship,information, trustLevel,description);



        //create the random hiding spots
        hidingSpots = new ArrayList<>();
        for (HidingSpot spot : spots) {
            hidingSpots.add(spot);
        }

       assignRandomPosition();
    }



    private void assignRandomPosition() {
        position = hidingSpots.get(RandomHidingSpot()); // just to clean up the constructor
        position.setIsOccupied(true);
    }



    public int RandomHidingSpot(){
        SecureRandom randNum = new SecureRandom();
        return randNum.nextInt(hidingSpots.size());
    }

    //getters and setters
    public HidingSpot getPosition(){
        return position;
    }



    public ArrayList<HidingSpot> getHidingSpots(){
        return hidingSpots;
    }

    public String getHidingSpotNames(){

        StringBuilder sb = new StringBuilder();
        for (HidingSpot spot : hidingSpots) {
            sb.append(spot.getName()).append("|");
        }
        return sb.toString().trim();
    }



    @Override
    public void addToInventory(Item item){
        getInventory().add(item);
    }

    @Override
    public void interact(){
        System.out.println("Hello");
    }


    @Override
    public  void play() {
        Scanner scan = new Scanner(System.in);
        boolean witnessFound = false;

        while (!witnessFound)    {

            System.out.println(getHidingSpotNames());
            System.out.println("What Spot: ");
            String choice = scan.nextLine().toLowerCase().trim();


            boolean spotFound = false;
            for (HidingSpot spot : hidingSpots) { //cycles through hiding spots and checks if the choice matches

                if (spot.getName().toLowerCase().equals(choice) && spot.getIsOccupied()) {
                    System.out.println("Ah damn! you got me");
                    System.out.println("Ok I'll give you my story,");
                    System.out.println(getInformation());
                    witnessFound = true;


                } else if (spot.getName().toLowerCase().equals(choice) && !(spot.getIsOccupied())) {
                    System.out.println("You haven't got me ye hahaha ");
                    System.out.println("Come on this is too easy");
                }
                spotFound = true;

            }
            if (!spotFound)
                System.out.println("That is not a valid hiding spot");
        }
    }

    }




