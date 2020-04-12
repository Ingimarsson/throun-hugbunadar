package is.hi.flugleit;

import java.sql.*;
import java.util.*;

public class BookingDB extends Database {
    public BookingDB(String url) {
        super(url);
    }

    /*
    Returns the details of a specific booking.

    @param groupBookingNumber the booking number to search for.
    @return a GroupBooking object for the given booking.
    */
    public GroupBooking getGroupBooking(String groupBookingNumber) {
        return null;
    }

    /*
    Creates a new group booking.

    @param g a GroupBooking object 
    */
    public void createGroupBooking(GroupBooking g) {
        for (Booking b : g.getBookings()) {
            this.createBooking(b, g.getGroupBookingNumber());
        }
    }

    /*
    Creates a new booking.

    @param b a Booking object
    @param groupBookingNumber the number of the group which the booking belongs to.
    @return the id of the inserted row in the Booking table
    */
    private void createBooking(Booking b, String groupBookingNumber) {
        this.createPassenger(b.getPassenger(), b.getBookingNumber());
    }

    /*
    Creates a new passenger.

    @param p a Passenger object.
    @param bookingNumber the number of the booking which this passenger belongs to.
    */
    private void createPassenger(Passenger p, String bookingNumber) {
        
    }
}
