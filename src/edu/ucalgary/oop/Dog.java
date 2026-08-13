package edu.ucalgary.oop;

/**
 * Represents a dog in the clinic.
 * @author David Onofeghara
 * @version 1.0
 * @since 1.0
 */

public class Dog extends Pet{
    private boolean isVaccinated;

    public Dog(String name, int age, Owner owner, boolean isVaccinated) {
        super(name, age, owner, "Dog");
        this.isVaccinated = isVaccinated;
    }

    public boolean isVaccinated() {
        return isVaccinated;
    }

    public void setVaccinated(boolean vaccinated) {
        isVaccinated = vaccinated;
    }

    @Override
    public String getDetails() {
        return "Dog Name: " + getName() + ", Age: " + getAge() + ", Owner: " + getOwner().getName() + ", Vaccinated: " + isVaccinated;
    }

    
}
