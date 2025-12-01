public abstract class Quest {
    private String questDetails;
    private boolean completed;
    private boolean isRunning;


    public Quest(String questDetails){
        this.questDetails = questDetails;
        completed = false;
        isRunning = false;
    }

   public  String giveDetails() {
       return questDetails;
   }

    // Abstract methods
    public abstract void endResult(Witness witness);
    public abstract void startQuest();
    public abstract String giveProblem();




    public  boolean isRunning(){
       return isRunning;
   }

   public void setIsRunning(boolean isRunning){
       this.isRunning = isRunning;
   }


   public  boolean isCompleted(){
       return completed;
   }
   public void setCompleted(boolean completed){
       this.completed = completed;
   }

}
