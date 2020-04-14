package is.hi.flugleit;

import java.sql.*;
import java.util.*;

public class FlightDB extends Database {
    public FlightDB(String url) {
        super(url);
    }

    /*
    Returns all flights on a given route on given day.

    @param date the date to search for on the format YYYY-MM-DD
    @param destTo the destination of the flight
    @param destFrom the departure location of the flight
    @return an array of Flight objects
    */
    public Flight[] getFlights(String date, String destTo, String destFrom) {
        List<Flight> flights = new ArrayList<Flight>();

        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM flight WHERE dest_to=? AND dest_from=? AND DATE(departure_time)=?");

            pstmt.setString(1, destTo);
            pstmt.setString(2, destFrom);
            pstmt.setString(3, date);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Flight f = new Flight(
                    rs.getString(1), 
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getInt(7)
                );

                List<Seat> seats = new ArrayList<Seat>();

                pstmt = conn.prepareStatement("SELECT * FROM seat WHERE flight_number=? AND availability=true");
                pstmt.setString(1, f.getFlightNumber());
                ResultSet srs = pstmt.executeQuery();

                while (srs.next()) {
                    Seat s = new Seat(srs.getString(1), srs.getInt(3));
                    seats.add(s);
                }

                f.setSeats(seats.toArray(new Seat[seats.size()]));
                flights.add(f);
            }
        }
        catch (java.sql.SQLException e) {
            System.out.println(e);
        }
       
        return flights.toArray(new Flight[flights.size()]);
    }

    /*
    Returns the flight with the given flight number

    @param flightNumber the number of the flight
    @return a Flight object
    */
    public Flight getFlight(String flightNumber) {
        Flight f = new Flight("","","","","","",0);

        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM flight WHERE flight_number=?");

            pstmt.setString(1, flightNumber);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                f = new Flight(
                    rs.getString(1), 
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getInt(7)
                );

                List<Seat> seats = new ArrayList<Seat>();

                pstmt = conn.prepareStatement("SELECT * FROM seat WHERE flight_number=? AND availability=true");
                pstmt.setString(1, f.getFlightNumber());
                ResultSet srs = pstmt.executeQuery();

                while (srs.next()) {
                    Seat s = new Seat(srs.getString(1), srs.getInt(3));
                    seats.add(s);
                }

                f.setSeats(seats.toArray(new Seat[seats.size()]));
            }

        }
        catch (java.sql.SQLException e) {
            System.out.println(e);
        }

        return f;
    }

    /*
    Creates a flight from a given Flight object.

    @param f a Flight object to insert into the database
    */
    public void createFlight(Flight f) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO flight(flight_number, airline, dest_to, dest_from, departure_time, arrival_time, price, luggage_price, duration) VALUES(?,?,?,?,?,?,?,?,?)");

            pstmt.setString(1, f.getFlightNumber());
            pstmt.setString(2, f.getAirline());
            pstmt.setString(3, f.getDestTo());
            pstmt.setString(4, f.getDestFrom());
            pstmt.setString(5, f.getDepartureTime());
            pstmt.setString(6, f.getArrivalTime());
            pstmt.setInt(7, f.getPrice());
            pstmt.setInt(8, f.getLuggagePrice());
            pstmt.setString(9, f.getDuration());

            pstmt.executeUpdate();
        }
        catch (java.sql.SQLException e) {

        }

        // Insert all the seats.
        for (Seat s : f.getSeats()) {
            this.createSeat(s, f.getFlightNumber());
        }
    }

    /*
    Creates a new seat for a given flight.

    @param s the Seat object to insert
    @param flightNumber the number of the flight which the seat belongs to
    */
    private void createSeat(Seat s, String flightNumber) {
        try {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO seat(num, available, price, flight_number) VALUES(?,?,?,?);");

            pstmt.setString(1, s.getSeatNumber());
            pstmt.setBoolean(2, s.getAvailability());
            pstmt.setInt(3, s.getPrice());
            pstmt.setString(4, flightNumber);
        }
        catch (java.sql.SQLException e) {

        }
    }
}
