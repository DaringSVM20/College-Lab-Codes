// Q2

class GenericDisplayDemo {

    public static <T> void display(T data) {
        System.out.println("Value: " + data);
    }

    public static void main(String[] args) {
        display(101);
        display("Deluxe Room");
        display(1999.99);
        display(true);
    }
}