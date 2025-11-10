import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GUI extends Application {

    public void showGUI() {
        launch();
    }



    @Override
    public void start(Stage primaryStage) {

        Button northButton  = new Button("North");
        Button southButton = new Button("South");
        Button westButton = new Button("West");
        Button eastButton = new Button("East");


        northButton.setOnAction(e -> {
            Room outside = new Room("outside the main entrance of the university");
            Detective player = new Detective("Player", outside);
            player.placeItem();
        });

        southButton.setOnAction(e -> {
            System.out.println("Going South");

        });
        westButton.setOnAction(e -> {
            System.out.println("Going West");

        });
        eastButton.setOnAction(e -> {
            System.out.println("Going East");

        });

        northButton.setLayoutX(0);
        northButton.setLayoutY(100);

        southButton.setLayoutX(150);
        southButton.setLayoutY(100);

        westButton.setLayoutX(250);
        westButton.setLayoutY(100);

        eastButton.setLayoutX(360);
        eastButton.setLayoutY(100);





        Pane root = new Pane(northButton, southButton, westButton, eastButton);



        Scene scene = new Scene(root, 400, 200);

        primaryStage.setTitle("My First JavaFX Window");
        primaryStage.setScene(scene);

        primaryStage.show();

    }
}
