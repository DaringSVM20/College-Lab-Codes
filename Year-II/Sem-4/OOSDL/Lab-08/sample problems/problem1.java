// Program demonstrating ArrayList operations
import java.util.*;

public class problem1 {
    public static void main(String[] args) {
        List<String> students = new ArrayList<>();

        students.add("Shlok");
        students.add("Isha");
        students.add("Atmaj");

        System.out.println("Initial ArrayList: " + students);

        students.add(2, "Ayaan");
        System.out.println("Array after inserting at index 2: " + students);

        System.out.println("Element at index 1: " + students.get(1));

        System.out.println("Using iterator (for-each loop) ");
        for (String name: students) {
            System.out.println(name);
        }

        System.out.println("Using iterator");
        Iterator<String>it = students.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }

    }
}
