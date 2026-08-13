package edu.ucalgary.oop;

/**
 * Represents a veterinarian staff member in the clinic.
 * @author David Onofeghara
 * @version 1.0
 * @since 1.0
 */


public class Veterinarian extends Staff {
    private final String specialization;

    public Veterinarian(String name, String staffID, String specialization) {
        super(name, staffID);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    @Override
    public String getRole() {
        return "Veterinarian";
    }

    @Override
    public String getDetails() {
        return "Veterinarian Name: " + getName() + ", Staff ID: " + getStaffID() + ", Role: " + getRole() + ", Specialization: " + specialization;
    }
}
