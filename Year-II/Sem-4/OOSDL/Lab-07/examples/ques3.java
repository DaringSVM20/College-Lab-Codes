/*
3. Develop a Java application that uses bounded type parameters to calculate room
charges. The application should accept only numeric values for pricing and discount
calculations.
• Create a generic class or method using <T extends Number>
• Accept room price and discount as generic parameters
• Perform calculations such as:
o Total price
o Discounted price
• Prevent non-numeric data types at compile time

*/

class Charges<T extends Number> {
    T price, discount;

    Charges(T price, T discount) {
        this.price=price;
        this.discount=discount;
    }

    double total() {
        return price.doubleValue();
    }

    double finalPrice() {
        return price.doubleValue() - discount.doubleValue();
    }
}

public class ques3 {
    public static void main(String[] args) {
        
        Charges<Double> c = new Charges<>(500.0,150.0);

        System.out.println("Total Price: " + c.total());
        System.out.println("Final Price: " + c.finalPrice());
    }
}
