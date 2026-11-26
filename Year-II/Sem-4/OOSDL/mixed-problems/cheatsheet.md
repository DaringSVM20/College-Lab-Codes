Listen closely, student. You have a lab exam in hours, not days. I will cut the fluff and give you exactly what you need to solve **one complex question** combining Weeks 6–9. 

Here’s your battle plan:
1. **Week-by-Week Core Drills** (syntax, structure, thought process)
2. **Mixed-Concept Exam Simulations** (how your actual question will look)
3. **Sensei’s Blueprint** (step-by-step approach during the exam)

---
## 📘 WEEK 6: Random Access File & Serialization
### 🔑 Core Concepts
- `RandomAccessFile`: Reads/writes at specific byte positions using `seek()`.
- `Serialization`: Converts objects to bytes for file storage. Requires `implements Serializable`.
- Always handle `IOException` & `ClassNotFoundException`.

### 🧠 Single-Week Question
**Problem:** Create a `BookRecord` system using `RandomAccessFile`. Each record stores: `id` (int), `title` (String), `price` (double). Implement `addRecord()`, `displayRecord(int recordIndex)`, and `updatePrice(int recordIndex, double newPrice)`.

### 📐 Thought Process
1. **Calculate Record Size:** `int` = 4 bytes, `double` = 8 bytes. `writeUTF()` writes 2 bytes + string length + characters. For exam simplicity, assume a **fixed 30-byte string** or use `seek()` based on known offsets.
2. **Use `seek(index * RECORD_SIZE)`** to jump directly.
3. **Wrap in `try-catch`**, close resources in `finally` or use try-with-resources.

### 💻 Exam Code
```java
import java.io.*;

public class BookManager {
    private static final String FILE = "books.dat";
    // Fixed size: 4(int) + 2(String length) + 20(chars) + 8(double) ≈ 34 bytes
    // For exam, we'll track position manually for safety
    private static final int RECORD_SIZE = 34; 

    public static void addRecord(int id, String title, double price) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(FILE, "rw")) {
            raf.seek(raf.length()); // Append at end
            raf.writeInt(id);
            raf.writeUTF(title);    // Exam-safe
            raf.writeDouble(price);
        }
    }

    public static void displayRecord(int index) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(FILE, "r")) {
            long position = index * RECORD_SIZE;
            if (position > raf.length()) return;
            raf.seek(position);
            System.out.println("ID: " + raf.readInt());
            System.out.println("Title: " + raf.readUTF());
            System.out.println("Price: " + raf.readDouble());
        }
    }

    public static void updatePrice(int index, double newPrice) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(FILE, "rw")) {
            long position = index * RECORD_SIZE;
            if (position > raf.length()) return;
            // Skip id(4) + title(2+len)
            raf.seek(position);
            raf.skipBytes(4);
            int titleLen = raf.readShort(); // UTF length prefix
            raf.skipBytes(titleLen);
            // Now at double
            raf.writeDouble(newPrice);
        }
    }
}
```
**🔑 Exam Tip:** If the professor doesn't require strict fixed-size, just use `seek(0)` and read/write sequentially. They mostly check `seek()`, `readUTF()`, `writeDouble()`, and exception handling.

---
## 📘 WEEK 7: Generics
### 🔑 Core Concepts
- Generic class: `class Box<T> { T data; }`
- Bounded type: `<T extends Number>` (allows `.doubleValue()`, `.intValue()`)
- Generic method: `public static <T> void print(T[] arr)`

### 🧠 Single-Week Question
**Problem:** Create a generic `PriceCalculator<T extends Number>` class. Store two values, add a method `calculateTotal()` that returns sum as `double`. Demonstrate with `Integer` and `Double`.

