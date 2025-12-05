import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.util.*;

public class GUI extends Application{

    GameController controller = new GameController();

    Stage window;
    ImageView characterView;
    TextArea console;
    Scene scene;
    StackPane centralImage;
    BorderPane fundamentalStructure;

    GridPane dpad;
    VBox leftBox;
    InformationWIndow info;
    ChoiceBox<String> inventory;
    Button saveButton;
    Button loadButton;





    public void showGUI() {
        launch();
    }




    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
        window.setWidth(1200);
        window.setHeight(900);

        // closing the game logic
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        window.setTitle("Murder Mystery");
        fundamentalStructure = new BorderPane();

        //set console on the top
        console = new TextArea();
        console.setEditable(false);     // user can't type
        console.setWrapText(true);      // long lines wrap
        console.setPrefHeight(150);
        console.setStyle("-fx-control-inner-background: black;");

        fundamentalStructure.setTop(console);


        //set the inventory on the left
        leftBox = new VBox();
        leftBox.setPrefWidth(150);
        leftBox.setAlignment(Pos.CENTER);

        // inventory drop down
        inventory = new ChoiceBox<>();
        Label inventoryHeader  = new Label("Inventory");
        leftBox.getChildren().addAll(inventoryHeader,inventory);


        // information window access button
         HashMap<String,String> witnessInfo = controller.getWitnessInfo();
         HashMap<String,String> suspectInfo =  controller.getSuspectInfo();

        info = new InformationWIndow(witnessInfo,suspectInfo);
        Button infoButton = new Button("See current information");
        infoButton.setOnAction(e -> info.displayInfo() );
        leftBox.getChildren().add(infoButton);

        fundamentalStructure.setLeft(leftBox);



        //character
        Image characterImg = new Image(ImageAdresses.detective);
        characterView = new ImageView(characterImg);
        characterView.setFitWidth(150);
        characterView.setFitHeight(150);
        // set character in the top left
        characterView.setTranslateX(-150);
        characterView.setTranslateY(-150);







        centralImage = new StackPane();
        centralImage.setAlignment(Pos.CENTER);
        fundamentalStructure.setCenter(centralImage);












        //set directional movement for the mapp
        dpad = new GridPane();
        Button upButton = new Button(Direction.NORTH.getText());
        upButton.setMinWidth(50);
        upButton.setMinWidth(50);;
        upButton.setOnAction( e -> {
            setNewRoom(Direction.NORTH.getText());

        });

        Button downButton = new Button(Direction.SOUTH.getText());
        downButton.setMinWidth(50);
        downButton.setMinWidth(50);
        downButton.setOnAction(e -> {
            setNewRoom(Direction.SOUTH.getText());
        });

        Button leftButton = new Button(Direction.WEST.getText());
        leftButton.setMinWidth(50);
        leftButton.setMinWidth(50);
        leftButton.setOnAction(e -> {
            setNewRoom(Direction.WEST.getText());
        });

        Button rightButton = new Button(Direction.EAST.getText());
        rightButton.setMinWidth(50);
        rightButton.setMinWidth(50);
        rightButton.setOnAction(e -> {
            setNewRoom(Direction.EAST.getText());
        });


        dpad.setHgap(5);
        dpad.setVgap(5);

        dpad.add(upButton,    1, 0);
        dpad.add(leftButton,  0, 1);
        dpad.add(rightButton, 2, 1);
        dpad.add(downButton,  1, 2);

        VBox rightBox = new VBox(20);
        rightBox.setPrefWidth(150);
        rightBox.setAlignment(Pos.CENTER);
        rightBox.getChildren().add(dpad);


        saveButton = new Button("Save");
        saveButton.setOnAction(e -> printToConsole(controller.saveGame()));

        loadButton = new Button("Load");
        loadButton.setOnAction(e -> {
            printToConsole(controller.loadGame());
            updateInventoryChoiceBox();
        });
        rightBox.getChildren().addAll(saveButton,loadButton);

