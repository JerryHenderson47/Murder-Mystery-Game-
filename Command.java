public class Command {
    private String commandWord;
    private String secondWord;

    // constructs a command object
    public Command(String firstWord, String secondWord) {
        this.commandWord = firstWord;
        this.secondWord = secondWord;
    }

    //getters
    public String getCommandWord() {
        return commandWord;
    }

    public String getSecondWord() {
        return secondWord;
    }

    // if command does not exist
    public boolean isUnknown() {
        return commandWord == null;
    }

    // checks if there is 2 words
    public boolean hasSecondWord() {
        return secondWord != null;
    }
}
