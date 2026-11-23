public class HotelBillingSystem {
    public static void main(String[] args) {
        // Step i: Initialize room tariff and number of days using primitive data types
        double roomTariffPrimitive = 2500.50;   // primitive double
        int daysPrimitive = 3;                  // primitive int
        double serviceChargesPrimitive = 1200.75; // primitive double

        // Autoboxing: converting primitive values into wrapper objects
        Double roomTariff = roomTariffPrimitive;   // autoboxing
        Integer daysStayed = daysPrimitive;        // autoboxing
        Double serviceCharges = serviceChargesPrimitive; // autoboxing

        // Step ii: Perform total bill calculation using unboxed primitive values
        // Unboxing happens automatically when performing arithmetic operations
        double totalRoomCharges = roomTariff * daysStayed; // unboxing
        double totalBill = totalRoomCharges + serviceCharges; // unboxing

        // Step iii: Display the final hotel bill
        System.out.println("Hotel Billing System");
        System.out.println("---------------------");
        System.out.println("Room Tariff (per day): " + roomTariff);
        System.out.println("Number of Days Stayed: " + daysStayed);
        System.out.println("Service Charges: " + serviceCharges);
        System.out.println("Total Room Charges: " + totalRoomCharges);
        System.out.println("Final Bill Amount: " + totalBill);
    }
}


