import java.util.*;

public class BreakQuest  extends AbstractQuest{
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
    public void endResult(Witness witness){

        if (isBroken){
            System.out.println("Thank so much!!!\nI honestly don't know what I would have done with my life otherwise");
            System.out.println("I'll tell you what I saw....");
            System.out.println(witness.getInformation());
            setIsRunning(false);
            setCompleted(true);

        }
        else System.out.println("Cm'onnn grow a pair would ya.....");
    }







    public void startQuest(){
        System.out.println("Thank you so much I need that " + targetItem.getName() + " destroyed!");
        System.out.println();
        System.out.println("===Explore the house to try and find the " + targetItem.getName() + "===" );
        System.out.println("Once found, break the item and report back to the witness");
        setIsRunning(true);

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
