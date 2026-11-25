import java.io.*;
import java.util.*;

public class PalindromeToFile {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        FileWriter fw = new FileWriter("palindrome.txt");

        System.out.print("Enter number of words: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter word: ");
            String str = sc.nextLine();

            String rev = new StringBuilder(str).reverse().toString();

            if (str.equalsIgnoreCase(rev)) {
                fw.write(str + "\n");
            }
        }

        fw.close();
        System.out.println("Palindromes written to file.");
    }
}