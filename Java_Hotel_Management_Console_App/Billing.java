import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Billing {
    public static void generateInvoice(Booking booking) {
        Room room = booking.getRoom();
        double pricePerDay = room.getPrice();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate checkIn = LocalDate.parse(booking.getCheckInDate(), formatter);
            LocalDate checkOut = LocalDate.parse(booking.getCheckOutDate(), formatter);
            long days = ChronoUnit.DAYS.between(checkIn, checkOut);
            if (days == 0) days = 1;

            double subtotal = pricePerDay * days;
            double tax = subtotal * 0.12;
            double total = subtotal + tax;

            System.out.println("\n🧾 ------ BILLING INVOICE ------");
            System.out.println("👤 Customer: " + booking.getCustomerName());
            System.out.println("🏨 Room Type: " + room.getRoomType());
            System.out.println("💰 Rate per Day: ₹" + pricePerDay);
            System.out.println("📅 Stay Duration: " + days + " day(s)");
            System.out.println("💵 Subtotal: ₹" + subtotal);
            System.out.println("🧾 GST (12%): ₹" + tax);
            System.out.println("💳 Total Amount: ₹" + total);
            System.out.println("-------------------------------\n");

        } catch (Exception e) {
            System.out.println("❌ Date format error. Use dd/MM/yyyy.");
        }
    }
}