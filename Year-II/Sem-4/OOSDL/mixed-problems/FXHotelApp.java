/*
Build a JavaFX app with TextField for room type, Button to add to an ArrayList<Room>, and Button to display all rooms sorted by price in a Label. Clear inputs after adding.

javac --module-path "C:\javafx\lib" --add-modules javafx.controls .\FXHotelApp.java
java --module-path "C:\javafx\lib" --add-modules javafx.controls FXHotelApp

*/

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.*;

class SimpleRoom { String type; double price; 
    SimpleRoom(String t, double p){type=t;price=p;}
    public String toString(){return type+" ($"+price+")";}
}

public class FXHotelApp extends Application {
    private ArrayList<SimpleRoom> roomList = new ArrayList<>();

    @Override
    public void start(Stage stage) {
        Label lblMsg = new Label("Enter Room Details:");
        TextField txtType = new TextField();
        TextField txtPrice = new TextField();
        Button btnAdd = new Button("Add Room");
        Button btnShow = new Button("Show & Sort");
        Label lblOutput = new Label();

        btnAdd.setOnAction(e -> {
            try {
                String t = txtType.getText().trim();
                double p = Double.parseDouble(txtPrice.getText());
                roomList.add(new SimpleRoom(t, p));
                lblMsg.setText("✅ Added!");
                txtType.clear(); txtPrice.clear();
            } catch(NumberFormatException ex) {
                lblMsg.setText("❌ Invalid price!");
            }
        });

        btnShow.setOnAction(e -> {
            Collections.sort(roomList, Comparator.comparingDouble(r -> r.price));
            StringBuilder sb = new StringBuilder();
            for(SimpleRoom r : roomList) sb.append(r).append("\n");
            lblOutput.setText(sb.toString());
        });

        VBox root = new VBox(10, lblMsg, txtType, txtPrice, btnAdd, btnShow, lblOutput);
        stage.setScene(new Scene(root, 300, 350));
        stage.setTitle("FX Hotel Manager");
        stage.show();
    }
    public static void main(String[] args) { launch(args); }
}