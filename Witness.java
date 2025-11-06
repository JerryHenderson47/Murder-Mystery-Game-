public abstract class Witness extends Character {
    private String victimRelationship;
    private String information;
    private int trustLevel;

    public Witness(String name, Room currentRoom ,String victimRelationship, String information,
                   int trustLevel) {
        super(name,currentRoom);
        this.victimRelationship = victimRelationship;
        this.information = information;
        this.trustLevel = trustLevel;
    }


}