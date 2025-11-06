public abstract class ItemWitness extends Witness{
    private Item requiredItem;

    public ItemWitness(String name, Room currentRoom ,
                         String victimRelationship, String information,
                         int trustLevel, Item requiredItem){
        super(name, currentRoom, victimRelationship, information, trustLevel);
        this.requiredItem = requiredItem;
    }


}