        fundamentalStructure.setRight(rightBox);




        //Command Line
        HBox commandLine = new HBox();
        Label prompt = new Label("Enter your command:");
        TextField userCommand = new TextField();
        Button enter = new Button("Enter");
        enter.setOnAction(e -> {
            String command = userCommand.getText();
            printToConsole("> " + command);
            userCommand.clear();
            String result = controller.processCommand(command);


            if (command.startsWith("pick") || command.startsWith("place") || command.startsWith("collect") || command.startsWith("take") || command.startsWith("drop")   || command.startsWith("give")) {
                updateInventoryChoiceBox();
            }
            switch(result) {
                case "suspect": {
                     printToConsole(handleSuspectChoice());
                     break;
                }
                case "witness":
                    boolean answer = false;
                    Witness witness = controller.getCurrentRoom().getWitness();
                   console.setText(handleWitness());
                    answer = ConfirmBox.display("Witness", "Help Witness?");


                    // handle the diffenrt types of witnesses
                       if (answer && controller.isAmnesiaWitness()) {
                           console.setText(witness.play());
                           showMemoryPuzzle(controller.getAmnesiaWitness());
                           break;
                       } else if (answer && controller.isHideNSeekWitness()) {
                           console.setText(witness.play());
                           hideNSeek((HideNSeekWitness) witness);
                           break;
                       } else if (answer && controller.isQuestWitness()) {
                           console.setText(witness.play());
                           break;
                       } else if (!answer)
                           break;

                default:
                    console.setText(result);
            }
        if (controller.getPlayer().isGameOver()){

            PauseTransition delay = new PauseTransition(Duration.seconds(3));
            delay.setOnFinished(event-> Platform.exit());
            delay.play();
        }

        });

        commandLine.setAlignment(Pos.CENTER);
        commandLine.getChildren().addAll(prompt,userCommand,enter);
        fundamentalStructure.setBottom(commandLine);



        fundamentalStructure.setStyle("-fx-background-color: #7E6B5A;");
        setCentralImage(controller.getCurrentRoom().getImagePath(),600,600);
        centralImage.getChildren().add(characterView);

