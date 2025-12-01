import java.util.*;


public class Suspect extends Character {
    Scanner shcan = new Scanner(System.in);

    private String victimRelationship;
    private String alby;
    private boolean isGuilty;
    private String motivation;
    private int id;

    public Suspect(String name,String victimRelationship, String description, String alby, boolean isGuilty,int id){
        super(name, description);
        this.alby = alby;
        this.isGuilty = isGuilty;
        this.id = id;
        this.victimRelationship = victimRelationship;

    }



    public String getDetails(){
        return ("===" + getName() + "====\nMotivations:" + motivation + "\nVictim Relationship: " + victimRelationship + "\nAlby: " + alby +"\n");

    }
    public void addSuspectInfo(Detective currentPlayer, Suspect suspect){
        currentPlayer.setSuspectInfo(getName(),getDetails());
    }

    public void talk(){
        System.out.println("This is suspect number " + id );
        System.out.println("Would you like to see their details?");
        System.out.println("Yes/No: ");
        String choice = shcan.nextLine().toLowerCase().trim();
        if(choice.equals("yes")){
            System.out.println(getDetails());
        }


    }

}
