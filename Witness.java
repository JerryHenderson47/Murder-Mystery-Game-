public abstract class Witness  {
    private String victimRelationship;

    private int trustLevel;
    private String name;

    public Witness(String name,String victimRelationship,
                   int trustLevel) {
        this.name = name;
        this.victimRelationship = victimRelationship;
        this.trustLevel = trustLevel;
    }

    public String getName() {
        return name;
    }
    public String getVictimRelationship() {
        return victimRelationship;
    }


}