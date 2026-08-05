package edu.ucalgary.oop;

/**
 * Represents a receptionist staff member in the clinic.
 * @author David Onofeghara
 * @version 1.0
 * @since 1.0
 */

public class Receptionist extends Staff{

    public Receptionist(String name, String staffID) {
        super(name, staffID);
    }

    @Override
    public String getRole() {
        return "Receptionist";
    }

    @Override
    public String getDetails() {
        return "Receptionist Name: " + getName() + ", Staff ID: " + getStaffID();
    }
    
}
