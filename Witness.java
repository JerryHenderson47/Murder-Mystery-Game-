import java.io.Serializable;
import java.util.ArrayList;

public abstract class Witness<T> extends Character implements Serializable {
    private static final long serialVersionUID = 1L;
    private String victimRelationship;
    private ArrayList<Item> inventory;
    private int trustLevel;
    private String information;
    private Detective player;



    public Witness(String name,String victimRelationship,
                   String information, int trustLevel,
                   String description,Detective player) {
        super(name,description);
        this.victimRelationship = victimRelationship;
        this.trustLevel = trustLevel;
        inventory = new ArrayList<>();
        this.information = information;
        this.player = player;

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

    public Detective getPlayer(){
        return player;
    }

    public abstract void addToInventory(Item item);


    public abstract String interact();
    public abstract String play();
    public abstract T getRequired();


}