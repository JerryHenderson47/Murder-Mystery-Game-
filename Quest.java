public interface Quest{

     String giveDetails();
     void endResult(Witness witness);
     void startQuest();
     String giveProblem();
     boolean isRunning();
     boolean isCompleted();

}
