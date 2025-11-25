import java.util.ArrayList;

public abstract class Witness extends Character  {
    private String victimRelationship;
    private ArrayList<Item> inventory;
    private int trustLevel;
    private String information;



    public Witness(String name,String victimRelationship,
                   String information, int trustLevel,
                   String description) {
        super(name,description);
        this.victimRelationship = victimRelationship;
        this.trustLevel = trustLevel;
        inventory = new ArrayList<>();
        this.information = information;
    }

    //getters and setters
    public String getVictimRelationship() {
        return victimRelationship;
    }

    public void setVictimRelationship(String victimRelationship) {
        this.victimRelationship = victimRelationship;
    }

    public String getInformation(){
        return information;
    }

    public void setInformation(String information){
        this.information = information;
    }

    public ArrayList<Item> getInventory(){
        return inventory;
    }

    public int getTrustLevel(){
        return trustLevel;
    }

    public void setTrustLevel(int trustLevel){
        this.trustLevel = trustLevel;
    }

    public abstract void addToInventory(Item item);

    public abstract void interact();
    public abstract void play();


}