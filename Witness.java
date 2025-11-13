import java.util.ArrayList;

public abstract class Witness  {
    private String victimRelationship;
    private ArrayList<Item> inventory;

    private int trustLevel;
    private String name;

    public Witness(String name,String victimRelationship,
                   int trustLevel) {
        this.name = name;
        this.victimRelationship = victimRelationship;
        this.trustLevel = trustLevel;
        inventory = new ArrayList<>();
    }

    public abstract void interact();

    public String getName() {
        return name;
    }
    public String getVictimRelationship() {
        return victimRelationship;
    }

  public abstract void addToInventory(Item item);



    public abstract String getInventoryNames();

    public int getTrustLevel() {
        return trustLevel;
    }
}