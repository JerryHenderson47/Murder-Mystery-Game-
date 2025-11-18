public class Quest {

    private String questDetails;
    private boolean completed;
    private QuestItem requiredItem;

    public Quest(String questDetails,boolean completed,QuestItem requiredItem){
        this.questDetails = questDetails;
        this.completed = completed;
        this.requiredItem = requiredItem;
    }


    public String getQuestDetails(){
        return questDetails;
    }

    public boolean getCompleted(){
        return completed;
    }

    public QuestItem getRequiredItem(){
        return requiredItem;
    }





}
