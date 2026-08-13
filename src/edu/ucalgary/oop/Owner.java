package edu.ucalgary.oop;
import java.util.ArrayList;

/**
 * Represents a pet owner registered in the clinic.
 * @author David Onofeghara
 * @version 1.0
 * @since 1.0
 */


public class Owner implements ClinicEntity {
    private String name;
    private String phone;
    private String email;
    private static int ownerCount;
    private ArrayList<Pet> pets;

    public Owner(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.pets = new ArrayList<>();
        ownerCount++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static int getOwnerCount() {
        return ownerCount;
    }

    public void addPet(Pet pet) {
        if (pets == null) {
            pets = new ArrayList<>();
        }
        pets.add(pet);
    }

    public void removePet(Pet pet) {
        if (pets != null) {
            pets.remove(pet);
        }
    }

    public ArrayList<Pet> getPets() {
        return pets;
    }

    @Override
    public String getDetails() {
        return "Owner Name: " + name + ", Phone: " + phone + ", Email: " + email;
    }
    
}
