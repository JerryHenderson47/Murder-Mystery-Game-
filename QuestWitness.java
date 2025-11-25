import java.util.*;

public  class QuestWitness extends Witness{
    private Quest quest;


    public QuestWitness(String name, String victimRelationship,
                        String information, int trustLevel,
                        String description,Quest quest){

        super(name,victimRelationship,information, trustLevel,description);
        this.quest = quest;



    }

    @Override
    public void addToInventory(Item item){
        if (quest instanceof ItemQuest quest) {
            if (quest.getRequiredItem().equals(item)){
                 quest.setCompleted(true);
            }
            quest.endResult(this);



        }
        else getInventory().add(item);

    }

    @Override
    public void play(){
        quest.startQuest();
    }



    @Override
    public void interact(){

        if (!quest.getCompleted() && quest.isRunning()){
            System.out.println("Have you done it yet????");
            System.out.println("Come on I am waiting");
        }

        else if (!(quest.isRunning())) {
            Scanner shcan = new Scanner(System.in);
            System.out.println("Hi I'm " + getName());
            System.out.println("I'd love to help but..... " + quest.giveProblem());
            System.out.println("Would you like to help me?\n Yes/No: ");
            String choice = shcan.nextLine().toLowerCase().trim();
            if (choice.equals("yes")) {
                System.out.println(quest.giveDetails());
                play();
            } else {
                System.out.println("If you want my information you will have to do this for me!");
            }
        }
        else if(quest.getCompleted()){
            System.out.println("Thanks for that, I hope i helped!");
        }
    }


}
