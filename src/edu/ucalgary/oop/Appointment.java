package edu.ucalgary.oop;
import java.time.LocalDateTime;
import java.util.ArrayList;

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

   public Appointment(Pet pet, Veterinarian veterinarian, LocalDateTime dateTime, String notes, ArrayList<Appointment> existingAppointments) throws DoubleBookingException {
    for (Appointment a : existingAppointments) {
        if (a.getVeterinarian().getStaffID().equals(veterinarian.getStaffID()) 
            && a.getDateTime().equals(dateTime)) {
            throw new DoubleBookingException("Vet is already booked at this time");
        }
    }
    this.pet = pet;
    this.veterinarian = veterinarian;
    this.dateTime = dateTime;
    this.notes = notes;
}

    public Pet getPet() {
        return pet;         
    }

    public Veterinarian getVeterinarian() {
        return veterinarian;
    }

    public LocalDateTime getDateTime() {
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
