import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {


        static boolean answer;

        public static boolean display(String title, String message){
            Stage window = new Stage();

            window.initModality(Modality.APPLICATION_MODAL); // no other input on other events
            window.setTitle(title);
            window.setMinWidth(250);

            Label label1 = new Label();
            label1.setText(message);

            Button yesButton = new Button("Yes");
            Button noButton = new Button("No");

            noButton.setOnAction(e -> {
                answer = false;
                window.close();
            });

            yesButton.setOnAction(e -> {
                answer = true;
                window.close();
            });



            VBox layout = new VBox(20);

            layout.getChildren().addAll(label1, yesButton,noButton);
            layout.setAlignment(Pos.CENTER);

            Scene scene = new Scene(layout);
            window.setScene(scene);
            window.showAndWait();
            return answer;


        }
    }


