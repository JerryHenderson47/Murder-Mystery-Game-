import java.io.Serializable;

public abstract class AbstractQuest implements Serializable,Quest {
    private static final long serialVersionUID = 1L;
    private String questDetails;
    private boolean completed;
    private boolean isRunning;


    public AbstractQuest(String questDetails){
        this.questDetails = questDetails;
        completed = false;
        isRunning = false;
    }

   public  String giveDetails() {
       return questDetails;
   }






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
