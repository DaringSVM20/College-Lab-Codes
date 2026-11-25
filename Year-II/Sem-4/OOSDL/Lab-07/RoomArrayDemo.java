// Q4

class RoomArrayDemo {

    public static <T> void printArray(T[] arr) {
        for (T i : arr)
            System.out.print(i + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        Integer[] roomNo = {101, 102, 103};
        String[] types = {"Single", "Double", "Suite"};
        Double[] price = {1000.0, 1500.0, 3000.0};

        printArray(roomNo);
        printArray(types);
        printArray(price);
    }
}