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
        List<Booking> bookings = new ArrayList<Booking>();

        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM booking WHERE group_number=?;");

            pstmt.setString(1, groupBookingNumber);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                pstmt = conn.prepareStatement("SELECT * FROM passenger WHERE ssn=?;");
                pstmt.setInt(1, rs.getInt(7));
      
                ResultSet prs = pstmt.executeQuery();
                prs.next();
                
                Passenger p = new Passenger(prs.getString(2), prs.getInt(1), prs.getString(3), prs.getString(4), prs.getInt(5));

                Booking b = new Booking(rs.getString(6), rs.getString(8), p);

                b.setBookingNumber(rs.getString(1));
                b.setLuggage(rs.getBoolean(3));
                b.setPaid(rs.getBoolean(4));
                b.setRefunded(rs.getBoolean(5));

                bookings.add(b);
            }
        }
        catch (java.sql.SQLException e) {
            System.out.println(e);
        }

        GroupBooking g = new GroupBooking(groupBookingNumber, bookings.toArray(new Booking[bookings.size()]));

        return g;
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
