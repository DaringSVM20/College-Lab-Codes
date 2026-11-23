// package Lab 2;
// 1.	The Hotel Billing system should calculate the total bill amount for hotel guests based on room charges and additional service charges. Store numeric values such as room tariff, number of days stayed, and service charges using wrapper class objects (Integer, Double) instead of primitive data types.
// Demonstrate autoboxing by automatically converting primitive values to wrapper class objects when assigning values or storing them in collections. Demonstrate unboxing by automatically converting wrapper class objects back to primitive types while performing arithmetic operations for bill calculation.
// Create a main class to:
// i.	Initialize room tariff and number of days using primitive data types and store them in wrapper objects.
// ii.	Perform total bill calculation using unboxed primitive values.
// iii.	Display the final hotel bill.

public class HotelBilling {
    public static void main(String[] args) {
        
        // Primitive
        double roomTariff = 3000.0;
        int daysStayed = 3;
        double serviceCharges = 500.0;

        // Autoboxing: primitive → wrapper
        Double roomTariffObj = roomTariff;
        Integer daysStayedObj = daysStayed;
        Double serviceChargesObj = serviceCharges;

        // Unboxing + arithmetic
        double totalRoomCost = roomTariffObj * daysStayedObj;
        double finalBill = totalRoomCost + serviceChargesObj;

        System.out.println("===== Hotel Billing System =====");
        System.out.println("Room Tariff per Day : " + roomTariffObj);
        System.out.println("Days Stayed         : " + daysStayedObj);
        System.out.println("Serice Charges      : " + serviceChargesObj);
        System.out.println("--------------------------------");
        System.out.println("Final Total Bill  : " + finalBill);
    }
}
