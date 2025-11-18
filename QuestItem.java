public class QuestItem extends Item {

    private Quest quest;


    public QuestItem(String name, String description,Quest quest){
        super(name, description);
        this.quest = quest;
    }
}
