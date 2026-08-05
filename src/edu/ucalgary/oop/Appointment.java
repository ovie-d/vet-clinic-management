package edu.ucalgary.oop;
import java.time.LocalDateTime;

/**
 * Represents an appointment in the clinic.
 * @author David Onofeghara
 * @version 1.0
 * @since 1.0
 */

public class Appointment {
    private Pet pet;
    private Veterinarian veterinarian;
    private LocalDateTime dateTime;
    private String notes;
    private static final int MAX_APPOINTMENTS = 10;

    public Appointment(Pet pet, Veterinarian veterinarian, LocalDateTime dateTime, String notes) {
        this.pet = pet;
        this.veterinarian = veterinarian;
        this.dateTime = dateTime;
        this.notes = notes;
    }

    public Pet getPet() {
        return pet;
    }

    public getveterinarian() {
        return veterinarian;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public static int getMaxDailyAppointments() {
        return MAX_APPOINTMENTS;
    }

}
