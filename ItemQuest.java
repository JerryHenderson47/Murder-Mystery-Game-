public class ItemQuest extends Quest {

    private Item requiredItem;


    public ItemQuest(String questDetails, Item requiredItem){
        this.requiredItem = requiredItem;
        super(questDetails);
    }





    public Item getRequiredItem(){
        return requiredItem;
    }





    @Override
    public String giveProblem(){
        return "I have lost my " + requiredItem.getName();
    }


    @Override
    public void endResult(Witness witness){
            if (witness.getInventory().contains(requiredItem)) {
                System.out.println("You actually find my " + requiredItem.getName());
                System.out.println("Thank you so much!!!");
                System.out.println("I suppose I owe you one....");
                System.out.println("Ok so, here is what i know..." + witness.getInformation());
                setIsRunning(false);
                setCompleted(true);
            } else System.out.println("You still don't have it???\n Come on man......");

    }
    @Override
    public void startQuest(){
        System.out.println("Thank you so much my " + requiredItem.getName() + "Means the world to me!");
        System.out.println();
        System.out.println("===Explore the house to try and find the " + requiredItem.getName() + "===" );
        System.out.println("Once found, give the item to the witness to complete the quest");
        setIsRunning(true);

    }




}
