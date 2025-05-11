import java.util.Scanner;

public class Hotel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookingManager manager = new BookingManager();

        if (!manager.adminLogin()) {
            System.out.println("Access Denied.");
            return;
        }

        int choice;
        do {
            System.out.println("======== HOTEL MANAGEMENT MENU ========");
            System.out.println("1. Add Booking");
            System.out.println("2. View All Bookings");
            System.out.println("3. Update Booking");
            System.out.println("4. Delete Booking");
            System.out.println("5. Search Booking");
            System.out.println("6. Generate Bill");
            System.out.println("7. Clear All Bookings");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: manager.addBooking(); break;
                case 2: manager.viewAllBookings(); break;
                case 3: manager.updateBooking(); break;
                case 4: manager.deleteBooking(); break;
                case 5: manager.searchBooking(); break;
                case 6: manager.generateBillForCustomer(); break;
                case 7: manager.clearAllBookings(); break;
                case 0: System.out.println("Exiting..."); break;
                default: System.out.println("❌ Invalid choice!");
            }
        } while (choice != 0);
    }
}