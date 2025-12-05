import java.util.*;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;

public class GameController  {

    private ZorkULGame game;
    private Detective player;



    public GameController() {
        this.game = new ZorkULGame();
        this.player = game.getPlayer();
    }

    public Detective getPlayer() {
        return player;
    }

    public Room getCurrentRoom() {
        return player.getCurrentRoom();
    }

    public boolean move(String direction) {

        return player.move(direction);
    }
    public boolean isAmnesiaWitness() {
        return getCurrentWitness() instanceof AmnesiaWitness;
    }

    public AmnesiaWitness getAmnesiaWitness() {
        return (AmnesiaWitness)getCurrentWitness();
    }

    public boolean isHideNSeekWitness() {
        return getCurrentWitness() instanceof HideNSeekWitness;
    }

    public HideNSeekWitness getHideNSeekWitness() {
        return (HideNSeekWitness)getCurrentWitness();
    }

    public boolean isQuestWitness() {
        return getCurrentWitness() instanceof QuestWitness;
    }

    public QuestWitness getQuestWitness() {
        return (QuestWitness)getCurrentWitness();
    }
    public HashMap<String,String> getWitnessInfo() {
        return player.getWitnessInfo();
    }

    public HashMap<String,String> getSuspectInfo() {
        return player.getSuspectInfo();
    }

    public ArrayList<Item> getPlayerInventory(){
        return player.getInventory();
    }

    public ArrayList<String> getPlayerInventoryItemNames(){
        return player.getInventoryItemNames();
    }
    public Witness getCurrentWitness(){
        return player.getCurrentRoom().getWitness();
    }

    public boolean questActive(){
        return player.getCurrentQuest() != null && !player.getCurrentQuest().isCompleted();
    }



    public String talkToSuspect(String name) {
        for (Suspect suspect : player.getCurrentRoom().getSuspectList()) {
            if (suspect.getName().equals(name)) {
                suspect.addSuspectInfo(player);
                return suspect.getDetails();
            }
        }
        return "Invalid suspect.";
    }

    public String checkHidingSpot(String choice) {
        Witness witness = player.getCurrentRoom().getWitness();
        if (witness instanceof HideNSeekWitness) {
            return ((HideNSeekWitness) witness).checkHidingSpot(choice);
        }
        return "There is no hide-and-seek witness here.";
    }

    public String saveGame() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("savegame.dat"))) {
            out.writeObject(player);   // save the player
            return "Game saved.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }

    public String loadGame() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("savegame.dat"))) {
            player = (Detective) in.readObject();
            return  "Game loaded from last save";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }




    public String processCommand(String command) {
        String[] words = command.trim().split("\\s+");
        String commandWord = words[0];
        Room currentRoom = player.getCurrentRoom();


        if (commandWord == null) {
            return "I don't understand your command...";
        }

        switch (commandWord) {
            case "pick","collect","take":
                return player.pickUpItem(words[1]);
            case "place","drop":
                return player.placeItem(words[1]);

            case "talk":
                if (currentRoom.hasWitness()) {
                    return "witness";
                }
                else if (currentRoom.hasSuspects()) {
                    return "suspect";
                }
                else {
                    return "There is no one to talk to.";
                }
            case "give":
                return player.giveItem(words[1]);
            case "break":
                return player.breakItem(words[1]);
            case "accuse":
                return player.accuse(words[1]);
            default:
                return "I don't know what you mean...";

        }
    }
}
