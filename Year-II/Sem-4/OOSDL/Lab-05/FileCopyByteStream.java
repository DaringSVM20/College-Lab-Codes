import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopyByteStream {
    public static void main(String[] args) {

        try (
            FileInputStream fis = new FileInputStream("source.bin");
            FileOutputStream fos = new FileOutputStream("destination.bin")
        ) {

            int data;
            while ((data = fis.read()) != -1) {
                fos.write(data);
            }

            System.out.println("File copied successfully.");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
