/*
Build a "Room Price Checker" GUI. Input room type, click button, show price in a Label. Validate empty input.
*/

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RoomPriceGUI extends Application {
    @Override
    public void start(Stage stage) {
        Label lblType = new Label("Room Type:");
        TextField txtType = new TextField();
        Button btnCheck = new Button("Get Price");
        Label lblResult = new Label("");

        btnCheck.setOnAction(e -> {
            String type = txtType.getText().trim();
            if(type.isEmpty()) {
                lblResult.setText("⚠️ Please enter a room type.");
                return;
            }
            double price = type.equalsIgnoreCase("Deluxe") ? 350.0 : 200.0;
            lblResult.setText("Price for "+type+": $"+price);
            txtType.clear();
        });

        GridPane root = new GridPane();
        root.setHgap(10); root.setVgap(10);
        root.add(lblType, 0, 0); root.add(txtType, 1, 0);
        root.add(btnCheck, 1, 1); root.add(lblResult, 1, 2);

        Scene scene = new Scene(root, 350, 200);
        stage.setTitle("Room Price Checker");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) { launch(args); }
}