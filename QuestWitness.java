import java.util.ArrayList;

public abstract class QuestWitness extends Witness{


    private Quest quest;
    private ArrayList<Item> inventory;


    public QuestWitness(String name, String victimRelationship,
                        String information, int trustLevel,
                        String description, Quest quest){

        super(name,victimRelationship,information, trustLevel,description);
        this.quest = quest;
        inventory = new ArrayList<>();

    }

    @Override
    public void addToInventory(Item item){
        if (item == quest.getRequiredItem()){
            inventory.add(item);
        }
        else
            System.out.println("That is not the item I wanted!");
    };







}