### 💻 Exam Code
```java
public class PriceCalculator<T extends Number> {
    private T val1, val2;

    public PriceCalculator(T v1, T v2) { this.val1 = v1; this.val2 = v2; }

    public double calculateTotal() {
        return val1.doubleValue() + val2.doubleValue();
    }

    // Generic method example
    public static <E> void displayDetails(E[] items) {
        for (E item : items) System.out.println(item);
    }

    public static void main(String[] args) {
        PriceCalculator<Integer> pc1 = new PriceCalculator<>(100, 50);
        System.out.println("Total: " + pc1.calculateTotal()); // 150.0

        String[] rooms = {"Deluxe", "Suite", "Standard"};
        displayDetails(rooms);
    }
}
```
**🔑 Exam Tip:** Always use `.doubleValue()` with bounded generics. Never assume `+` works on `T`.

---
## 📘 WEEK 8: Collections (ArrayList & Iterator)
### 🔑 Core Concepts
- `ArrayList<T>`: Dynamic, `add()`, `get()`, `remove()`, `size()`, `contains()`
- `Iterator<T>`: `hasNext()`, `next()`, `remove()` (safe deletion)
- `Collections.sort(list)`: Requires `Comparable` or custom `Comparator`

### 🧠 Single-Week Question
**Problem:** Manage a list of `Room` objects. Add rooms, remove by ID, sort by price ascending, and safely print using `Iterator`.

### 💻 Exam Code
```java
import java.util.*;

class Room implements Comparable<Room> {
    int id; String type; double price;
    public Room(int id, String type, double price) { this.id=id; this.type=type; this.price=price; }
    @Override public int compareTo(Room o) { return Double.compare(this.price, o.price); }
    public String toString() { return "Room "+id+" | "+type+" | $"+price; }
}

public class RoomCollectionDemo {
    public static void main(String[] args) {
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(new Room(1, "Standard", 150.0));
        rooms.add(new Room(2, "Deluxe", 300.0));
        rooms.add(new Room(3, "Suite", 500.0));

        // Safe removal using Iterator
        Iterator<Room> it = rooms.iterator();
        while(it.hasNext()) {
            Room r = it.next();
            if(r.price > 400) it.remove(); // Removes safely
        }

        Collections.sort(rooms); // Uses compareTo
        for(Room r : rooms) System.out.println(r);
    }
}
```
**🔑 Exam Tip:** If asked to sort custom objects, implement `Comparable<T>` and override `compareTo()`. Use `Collections.sort(list)`.

---
## 📘 WEEK 9: JavaFX GUI Basics
### 🔑 Core Concepts
- `extends Application`, override `start(Stage stage)`
- `Scene` holds layout (`VBox`, `GridPane`, `HBox`)
- Controls: `Label`, `TextField`, `Button`, `ComboBox`
- Event: `btn.setOnAction(e -> { ... })`

### 🧠 Single-Week Question
**Problem:** Build a "Room Price Checker" GUI. Input room type, click button, show price in a Label. Validate empty input.

### 💻 Exam Code
```java
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
```
**🔑 Exam Tip:** Keep layouts simple (`GridPane` for forms, `VBox` for vertical stacking). Always clear fields after success. Use `trim()` on text inputs.

---
## 🌪️ MIXED-CONCEPT EXAM SIMULATIONS
*These are how your actual exam question will look. Study the architecture.*

### 🔹 Mixed 1: Collections + Generics + Serialization (Console)
**Problem:** Design a `HotelSystem<T extends Number>` that stores `Room<T>` objects in an `ArrayList`. Each room has `roomNo`, `type`, `price(T)`. Implement:
1. Add rooms, sort by price, display using `Iterator`.
2. Serialize the `ArrayList` to `"hotel.dat"`.
3. Deserialize and print all rooms.

**🧠 Architecture & Thought Process:**
1. `Room<T>` must `implements Serializable`.
2. Generic class holds `ArrayList<Room<T>>`.
3. Use `ObjectOutputStream`/`ObjectInputStream` for the list itself (Java serializes collections automatically).
4. Handle exceptions cleanly.

