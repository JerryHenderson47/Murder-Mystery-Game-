import java.io.Serializable;

public  class QuestWitness extends Witness<AbstractQuest> implements Serializable {
    private static final long serialVersionUID = 1L;
    private AbstractQuest quest;



    public QuestWitness(String name, String victimRelationship,
                        String information, int trustLevel,
                        String description, AbstractQuest quest, Detective player){

        super(name,victimRelationship,information, trustLevel,description, player);
        this.quest = quest;
    }






    @Override
    public void addToInventory(Item item){
        getInventory().add(item);
    }

    @Override
    public String play(){
        getPlayer().setCurrentQuest(quest);
        return  quest.startQuest();

    }
    @Override
    public AbstractQuest getRequired(){
        return quest;
    }

    public AbstractQuest getQuest(){
        return quest;
    }




    @Override
    public String interact(){
        // If quest already completed
        if (quest.isCompleted()) {
            return "Thanks for that, I hope I helped!";
        }

        // If quest has started but not finished
        if (quest.isRunning()) {

            // Ask the QUEST for the next message
            String result = quest.endResult(this);

            if (!quest.isCompleted()) {
                return "Have you done it yet????\nCome on, I am waiting!";
            } else {
                getPlayer().setWitnessInfo(getName(), getInformation());
                return result + "\nThank you so much!";
            }



        }



        // If quest has not started yet
        return "Hi I'm " + getName() + "\n"
                + "I'd love to help but... " + quest.giveProblem();
    }


}
