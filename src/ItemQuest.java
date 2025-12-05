import java.io.Serializable;

public class ItemQuest extends AbstractQuest implements Serializable {
    private static final long serialVersionUID = 1L;
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
    public String endResult(Witness witness){
            if (witness.getInventory().contains(requiredItem)) {
                setIsRunning(false);
                setCompleted(true);
                return "You actually found my " + requiredItem.getName() + "\n" +
                        "Thank you so much!!!\n" +
                        "I suppose I owe you one....\n" +
                        "Ok so, here is what I know... " + witness.getInformation();

            } else return "You still don't have it???\n Come on man......";

    }
    @Override
    public String startQuest(){
        setIsRunning(true);
        return "Thank you so much, my " + requiredItem.getName() + "! It means the world to me!\n\n" +
                "=== Explore the house to try and find the " + requiredItem.getName() + " ===\n" +
                "Once found, give the item to the witness to complete the quest";

    }




}
