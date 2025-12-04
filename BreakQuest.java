import java.io.Serializable;

public class BreakQuest  extends AbstractQuest implements Serializable {
    private static final long serialVersionUID = 1L;
    private Item targetItem;
    private boolean isBroken;
    private Item requiredTool;
    private String problem;

    public BreakQuest(String questDetails,String problem, Item targetItem,Item requiredTool){


        super(questDetails);
        isBroken = false;
        this.targetItem = targetItem;
        this.requiredTool = requiredTool;
        this.problem = problem;

    }

    @Override
    public String endResult(Witness witness){

        if (isBroken){
            setIsRunning(false);
            setCompleted(true);

            return "Thank so much!!!\n" +
                    "I honestly don't know what I would have done with my life otherwise\n" +
                    "I'll tell you what I saw....\n" +
                    witness.getInformation();

        }
        else return "Cm'onnn grow a pair would ya.....";
    }







    public String startQuest(){
        setIsRunning(true);
        return "Thank you so much, I need that " + targetItem.getName() + " destroyed!\n\n" +
                "=== Explore the house to try and find the " + targetItem.getName() + " ===\n" +
                "Once found, break the item and report back to the witness";

    };
    public String giveProblem(){
        return  problem;
    }

    public Item getRequiredTool() {
        return requiredTool;
    }

    public Item getTargetItem(){
        return targetItem;
    }

    public boolean isBroken(){
        return isBroken;
    }

    public void setBroken(boolean isBroken){
        this.isBroken = isBroken;
    }
}
