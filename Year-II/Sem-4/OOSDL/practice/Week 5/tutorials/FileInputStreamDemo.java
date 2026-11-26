import java.io.FileInputStream;
import java.io.IOException;

public class FileInputStreamDemo {
    public static void main(String[] args) {
        
        FileInputStream fis = null;

        try {
            // Open the file
            fis = new FileInputStream("input.txt");

            int data;
            System.out.println("File Contents: ");
            System.out.println("--------------");

            // Read file byte by fucking byte
            while ((data = fis.read()) != -1) {
                // Convert byte to char and print
                System.out.print((char) data);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }

}