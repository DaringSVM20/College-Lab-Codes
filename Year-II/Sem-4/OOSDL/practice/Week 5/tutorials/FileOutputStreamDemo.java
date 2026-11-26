import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamDemo {

    public static void main(String[] args) {

        FileOutputStream fos = null;

        try {
            // Create or open the file
            fos = new FileOutputStream("output.txt");

            String message = "Hello, this is the output I want to see in the file.";

            // Convert String to bytes
            byte[] data = message.getBytes();

            // Write data to file
            fos.write(data);

            System.out.println("Data written to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close(); //Close the file
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}