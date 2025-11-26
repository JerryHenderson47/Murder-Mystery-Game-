import java.util.HashMap;
import java.util.Map;

public class CommandWords {
    private Map<String, String> validCommands; // just creates a map with two string values

    public CommandWords() {
        validCommands = new HashMap<>();
        validCommands.put("go", "Move to another room");
        validCommands.put("quit", "End the game");
        validCommands.put("help", "Show help");
        validCommands.put("look", "Look around");
        validCommands.put("eat", "Eat something");
        validCommands.put("pick", "Pick up something");
        validCommands.put("place", "Place an item");
        validCommands.put("give","Give an item to another character");
        validCommands.put("talk","talk to another character");
        validCommands.put("break", "Break an item");

        //Amnesia witness game
        validCommands.put("play", "play the witness's game");
        validCommands.put("switch", "Switch a memory with another memory");

    }

    public boolean isCommand(String commandWord) {
        return validCommands.containsKey(commandWord);
    } // checks if a command word is within the hashmap

    //Method that prints out all the commands
    public void showAll() {
        System.out.print("Valid commands are: ");
        for (String command : validCommands.keySet()) {  // returns a list of keys and cycles through them
            System.out.print(command + " ");
        }
        System.out.println();
    }
}
