import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.HashMap;

public class GUI extends Application{

    Stage window;
    ImageView characterView;
    TextArea console;
    Scene scene;
    StackPane centralImage;
    BorderPane fundamentalStructure;
    ImageView studyView, diningRoomView, childBedroomView, livingRoomView;
    GridPane dpad;
    HashMap<String,ImageView> exits;



    public void showGUI() {
        launch();
    }




    @Override
    public void start(Stage stage) {
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

        //set textBox on the top
        console = new TextArea();
        console.setEditable(false);     // user can't type
        console.setWrapText(true);      // long lines wrap
        console.setPrefHeight(150);
        console.setStyle("-fx-control-inner-background: black;");

        fundamentalStructure.setTop(console);


        //set the inventory on the left
        VBox leftBox = new VBox();
        leftBox.setPrefWidth(150);
        leftBox.setAlignment(Pos.CENTER);

        ChoiceBox<String> inventory = new ChoiceBox<>();
        Label inventoryHeader  = new Label("Inventory");
        inventory.getItems().addAll("Hammer", "Shoe", "Door Handle");
        inventory.setValue("Hammer");
        leftBox.getChildren().addAll(inventoryHeader,inventory);
        fundamentalStructure.setLeft(leftBox);


        //character
        Image characterImg = new Image(ImageAdresses.detective);
        characterView = new ImageView(characterImg);
        characterView.setFitWidth(150);
        characterView.setFitHeight(150);
        // set character in the top left
        characterView.setTranslateX(-150);
        characterView.setTranslateY(-150);

        // set the murderScene image
        Image murderScene = new Image(ImageAdresses.murderScene);
        ImageView murderSceneView = new ImageView(murderScene);
        murderSceneView.setFitHeight(600);
        murderSceneView.setFitWidth(600);

        // MAKE THE study image
        Image study = new Image(ImageAdresses.study);
        studyView = new ImageView(study);
        studyView.setFitHeight(600);
        studyView.setFitWidth(600);

        // make chikdl bedreoom
        Image childBedroom = new Image(ImageAdresses.childBedroom);
        childBedroomView = new ImageView(childBedroom);
        childBedroomView.setFitHeight(600);
        childBedroomView.setFitWidth(600);


        // make dining room
        Image diningRoom = new Image(ImageAdresses.diningRoom);
        diningRoomView = new ImageView(diningRoom);
        diningRoomView.setFitHeight(600);
        diningRoomView.setFitWidth(600);


        // make living room
        Image livingRoom = new Image(ImageAdresses.livingRoom);
        livingRoomView = new ImageView(livingRoom);
        livingRoomView.setFitHeight(600);
        livingRoomView.setFitWidth(600);



        centralImage = new StackPane();
        centralImage.setAlignment(Pos.CENTER);
        fundamentalStructure.setCenter(centralImage);

        setExits(studyView,childBedroomView, diningRoomView,livingRoomView);





        //set directional movement on right
        dpad = new GridPane();
        Button upButton = new Button("Up");
        upButton.setOnAction( e -> moveUp());

        Button downButton = new Button("Down");
        downButton.setOnAction(e -> moveDown());

        Button leftButton = new Button("Left");
        leftButton.setOnAction(e -> moveLeft());

        Button rightButton = new Button("Right");
        rightButton.setOnAction(e -> moveRight());

        Button[] dpadButtons = {upButton,downButton,leftButton,rightButton};
        for(Button button : dpadButtons){
            button.setPrefSize(50, 50);

        }
        dpad.setHgap(5);
        dpad.setVgap(5);

        dpad.add(upButton,    1, 0);
        dpad.add(leftButton,  0, 1);
        dpad.add(rightButton, 2, 1);
        dpad.add(downButton,  1, 2);

        VBox rightBox = new VBox();
        rightBox.setPrefWidth(150);
        rightBox.setAlignment(Pos.CENTER);
        rightBox.getChildren().add(dpad);

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

        });

        commandLine.setAlignment(Pos.CENTER);
        commandLine.getChildren().addAll(prompt,userCommand,enter);
        fundamentalStructure.setBottom(commandLine);



        fundamentalStructure.setStyle("-fx-background-color: #7E6B5A;");
        setCentralImage(murderSceneView, false);
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


    public boolean verifyInt( String command){
        try{
            int i = Integer.parseInt(command);
            System.out.println("You are being transported to level: " + i);
            return true;
        }catch (NumberFormatException e){
            System.out.println("Error: Invalid Input");
            return false;
        }
    }




    public void moveUp(){
        if (characterView.getTranslateY() > -230)
            characterView.setTranslateY(characterView.getTranslateY() - 10);
        else{
            setCentralImage(exits.get("Up"),false);
            setCharacterPosition(0,0);
        }
    }

    public void moveDown(){
        if (characterView.getTranslateY() < 230)
            characterView.setTranslateY(characterView.getTranslateY() + 10);
        else{
            setCentralImage(exits.get("Down"),true);
            setCharacterPosition(0,0);
        }
    }
    public void moveLeft(){
        if (characterView.getTranslateX() > -250)
            characterView.setTranslateX(characterView.getTranslateX() - 10);
        else{
            setCentralImage(exits.get("Left"), true);
            setCharacterPosition(0,0);

        }
    }
    public void moveRight(){
        if (characterView.getTranslateX() < 250)
            characterView.setTranslateX(characterView.getTranslateX() + 10);
        else{
            setCentralImage(exits.get("Right"), true);
            setCharacterPosition(0,0);

        }

    }

    public void printToConsole(String message) {
        console.appendText(message + "\n");
    }

    public void setCharacterPosition(int x , int y){
        characterView.setTranslateX(x);
        characterView.setTranslateY(y);
    }

    public void setCentralImage(ImageView room, boolean firsPerson){

        centralImage.getChildren().clear();

        if (firsPerson) {
            centralImage.getChildren().add(room);
            dpad.setVisible(false);
            dpad.setManaged(false);

        }
        else  {
            centralImage.getChildren().addAll(room,characterView);
            dpad.setVisible(true);
            dpad.setManaged(true);
        }

    }

    public void setExits(ImageView upRoom, ImageView downRoom, ImageView leftRoom, ImageView rightRoom){
        exits.put("Left", leftRoom);
        exits.put("Right", rightRoom);
        exits.put("Up", upRoom);
        exits.put("Down", downRoom);

    }





}
