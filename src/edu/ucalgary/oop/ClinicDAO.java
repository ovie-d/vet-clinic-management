package edu.ucalgary.oop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object to manage clinic entities and persistence.
 * @author David Onofeghara
 * @version 2.0
 */
public class ClinicDAO {

    // ==========================================
    // STARTUP METRICS
    // ==========================================

    public int getRecordCount(String tableName) {
        if (!tableName.equals("staff") && !tableName.equals("owners") && 
            !tableName.equals("pets") && !tableName.equals("appointments")) {
            throw new IllegalArgumentException("Invalid table name specified.");
        }

        String sql = "SELECT COUNT(*) FROM " + tableName;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error: Failed to fetch record metrics from table: " + tableName, e);
        }
        return 0;
    }

    // ==========================================
    // STAFF METHODS 
    // ==========================================

    public void addVeterinarian(Veterinarian vet) {
        String sql = "INSERT INTO staff (name, role, specialization) VALUES (?, 'Vet', ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, vet.getName());
            pstmt.setString(2, vet.getSpecialization()); 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error saving Veterinarian: " + vet.getName(), e);
        }
    }

    public void addReceptionist(Receptionist receptionist) {
        String sql = "INSERT INTO staff (name, role) VALUES (?, 'Receptionist')";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, receptionist.getName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error saving Receptionist: " + receptionist.getName(), e);
        }
    }

    public List<Staff> getAllStaff() {
        List<Staff> staffList = new ArrayList<>();
        String sql = "SELECT id, name, role, specialization FROM staff";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                String id = String.valueOf(rs.getInt("id"));
                String name = rs.getString("name");
                String role = rs.getString("role");
                
                if ("Vet".equalsIgnoreCase(role)) {
                    String specialty = rs.getString("specialization");
                    staffList.add(new Veterinarian(name, id, specialty));
                } else if ("Receptionist".equalsIgnoreCase(role)) {
                    staffList.add(new Receptionist(name, id));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error fetching all staff members", e);
        }
        return staffList;
    }



    // ==========================================
    // OWNER METHODS
    // ==========================================

    public void addOwner(Owner owner) {
        String sql = "INSERT INTO owners (name, phone, email) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, owner.getName());
            pstmt.setString(2, owner.getPhone());
            pstmt.setString(3, owner.getEmail());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error saving Owner: " + owner.getName(), e);
        }
    }

    public List<Owner> getAllOwners() {
        List<Owner> owners = new ArrayList<>();
        String sql = "SELECT name, phone, email FROM owners";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                owners.add(new Owner(
                    rs.getString("name"),
                    rs.getString("phone"),
                    rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error fetching all owners", e);
        }
        return owners;
    }

    // ==========================================
    // PETS METHODS 
    // ==========================================

    public void addPet(Pet pet) {
        String getOwnerIdSql = "SELECT id FROM owners WHERE email = ?";
        String insertPetSql = "INSERT INTO pets (name, age, species, owner_id, is_vaccinated, is_indoor) VALUES (?, ?, ?::species_type, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            int ownerId = -1;
            try (PreparedStatement pstmt = conn.prepareStatement(getOwnerIdSql)) {
                pstmt.setString(1, pet.getOwner() != null ? pet.getOwner().getEmail() : "");
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) ownerId = rs.getInt("id");
                }
            }

            if (ownerId == -1) {
                throw new SQLException("Owner not found for pet: " + pet.getName());
            }

            try (PreparedStatement pstmt = conn.prepareStatement(insertPetSql)) {
                pstmt.setString(1, pet.getName());
                pstmt.setInt(2, pet.getAge());
                pstmt.setString(3, pet.getType()); 
                pstmt.setInt(4, ownerId);
                
                if ("Dog".equalsIgnoreCase(pet.getType())) {
                    pstmt.setBoolean(5, ((Dog) pet).isVaccinated()); 
                    pstmt.setNull(6, java.sql.Types.BOOLEAN);
                } else {
                    pstmt.setNull(5, java.sql.Types.BOOLEAN);
                    pstmt.setBoolean(6, ((Cat) pet).isIndoor()); 
                }
                
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error saving Pet: " + pet.getName(), e);
        }
    }

    public List<Pet> getAllPets() {
        List<Pet> pets = new ArrayList<>();
        String sql = "SELECT p.name AS pet_name, p.age, p.species, p.is_vaccinated, p.is_indoor, " +
                     "o.name AS owner_name, o.phone, o.email " +
                     "FROM pets p LEFT JOIN owners o ON p.owner_id = o.id";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                Owner owner = null;
                if (rs.getString("email") != null) {
                    owner = new Owner(
                        rs.getString("owner_name"),
                        rs.getString("phone"),
                        rs.getString("email")
                    );
                }
                
                String species = rs.getString("species");
                String name = rs.getString("pet_name");
                int age = rs.getInt("age");
                
                Pet pet;
                if ("Dog".equalsIgnoreCase(species)) {
                    boolean isVaccinated = rs.getBoolean("is_vaccinated");
                    pet = new Dog(name, age, owner, isVaccinated);
                } else {
                    boolean isIndoor = rs.getBoolean("is_indoor");
                    pet = new Cat(name, age, owner, isIndoor);
                }
                
                if (owner != null) {
                    owner.addPet(pet);
                }
                pets.add(pet);
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error fetching all pets", e);
        }
        return pets;
    }

    // ==========================================
    // APPOINTMENTS METHODS
    // ==========================================

    public void addAppointment(Appointment appointment) {
        String sql = "INSERT INTO appointments (pet_name, vet_id, date_time, notes) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, appointment.getPet().getName());
            pstmt.setString(2, appointment.getVeterinarian().getStaffID());
            pstmt.setTimestamp(3, Timestamp.valueOf(appointment.getDateTime()));
            pstmt.setString(4, appointment.getNotes());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error saving appointment for date: " + appointment.getDateTime(), e);
        }
    }

    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        // Note: Added s.specialty to the query columns
        String sql = "SELECT a.id, a.date_time, a.notes, " +
                     "p.name AS pet_name, p.age, p.type, p.subclass_boolean_flag, " +
                     "s.name AS vet_name, s.staff_id, s.specialty, " +
                     "o.name AS owner_name, o.phone, o.email " +
                     "FROM appointments a " +
                     "JOIN pets p ON a.pet_name = p.name " +
                     "JOIN staff s ON a.vet_id = s.staff_id " +
                     "LEFT JOIN owners o ON p.owner_email = o.email";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Owner owner = null;
                if (rs.getString("email") != null) {
                    owner = new Owner(
                        rs.getString("owner_name"), 
                        rs.getString("phone"), 
                        rs.getString("email")
                    );
                }

                String petType = rs.getString("type");
                String name = rs.getString("pet_name");
                int age = rs.getInt("age");
                boolean flag = rs.getBoolean("subclass_boolean_flag");

                Pet pet = "Dog".equalsIgnoreCase(petType) ? 
                          new Dog(name, age, owner, flag) : 
                          new Cat(name, age, owner, flag);

                String vetName = rs.getString("vet_name");
                String vetId = rs.getString("staff_id");
                String specialty = rs.getString("specialty");

                Veterinarian vet = new Veterinarian(vetName, vetId, specialty);
                
                LocalDateTime dateTime = rs.getTimestamp("date_time").toLocalDateTime();
                String notes = rs.getString("notes");

                try {
                    Appointment appt = new Appointment(pet, vet, dateTime, notes, new ArrayList<>(appointments));
                    appointments.add(appt);
                } catch (DoubleBookingException e) {
                    System.err.println("Skipped database record matching overlap rule: " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error compiling complete appointment manifest details", e);
        }
        return appointments;
    }



    public void cancelAppointment(int appointmentId) {
        String sql = "DELETE FROM appointments WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, appointmentId);
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error tracking deletion failure cancellation for appointment record ID: " + appointmentId, e);
        }
    }

    // ==========================================
    // MAIN METHOD FOR TESTING
    // ==========================================
    // public static void main(String[] args) {
    //     System.out.println("=== STARTING CLINIC DATABASE TESTS ===");
    //     ClinicDAO dao = new ClinicDAO();

    //     try {
    //         // 1. Check Table Metrics
    //         System.out.println("\n--- Testing Startup Metrics ---");
    //         System.out.println("Staff Count: " + dao.getRecordCount("staff"));
    //         System.out.println("Owners Count: " + dao.getRecordCount("owners"));

    //         // 2. Test Adding Staff Members
    //         System.out.println("\n--- Testing Add Staff ---");
    //         Veterinarian testVet = new Veterinarian("Dr. Alice Jones", "0", "Surgery");
    //         Receptionist testRec = new Receptionist("Bob Smith", "0");
            
    //         dao.addVeterinarian(testVet);
    //         dao.addReceptionist(testRec);
    //         System.out.println("Successfully added test staff members!");

    //         // 3. Test Fetching Staff Members
    //         System.out.println("\n--- Testing Get All Staff ---");
    //         List<Staff> everyone = dao.getAllStaff();
    //         for (Staff member : everyone) {
    //             System.out.println(" - " + member.getName() + " (" + member.getStaffID() + ") [" + member.getClass().getSimpleName() + "]");
    //         }

    //         // 4. Test Adding Owners and Pets
    //         System.out.println("\n--- Testing Add Owner & Pet ---");
    //         Owner testOwner = new Owner("John Doe", "403-123-4567", "john.doe@example.com");
    //         dao.addOwner(testOwner);
            
    //         Dog testDog = new Dog("Buddy", 3, testOwner, true); 
    //         dao.addPet(testDog);
    //         System.out.println("Successfully added owner and pet!");

    //         // 5. Test Fetching Pets
    //         System.out.println("\n--- Testing Get All Pets ---");
    //         List<Pet> pets = dao.getAllPets();
    //         for (Pet pet : pets) {
    //             System.out.println(" - " + pet.getName() + " (" + pet.getType() + "), Owner: " + 
    //                                (pet.getOwner() != null ? pet.getOwner().getName() : "None"));
    //         }

    //         System.out.println("\n=== ALL TESTS EXECUTED SUCCESSFULLY ===");

    //     } catch (Exception e) {
    //         System.err.println("\nTEST FAILED WITH EXCEPTION:");
    //         e.printStackTrace();
    //     }
    // }


}
