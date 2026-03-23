import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GymBookingSystem {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Booking> bookings = new ArrayList<>();
    static HashMap<String, Integer> classPrices = new HashMap<>();

    public static void main(String[] args) {
        // ตั้งค่าราคาคลาส
        classPrices.put("Yoga", 20);        // ค่าเช่าแมทโยคะ
        classPrices.put("Boxing", 50);     // ค่าขึ้นครู
        classPrices.put("Pilates", 200);   // ค่าเสื่อมราคาเครื่องเล่น

        while (true) {
            System.out.println("\n--- Gym Booking System ---");
            System.out.println("1. Book Classes");
            System.out.println("2. View Bookings");
            System.out.println("3. View Class Information");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    bookClasses();
                    break;
                case 2:
                    viewBookings();
                    break;
                case 3:
                    viewClassInfo();
                    break;
                case 4:
                    System.out.println("Thank you for using Gym Booking System!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // ฟังก์ชันสำหรับจองคลาส
    public static void bookClasses() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.println("Available classes:");
        for (String className : classPrices.keySet()) {
            System.out.println("- " + className + " (" + classPrices.get(className) + " THB)");
        }

        ArrayList<String> selectedClasses = new ArrayList<>();
        while (true) {
            System.out.print("Enter class name to book (or type 'done' to finish): ");
            String className = scanner.nextLine();

            if (className.equalsIgnoreCase("done")) {
                break;
            }

            if (classPrices.containsKey(className)) {
                selectedClasses.add(className);
                System.out.println(className + " class added.");
            } else {
                System.out.println("Invalid class name. Please try again.");
            }
        }

        if (selectedClasses.isEmpty()) {
            System.out.println("No classes selected. Booking cancelled.");
            return;
        }

        // คำนวณค่าใช้จ่าย
        int totalCost = 0;
        for (String className : selectedClasses) {
            totalCost += classPrices.get(className);
        }

        // สร้างการจองใหม่
        Booking booking = new Booking(name, selectedClasses, totalCost);
        bookings.add(booking);

        System.out.println("\n--- Booking Confirmation ---");
        System.out.println(booking);
        System.out.println("Booking successful!\n");
    }

    // ฟังก์ชันสำหรับดูการจองทั้งหมด
    public static void viewBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        System.out.println("\n--- All Bookings ---");
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }

    // ฟังก์ชันสำหรับดูข้อมูลคลาส
    public static void viewClassInfo() {
        System.out.println("\n--- Class Information ---");
        for (String className : classPrices.keySet()) {
            System.out.println(className + ": " + classPrices.get(className) + " THB");
        }
    }
}

// คลาสสำหรับเก็บข้อมูลการจอง
class Booking {
    private String customerName;
    private ArrayList<String> classes;
    private int totalCost;

    public Booking(String customerName, ArrayList<String> classes, int totalCost) {
        this.customerName = customerName;
        this.classes = new ArrayList<>(classes);
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "Customer: " + customerName + 
               "\nClasses: " + String.join(", ", classes) + 
               "\nTotal Cost: " + totalCost + " THB\n";
    }
}
