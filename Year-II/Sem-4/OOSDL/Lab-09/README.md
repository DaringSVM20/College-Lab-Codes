# 📘 JavaFX Setup & Run Guide (Lab Ready)

## 🧾 Project Name

**HotelGUI.java / Any JavaFX Program**

---

# ⚙️ 1. Folder Setup (IMPORTANT)

Inside your **Documents** folder, keep structure like this:

```
Documents/
│
├── SHLOK_240968500/
│   ├── Lab 9/
│   │   ├── HotelGUI.java
│   │
│   ├── javafx/
│       └── lib/
│           ├── javafx.base.jar
│           ├── javafx.controls.jar
│           ├── javafx.graphics.jar
│           └── ...
```

👉 You must download and extract **JavaFX SDK** into:

```
Documents\SHLOK_240968500\javafx
```

---

# 📦 2. JavaFX Installation

### Step 1: Download JavaFX

* Go to: https://gluonhq.com/products/javafx/
* Download **JavaFX SDK (version 17 recommended)**

### Step 2: Extract

Extract it inside your main folder:

```
Documents\SHLOK_240968500\javafx
```

---

# 🧠 3. Important Rule

✔ Java version and JavaFX version must match
✔ Use JavaFX 17 if unsure (safe for labs)

---

# 💻 4. Open Terminal

Navigate to your program folder:

```bash
cd "C:\Users\<your-username>\Documents\SHLOK_240968500\Lab 9"
```

---

# ⚡ 5. Compile Program

```bash
javac --module-path "C:\Users\<your-username>\Documents\SHLOK_240968500\javafx\lib" --add-modules javafx.controls HotelGUI.java
```

---

# ▶️ 6. Run Program

```bash
java --module-path "C:\Users\<your-username>\Documents\SHLOK_240968500\javafx\lib" --add-modules javafx.controls HotelGUI
```

---

# ✅ 7. Expected Output

* A window opens
* GUI with:

  * TextFields
  * Buttons
  * Room management system

---

# 🚨 8. Common Errors & Fixes

### ❌ Error: `package javafx does not exist`

👉 JavaFX path incorrect
✔ Check:

```
javafx\lib
```

---

### ❌ Error: `Unsupported major.minor version`

👉 JavaFX version > Java version
✔ Fix:

* Install newer Java
  OR
* Use JavaFX 17

---

### ❌ Error: Window not opening

👉 Wrong run command or module missing
✔ Always include:

```
--add-modules javafx.controls
```

---

### ❌ Error: File not found

👉 Wrong directory
✔ Use:

```bash
cd path_to_file
```

---

# 🧪 9. Quick Test Command (Optional)

Check Java version:

```bash
java -version
```

Check compiler:

```bash
javac -version
```

---

# 🧠 10. Viva Quick Notes

* **Stage** → Main window
* **Scene** → Container for UI elements
* **Controls** → Button, TextField, Label
* **Event Handling** → `setOnAction()`
* **Layout Used** → GridPane, VBox, HBox

---

# ⚡ 11. Pro Tips (Lab Survival Mode)

* Copy commands somewhere (don’t trust memory)
* Always run from correct folder
* Keep JavaFX folder path SAME everywhere
* If panic → restart terminal and try again

---

# 🏁 Final Checklist

✔ Java installed
✔ JavaFX extracted
✔ Correct folder structure
✔ Correct compile command
✔ Correct run command

---

If everything is correct → **your GUI will run smoothly** 😌
If not → it's always a path issue. Always.

---
