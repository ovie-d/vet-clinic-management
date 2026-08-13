# Vet Clinic Management System

A Java-based veterinary clinic management system for Paws & Care Veterinary Clinic.
Built with Java 17, PostgreSQL, and JDBC.

---

## Tech Stack
- Java 17
- PostgreSQL 18
- JDBC (PostgreSQL Driver)
- JUnit 5
- Maven

---

## Project Structure

vet-clinic-management/
├── src/edu/ucalgary/oop/ # Source classes
├── tests/edu/ucalgary/oop/ # JUnit tests
├── documentation/ # Generated Javadoc
├── clinic_database.sql # Database schema
└── pom.xml # Maven config

## Prerequisites
- Java 17+
- PostgreSQL 18
- Maven

---

## Setup

### 1. Clone the repository
git clone https://github.com/ovie-d/vet-clinic-management.git
cd vet-clinic-management

### 2. Set up the database
/Library/PostgreSQL/18/bin/psql -U oop -d postgres
\i clinic_database.sql

### 3. Configure database connection
Open `src/edu/ucalgary/oop/DatabaseConnection.java` and verify:
```java
private static final String URL = "jdbc:postgresql://localhost:5432/vet_clinic";
private static final String USER = "oop";
private static final String PASSWORD = "ucalgary";
```

### 4. Run the application
mvn compile exec:java -Dexec.mainClass="edu.ucalgary.oop.ClinicApp"
### or in powershell
mvn compile exec:java '-Dexec.mainClass=edu.ucalgary.oop.ClinicApp'

### 5. Run tests
mvn test


## User Manual

### Main Menu
When the program starts you will see:

1. Manage Staff
2. Manage Owners
3. Manage Pets
4. Manage Appointments
5. Exit

Enter a number and press Enter to select an option.

---

### Managing Staff

**Add Veterinarian**
- Select `1` from main menu then `1` to add a veterinarian
- Enter name, staff ID, and specialization (e.g. Surgery, Dermatology)

**Add Receptionist**
- Select `1` from main menu then `2` to add a receptionist
- Enter name and staff ID

**View All Staff**
- Select `1` from main menu then `3`
- Displays all vets and receptionists with their details

---

### Managing Owners

**Add Owner**
- Select `2` from main menu then `1`
- Enter name, phone number, and email address

**View All Owners**
- Select `2` from main menu then `2`
- Displays all registered owners

---

### Managing Pets

**Add Dog**
- Select `3` from main menu then `1`
- Enter name, age, owner email, and vaccination status (true/false)
- Note: Owner must be registered before adding a pet

**Add Cat**
- Select `3` from main menu then `2`
- Enter name, age, owner email, and indoor status (true/false)
- Note: Owner must be registered before adding a pet

**View All Pets**
- Select `3` from main menu then `3`
- Displays all pets with their details and owner

---

### Managing Appointments

**Schedule Appointment**
- Select `4` from main menu then `1`
- Enter pet name, vet staff ID, date and time, and notes
- Date format: `yyyy-MM-dd HH:mm` (e.g. `2025-08-05 10:00`)
- Note: System will prevent double booking automatically

**Cancel Appointment**
- Select `4` from main menu then `2`
- View all appointments and enter the appointment ID to cancel

**View All Appointments**
- Select `4` from main menu then `3`
- Displays all scheduled appointments

---

### Error Handling
- **Double booking** — if you try to book a vet at a time they are already booked the system will display: `Error: Vet is already booked at this time`
- **Pet not found** — make sure pet name is spelled correctly with no extra spaces
- **Owner not found** — owner must be registered before adding a pet
- **Database connection error** — verify PostgreSQL is running and credentials are correct



