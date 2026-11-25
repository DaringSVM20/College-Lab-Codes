// Q3

class Charges<T extends Number> {
    T price, discount;

    Charges(T price, T discount) {
        this.price = price;
        this.discount = discount;
    }

    double total() {
        return price.doubleValue();
    }

    double finalPrice() {
        return price.doubleValue() - discount.doubleValue();
    }
}

public class RoomChargesDemo {
    public static void main(String[] args) {
        Charges<Double> c = new Charges<>(2000.0, 200.0);

        System.out.println("Total Price: " + c.total());
        System.out.println("After Discount: " + c.finalPrice());
    }
}