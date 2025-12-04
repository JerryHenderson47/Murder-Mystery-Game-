import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.paint.*;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;


public class InformationWIndow {

    Button witnessButton;
    Button suspectButton;
    Stage window;
    private HashMap<String,String> witnessInfo;
    private HashMap<String,String> suspectInfo;

    public InformationWIndow(HashMap<String,String> witnessInfo,HashMap<String,String> suspectInfo){
        this.witnessInfo = witnessInfo;
        this.suspectInfo = suspectInfo;
    }

    public  void displayInfo(){
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        witnessButton = new Button("Witness Information");
        witnessButton.setOnAction(e -> displayWitnessInfo());

        suspectButton = new Button("Suspect Information");
        suspectButton.setOnAction(e -> displaySuspectInfo());

        HBox options = new HBox();
        options.getChildren().addAll(witnessButton,suspectButton);



        window.setTitle("Information Gathered");
        window.setMinWidth(250);
        window.setMaxHeight(225);
        Label label = new Label();
        label.setText("Display witness info or Suspect info?");

        VBox layout = new VBox(20);
        layout.getChildren().addAll(label,options);

        Scene intitialScene = new Scene(layout);
        window.setScene(intitialScene);
        window.showAndWait();

    }


    public void displayWitnessInfo(){
        VBox layout = new VBox(20);
        TextArea info = new TextArea();
        info.setEditable(false);
        for (Map.Entry<String, String> entry : witnessInfo.entrySet()) {
            String witnessName = entry.getKey();
            String information = entry.getValue();

            info.appendText(witnessName + ":\n" + information + "\n\n");

        }

        layout.getChildren().add(info);
        Scene newScene = new Scene(layout,800, 600);

        window.setScene(newScene);
    }

    public void displaySuspectInfo(){
        VBox layout = new VBox(20);
        TextArea info = new TextArea();
        info.setEditable(false);
        for (Map.Entry<String, String> entry : suspectInfo.entrySet()) {
            String suspectName = entry.getKey();
            String information = entry.getValue();

           info.appendText(suspectName + ":\n" + information + "\n\n");

        }

        layout.getChildren().add(info);
        Scene newScene = new Scene(layout,800, 600);

        window.setScene(newScene);
    }


    }
