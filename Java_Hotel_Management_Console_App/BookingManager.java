import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class BookingManager {
    private ArrayList<Booking> bookings = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private final String adminUsername = "admin";
    private final String adminPassword = "1234";

    public boolean adminLogin() {
        System.out.print("👤 Enter Admin Username: ");
        String username = scanner.nextLine();
        System.out.print("🔒 Enter Password: ");
        String password = scanner.nextLine();

        if (username.equals(adminUsername) && password.equals(adminPassword)) {
            System.out.println("✅ Login Successful.\n");
            return true;
        } else {
            System.out.println("❌ Invalid credentials.\n");
            return false;
        }
    }

    public void addBooking() {
        System.out.print("📛 Enter Customer Name: ");
        String name = scanner.nextLine();
        System.out.print("📞 Enter Phone Number: ");
        String phone = scanner.nextLine();

        System.out.println("🏨 Select Room Type:");
        System.out.println("1. AC Room");
        System.out.println("2. Non-AC Room");
        System.out.println("3. Suite");
        int roomChoice = scanner.nextInt();
        scanner.nextLine();

        Room room;
        switch (roomChoice) {
            case 1:
                room = new Room("AC", 2000.0);
                break;
            case 2:
                room = new Room("Non-AC", 1500.0);
                break;
            case 3:
                room = new Room("Suite", 3000.0);
                break;
            default:
                System.out.println("❌ Invalid room type.");
                return;
        }

        System.out.print("📅 Enter Check-In Date (dd/MM/yyyy): ");
        String checkIn = scanner.nextLine();
        System.out.print("📅 Enter Check-Out Date (dd/MM/yyyy): ");
        String checkOut = scanner.nextLine();

        Booking booking = new Booking(name, phone, room, checkIn, checkOut);
        bookings.add(booking);
        System.out.println("✅ Booking Successful for " + name + "!\n");
    }

    public void updateBooking() {
        System.out.print("🔍 Enter Customer Name to Update: ");
        String name = scanner.nextLine();
        for (Booking booking : bookings) {
            if (booking.getCustomerName().equalsIgnoreCase(name)) {
                System.out.print("📞 New Phone: ");
                booking.setCustomerPhone(scanner.nextLine());

                System.out.println("🏨 New Room Type: 1. AC 2. Non-AC 3. Suite");
                int type = scanner.nextInt();
                scanner.nextLine();
                switch (type) {
                    case 1: booking.setRoom(new Room("AC", 2000)); break;
                    case 2: booking.setRoom(new Room("Non-AC", 1500)); break;
                    case 3: booking.setRoom(new Room("Suite", 3000)); break;
                    default: System.out.println("❌ Invalid Room Type"); return;
                }

                System.out.print("📅 New Check-In (dd/MM/yyyy): ");
                booking.setCheckInDate(scanner.nextLine());
                System.out.print("📅 New Check-Out (dd/MM/yyyy): ");
                booking.setCheckOutDate(scanner.nextLine());

                System.out.println("✅ Booking Updated!\n");
                return;
            }
        }
        System.out.println("❌ Booking Not Found.\n");
    }

    public void deleteBooking() {
        System.out.print("🗑️ Enter Customer Name to Delete: ");
        String name = scanner.nextLine();
        Iterator<Booking> iterator = bookings.iterator();
        while (iterator.hasNext()) {
            Booking booking = iterator.next();
            if (booking.getCustomerName().equalsIgnoreCase(name)) {
                iterator.remove();
                System.out.println("✅ Booking Deleted!\n");
                return;
            }
        }
        System.out.println("❌ Booking Not Found.\n");
    }

    public void viewAllBookings() {
        if (bookings.isEmpty()) {
            System.out.println("📭 No Bookings Found.\n");
            return;
        }

        System.out.println("\n📋 All Bookings:");
        for (Booking booking : bookings) {
            System.out.println("--------------------------------");
            System.out.println("👤 Customer: " + booking.getCustomerName());
            System.out.println("📞 Phone: " + booking.getCustomerPhone());
            System.out.println("🏨 Room: " + booking.getRoom().getRoomType() + " (₹" + booking.getRoom().getPrice() + ")");
            System.out.println("📅 Check-In: " + booking.getCheckInDate());
            System.out.println("📅 Check-Out: " + booking.getCheckOutDate());
        }
        System.out.println("--------------------------------\n");
    }

    public void searchBooking() {
        System.out.print("🔍 Enter Customer Name or Phone: ");
        String keyword = scanner.nextLine();
        boolean found = false;

        for (Booking booking : bookings) {
            if (booking.getCustomerName().equalsIgnoreCase(keyword) ||
                booking.getCustomerPhone().equalsIgnoreCase(keyword)) {
                System.out.println("\n✅ Booking Found:");
                System.out.println("👤 Customer: " + booking.getCustomerName());
                System.out.println("📞 Phone: " + booking.getCustomerPhone());
                System.out.println("🏨 Room: " + booking.getRoom().getRoomType());
                System.out.println("📅 Check-In: " + booking.getCheckInDate());
                System.out.println("📅 Check-Out: " + booking.getCheckOutDate());
                found = true;
            }
        }

        if (!found) {
            System.out.println("❌ No Booking Found with provided info.\n");
        }
    }

    public void clearAllBookings() {
        System.out.print("⚠️ Are you sure you want to clear all bookings? (yes/no): ");
        String confirm = scanner.nextLine();
        if (confirm.equalsIgnoreCase("yes")) {
            bookings.clear();
            System.out.println("✅ All bookings cleared!\n");
        } else {
            System.out.println("❌ Operation cancelled.\n");
        }
    }

    public void generateBillForCustomer() {
        System.out.print("🧾 Enter Customer Name for Billing: ");
        String customerName = scanner.nextLine();

        for (Booking booking : bookings) {
            if (booking.getCustomerName().equalsIgnoreCase(customerName)) {
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
                    System.out.println("👤 Customer: " + customerName);
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
                return;
            }
        }

        System.out.println("❌ No booking found for this customer.\n");
    }
}
