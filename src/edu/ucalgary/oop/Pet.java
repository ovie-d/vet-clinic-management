package edu.ucalgary.oop;

/**
 * Abstract class representing a pet in the clinic.
 * @author David Onofeghara
 * @version 1.0
 * @since 1.0
 */
public abstract class Pet implements ClinicEntity {
    private String name;
    private int age;
    private Owner owner;
    private final String type;

    public Pet(String name, int age, Owner owner, String type) {
        this.name = name;
        this.age = age;
        this.owner = owner;
        this.type = type;
    }

    public String getName() { 
        return name; 
    }

    public int getAge() { 
        return age; 
    }

    public Owner getOwner() { 
        return owner;
    }


    public String getType() { 
        return type;
    }
    public void setAge(int age) { 
        this.age = age;
    }

    public void setOwner(Owner owner) { 
        this.owner = owner;
    }

    public abstract String getDetails();
}