public abstract class ItemWitness extends Witness{
    private Item requiredItem;
    private String information;

    public ItemWitness(String name, String victimRelationship,
                       String information,
                         int trustLevel, Item requiredItem){
        super(name, victimRelationship, trustLevel);
        this.information = information;
        this.requiredItem = requiredItem;
    }


}
