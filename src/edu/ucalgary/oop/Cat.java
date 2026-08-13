package edu.ucalgary.oop;

/**
 * Represents a cat in the clinic.
 * @author David Onofeghara
 * @version 1.0
 * @since 1.0
 */

public class Cat extends Pet {
    private boolean isIndoor;

    public Cat(String name, int age, Owner owner, boolean isIndoor) {
        super(name, age, owner, "Cat");
        this.isIndoor = isIndoor;
    }

    public boolean isIndoor() {
        return isIndoor;
    }

    public void setIndoor(boolean indoor) {
        isIndoor = indoor;
    }

    @Override
    public String getDetails() {
        return "Cat Name: " + getName() + ", Age: " + getAge() + ", Owner: " + getOwner().getName() + ", Indoor: " + isIndoor;
    }
    
}
