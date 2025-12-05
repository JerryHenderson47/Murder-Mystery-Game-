import java.io.Serializable;


public class Suspect extends Character implements Serializable {
    private static final long serialVersionUID = 1L;
    private String victimRelationship;
    private String alby;
    private boolean isGuilty;
    private String motivation;


    public Suspect(String name,String motivation,String victimRelationship, String description, String alby, boolean isGuilty){
        super(name, description);
        this.alby = alby;
        this.isGuilty = isGuilty;

        this.victimRelationship = victimRelationship;
        this.motivation = motivation;

    }



    public String getDetails(){
        return ("===" + getName() + "====\nMotivations:" + motivation + "\nVictim Relationship: " + victimRelationship + "\nAlby: " + alby +"\n");

    }

    public boolean isGuilty(){
        return isGuilty;
    }


    public void addSuspectInfo(Detective currentPlayer){
        currentPlayer.setSuspectInfo(getName(),getDetails());
    }



}
