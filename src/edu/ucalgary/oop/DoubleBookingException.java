package edu.ucalgary.oop;

/**
 * Exception thrown when a vet is double booked.
 * @author David Onofeghara
 * @version 1.0
 * @since 1.0
 */


public class DoubleBookingException extends Exception {
    public DoubleBookingException(String message) {
        super(message);
    }
}