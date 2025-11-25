public class ItemQuest implements Quest<Witness>{

    private String questDetails;
    private boolean completed;
    private Item requiredItem;
    private boolean isRunning;



    public ItemQuest(String questDetails, Item requiredItem){
        this.questDetails = questDetails;
        this.requiredItem = requiredItem;
        completed = false;
        isRunning = false;
    }





    public Item getRequiredItem(){
        return requiredItem;
    }

    public void setCompleted(boolean completed){
        this.completed = completed;
    }

    @Override
    public boolean getCompleted(){
        return completed;
    }


    @Override
    public boolean isRunning(){
        return isRunning;
    }





    @Override
    public String giveProblem(){
        return "I have lost my " + requiredItem.getName();
    }

    @Override
    public String giveDetails(){
        return questDetails;
    }
    @Override
    public void endResult(Witness witness){
        if(completed){
            System.out.println("You actually find my " + requiredItem.getName());
            System.out.println("Thank you so much!!!");
            System.out.println("I suppose I owe you one....");
            System.out.println("Ok so, here is what i know..." + witness.getInformation());
            isRunning = false;
        }
        else System.out.println("You still don't have it???\n Come on man......");
    }
    @Override
    public void startQuest(){
        System.out.println("Thank you so much my " + requiredItem.getName() + "Means the world to me!");
        System.out.println();
        System.out.println("===Explore the house to try and find the " + requiredItem.getName() + "===" );
        System.out.println("Once found, give the item to the witness to complete the quest");
        isRunning = true;

    }




}
