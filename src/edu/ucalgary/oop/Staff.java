package edu.ucalgary.oop;

/**
 * Abstract class representing a staff member in the clinic.
 * @author David Onofeghara
 * @version 1.0
 * @since 1.0
 */



public abstract class Staff implements ClinicEntity {
    private final String name;
    private final String staffID;
    private static int staffCount;

    public Staff(String name, String staffID) {
        this.name = name;
        this.staffID = staffID;
        staffCount++;
    }

    public String getName() {
        return name;
    }

    public String getStaffID() {
        return staffID;
    }

    public static int getStaffCount() {
        return staffCount;
    }

    public abstract String getRole();
    public abstract String getDetails();
}
