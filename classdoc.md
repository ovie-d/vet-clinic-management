package edu.ucalgary.oop

Staff (Abstract Class, implements ClinicEntity)

**Fields**
- private final String name
- private final String staffID
- private static int staffCount

**Constructor:**
- `Staff(String name, String staffID)`

**Methods:**
- `getName() : String`
- `getStaffID() : String`
- `static getStaffCount() : int`
- `getRole() : String` — abstract, returns "Veterinarian" or "Receptionist"
- `getDetails() : String` — abstract



Veterinarian (extends Staff)
**Fields:**
- `private final String specialization`

**Constructor:**
- `Veterinarian(String name, String staffID, String specialization)`

**Methods:**
- `getSpecialization() : String`
- `getRole() : String` — returns "Veterinarian" 
- `getDetails() : String` — returns vet's name, ID, role, specialization

Receptionist (extends Staff)
**Constructor:**
- `Receptionist(String name, String staffID)`

**Methods:**
- `getRole() : String` — returns "Receptionist"
- `getDetails() : String` — returns receptionist's name, ID, role


Pet (Abstract Class, implements ClinicEntity)
**Fields:**
- `private String name`
- `private int age`
- `private Owner owner`
- `private final String type`

**Constructor:**
- `Pet(String name, int age, Owner owner, String type)`

**Methods:**
- `getName() : String`
- `getAge() : int`
- `setAge(int age) : void`
- `getOwner() : Owner`
- `setOwner(Owner owner) : void`
- `getType() : String`
- `getDetails() : String` — abstract


Dog (extends Pet)
**Fields:**
- `private boolean isVaccinated`

**Constructor:**
- `Dog(String name, int age, Owner owner, boolean isVaccinated)`

**Methods:**
- `isVaccinated() : boolean`
- `setVaccinated(boolean isVaccinated) : void`
- `getDetails() : String`


Cat (extends Pet)
**Fields:**
- `private boolean isIndoor`

**Constructor:**
- `Cat(String name, int age, Owner owner, boolean isIndoor)`

**Methods:**
- `isIndoor() : boolean`
- `setIndoor(boolean isIndoor) : void`
- `getDetails() : String`

Owner (implements ClinicEntity)
**Fields:**
- `private String name`
- `private String phone`
- `private String email`
- `private static int ownerCount`
- `private ArrayList<Pet> pets`

**Constructor:**
- `Owner(String name, String phone, String email)`

**Methods:**
- `getName() : String`
- `getPhone() : String`
- `getEmail() : String`
- `setPhone(String phone) : void`
- `setEmail(String email) : void`
- `static getOwnerCount() : int`
- `getPets() : ArrayList<Pet>`
- `addPet(Pet pet) : void`
- `removePet(Pet pet) : void`

Appointment
**Fields:**
- `private Pet pet`
- `private Veterinarian vet`
- `private LocalDateTime dateTime`
- `private String notes`
- `private static final int MAX_DAILY_APPOINTMENTS = 10`

**Constructor:**
- `Appointment(Pet pet, Veterinarian vet, LocalDateTime dateTime, String notes)`

**Methods:**
- `getPet() : Pet`
- `getVet() : Veterinarian`
- `getDateTime() : LocalDateTime`
- `getNotes() : String`
- `setNotes(String notes) : void`
- `static getMaxDailyAppointments() : int`


DoubleBookingException (extends Exception)
**Constructor:**
- `DoubleBookingException(String message)`


DailyLimitExceededException (extends Exception)
**Constructor:**
- `DailyLimitExceededException(String message)`


ClinicEntity (Interface)
**Methods:**
- `getDetails() : String`