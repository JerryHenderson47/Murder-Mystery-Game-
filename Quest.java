public interface Quest<T> {

     String giveDetails();
     void endResult(T context);
     void startQuest();
     String giveProblem();
     boolean isRunning();
     boolean getCompleted();

}
