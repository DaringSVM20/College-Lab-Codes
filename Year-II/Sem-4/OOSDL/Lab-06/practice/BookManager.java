/*
Problem: Create a BookRecord system using RandomAccessFile. 
Each record stores: id (int), title (String), price (double). 
Implement addRecord(), displayRecord(int recordIndex), and updatePrice(int recordIndex, double newPrice).
*/

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