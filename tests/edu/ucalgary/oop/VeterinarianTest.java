package edu.ucalgary.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VeterinarianTest {

    @Test
    public void testVeterinarianConstructorSetsName() {
        Veterinarian vet = new Veterinarian("Dr. Smith", "V001", "Surgery");
        assertEquals("Dr. Smith", vet.getName());
    }

    @Test
    public void testVeterinarianConstructorSetsStaffID() {
        Veterinarian vet = new Veterinarian("Dr. Smith", "V001", "Surgery");
        assertEquals("V001", vet.getStaffID());
    }

    @Test
    public void testVeterinarianConstructorSetsSpecialization() {
        Veterinarian vet = new Veterinarian("Dr. Smith", "V001", "Surgery");
        assertEquals("Surgery", vet.getSpecialization());
    }

    @Test
    public void testVeterinarianGetRoleReturnsVeterinarian() {
        Veterinarian vet = new Veterinarian("Dr. Smith", "V001", "Surgery");
        assertEquals("Veterinarian", vet.getRole());
    }

    @Test
    public void testVeterinarianStaffCountIncrements() {
        int countBefore = Staff.getStaffCount();
        Veterinarian vet = new Veterinarian("Dr. Smith", "V001", "Surgery");
        assertEquals(countBefore + 1, Staff.getStaffCount());
    }
}