import java.util.Scanner;

public class AdminLogin {
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "1234";

    public static boolean login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("👤 Enter Admin Username: ");
        String user = scanner.nextLine();
        System.out.print("🔒 Enter Password: ");
        String pass = scanner.nextLine();
        return user.equals(USERNAME) && pass.equals(PASSWORD);
    }
}