**💻 Exam Code:**
```java
import java.io.*;
import java.util.*;

class Room<T extends Number> implements Serializable {
    int roomNo; String type; T price;
    public Room(int roomNo, String type, T price) {
        this.roomNo=roomNo; this.type=type; this.price=price;
    }
    public double getPriceDouble() { return price.doubleValue(); }
    public String toString() { return roomNo+" | "+type+" | $"+price; }
}

public class HotelSystem<T extends Number> implements Serializable {
    private ArrayList<Room<T>> rooms = new ArrayList<>();

    public void addRoom(Room<T> r) { rooms.add(r); }

    public void displayAndSort() {
        Collections.sort(rooms, Comparator.comparingDouble(Room::getPriceDouble));
        Iterator<Room<T>> it = rooms.iterator();
        while(it.hasNext()) System.out.println(it.next());
    }

    public void saveToFile(String file) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(rooms);
        }
    }

    @SuppressWarnings("unchecked")
    public void loadFromFile(String file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            rooms = (ArrayList<Room<T>>) ois.readObject();
        }
    }

    public static void main(String[] args) throws Exception {
        HotelSystem<Double> sys = new HotelSystem<>();
        sys.addRoom(new Room<>(101, "Standard", 150.0));
        sys.addRoom(new Room<>(102, "Deluxe", 300.0));

        sys.saveToFile("hotel.dat");
        System.out.println("Saved.");

        sys.loadFromFile("hotel.dat");
        sys.displayAndSort();
    }
}
```

### 🔹 Mixed 2: JavaFX + Collections (GUI)
**Problem:** Build a JavaFX app with `TextField` for room type, `Button` to add to an `ArrayList<Room>`, and `Button` to display all rooms sorted by price in a `Label`. Clear inputs after adding.

**💻 Exam Code Structure:**
```java
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
```

---
## 🧠 SENSEI'S EXAM BLUEPRINT (Follow This During Test)
1. **Read the prompt 3 times.** Identify required features: GUI? Collections? File I/O? Generics?
2. **Sketch the architecture on paper first:**
   - Model class(es) → `class Room implements Serializable/Comparable`
   - Manager/Controller → `ArrayList`, CRUD methods, sort logic
   - UI/IO Layer → JavaFX `start()` or `main()` with file streams
3. **Write in this exact order:**
   - Imports
   - Model class (`implements Serializable` if file I/O, `Comparable` if sorting)
   - Generic class/method if required
   - Collection initialization
   - Business logic (CRUD, sort, calculate)
   - UI/File persistence layer
   - `main()` or `launch()`
4. **Syntax Traps to Avoid:**
   - Forgetting `implements Serializable` → `NotSerializableException`
   - Using `+` on generics → Use `.doubleValue()`
   - Modifying `ArrayList` in `for-each` → Use `Iterator.remove()`
   - JavaFX `launch()` not in `main` → App won't start
   - Not closing streams → Resource leak (use try-with-resources)
5. **If stuck mid-exam:** Write pseudocode comments, implement the skeleton, fill methods one by one. Partial credit > blank page.

---
## ⚡ QUICK SYNTAX CHEAT SHEET
| Concept | Syntax |
|---------|--------|
| Generic Class | `class Box<T> { T data; }` |
| Bounded Generic | `<T extends Number>` |
| Generic Method | `public static <T> void print(T val)` |
| ArrayList Init | `ArrayList<Room> list = new ArrayList<>();` |
| Iterator Safe Remove | `while(it.hasNext()) { Room r = it.next(); if(cond) it.remove(); }` |
| Sort Custom Objects | `Collections.sort(list, Comparator.comparingDouble(Room::getPrice))` |
| Serialization | `implements Serializable`, `ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("f.dat")); oos.writeObject(obj);` |
| RAF Seek | `raf.seek(index * RECORD_SIZE);` |
| JavaFX Setup | `extends Application`, `start(Stage s)`, `Scene sc = new Scene(root, w, h)`, `stage.setScene(sc); stage.show();` |
| FX Event | `btn.setOnAction(e -> { /* logic */ });` |

You have the structure. You have the patterns. You have the code. 
**Now go in, breathe, map the question to these templates, and execute line by line.** 
If you need me to simulate a specific variation or debug a draft before you walk in, send it. I'm here. 🥋💻