import java.util.Scanner;

public class Parser {
    private CommandWords commands;
    private Scanner scan;


    //creates parser object
    public Parser() {
        commands = new CommandWords();
        scan = new Scanner(System.in);
    }


    public Command getCommand() {
        // current user interface to get input
        System.out.print("> ");
        String inputLine = scan.nextLine();


        // initializes 2 string words to null
        String word1 = null;
        String word2 = null;

        // a new scanner that takes in the input line
        Scanner tokenizer = new Scanner(inputLine);
        if (tokenizer.hasNext()) {
            word1 = tokenizer.next(); // if there is a word to read the scanner will set it equal to word 1
            if (tokenizer.hasNext()) { // if there is a second word to read the scanner will set it to word 2
                word2 = tokenizer.next();
            }
        }


        if (commands.isCommand(word1)) { // checks if the word 1 is valid but does not check the 2nd one
            return new Command(word1, word2);
        } else {
            return new Command(null, word2);
        }
    }

    public void showCommands() {
        commands.showAll();
    }
}
