import java.io.Serializable;
import java.util.ArrayList;
import java.security.SecureRandom;


public class HideNSeekWitness extends Witness<ArrayList<HidingSpot>> implements Serializable {

    private static final long serialVersionUID = 1L;
    private HidingSpot position;

    private ArrayList<HidingSpot> hidingSpots;

    public HideNSeekWitness(String name, String victimRelationship,
                            int trustLevel,
                            String description,
                            String information, Detective player, HidingSpot... spots) {

        super(name, victimRelationship, information, trustLevel, description, player);


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
    @Override
    public ArrayList<HidingSpot> getRequired() {
        return hidingSpots;
    }


    public int RandomHidingSpot() {
        SecureRandom randNum = new SecureRandom();
        return randNum.nextInt(hidingSpots.size());
    }




    public ArrayList<String> getHidingSpotNames() {
        ArrayList<String> names = new ArrayList<>();
        for (HidingSpot spot : hidingSpots){
            names.add(spot.getName());
        }
        return names;
    }



    @Override
    public void addToInventory(Item item) {
        getInventory().add(item);
    }

    @Override
    public String interact() {
        return "Hi I'm " + getName() + "!\n" +
                "I'll tell you what I know if you can find me, hahah!\n" +
                "Would you like to play hide and seek???";
    }

    public String checkHidingSpot(String choice) {
        for (HidingSpot spot : hidingSpots) {

            // Player found the correct spot
            if (spot.getName().equals(choice) && spot.getIsOccupied()) {

                getPlayer().setWitnessInfo(getName(), getInformation());
                return "Ah damn! You got me!\n" +
                        "Ok I'll give you my story:\n" + getInformation();
            }

            // Wrong spot but valid guess
            else if (spot.getName().equals(choice) && (!spot.getIsOccupied())) {
                return "You haven't got me yet hahaha!\nCome on, this is too easy.";
            }
        }

        return "That is not a valid hiding spot.";
    }


    @Override
    public String play() {
        return "===Find " + getName() + " to retrive the information===";
    }
}




