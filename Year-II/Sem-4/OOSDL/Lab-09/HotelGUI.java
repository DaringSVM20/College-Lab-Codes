import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.*;

class Room {
    int no;
    String type;
    double price;
    boolean available = true;

    Room(int n, String t, double p) {
        no = n; type = t; price = p;
    }
}

public class HotelGUI extends Application {

    ArrayList<Room> rooms = new ArrayList<>();

    @Override
    public void start(Stage stage) {

        // ---- INPUT FIELDS ----
        TextField txtRoomNo = new TextField();
        TextField txtPrice = new TextField();
        ComboBox<String> cmbType = new ComboBox<>();
        cmbType.getItems().addAll("Single", "Double", "Deluxe");

        TextField txtCustName = new TextField();
        TextField txtContact = new TextField();
        TextField txtBookRoom = new TextField();

        Label lblOutput = new Label();

        // ---- BUTTONS ----
        Button btnAddRoom = new Button("Add Room");
        Button btnView = new Button("View Rooms");
        Button btnAvailable = new Button("Available Rooms");
        Button btnBook = new Button("Book Room");
        Button btnCheckout = new Button("Checkout");

        // ---- ADD ROOM ----
        btnAddRoom.setOnAction(e -> {
            try {
                int no = Integer.parseInt(txtRoomNo.getText());
                double price = Double.parseDouble(txtPrice.getText());
                String type = cmbType.getValue();

                rooms.add(new Room(no, type, price));
                lblOutput.setText("Room Added!");

                txtRoomNo.clear();
                txtPrice.clear();
            } catch (Exception ex) {
                lblOutput.setText("Invalid Input!");
            }
        });

        // ---- VIEW ALL ROOMS ----
        btnView.setOnAction(e -> {
            StringBuilder sb = new StringBuilder();
            for (Room r : rooms) {
                sb.append(r.no + " " + r.type + " " + r.price + " " + (r.available ? "Free" : "Booked") + "\n");
            }
            lblOutput.setText(sb.toString());
        });

        // ---- AVAILABLE ROOMS ----
        btnAvailable.setOnAction(e -> {
            StringBuilder sb = new StringBuilder();
            for (Room r : rooms) {
                if (r.available)
                    sb.append(r.no + " " + r.type + " " + r.price + "\n");
            }
            lblOutput.setText(sb.toString());
        });

        // ---- BOOK ROOM ----
        btnBook.setOnAction(e -> {
            try {
                int no = Integer.parseInt(txtBookRoom.getText());

                for (Room r : rooms) {
                    if (r.no == no) {
                        if (!r.available) {
                            lblOutput.setText("Already Booked!");
                            return;
                        }
                        r.available = false;
                        lblOutput.setText("Room Booked for " + txtCustName.getText());
                        return;
                    }
                }
                lblOutput.setText("Room not found!");
            } catch (Exception ex) {
                lblOutput.setText("Error!");
            }
        });

        // ---- CHECKOUT ----
        btnCheckout.setOnAction(e -> {
            int no = Integer.parseInt(txtBookRoom.getText());

            for (Room r : rooms) {
                if (r.no == no) {
                    r.available = true;
                    lblOutput.setText("Checked Out!");
                    return;
                }
            }
        });

        // ---- LAYOUT ----
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label("Room No:"), 0, 0);
        grid.add(txtRoomNo, 1, 0);

        grid.add(new Label("Type:"), 0, 1);
        grid.add(cmbType, 1, 1);

        grid.add(new Label("Price:"), 0, 2);
        grid.add(txtPrice, 1, 2);

        grid.add(btnAddRoom, 1, 3);

        grid.add(new Label("Customer Name:"), 0, 4);
        grid.add(txtCustName, 1, 4);

        grid.add(new Label("Contact:"), 0, 5);
        grid.add(txtContact, 1, 5);

        grid.add(new Label("Room No to Book:"), 0, 6);
        grid.add(txtBookRoom, 1, 6);

        HBox buttons = new HBox(10, btnView, btnAvailable, btnBook, btnCheckout);

        VBox root = new VBox(15, grid, buttons, lblOutput);

        Scene scene = new Scene(root, 400, 450);
        stage.setTitle("Hotel Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}