        scene = new Scene(fundamentalStructure);
        window.setScene(scene);
        window.show();



    }

    private void closeProgram(){
        boolean answer = ConfirmBox.display("Close Request","Would you like to leave");
        if (answer){
            window.close();
        }
    }


    private void setNewRoom(String direction){
        if (controller.move(direction)){
            Room newRoom = controller.getCurrentRoom();
            setCentralImage(newRoom.getImagePath(),600,600);

            // check if its a first perosn room
            if (newRoom.isFirstPersonEnabled()) {
                centralImage.getChildren().remove(characterView);
            }
            else{
                centralImage.getChildren().add(characterView);
                setCharacterPosition(-150,0);
            }

        }
        else {
            console.setText("You can't go that way!");
        }


    }

    public enum Direction {
        NORTH("north"),
        SOUTH("south"),
        EAST("east"),
        WEST("west");

        private final String text;

        Direction(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        @Override
        public String toString() {
            return text;
        }
    }




        private void hideNSeek(HideNSeekWitness witness) {

        List<String> spots = witness.getHidingSpotNames();

        ChoiceDialog<String> dialog = new ChoiceDialog<>(spots.get(0), spots);
        dialog.setTitle("Hide and Seek");
        dialog.setHeaderText("Choose a hiding spot");
        dialog.setContentText("Where do you think they are hiding?");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(choice -> {
            String message = controller.checkHidingSpot(choice);
            console.setText(message);
        });
    }

    public void showMemoryPuzzle(AmnesiaWitness witness) {

        Stage popup = new Stage();
        popup.setTitle("Memory Puzzle — " + witness.getName());

        // Convert memory items to simple strings
        ObservableList<String> items = FXCollections.observableArrayList();

        for (AmnesiaItem item : witness.getMemoryItems()) {
            items.add(item.getAttachedMemory());
        }

        ListView<String> listView = new ListView<>(items);

        Button swapBtn = new Button("Swap Items");
        Button checkBtn = new Button("Check Order");
        Button closeBtn = new Button("Close");
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listView.setOnMouseClicked(event -> {
            if (listView.getSelectionModel().getSelectedIndices().size() > 2) {
                // Keep only the last two selections
                ObservableList<Integer> selected = listView.getSelectionModel().getSelectedIndices();
                int last = selected.get(selected.size() - 1);
                int secondLast = selected.get(selected.size() - 2);

                listView.getSelectionModel().clearSelection();
                listView.getSelectionModel().selectIndices(secondLast, last);
            }
        });

        // Swap buton
        swapBtn.setOnAction(e -> {
            ObservableList<Integer> selected =
                    listView.getSelectionModel().getSelectedIndices();

            if (selected.size() != 2) {
                showAlert("Please select exactly TWO items to swap.");
                return;
            }

            int i = selected.get(0);
            int j = selected.get(1);
              // sync GUI → model



            String tmp = items.get(i);
            items.set(i, items.get(j));
            items.set(j, tmp);
        });

        // check button
        checkBtn.setOnAction(e -> {

            // Convert GUI list back to AmnesiaItem order
            ArrayList<AmnesiaItem> newOrder = new ArrayList<>();

            for (String memoryText : items) {
                for (AmnesiaItem item : witness.getMemoryItems()) {
                    if (item.getAttachedMemory().equals(memoryText)) {
                        newOrder.add(item);
                    }
                }
            }

            if (witness.isCorrectOrder(newOrder)) {



                // Build full story string
                StringBuilder story = new StringBuilder();
                for (AmnesiaItem item : witness.getSortedItems()) {
                    story.append(item.getAttachedMemory()).append("\n");

                }
                String info = story.toString();

                controller.getPlayer().setWitnessInfo(witness.getName(), info);
                console.setText("Puzzle solved!\nFull Story:\n" + info );


            }
            else {
                console.setText("Not correct — try again.");
            }
        });



        closeBtn.setOnAction(e -> popup.close());

        VBox root = new VBox(10, listView, swapBtn, checkBtn, closeBtn);
        root.setPadding(new Insets(10));

        popup.setScene(new Scene(root, 400, 500));
        popup.show();
    }





    private String handleSuspectChoice() {
        Room room = controller.getCurrentRoom();

        ArrayList<String> suspectNames = room.getSuspectNames();
        ChoiceDialog<String> dialog = new ChoiceDialog<>(suspectNames.get(0), suspectNames);
        dialog.setTitle("Choose Suspect");
        dialog.setHeaderText("Which suspect do you want to talk to?");
        dialog.setContentText("Choose:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String chosenName = result.get();
            return  controller.talkToSuspect(chosenName);

        }
        return "Supsect not present";
    }

    private String handleWitness(){
        Room room = controller.getCurrentRoom();
        Witness witness = room.getWitness();

        return  witness.interact();


    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }







    public void printToConsole(String message) {
        console.appendText(message + "\n");
    }

    public void setCharacterPosition(int x , int y){
        characterView.setTranslateX(x);
        characterView.setTranslateY(y);
    }


        private void updateInventoryChoiceBox() {
            inventory.getItems().clear();
            inventory.getItems().addAll(controller.getPlayerInventoryItemNames());
        }


    public void setCentralImage(String imagePath, int width , int height){
        Room currentRoom = controller.getCurrentRoom();
        ImageView room = new ImageView(imagePath);
        room.setFitWidth(width);
        room.setFitHeight(height);

        centralImage.getChildren().clear();
        centralImage.getChildren().add(room);
        console.clear();
        printToConsole(currentRoom.getLongDescription());
    }








}
