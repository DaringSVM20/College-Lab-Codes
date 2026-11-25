/*
2. Create a Java program for a hotel room management system that uses a generic
method to display room-related data of different types such as room numbers,
room types, prices, and booking status.
• Implement a generic method <T> void display(T data)
• Call the method with:
1. Room number (Integer)
2. Room type (String)
3. Price per night (Double)
4. Booking status (Boolean)
• Ensure type safety without explicit casting
*/

public class ques2 {

    public static <T> void display(T data) {
        System.out.println("Value: " + data);
    }
    public static void main(String[] args) {
        display(101);
        display("Deluxe");
        display(102.5);
        display(true);
    }
}