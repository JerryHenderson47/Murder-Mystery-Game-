public class BreakQuest implements Quest<Witness>{
    private String details;
    private Item targetItem;
    private boolean isBroken;
    private boolean isRunning;
    private boolean completed;

    public BreakQuest(String details, Item targetItem){
        isBroken = false;
        completed = false;
        this.details = details;
        this.targetItem = targetItem;
    }

    @Override
    public void endResult(Witness witness){
        if (isBroken){
            System.out.println("Thank so much!!!\nI honestly don't know what I would have done with my life otherwise");
            System.out.println("I'll tell you what I saw....");
            System.out.println(witness.getInformation());

        }
        else System.out.println("Cm'onnn grow a pair would ya.....");
    }



    @Override
    public boolean getCompleted(){
        return completed;
    }

    public String giveDetails(){
        return "hello";
    }
    public void startQuest(){
        System.out.println(2);
    };
    public String giveProblem(){
        return  "hello";
    }

    public boolean isRunning(){
        return isRunning;
    }





}
