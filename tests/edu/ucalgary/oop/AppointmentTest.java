package edu.ucalgary.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class AppointmentTest {

    @Test
    public void testDoubleBookingThrowsException() {
        Veterinarian vet = new Veterinarian("Dr. Smith", "V001", "Surgery");
        Owner owner = new Owner("John", "555-1234", "john@email.com");
        Dog dog = new Dog("Buddy", 3, owner, true);
        LocalDateTime dateTime = LocalDateTime.of(2025, 8, 1, 10, 0);

        ArrayList<Appointment> existing = new ArrayList<>();

        assertThrows(DoubleBookingException.class, () -> {
            Appointment a1 = new Appointment(dog, vet, dateTime, "First", existing);
            existing.add(a1);
            new Appointment(dog, vet, dateTime, "Second", existing);
        });
    }
}