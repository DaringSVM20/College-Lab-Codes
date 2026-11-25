/*
4. Design a Java program that uses generic methods to manage an array of hotel rooms.
The program should be capable of storing and displaying arrays of different room
attributes.
• Create a generic method to print arrays
• Use it for:
o Room numbers array
o Room types array
o Room prices array
• Do not use collections framework
*/

public class ques4 {
    public static <T> void printArray(T arr[]) {
        for (T i:arr)
            System.out.println(i + " ");
        System.out.println();
    }
    
    public static void main(String[] args) {
        Integer[] roomNo = {101, 102, 103};
        String[] types = {"Single", "Double", "Suite"};
        Double[] price = {500.0, 1000.0, 1800.0};
        
        printArray(roomNo);
        printArray(types);
        printArray(price);
    }
}
