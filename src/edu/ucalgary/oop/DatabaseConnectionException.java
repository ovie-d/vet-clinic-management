package edu.ucalgary.oop;

/**
 * Custom runtime exception to handle PostgreSQL database errors.
 */
public class DatabaseConnectionException extends RuntimeException {
    public DatabaseConnectionException(String message) {
        super(message);
    }

    public DatabaseConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
