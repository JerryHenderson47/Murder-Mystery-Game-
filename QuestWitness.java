public abstract class QuestWitness extends Witness{


    private Quest quest;


    public QuestWitness(String name, String victimRelationship,
                        String information, int trustLevel,
                        String description, Quest quest){

        super(name,victimRelationship,information, trustLevel,description);
        this.quest = quest;

    }







}
