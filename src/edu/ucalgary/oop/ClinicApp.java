package edu.ucalgary.oop;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Main CLI application for Paws and Care Veterinary Clinic.
 * @author David Onofeghara
 * @version 1.0
 * @since 1.0
 */
public class ClinicApp {
    private static ClinicDAO dao = new ClinicDAO();
    private static Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   Paws & Care Veterinary Clinic");
        System.out.println("========================================");
        mainMenu();
    }

    // ==========================================
    // MAIN MENU
    // ==========================================

    private static void mainMenu() {
        while (true) {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. Manage Staff");
            System.out.println("2. Manage Owners");
            System.out.println("3. Manage Pets");
            System.out.println("4. Manage Appointments");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = readInt();

            switch (choice) {
                case 1 -> manageStaff();
                case 2 -> manageOwners();
                case 3 -> managePets();
                case 4 -> manageAppointments();
                case 5 -> {
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // ==========================================
    // STAFF MENU
    // ==========================================

    private static void manageStaff() {
        while (true) {
            System.out.println("\n=== Manage Staff ===");
            System.out.println("1. Add Veterinarian");
            System.out.println("2. Add Receptionist");
            System.out.println("3. View All Staff");
            System.out.println("4. Back");
            System.out.print("Enter choice: ");

            int choice = readInt();

            switch (choice) {
                case 1 -> addVeterinarian();
                case 2 -> addReceptionist();
                case 3 -> viewAllStaff();
                case 4 -> { return; }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addVeterinarian() {
        System.out.println("\n--- Add Veterinarian ---");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Staff ID: ");
        String staffID = scanner.nextLine();
        System.out.print("Specialization: ");
        String specialization = scanner.nextLine();

        Veterinarian vet = new Veterinarian(name, staffID, specialization);
        dao.addVeterinarian(vet);
        System.out.println("Veterinarian added successfully.");
    }

    private static void addReceptionist() {
        System.out.println("\n--- Add Receptionist ---");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Staff ID: ");
        String staffID = scanner.nextLine();

        Receptionist receptionist = new Receptionist(name, staffID);
        dao.addReceptionist(receptionist);
        System.out.println("Receptionist added successfully.");
    }

    private static void viewAllStaff() {
        System.out.println("\n--- All Staff ---");
        List<Staff> staffList = dao.getAllStaff();
        if (staffList.isEmpty()) {
            System.out.println("No staff found.");
            return;
        }
        for (Staff s : staffList) {
            System.out.println(s.getDetails());
        }
    }

    // ==========================================
    // OWNER MENU
    // ==========================================

    private static void manageOwners() {
        while (true) {
            System.out.println("\n=== Manage Owners ===");
            System.out.println("1. Add Owner");
            System.out.println("2. View All Owners");
            System.out.println("3. Back");
            System.out.print("Enter choice: ");

            int choice = readInt();

            switch (choice) {
                case 1 -> addOwner();
                case 2 -> viewAllOwners();
                case 3 -> { return; }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addOwner() {
        System.out.println("\n--- Add Owner ---");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        Owner owner = new Owner(name, phone, email);
        dao.addOwner(owner);
        System.out.println("Owner added successfully.");
    }

    private static void viewAllOwners() {
        System.out.println("\n--- All Owners ---");
        List<Owner> owners = dao.getAllOwners();
        if (owners.isEmpty()) {
            System.out.println("No owners found.");
            return;
        }
        for (Owner o : owners) {
            System.out.println(o.getDetails());
        }
    }

    // ==========================================
    // PETS MENU
    // ==========================================

    private static void managePets() {
        while (true) {
            System.out.println("\n=== Manage Pets ===");
            System.out.println("1. Add Dog");
            System.out.println("2. Add Cat");
            System.out.println("3. View All Pets");
            System.out.println("4. Back");
            System.out.print("Enter choice: ");

            int choice = readInt();

            switch (choice) {
                case 1 -> addDog();
                case 2 -> addCat();
                case 3 -> viewAllPets();
                case 4 -> { return; }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addDog() {
        System.out.println("\n--- Add Dog ---");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Age: ");
        int age = readInt();
        System.out.print("Owner Email: ");
        String email = scanner.nextLine();
        System.out.print("Vaccinated? (true/false): ");
        boolean isVaccinated = Boolean.parseBoolean(scanner.nextLine());

        Owner owner = findOwnerByEmail(email);
        if (owner == null) {
            System.out.println("Owner not found. Please add the owner first.");
            return;
        }

        Dog dog = new Dog(name, age, owner, isVaccinated);
        dao.addPet(dog);
        System.out.println("Dog added successfully.");
    }

    private static void addCat() {
        System.out.println("\n--- Add Cat ---");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Age: ");
        int age = readInt();
        System.out.print("Owner Email: ");
        String email = scanner.nextLine();
        System.out.print("Indoor? (true/false): ");
        boolean isIndoor = Boolean.parseBoolean(scanner.nextLine());

        Owner owner = findOwnerByEmail(email);
        if (owner == null) {
            System.out.println("Owner not found. Please add the owner first.");
            return;
        }

        Cat cat = new Cat(name, age, owner, isIndoor);
        dao.addPet(cat);
        System.out.println("Cat added successfully.");
    }

    private static void viewAllPets() {
        System.out.println("\n--- All Pets ---");
        List<Pet> pets = dao.getAllPets();
        if (pets.isEmpty()) {
            System.out.println("No pets found.");
            return;
        }
        for (Pet p : pets) {
            System.out.println(p.getDetails());
        }
    }

    // ==========================================
    // APPOINTMENTS MENU
    // ==========================================

    private static void manageAppointments() {
        while (true) {
            System.out.println("\n=== Manage Appointments ===");
            System.out.println("1. Schedule Appointment");
            System.out.println("2. Cancel Appointment");
            System.out.println("3. View All Appointments");
            System.out.println("4. Back");
            System.out.print("Enter choice: ");

            int choice = readInt();

            switch (choice) {
                case 1 -> scheduleAppointment();
                case 2 -> cancelAppointment();
                case 3 -> viewAllAppointments();
                case 4 -> { return; }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void scheduleAppointment() {
        System.out.println("\n--- Schedule Appointment ---");
        System.out.print("Pet Name: ");
        String petName = scanner.nextLine();
        System.out.print("Vet Staff ID: ");
        String vetID = scanner.nextLine();
        System.out.print("Date and Time (yyyy-MM-dd HH:mm): ");
        String dateTimeStr = scanner.nextLine();
        System.out.print("Notes: ");
        String notes = scanner.nextLine();

        Pet pet = findPetByName(petName);
        if (pet == null) {
            System.out.println("Pet not found.");
            return;
        }

        Veterinarian vet = findVetByID(vetID);
        if (vet == null) {
            System.out.println("Veterinarian not found.");
            return;
        }

        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, FORMATTER);
            ArrayList<Appointment> existing = new ArrayList<>(dao.getAllAppointments());
            Appointment appointment = new Appointment(pet, vet, dateTime, notes, existing);
            dao.addAppointment(appointment);
            System.out.println("Appointment scheduled successfully.");
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Use yyyy-MM-dd HH:mm");
        } catch (DoubleBookingException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void cancelAppointment() {
        System.out.println("\n--- Cancel Appointment ---");
        viewAllAppointments();
        System.out.print("Enter Appointment ID to cancel: ");
        int id = readInt();
        dao.cancelAppointment(id);
        System.out.println("Appointment cancelled successfully.");
    }

    private static void viewAllAppointments() {
        System.out.println("\n--- All Appointments ---");
        List<Appointment> appointments = dao.getAllAppointments();
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
            return;
        }
        for (Appointment a : appointments) {
            System.out.println(a.getDetails());
        }
    }

    // ==========================================
    // HELPER METHODS
    // ==========================================

    private static Owner findOwnerByEmail(String email) {
        List<Owner> owners = dao.getAllOwners();
        for (Owner o : owners) {
            if (o.getEmail().equalsIgnoreCase(email)) {
                return o;
            }
        }
        return null;
    }

    private static Pet findPetByName(String name) {
        List<Pet> pets = dao.getAllPets();
        for (Pet p : pets) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    private static Veterinarian findVetByID(String staffID) {
        List<Staff> staffList = dao.getAllStaff();
        for (Staff s : staffList) {
            if (s.getStaffID().equalsIgnoreCase(staffID) && s instanceof Veterinarian) {
                return (Veterinarian) s;
            }
        }
        return null;
    }

    private static int readInt() {
        while (true) {
            try {
                int value = Integer.parseInt(scanner.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Enter a number: ");
            }
        }
    }
}