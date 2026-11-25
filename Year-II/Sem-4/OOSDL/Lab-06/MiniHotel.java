// Question 1

import java.io.*;

public class MiniHotel {
    static final int RECORD_SIZE = 53;
    static final int TYPE_LEN = 20;

    // Write a fixed length (20-char) string
    static void writeString(String s, RandomAccessFile raf) throws IOException {
        StringBuilder sb = new StringBuilder(s);
        sb.setLength(TYPE_LEN); 
        raf.writeChars(sb.toString());
    }

    // Read fixed length string
    static String readString(RandomAccessFile raf) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < TYPE_LEN; i++) sb.append(raf.readChar());
        return sb.toString().trim();
    }

    // Add room (hardcoded)
    static void addRoom(int no, String type, double price, boolean status) throws Exception {
        RandomAccessFile raf = new RandomAccessFile("rooms.dat", "rw");
        long pos = (no - 1) * RECORD_SIZE;
        raf.seek(pos);

        raf.writeInt(no);
        writeString(type, raf);
        raf.writeDouble(price);
        raf.writeBoolean(status);

        raf.close();
        System.out.println("Added Room " + no);
    }

    // Display room
    static void showRoom(int no) throws Exception {
        RandomAccessFile raf = new RandomAccessFile("rooms.dat", "r");
        long pos = (no - 1) * RECORD_SIZE;

        if (pos >= raf.length()) {
            System.out.println("Room not found!");
            raf.close();
            return;
        }

        raf.seek(pos);
        System.out.println("\nRoom No: " + raf.readInt());
        System.out.println("Type   : " + readString(raf));
        System.out.println("Price  : " + raf.readDouble());
        System.out.println("Booked : " + raf.readBoolean());

        raf.close();
    }

    // Update booking status
    static void updateStatus(int no, boolean status) throws Exception {
        RandomAccessFile raf = new RandomAccessFile("rooms.dat", "rw");
        long pos = (no - 1) * RECORD_SIZE;

        raf.seek(pos + 4 + (TYPE_LEN * 2) + 8); // skip int + string + double
        raf.writeBoolean(status);

        raf.close();
        System.out.println("Updated Room " + no);
    }

    public static void main(String[] args) throws Exception {

        addRoom(1, "Deluxe", 2500, false);
        addRoom(2, "Suite", 4500, true);

        showRoom(1);

        updateStatus(1, true);

        showRoom(1);
    }
} 