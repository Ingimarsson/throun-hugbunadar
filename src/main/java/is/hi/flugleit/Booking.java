package is.hi.flugleit;

import javax.json.*;
import org.apache.commons.lang3.RandomStringUtils;

public class Booking {
    private String bookingNumber;
    private Flight flight;
    private Seat seat;
    private Passenger passenger;
    private String flightNumber;
    private String seatNumber;
    private boolean luggage = false;
    private boolean paid = true;
    private boolean refunded = true;
    
    public Booking(String flightNumber, String seatNumber, Passenger passenger){
        this.flightNumber=flightNumber;
        this.seatNumber=seatNumber;
        this.passenger=passenger;
    }
     
    public String getBookingNumber(){return bookingNumber;}
    public void setBookingNumber(String val){bookingNumber=val;}
    public Flight getFlight(){return flight;}
    public Seat getSeat(){return seat;}
    public Passenger getPassenger(){return passenger;}
    public boolean getLuggage(){return luggage;}
    public void setLuggage(boolean val){luggage=val;}
    public boolean getPaid(){return paid;}
    public void setPaid(boolean val){paid=val;}
    public boolean getRefunded(){return refunded;}
    public void setRefunded(boolean val){refunded=val;}
    public String getFlightNumber() {return flightNumber;}
    public void setFlightNumber(String val) {flightNumber=val;}
    public String getSeatNumber() {return seatNumber;}
    public void setSeatNumber(String val) {seatNumber=val;}

    /*
    Generates a random alphanumeric booking number of length 6.
    */
    public void generateBookingNumber() {
        this.bookingNumber = RandomStringUtils.randomAlphanumeric(6).toUpperCase();
    }

    /*
    Returns a json object for the web API.
    @return JsonObjectBuilder with the flight data.
    */
    public JsonObjectBuilder createJson() {
        JsonObjectBuilder result = Json.createObjectBuilder();

        result.add("bookingNumber", this.getBookingNumber());
        result.add("flightNumber", this.getFlightNumber());
        result.add("seat", this.getSeatNumber());
        result.add("passenger", this.getPassenger().createJson());
        result.add("luggage", this.getLuggage());   
        result.add("paid", this.getPaid());
        result.add("refunded", this.getRefunded());
       
        return result;
    }
}
